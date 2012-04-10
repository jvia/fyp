package org.bham.aucom.fts.graph;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.data.timeseries.ScoreTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesStatusEvent;
import org.bham.aucom.data.timeseries.TimeSeriesStatusListener;
import org.bham.aucom.data.util.DataManager;
import org.bham.aucom.data.util.SlidingWindow;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassifier;
import org.bham.aucom.fts.sink.TimeSeriesSink;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.CalcEntropyAvgScore;
import org.bham.aucom.fts.tranform.CalcMeanValue;
import org.bham.aucom.fts.tranform.Classify;
import org.bham.aucom.fts.tranform.CountDataTypes;
import org.bham.aucom.fts.tranform.CropTimestampFromData;
import org.bham.aucom.fts.tranform.EncodeData;
import org.bham.aucom.fts.tranform.GenerateTemporalDurationFeature;
import org.bham.aucom.fts.tranform.GenerateTemporalProbabilityFeature;
import org.bham.aucom.fts.tranform.TemporalDurationFeatureGenerator;

import java.util.logging.Logger;

import static org.bham.aucom.util.Constants.TEST_SOURCE;

/**
 * This class represent the implementation of the detection algorithm. It sets
 * up the FTS nodes that perform all of the aspects of fault detection.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class DetectorGraph extends AbstractAucomGraph implements TimeSeriesStatusListener {
    private static final long serialVersionUID = 1L;
    private final transient Logger log = Logger.getLogger(getClass().getName());

    // graph stateful fields
    private T2GramModelI model;

    // transient fields
    private transient TimeSeriesSource<Observation> observationNode;
    private transient EncodeData encoderNode;
    private transient CropTimestampFromData<Observation> timestampShiftingNode;
    private transient CountDataTypes countingNode;
    private transient GenerateTemporalDurationFeature durationFeatureNode;
    private transient GenerateTemporalProbabilityFeature probabilityFeatureNode;
    private transient CalcEntropyAvgScore rawScoreCalculatorNode;
    private transient CalcMeanValue meanScoreCalculatorNode;
    private transient Classify classificationNode;

    private transient TimeSeriesSource<Score> scoreTimeseriesSource;
    private transient TimeSeriesSink<Classification> sink;

    /**
     * Constructs the DetectorGraph.
     */
    public DetectorGraph() {
        super("detectorGraph");
        initGraph();
    }

    /**
     * Sets the model used by this detector graph.
     *
     * @param inModel the model to use
     */
    public void setModel(T2GramModelI inModel) {
        if (inModel == null) {
            log.severe("Model must not be null.");
            throw new IllegalArgumentException("Model must not be null");
        }

        model = inModel;
        probabilityFeatureNode.setModel(inModel);
        rawScoreCalculatorNode.setModel(inModel);
        if (durationFeatureNode.getGenerator() != null) {
            durationFeatureNode.getGenerator().clearInitialClasses();
            durationFeatureNode.getGenerator().addInitalClasses(model.getDimensions());
        } else {
            durationFeatureNode.setGenerator(new TemporalDurationFeatureGenerator(inModel.getDimensions()));
        }
    }

    public void setClassificator(AnomalyClassifier classifierToSet) {
        classificationNode.setClassifier(classifierToSet);
    }

    @Override
    protected void initGraph() {
        observationNode = new TimeSeriesSource<Observation>(TEST_SOURCE);
        observationNode.addSourceStatusListener(this);
        timestampShiftingNode = new CropTimestampFromData<Observation>();
        scoreTimeseriesSource = new TimeSeriesSource<Score>("scoreTimeseriesSource");
        encoderNode = new EncodeData();
        countingNode = new CountDataTypes();
        probabilityFeatureNode = new GenerateTemporalProbabilityFeature();
        durationFeatureNode = new GenerateTemporalDurationFeature();
        rawScoreCalculatorNode = new CalcEntropyAvgScore();

        meanScoreCalculatorNode = new CalcMeanValue();
        meanScoreCalculatorNode.setSlidingWindow(new SlidingWindow(100, 50));

        classificationNode = new Classify();
        sink = new TimeSeriesSink<Classification>(new ClassificationTimeSeries());
        DataManager.getInstance().addTimeSeries(sink.getOutput());
        sink.getOutput().addTimeSeriesStatusListener(this);

        graph.connect(observationNode, timestampShiftingNode);
        graph.connect(timestampShiftingNode, encoderNode);
        graph.connect(encoderNode, countingNode);
        graph.connect(countingNode, durationFeatureNode);
        graph.connect(durationFeatureNode, probabilityFeatureNode);
        graph.connect(probabilityFeatureNode, rawScoreCalculatorNode);
        graph.connect(rawScoreCalculatorNode, meanScoreCalculatorNode);
        graph.connect(meanScoreCalculatorNode, classificationNode);
        //graph.connect(rawScoreCalculatorNode, classificationNode);
        graph.connect(scoreTimeseriesSource, classificationNode);

        graph.connect(classificationNode, sink);
    }

    public T2GramModelI getModel() {
        return model;
    }

    public void reset() {
        observationNode.reset();
        scoreTimeseriesSource.reset();
        sink.getOutput().clear();
        meanScoreCalculatorNode.reset();
        durationFeatureNode.reset();
        classificationNode.reset();
    }

    public String getName() {
        return "DetectorGraph";
    }

    public void setInput(TimeSeries<Observation> inTimeSeries) throws ActionFailedException {
        observationNode.setInput(inTimeSeries);
    }

    public AnomalyClassifier getClassificator() {
        return classificationNode.getClassifier();
    }

    public void setSlidingWindow(SlidingWindow slidingWindow) {
        meanScoreCalculatorNode.setSlidingWindow(slidingWindow);
    }

    public void updateSlidingWindow(SlidingWindow slidingWindow) {
        getSlidingWindow().copy(slidingWindow);
    }

    public SlidingWindow getSlidingWindow() {
        return meanScoreCalculatorNode.getSlidingWindow();
    }

    public TimeSeries<Classification> getClassificationTimeSeries() {
        return sink.getOutput();
    }

    @Override
    protected void cleanUp() {
        scoreTimeseriesSource.setInput(new ScoreTimeSeries());
        scoreTimeseriesSource.setInput(new ScoreTimeSeries());
    }

    /**
     * Determines if all of the preconditions required to run the graph are
     * satisfied. In order for the graph to be ready, there must be valid
     * input, a feature generator, a model, and an anomaly detector.
     *
     * @return true if ready.
     */
    @Override
    public boolean preconditionsSatisfied() {
        return inputIsPresent() &&
               featureGeneratorIsReady() &&
               modelIsReady() &&
               anomalyDetectorIsReady();
    }

    /**
     * Returns the reasons why the DetectorGraph is not working.
     *
     * @return reasons for malfunction
     */
    @Override
    protected String getReason() {
        StringBuilder reason = new StringBuilder("\n");

        if (!inputIsPresent()) {
            reason.append("- input is missing, connect to a system first\n");
        }
        if (!featureGeneratorIsReady()) {
            reason.append("- feature generator not ready\n");
        }
        if (!modelIsReady()) {
            reason.append("- model is not ready\n");
        }
        if (!anomalyDetectorIsReady()) {
            reason.append("- anomaly detector is not ready\n");
        }

        return reason.toString();
    }

    /**
     * Determines if the {@link TimeSeriesSource} is ready.
     *
     * @return true if ready
     */
    private boolean inputIsPresent() {
        return observationNode.getInput() != null;
    }

    /**
     * Determines if the {@link org.bham.aucom.fts.tranform.Classify}
     * classificator is ready.
     *
     * @return true if ready
     */
    private boolean anomalyDetectorIsReady() {
        return classificationNode.getClassifier() != null;
    }

    /**
     * Determines if the {@link GenerateTemporalProbabilityFeature} is ready.
     *
     * @return true if ready
     */
    private boolean modelIsReady() {
        return probabilityFeatureNode.getModel() != null;
    }

    public TimeSeries<Score> getScoreTimeSeries() {
        return rawScoreCalculatorNode.getTimeSeries();
    }

    @Override
    public void timeseriesStatusChanged(TimeSeriesStatusEvent status) {

    }

    /**
     * Determines if the {@link GenerateTemporalDurationFeature} generator is
     * ready.
     *
     * @return true if ready
     */
    private boolean featureGeneratorIsReady() {
        return durationFeatureNode.getGenerator() != null;
    }

    public long getTotalElementsSeen() {
        return countingNode.getTotal();
    }
}

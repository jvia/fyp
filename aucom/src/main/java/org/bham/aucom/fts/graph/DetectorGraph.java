package org.bham.aucom.fts.graph;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.*;
import org.bham.aucom.data.util.DataManager;
import org.bham.aucom.data.util.SlidingWindow;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
import org.bham.aucom.fts.sink.TimeSeriesSink;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.*;

import java.util.logging.Logger;

import static org.bham.aucom.util.Constants.TEST_SOURCE;

/**
 * This class represent the implementation of the detection algorithm. It sets
 * up the FTS nodes that perform all of the aspects of fault detection.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public class DetectorGraph extends AbstractAucomGraph implements TimeSeriesStatusListener {
    private static final long serialVersionUID = 1L;
    private final transient Logger log = Logger.getLogger(getClass().getName());

    // graph stateful fields
    private T2GramModelI model;

    // transient fields
    private transient TimeSeriesSource<Observation> observationTimeseriesSource;
    private transient TimeSeriesSource<Score> scoreTimeseriesSource;
    private transient CalcEntropyAvgScore calculateScore;
    private transient EncodeData encodeData;
    private transient CountDataTypes countDataTypes;
    private transient GenerateTemporalDurationFeature generateDurationFeature;
    private transient GenerateTemporalProbabilityFeature test;
    private transient TimeSeriesSink<Classification> sink;
    private transient CalcMeanvalue mean;
    private transient Classificate anomalyClassification;
    private transient CropTimestampFromData<Observation> cropTimestampFromData;

    /**
     * Constructs the DetectorGraph.
     */
    public DetectorGraph() {
        super("detectorGraph");
        initGraph();
    }

//    /**
//     * @param model         detector model to use
//     * @param classificator the classifier to use
//     * @param slidingWindow the sliding window to use
//     */
//    private void setMonitorState(T2GramModelI model, AnomalyClassificator classificator, SlidingWindow slidingWindow) {
//        if (getStatus().equals(GraphStatus.RUNNING)) {
//            try {
//                pause();
//            } catch (IllegalStateChange e) {
//                e.printStackTrace();
//            }
//        }
//        setModel(model);
//        setClassificator(classificator);
//        if (model == null) {
//            return;
//        } else {
//            generateDurationFeature.setGenerator(new TemporalDurationFeatureGenerator(model.getDimensions()));
//        }
//        setSlidingWindow(slidingWindow);
//        if (getStatus().equals(GraphStatus.PAUSED)) {
//            resume();
//        }
//
//    }

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
        test.setModel(inModel);
        calculateScore.setModel(inModel);
        if (generateDurationFeature.getGenerator() != null) {
            generateDurationFeature.getGenerator().clearInitialClasses();
            generateDurationFeature.getGenerator().addInitalClasses(model.getDimensions());
        } else {
            generateDurationFeature.setGenerator(new TemporalDurationFeatureGenerator(inModel.getDimensions()));
        }
    }

    @Override
    protected void setStatus(GraphStatus newStatus) {
        super.setStatus(newStatus);
    }

    public void setClassificator(AnomalyClassificator classificatorToSet) {
        anomalyClassification.setClassificator(classificatorToSet);
    }

    @Override
    protected void initGraph() {
        observationTimeseriesSource = new TimeSeriesSource<Observation>(TEST_SOURCE);
        observationTimeseriesSource.addSourceStatusListener(this);
        cropTimestampFromData = new CropTimestampFromData<Observation>();
        scoreTimeseriesSource = new TimeSeriesSource<Score>("scoreTimeseriesSource");
        encodeData = new EncodeData();
        countDataTypes = new CountDataTypes();
        test = new GenerateTemporalProbabilityFeature();
        generateDurationFeature = new GenerateTemporalDurationFeature();
        calculateScore = new CalcEntropyAvgScore();
        mean = new CalcMeanvalue();
        mean.setSlidingWindow(new SlidingWindow(100, 50));
        anomalyClassification = new Classificate();
        sink = new TimeSeriesSink<Classification>(new ClassificationTimeSeries());
        DataManager.getInstance().addTimeSeries(sink.getOutput());
        sink.getOutput().addTimeSeriesStatusListener(this);

        graph.connect(observationTimeseriesSource, cropTimestampFromData);
        graph.connect(cropTimestampFromData, encodeData);
        graph.connect(encodeData, countDataTypes);
        graph.connect(countDataTypes, generateDurationFeature);
        graph.connect(generateDurationFeature, test);
        graph.connect(test, calculateScore);
        graph.connect(calculateScore, mean);
        graph.connect(mean, anomalyClassification);

        graph.connect(scoreTimeseriesSource, anomalyClassification);

        graph.connect(anomalyClassification, sink);
    }

    public T2GramModelI getModel() {
        return model;
    }

    public void reset() {
        observationTimeseriesSource.reset();
        scoreTimeseriesSource.reset();
        sink.getOutput().clear();
        mean.reset();
        generateDurationFeature.reset();
        anomalyClassification.reset();
    }

    public String getName() {
        return "testgraph";
    }

    public void setInput(TimeSeries<Observation> inTimeSeries) throws ActionFailedException {
        observationTimeseriesSource.setInput(inTimeSeries);
    }

    public AnomalyClassificator getClassificator() {
        return anomalyClassification.getClassificator();
    }

    public void setSlidingWindow(SlidingWindow slidingWindow) {
        mean.setSlidingWindow(slidingWindow);
    }

    public void updateSlidingWindow(SlidingWindow slidingWindow) {
        getSlidingWindow().copy(slidingWindow);
    }

    public SlidingWindow getSlidingWindow() {
        return mean.getSlidingWindow();
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
     * input,
     * a feature generator, a model, and an anomaly detector.
     *
     * @return true if ready.
     */
    @Override
    public boolean preconditionsSatisfied() {
        return inputIsPresent() && featureGeneratorIsReady() && modelIsReady() && anomalyDetectorIsReady();
    }

    /**
     * Returns the reasons why the DetectorGraph is not working.
     *
     * @return reasons for malfunction
     */
    @Override
    protected String getReason() {
        String reason = "\n";

        if (!inputIsPresent())
            reason += "- input is missing, connect to a system first\n";
        if (!featureGeneratorIsReady())
            reason += "- feature generator not ready\n";
        if (!modelIsReady())
            reason += "- model is not ready\n";
        if (!anomalyDetectorIsReady())
            reason += "- anomaly detector is not ready\n";

        return reason;
    }

    /**
     * Determines if the {@link TimeSeriesSource} is ready.
     *
     * @return true if ready
     */
    private boolean inputIsPresent() {
        return observationTimeseriesSource.getInput() != null;
    }

    /**
     * Determines if the {@link Classificate} classificator is ready.
     *
     * @return true if ready
     */
    private boolean anomalyDetectorIsReady() {
        return anomalyClassification.getClassificator() != null;
    }

    /**
     * Determines if the {@link GenerateTemporalProbabilityFeature} is ready.
     *
     * @return true if ready
     */
    private boolean modelIsReady() {
        return test.getModel() != null;
    }

    public TimeSeries<Score> getScoreTimeSeries() {
        return calculateScore.getTimeSeries();
    }

    @Override
    public void timeseriesStatusChanged(TimeseriesStatusEvent status) {

    }

    /**
     * Determines if the {@link GenerateTemporalDurationFeature} generator is
     * ready.
     *
     * @return true if ready
     */
    private boolean featureGeneratorIsReady() {
        return generateDurationFeature.getGenerator() != null;
    }
}

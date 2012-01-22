package org.bham.aucom.fts.graph;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.*;
import org.bham.aucom.data.util.DataManager;
import org.bham.aucom.data.util.SlidingWindow;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.AnomalyClassifier;
import org.bham.aucom.fts.sink.TimeSeriesSink;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.*;

import java.util.logging.Level;
import java.util.logging.Logger;

import static org.bham.aucom.util.Constants.TEST_SOURCE;


/*
 * This class represent the implementation of the detection algorithm. It sets up the FTS nodes that perform all of the
 * aspects of fault detection.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public class DetectorGraph extends AbstractAucomGraph implements TimeSeriesStatusListener {
    private static final long serialVersionUID = 1L;
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
    private transient Classify anomalyClassification;
    private transient CropTimestampFromData<Observation> cropTimestampFromData;

    /*
     * Constructs the DetectorGraph with the specified model, classifier, and
     * sliding window.
     *
     * @param model          detector model to use
     * @param classificator  the classifier to use
     * @param slindingWindow the sliding window to use
     */
    public DetectorGraph() {
        super("detectorGraph");
        initGraph();
    }

    /*
     * Sets the model used by this detector graph.
     *
     * @param inModel
     */
    public void setModel(T2GramModelI inModel) {
        if (inModel == null) {
            throw new IllegalArgumentException("model must not be null");
        }
        this.model = inModel;
        this.test.setModel(inModel);
        this.calculateScore.setModel(inModel);
        if (generateDurationFeature.getGenerator() != null) {
            generateDurationFeature.getGenerator().clearInitialClasses();
            generateDurationFeature.getGenerator().addInitalClasses(model.getDimensions());
        } else {
            generateDurationFeature.setGenerator(new TemporalDurationFeatureGenerator(inModel.getDimensions()));
        }
    }

    /*
     * @param classifierToSet
     */
    public void setClassifier(AnomalyClassifier classifierToSet) {
        this.anomalyClassification.setClassifier(classifierToSet);
    }

    /*
     *
     */
    @Override
    protected void initGraph() {
        this.observationTimeseriesSource = new TimeSeriesSource<Observation>(TEST_SOURCE);

        this.observationTimeseriesSource.addSourceStatusListener(this);

        this.scoreTimeseriesSource = new TimeSeriesSource<Score>("scoreTimeseriesSource");
        this.encodeData = new EncodeData();
        this.countDataTypes = new CountDataTypes();
        this.test = new GenerateTemporalProbabilityFeature();
        this.generateDurationFeature = new GenerateTemporalDurationFeature();
        this.calculateScore = new CalcEntropyAvgScore();
        this.mean = new CalcMeanvalue();
        this.mean.setSlidingWindow(new SlidingWindow(100, 50));
        this.anomalyClassification = new Classify();
        this.sink = new TimeSeriesSink<Classification>(new ClassificationTimeSeries());
        DataManager.getInstance().addTimeSeries(sink.getOutput());
        sink.getOutput().addTimeSeriesStatusListener(this);
        cropTimestampFromData = new CropTimestampFromData<Observation>();
        this.graph.connect(this.observationTimeseriesSource, cropTimestampFromData);
        this.graph.connect(cropTimestampFromData, this.encodeData);
        this.graph.connect(this.encodeData, this.countDataTypes);
        this.graph.connect(this.countDataTypes, this.generateDurationFeature);
        this.graph.connect(this.generateDurationFeature, this.test);
        this.graph.connect(this.test, this.calculateScore);
        this.graph.connect(this.scoreTimeseriesSource, this.anomalyClassification);

        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.CONFIG, "mean calculation on");
        this.graph.connect(this.calculateScore, this.mean);
        this.graph.connect(this.mean, this.anomalyClassification);
        this.graph.connect(this.anomalyClassification, this.sink);
    }

    /*
     * @return
     */
    public T2GramModelI getModel() {
        return this.model;
    }

    public void reset() {
        observationTimeseriesSource.reset();
        scoreTimeseriesSource.reset();
        sink.getOutput().clear();
        if (mean != null) {
            mean.reset();
        }
        if (generateDurationFeature != null) {
            generateDurationFeature.reset();
        }
        if (anomalyClassification != null) {
            anomalyClassification.reset();
        }
    }

    public String getName() {
        return "testgraph";
    }

    /*
     * @param inTimeSeries
     * @throws ActionFailedException
     */
    public void setInput(TimeSeries<Observation> inTimeSeries) throws ActionFailedException {
        this.observationTimeseriesSource.setInput(inTimeSeries);
    }

    /*
     * @return
     */
    public AnomalyClassifier getClassificator() {
        return this.anomalyClassification.getClassifier();
    }

    /*
     * @param slidingWindow
     */
    public void setSlidingWindow(SlidingWindow slidingWindow) {
        this.mean.setSlidingWindow(slidingWindow);
    }

    /*
     * @param slidingWindow
     */
    public void updateSlidingWindow(SlidingWindow slidingWindow) {
        getSlidingWindow().copy(slidingWindow);
    }

    /*
     * @return
     */
    public SlidingWindow getSlidingWindow() {
        return this.mean.getSlidingWindow();
    }

    /*
     * @return
     */
    public TimeSeries<Classification> getClassificationTimeSeries() {
        return this.sink.getOutput();
    }

    /*
     *
     */
    @Override
    protected void cleanUp() {
        this.scoreTimeseriesSource.setInput(new ScoreTimeSeries());
        this.scoreTimeseriesSource.setInput(new ScoreTimeSeries());
    }

    /*
     * Determines if all of the preconditions required to run the graph are
     * satisfied. In order for the graph to be ready, there must be valid input,
     * a feature generator, a model, and an anomaly detector.
     *
     * @return true if ready.
     */
    @Override
    public boolean preconditionsSatisfied() {
        return inputIsPresent() && featureGeneratorIsReady() && modelIsReady() && anomalyDetectorIsReady();
    }

    /*
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

    /*
     * Determines if the {@link TimeSeriesSource} is ready.
     *
     * @return true if ready
     */
    private boolean inputIsPresent() {
        return observationTimeseriesSource.getInput() != null;
    }

    /*
     * Determines if the {@link org.bham.aucom.fts.tranform.Classify} classificator is ready.
     *
     * @return true if ready
     */
    private boolean anomalyDetectorIsReady() {
        return anomalyClassification.getClassifier() != null;
    }

    /*
     * Determines if the {@link GenerateTemporalProbabilityFeature} is ready.
     *
     * @return true if ready
     */
    private boolean modelIsReady() {
        return test.getModel() != null;
    }

    public TimeSeries<Score> getScoreTimeSeries() {
        return this.calculateScore.getTimeSeries();
    }

    @Override
    public void timeseriesStatusChanged(TimeseriesStatusEvent status) {

    }

    public String getInfoAsString() {
        String info = "";
        info += "no info yet";
        return info;
    }


    /*
     * Determines if the {@link GenerateTemporalDurationFeature} generator is ready.
     *
     * @return true if ready
     */
    private boolean featureGeneratorIsReady() {
        return generateDurationFeature.getGenerator() != null;
    }
}

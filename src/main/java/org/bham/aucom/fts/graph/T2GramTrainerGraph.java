package org.bham.aucom.fts.graph;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.timeseries.TemporalDurationFeatureTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.sink.TimeSeriesSink;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.EncodeData;
import org.bham.aucom.fts.tranform.GenerateTemporalDurationFeature;
import org.bham.aucom.fts.tranform.TemporalDurationFeatureGenerator;

import java.util.ArrayList;

/**
 * Class T2GramTrainerGraph ...
 *
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class T2GramTrainerGraph extends AbstractAucomGraph {
    private static final long serialVersionUID = 1L;
    private TimeSeriesSource<Observation> source;
    private GenerateTemporalDurationFeature tdfNode;
    private TimeSeriesSink<TemporalDurationFeature> sink;

    /**
     * Constructor T2GramTrainerGraph creates a new T2GramTrainerGraph
     * instance.
     */
    public T2GramTrainerGraph() {
        super("trainingGraph");
        initGraph();
    }

    /**
     * Method setInput sets the input of this T2GramTrainerGraph object.
     *
     * @param trainingData the input of this T2GramTrainerGraph object.
     */
    public void setInput(TimeSeries<Observation> trainingData) {
        source.setInput(trainingData);
    }

    /**
     * Method getInput returns the input of this T2GramTrainerGraph object.
     *
     * @return the input (type TimeSeries<Observation>) of this
     *         T2GramTrainerGraph object.
     */
    public TimeSeries<Observation> getInput() {
        return source.getInput();
    }

    /**
     * Method getOutput returns the output of this T2GramTrainerGraph object.
     *
     * @return the output (type TimeSeries<TemporalDurationFeature>) of this
     *         T2GramTrainerGraph object.
     */
    public TimeSeries<TemporalDurationFeature> getOutput() {
        return sink.getOutput();
    }

    /**
     * Method preconditionsSatisfied checks if the necessary preconditions to
     * run the graph have been met.
     *
     * @return boolean true if preconditions have been met
     * @see org.bham.aucom.fts.graph.AbstractAucomGraph#preconditionsSatisfied()
     */
    @Override
    public final boolean preconditionsSatisfied() {
        return hasTrainingData() && hasFeatureGenerator();
    }

    /**
     * Initializes the training graph.
     *
     * @see org.bham.aucom.fts.graph.AbstractAucomGraph#initGraph()
     */
    @Override
    protected final void initGraph() {
        source = new TimeSeriesSource<Observation>("trainerGraphSource");
        EncodeData encodeNode = new EncodeData();
        tdfNode = new GenerateTemporalDurationFeature();
        tdfNode.setGenerator(new TemporalDurationFeatureGenerator(new ArrayList<Integer>()));
        sink = new TimeSeriesSink<TemporalDurationFeature>(new TemporalDurationFeatureTimeSeries());

        graph.connect(source, encodeNode);
        graph.connect(encodeNode, tdfNode);
        graph.connect(tdfNode, sink);
    }

    /**
     * Method getReason returns the reason of this T2GramTrainerGraph object.
     *
     * @return the reason (type String) of this T2GramTrainerGraph object.
     * @see AbstractAucomGraph#getReason()
     */
    @Override
    protected final String getReason() {
        String reason = "%n";

        if (hasTrainingData()) {
            reason += "%nTraining data missing%n";
        }
        if (hasFeatureGenerator()) {
            reason += "%nFeature generator missing%n";
        }

        return reason;
    }

    /**
     * Unused.
     */
    @Override
    protected final void cleanUp() {}

    /**
     * Checks if feature generator exists..
     *
     * @return boolean  true if feature generator exists
     */
    private boolean hasFeatureGenerator() {
        return tdfNode.getGenerator() != null;
    }

    /**
     * Checks if the necessary training data is available.
     *
     * @return boolean true if training data is available
     */
    private boolean hasTrainingData() {
        return source.getInput() != null;
    }
}

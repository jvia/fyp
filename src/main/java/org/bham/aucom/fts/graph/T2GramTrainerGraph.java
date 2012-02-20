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

public class T2GramTrainerGraph extends AbstractAucomGraph {
    private static final long serialVersionUID = 1L;
    private TimeSeriesSource<Observation> source;
    GenerateTemporalDurationFeature tdfNode;
    private TimeSeriesSink<TemporalDurationFeature> sink;

    public T2GramTrainerGraph() {
        super("trainingGraph");
        initGraph();
    }

    @Override
    protected void initGraph() {
        source = new TimeSeriesSource<Observation>("trainerGraphSource");
        EncodeData encodeNode = new EncodeData();

        tdfNode = new GenerateTemporalDurationFeature();
        tdfNode.setGenerator(new TemporalDurationFeatureGenerator(new ArrayList<Integer>()));

        sink = new TimeSeriesSink<TemporalDurationFeature>(new TemporalDurationFeatureTimeSeries());

        graph.connect(source, encodeNode);
        graph.connect(encodeNode, tdfNode);
        graph.connect(tdfNode, sink);
    }

    public void setInput(TimeSeries<Observation> trainingData) {
        source.setInput(trainingData);
    }

    public TimeSeries<Observation> getInput() {
        return source.getInput();
    }

    @Override
    public void stop() {
        super.stop();
        source.setInput(null);
    }

    public TimeSeries<TemporalDurationFeature> getOutput() {
        return sink.getOutput();
    }

    @Override
    public boolean preconditionsSatisfied() {
        return !(isTrainingDataMissing() || isFreatureGeneratorMissing());
    }

    @Override
    protected String getReason() {
        String reason = "\n";

        if (!isTrainingDataMissing())
            reason += "\n Training data missing \n";
        if (!isFreatureGeneratorMissing())
            reason += "\n Feature generator missing \n";

        return reason;
    }

    private boolean isFreatureGeneratorMissing() {
        return tdfNode.getGenerator() == null;
    }

    private boolean isTrainingDataMissing() {
        return source.getInput() == null;
    }

    /**
     * Unused.
     */
    @Override
    protected void cleanUp() {}
}

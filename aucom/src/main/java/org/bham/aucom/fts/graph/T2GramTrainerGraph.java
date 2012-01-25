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
    private GenerateTemporalDurationFeature tdfNode;
    private TimeSeriesSink<TemporalDurationFeature> sink;

    public T2GramTrainerGraph() {
        super("trainingGraph");
        initGraph();
    }

    @Override
    protected void initGraph() {
        EncodeData encodeNode = new EncodeData();
        source = new TimeSeriesSource<Observation>("trainerGraphSource");

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

    @Override
    protected void cleanUp() {}

    public TimeSeries<TemporalDurationFeature> getOutput() {
        return sink.getOutput();
    }

    @Override
    public boolean preconditionsSatisfied() {
        return isTrainingDataMissing() && isFreatureGeneratorMissing();
    }

    @Override
    protected String getReason() {
        String reason = "\n";
        if (!isTrainingDataMissing())
            reason += "\n training data missing \n";

        if (!isFreatureGeneratorMissing())
            reason += "\n feature generator missing \n";

        return reason;
    }

    private boolean isFreatureGeneratorMissing() {
        return tdfNode.getGenerator() != null;
    }

    private boolean isTrainingDataMissing() {
        return source.getInput() != null;
    }

}

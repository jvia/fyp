package org.bham.aucom.fts.graph;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.ScoreTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.fts.sink.TimeSeriesSink;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.*;


public class ObservationToScoreGraph extends AbstractAucomGraph {
    private TimeSeriesSource<Observation> source;
    private EncodeData observationToDataType;
    private GenerateTemporalDurationFeature dataTypeToDurationFeature;
    private GenerateTemporalProbabilityFeature durationFeatureToProbabilityFeature;
    private CalcEntropyAvgScore probabilityFeatureToScore;
    private TimeSeriesSink<Score> sink;

    private static final long serialVersionUID = 1L;

    public ObservationToScoreGraph() {
        super("ObservationToScoreGraph");
        initGraph();
    }

    @Override
    protected void initGraph() {
        source = new TimeSeriesSource<Observation>("Observation source");
        observationToDataType = new EncodeData();
        dataTypeToDurationFeature = new GenerateTemporalDurationFeature();
        durationFeatureToProbabilityFeature = new GenerateTemporalProbabilityFeature();
        probabilityFeatureToScore = new CalcEntropyAvgScore();
        sink = new TimeSeriesSink<Score>(new ScoreTimeSeries());
        graph.connect(source, observationToDataType);
        graph.connect(observationToDataType, dataTypeToDurationFeature);
        graph.connect(dataTypeToDurationFeature, durationFeatureToProbabilityFeature);
        graph.connect(durationFeatureToProbabilityFeature, probabilityFeatureToScore);
        graph.connect(probabilityFeatureToScore, sink);
    }

    @Override
    protected String getReason() {
        if (durationFeatureToProbabilityFeature == null) {
            return "graph not initialized properly";
        }
        if (durationFeatureToProbabilityFeature.getModel() == null) {
            return "model missing";
        }
        return "unexpected reason";
    }

    @Override
    public boolean preconditionsSatisfied() {
        boolean satisfied = durationFeatureToProbabilityFeature.getModel() != null;
        satisfied &= source.getInput() != null;
        return satisfied;
    }

    @Override
    protected void cleanUp() {
        if (durationFeatureToProbabilityFeature != null) {
            durationFeatureToProbabilityFeature.setModel(null);
        }
    }

    public void setInput(TimeSeries<Observation> ts) {
        source.setInput(ts);
    }

    public TimeSeries<Score> getOutput() {
        return sink.getOutput();
    }

    public void setModel(Model inModel) {
        if (inModel != null) {
            durationFeatureToProbabilityFeature.setModel(inModel);
            dataTypeToDurationFeature.setGenerator(new TemporalDurationFeatureGenerator(inModel.getDimensions()));
            probabilityFeatureToScore.setModel(inModel);
        }
    }
}
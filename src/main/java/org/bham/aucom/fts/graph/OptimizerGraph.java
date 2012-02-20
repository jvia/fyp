package org.bham.aucom.fts.graph;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
import org.bham.aucom.fts.sink.TimeSeriesSink;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.Classificate;

import java.util.logging.Logger;

public class OptimizerGraph extends AbstractAucomGraph {
    private static final long serialVersionUID = 0L;
    TimeSeriesSource<Score> source;
    Classificate classificate;
    TimeSeriesSink<Classification> sink;

    public OptimizerGraph() {
        super("OptimizerGraph");
        initGraph();
    }

    @Override
    protected void initGraph() {

        // source
        source = new TimeSeriesSource<Score>("scoreSource");
        source.addSourceStatusListener(this);

        // node
        classificate = new Classificate();

        // sink
        sink = new TimeSeriesSink<Classification>(new ClassificationTimeSeries());
        sink.addSinkStatusListener(this);

        // connection
        graph.connect(source, classificate);
        graph.connect(classificate, sink);
    }

    @Override
    protected void cleanUp() {
        source.setInput(null);
        classificate.setClassificator(null);
        sink.setOutput(null);
    }

    @Override
    public boolean preconditionsSatisfied() {
        return classificate.getClassificator() != null;
    }

    public void setInput(TimeSeries<Score> input) {
        try {
            if (input != null && input.size() == 0) {
                Logger.getLogger(getClass().getCanonicalName()).severe(getClass().getName() + " warning: input queue is empty");
            }
            source.setInput(input);
            Logger.getLogger(getClass().getCanonicalName()).info("set input to " + input);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public AnomalyClassificator getClassificator() {
        return classificate.getClassificator();
    }

    public void setClassificator(AnomalyClassificator inAc) {
        classificate.setClassificator(inAc);
    }

    public TimeSeries<Classification> getOutput() {
        return sink.getOutput();
    }

    public void resetOutput() {
        if (sink.getOutput() != null) {
            sink.getOutput().clear();
        }
    }

    @Override
    protected String getReason() {
        if (classificate.getClassificator() == null) {
            return "anomaly classificator not set";
        }
        return "not ready for unknown reason";
    }
}

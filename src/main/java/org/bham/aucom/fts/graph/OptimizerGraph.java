package org.bham.aucom.fts.graph;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassifier;
import org.bham.aucom.fts.sink.TimeSeriesSink;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.Classify;

import java.util.logging.Logger;

public class OptimizerGraph extends AbstractAucomGraph {
    private static final long serialVersionUID = 0L;
    TimeSeriesSource<Score> source;
    Classify classify;
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
        classify = new Classify();

        // sink
        sink = new TimeSeriesSink<Classification>(new ClassificationTimeSeries());
        sink.addSinkStatusListener(this);

        // connection
        graph.connect(source, classify);
        graph.connect(classify, sink);
    }

    @Override
    protected void cleanUp() {
        source.setInput(null);
        classify.setClassifier(null);
        sink.setOutput(null);
    }

    @Override
    public boolean preconditionsSatisfied() {
        return classify.getClassifier() != null;
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

    public AnomalyClassifier getClassificator() {
        return classify.getClassifier();
    }

    public void setClassificator(AnomalyClassifier inAc) {
        classify.setClassifier(inAc);
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
        if (classify.getClassifier() == null) {
            return "anomaly classificator not set";
        }
        return "not ready for unknown reason";
    }
}

package org.bham.aucom.diagnoser.t2gram.visualizer;

import net.sf.xcf.fts.Triple;
import net.sf.xcf.fts.nodes.sink.QueueSink;
import org.bham.aucom.data.Classification;
import org.bham.aucom.data.SystemFaultStatus;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.graph.AbstractAucomGraph;
import org.bham.aucom.fts.source.TimeSeriesSource;

import java.util.concurrent.LinkedBlockingQueue;

public class T2GramVisualizerGraph extends AbstractAucomGraph {
    TimeSeriesSource<Classification> clSrc;
    ExtractTimestampScoreClassification extract;
    private final LinkedBlockingQueue<Triple<Long, Double, SystemFaultStatus>> queue;
    private QueueSink<Triple<Long, Double, SystemFaultStatus>> sink;
    private static final long serialVersionUID = 1L;

    public T2GramVisualizerGraph(TimeSeries<Classification> cl) {
        super("T2GramVisualizerGraph");
        queue = new LinkedBlockingQueue<Triple<Long, Double, SystemFaultStatus>>();
        initGraph();
        clSrc.setInput(cl);
    }

    @Override
    protected void initGraph() {
        clSrc = new TimeSeriesSource<Classification>("clInoutSrc");
        extract = new ExtractTimestampScoreClassification();
        sink = new QueueSink<Triple<Long, Double, SystemFaultStatus>>(getOuput());
        graph.connect(clSrc, extract);
        graph.connect(extract, sink);
    }

    public void setInput(TimeSeries<Classification> newInput) {
        if (clSrc != null) {
            clSrc.setInput(newInput);
        }
    }

    @Override
    protected String getReason() {
        return "input timeseries is null";
    }

    @Override
    public boolean preconditionsSatisfied() {
        return clSrc.getInput() != null;
    }

    @Override
    protected void cleanUp() {
    }

    public LinkedBlockingQueue<Triple<Long, Double, SystemFaultStatus>> getOuput() {
        return queue;
    }

}

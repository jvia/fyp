package org.bham.system.playfile;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.graph.AbstractAucomGraph;
import org.bham.aucom.fts.sink.TimeSeriesSink;

public class PlayFileGraph extends AbstractAucomGraph {

    PlayTimeSeriesSource<Observation> source;
    TimeSeriesSink<Observation> sink;
    private static final long serialVersionUID = 1L;

    public PlayFileGraph()
    {
        super("XcfPlayGraph");
        initGraph();
    }

    @Override
    protected void initGraph()
    {
        source = new PlayTimeSeriesSource<Observation>();
        sink = new TimeSeriesSink<Observation>(new ObservationTimeSeries());
        graph.connect(source, sink);
    }

    @Override
    public boolean preconditionsSatisfied()
    {
        return source.isReady();
    }

    @Override
    protected void cleanUp()
    {
        // ignored
    }

    public void setInput(TimeSeries<Observation> inTs)
    {
        source.setInput(inTs);
    }

    public TimeSeries<Observation> getInput()
    {
        return source.input;
    }

    public TimeSeries<Observation> getObservationTimeSeries()
    {
        return sink.getOutput();
    }

    @Override
    protected String getReason()
    {
        throw new UnsupportedOperationException("Not supported yet.");
    }
}

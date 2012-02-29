package org.bham.system.cast;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.graph.AbstractAucomGraph;
import org.bham.aucom.fts.sink.TimeSeriesSink;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
class CastNetworkGraph extends AbstractAucomGraph {

    private TimeSeriesSink<Observation> sink;
    private CastObservationSource source;

    public CastNetworkGraph() {
        super("CastNetworkGraph");
        initGraph();
    }

    @Override
    protected void initGraph() {
        source = new CastObservationSource();
        sink = new TimeSeriesSink<Observation>(new ObservationTimeSeries());
        graph.connect(source, sink);
    }

    @Override
    protected String getReason() {
        return "no input";
    }

    @Override
    public boolean preconditionsSatisfied() {
        // TODO: check if cast is running
        return true;
    }

    @Override
    protected void cleanUp() {
        // ignored
    }

    TimeSeries<Observation> getObservationTimeSeries() {
        return sink.getOutput();
    }

    public Observation getObservation(int index) {
        return source.getObservation(index);
    }
}

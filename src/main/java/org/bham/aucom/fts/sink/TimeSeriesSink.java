package org.bham.aucom.fts.sink;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;

import java.util.logging.Logger;

public class TimeSeriesSink<TIn extends AbstractData> extends AucomSinkAdapter<TIn> {
    private TimeSeries<TIn> outputTimeSeries;

    public TimeSeriesSink(TimeSeries<TIn> inOutputCollection) {
        super("TimeSeriesSink");
        setOutput(inOutputCollection);
    }

    public TimeSeriesSink() {
        super("TimeSeriesSink");
        setOutput(null);
    }

    @Override
    protected void pushItem(TIn arg) throws Exception {
        getOutput().add(arg);
        if (arg.isFirstElement()) {
            fireAucomSinkStatusChangedEvent(new AucomSinkStatusEvent(this, NodeStatus.RECEIVEDFIRSTELEMENT));
            Logger.getLogger(this.getClass().getCanonicalName()).info("pushing first item " + arg);
        }

        if (arg.isLastElement()) {
            fireAucomSinkStatusChangedEvent(new AucomSinkStatusEvent(this, NodeStatus.RECEIVEDLASTELEMENT));
            Logger.getLogger(this.getClass().getCanonicalName()).info("pushing last item " + arg);
        }
    }

    public void setOutput(TimeSeries<TIn> data) {
        this.outputTimeSeries = data;
    }

    public TimeSeries<TIn> getOutput() {
        return this.outputTimeSeries;
    }

}

package org.bham.aucom.fts.sink;

import org.bham.aucom.data.AbstractData;

import java.util.logging.Logger;

public class SingleDataSink<TIn extends AbstractData> extends AucomSinkAdapter<TIn> {
    private TIn data = null;

    public SingleDataSink() {
        super("SingleDataSink");
    }

    @Override
    protected void pushItem(TIn e) throws Exception {
        this.setData(e);
        Logger.getLogger(this.getClass().getCanonicalName()).info("pushing item " + getData().toString());
        if (getData().isLastElement()) {
            fireAucomSinkStatusChangedEvent(new AucomSinkStatusEvent(this, NodeStatus.RECEIVEDLASTELEMENT));
            Logger.getLogger(this.getClass().getCanonicalName()).info("pushing last item " + getData());
        } else if (getData().isFirstElement()) {
            fireAucomSinkStatusChangedEvent(new AucomSinkStatusEvent(this, NodeStatus.RECEIVEDLASTELEMENT));
            Logger.getLogger(this.getClass().getCanonicalName()).info("pushing first item " + getData());
        } else {
            fireAucomSinkStatusChangedEvent(new AucomSinkStatusEvent(this, NodeStatus.RECEIVEDELEMENT));
        }
    }

    protected void setData(TIn data) {
        this.data = data;
    }

    public TIn getData() {
        return data;
    }
}

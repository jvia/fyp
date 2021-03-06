package org.bham.aucom.fts.sink;

import org.bham.aucom.data.AbstractData;

public class DummySink<TIn extends AbstractData> extends AucomSinkAdapter<TIn> {

    public DummySink() {
        super("DummySink");
    }

    @Override
    protected void pushItem(TIn e) throws Exception {
        /*
           * do nothing, except notifying about first and last element received
           */
        if (e.isLastElement()) {
            fireAucomSinkStatusChangedEvent(new AucomSinkStatusEvent(this, NodeStatus.RECEIVEDLASTELEMENT));
        }
        if (e.isFirstElement()) {
            fireAucomSinkStatusChangedEvent(new AucomSinkStatusEvent(this, NodeStatus.RECEIVEDFIRSTELEMENT));
        }

    }

}

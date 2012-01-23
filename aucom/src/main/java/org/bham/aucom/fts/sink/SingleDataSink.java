package org.bham.aucom.fts.sink;

import java.util.logging.Logger;

import org.bham.aucom.data.AbstractData;


class SingleDataSink<TIn extends AbstractData> extends AucomSinkAdapter<TIn> {
	private TIn data = null;

	public SingleDataSink() {
		super("SingleDataSink");
	}

	@Override
	protected void pushItem(TIn e) throws Exception {
		this.setData(e);
		Logger.getLogger(this.getClass().getCanonicalName()).info("pushing item " + getData().toString());
		if (getData().isMarkedAsLastElement()) {
			fireAucomSinkStatusChangedEvent(new AucomSinkStatusEvent(this, NodeStatus.RECEIVED_LAST_ELEMENT));
			Logger.getLogger(this.getClass().getCanonicalName()).info("pushing last item " + getData());
		}else if (getData().isMarkedAsFirstElement()) {
			fireAucomSinkStatusChangedEvent(new AucomSinkStatusEvent(this, NodeStatus.RECEIVED_LAST_ELEMENT));
			Logger.getLogger(this.getClass().getCanonicalName()).info("pushing first item " + getData());
		} else {
			fireAucomSinkStatusChangedEvent(new AucomSinkStatusEvent(this, NodeStatus.RECEIVEDELEMENT));
		}
	}

	void setData(TIn data) {
		this.data = data;
	}

	TIn getData() {
		return data;
	}
}

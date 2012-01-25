package org.bham.aucom.fts.sink;

import java.util.EventObject;

public class AucomSinkStatusEvent extends EventObject {
	private NodeStatus status;
	private static final long serialVersionUID = 1L;

	public AucomSinkStatusEvent(Object source, NodeStatus status) {
		super(source);
		this.setStatus(status);
	}

	void setStatus(NodeStatus status) {
		this.status = status;
	}

	public NodeStatus getStatus() {
		return this.status;
	}
	@Override
	public String toString() {
		String str = "";
		str +="["+status+"]";
		return str;
	}

}

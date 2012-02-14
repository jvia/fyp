package org.bham.aucom.fts.tranform;

import java.util.EventObject;

import org.bham.aucom.fts.sink.NodeStatus;

public class TransformNodeEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	private final  NodeStatus status;
	public TransformNodeEvent(Object source, NodeStatus inStatus) {
		super(source);
		status = inStatus;
	}
	public NodeStatus getStatus() {
		return status;
	}		

}

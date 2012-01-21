package org.bham.aucom.fts.source;

import java.util.EventObject;

public class SourceStatusEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	private SourceStatus status;

	public SourceStatusEvent(Object source, SourceStatus status) {
		super(source);
		this.setStatus(status);
	}

	private void setStatus(SourceStatus status) {
		this.status = status;
	}

	public SourceStatus getStatus() {
		return this.status;
	}

}

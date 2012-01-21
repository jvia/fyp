package org.bham.aucom.fts.source;

public class ActionFailedException extends Exception {
	private static final long serialVersionUID = 1L;
	private final String reason;

	public ActionFailedException(String inReason) {
		super(inReason);
		reason = inReason;
	}

	public String getReason() {
		return reason;
	}
	
}

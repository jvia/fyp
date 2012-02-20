package org.bham.aucom.fts.source;

public class IllegalStateChange extends Exception {
	private static final long serialVersionUID = 1L;

	public IllegalStateChange(String reason) {
		super(reason);
	}
}

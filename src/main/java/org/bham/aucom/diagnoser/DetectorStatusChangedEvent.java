package org.bham.aucom.diagnoser;

import java.util.EventObject;

import org.bham.aucom.diagnoser.AbstractDetector.DetectorStatus;

public class DetectorStatusChangedEvent extends EventObject {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final DetectorStatus previousStatus;
	private final DetectorStatus currentStatus;

	public DetectorStatusChangedEvent(Object source, DetectorStatus previousStatus2, DetectorStatus currentStatus2) {
		super(source);
		previousStatus = previousStatus2; 
		currentStatus = currentStatus2;
	}

	public DetectorStatus getPreviousStatus() {
		return previousStatus;
	}

	public DetectorStatus getCurrentStatus() {
		return currentStatus;
	}
	@Override
	public String toString() {
		return "["+previousStatus + "->"+currentStatus + "]";
	}

}

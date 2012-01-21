package org.bham.aucom.diagnoser;

import java.util.EventObject;



public class StatusChangedEvent extends EventObject {
	
	
	private static final long serialVersionUID = 1L;
	private final TrainerStatus previousStatus;
	private final TrainerStatus currentStatus;
	
	public StatusChangedEvent(Object source, TrainerStatus inPreviousStatus, TrainerStatus inCurrentStatus) {
		super(source);
		previousStatus = inPreviousStatus;
		currentStatus = inCurrentStatus;
	}

	public TrainerStatus getPreviousStatus() {
		return previousStatus;
	}

	public TrainerStatus getCurrentStatus() {
		return currentStatus;
	}
	@Override
	public String toString() {
		return "changed from " + previousStatus + " to " + currentStatus;
	}
}

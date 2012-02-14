package org.bham.aucom.data;

public class ConsistentObservationChecker {
	public boolean check(Observation observationToCheck){
		if(observationToCheck == null){
			return false;
		}
		boolean timestampOK = observationToCheck.getTimestamp() != -1;
		boolean contentOK = observationToCheck.getContent() != null;
		return timestampOK && contentOK;
	}
}

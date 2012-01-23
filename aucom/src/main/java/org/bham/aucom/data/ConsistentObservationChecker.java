package org.bham.aucom.data;

public class ConsistentObservationChecker {
	public boolean notConsistent(Observation observationToCheck){
		if(null == observationToCheck){
			return true;
		}
		boolean timestampOK = -1 != observationToCheck.getTimestamp();
		boolean contentOK = observationToCheck.getContent() != null;
		return !timestampOK || !contentOK;
	}
}

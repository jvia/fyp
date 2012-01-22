package org.bham.aucom.data;

public class ConsistentObservationChecker {
	public boolean check(Observation observationToCheck){
		if(null == observationToCheck){
			return false;
		}
		boolean timestampOK = -1 != observationToCheck.getTimestamp();
		boolean contentOK = observationToCheck.getContent() != null;
		return timestampOK && contentOK;
	}
}

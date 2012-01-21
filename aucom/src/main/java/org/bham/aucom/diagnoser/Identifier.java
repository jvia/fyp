package org.bham.aucom.diagnoser;


public interface Identifier {
	public void monitor(Detector detector);
	public Object getResults();
	public Detector getDetector();
	
	/*
	 * should fire events in case it identifies an object
	 */
	
}

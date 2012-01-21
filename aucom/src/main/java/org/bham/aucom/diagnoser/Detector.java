package org.bham.aucom.diagnoser;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.source.IllegalStateChange;


public interface Detector{
	public void start(TimeSeries<Observation> inTimeSeries) throws ActionFailedException;
	public void pause()throws ActionFailedException, IllegalStateChange;
	public void resume();
	public void stop()throws ActionFailedException;

	public void addStatusListener(DetectorStatusChangedListener listener);
	public void removeStatusListener(DetectorStatusChangedListener listener);
	public void removeAllStatusListeners();
	public void addDetectionListener(DetectionListener listener);
	public void removeDetectionListener(DetectionListener listener);
	public void removeAllDetectionListeners();
	
	public TimeSeries<Classification> getOutput();
}

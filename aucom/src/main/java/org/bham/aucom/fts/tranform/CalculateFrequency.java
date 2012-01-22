package org.bham.aucom.fts.tranform;

import org.apache.commons.math.stat.descriptive.DescriptiveStatistics;
import org.bham.aucom.data.AbstractData;


public class CalculateFrequency<TIn extends AbstractData> extends AbstractAucomTransformNode<TIn, TIn> {
	private static final int HISTORY_SIZE = 20;
	long windowSize;
	long lastTimestamp;
	int eventcounter;
	int totalEventCounter;
	DescriptiveStatistics frequencyStatistics;
	public CalculateFrequency(long inWindowSize) {
		super("CalculateFrequency");
		this.windowSize= inWindowSize;
		initializeFrequencyCounter();
	}

	@Override
	protected TIn iTransform(TIn input) throws Exception {
		eventcounter++;
		totalEventCounter++;
		long currentTimestamp = System.currentTimeMillis();
		if(currentTimestamp - lastProcessingTime > windowSize){
			frequencyStatistics.addValue((double) eventcounter / (double) windowSize);
			eventcounter = 0;
			lastProcessingTime = currentTimestamp;
		}
		return input;
	}

	private void initializeFrequencyCounter() {
		lastTimestamp = -1;
		eventcounter = 0;
		totalEventCounter= 0;
		frequencyStatistics = new DescriptiveStatistics(HISTORY_SIZE);
	}
	public double getFrequency(){
		return frequencyStatistics.getMean();
	}
	public int getElementCount(){
		return totalEventCounter;
	}
}

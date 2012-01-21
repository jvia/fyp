package org.bham.aucom.data.io.csv.out.converter;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;


public abstract class TimeSeriesToCSVConverter<T extends AbstractData> {
	public String convertTimeSeries(TimeSeries<T> timeSeriesToWrite) throws IllegalArgumentException {
		String output = "";
		for(int i=0; i < timeSeriesToWrite.size();i++){
			output += convertTimeSeriesElement(timeSeriesToWrite.get(i)) + "\n";
		}
		return output;
	}
	protected abstract String convertTimeSeriesElement(T e);
}

package org.bham.aucom.data.io;

import java.io.File;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;


public interface TimeSeriesOutput {
	public <T extends AbstractData >boolean write(TimeSeries<T> timeSeriesTowrite, File file); 
}

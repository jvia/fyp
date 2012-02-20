package org.bham.aucom.data.io;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;

import java.io.File;

public interface TimeSeriesOutput {
    public <T extends AbstractData> boolean write(TimeSeries<T> timeSeriesTowrite, File file);
}

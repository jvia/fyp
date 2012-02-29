package org.bham.aucom.data.io.csv.out.converter;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;

public abstract class TimeSeriesToCSVConverter<T extends AbstractData> {
    public String convertTimeSeries(TimeSeries<T> timeSeriesToWrite) throws IllegalArgumentException {
        StringBuilder output = new StringBuilder();
        for (int i = 0; i < timeSeriesToWrite.size(); i++) {
            output.append(convertTimeSeriesElement(timeSeriesToWrite.get(i))).append("\n");
        }
        return output.toString();
    }

    protected abstract String convertTimeSeriesElement(T e);
}

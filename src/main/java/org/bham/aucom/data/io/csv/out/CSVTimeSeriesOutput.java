package org.bham.aucom.data.io.csv.out;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.io.TimeSeriesOutput;
import org.bham.aucom.data.io.csv.out.converter.TemporalDurationFeatureTimeSeriesToCSVConverter;
import org.bham.aucom.data.io.csv.out.converter.TimeSeriesToCSVConverter;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesType;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

// TODO finish converters for different timeseries 
public class CSVTimeSeriesOutput implements TimeSeriesOutput {
    HashMap<TimeSeriesType, TimeSeriesToCSVConverter<?>> converter;

    public CSVTimeSeriesOutput() {
        converter = new HashMap<TimeSeriesType, TimeSeriesToCSVConverter<?>>();
        converter.put(TimeSeriesType.tdf, new TemporalDurationFeatureTimeSeriesToCSVConverter());
    }

    @SuppressWarnings("unchecked")
    @Override
    public <T extends AbstractData> boolean write(TimeSeries<T> timeSeriesToWrite, File file) {
        System.out.println("writing to csv file " + file.getAbsolutePath());
        if (converter.containsKey(timeSeriesToWrite.getType())) {
            TimeSeriesToCSVConverter<T> conv = (TimeSeriesToCSVConverter<T>) converter.get(timeSeriesToWrite.getType());
            String s = conv.convertTimeSeries(timeSeriesToWrite);
            FileOutputStream fout;
            try {
                fout = new FileOutputStream(file);
                fout.write(s.getBytes());
                fout.close();
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            }
            return true;
        }
        System.out.println("converter missing for " + timeSeriesToWrite.getType());
        return false;
    }
}

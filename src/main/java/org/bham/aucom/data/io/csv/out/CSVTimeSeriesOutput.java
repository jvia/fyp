package org.bham.aucom.data.io.csv.out;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.io.TimeSeriesOutput;
import org.bham.aucom.data.io.csv.out.converter.TemporalDurationFeatureTimeSeriesToCSVConverter;
import org.bham.aucom.data.io.csv.out.converter.TimeSeriesToCSVConverter;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesType;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;

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

            Writer out = null;
            FileOutputStream fout;
            try {
                out = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file), "UTF-8"));
                out.write(s);
                //fout = new FileOutputStream(file);
                //fout.write(s.getBytes("UTF-8"));
                //fout.close();
            } catch (FileNotFoundException exception) {
                exception.printStackTrace();
            } catch (IOException exception) {
                exception.printStackTrace();
            } finally {
                try {
                    assert out != null;
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            return true;
        }

        System.out.println("converter missing for " + timeSeriesToWrite.getType());
        return false;
    }
}

package org.bham.aucom.data.io.xml.out;

import nu.xom.Document;
import nu.xom.Serializer;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.io.TimeSeriesOutput;
import org.bham.aucom.data.io.xml.out.converter.*;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesType;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.HashMap;

public class XmlTimeSeriesOutput implements TimeSeriesOutput {
    HashMap<TimeSeriesType, TimeSeriesToXmlDocumentConverter<?>> converter;

    public XmlTimeSeriesOutput() {
        converter = new HashMap<TimeSeriesType, TimeSeriesToXmlDocumentConverter<?>>();
        converter.put(TimeSeriesType.obs, new ObservationTimeSeriesToXmlConverter());
        converter.put(TimeSeriesType.dtp, new DataTypeTimeSeriesToXmlConverter());
        converter.put(TimeSeriesType.tdf, new TemporalDurationTimeSeriesFeatureToXmlConverter());
        converter.put(TimeSeriesType.tpf, new TemporalProbabilityFeatureTimeSeriesToXmlConverter());
        converter.put(TimeSeriesType.score, new ScoreTimeSeriesToXmlConverter());
        converter.put(TimeSeriesType.cl, new ClassificationTimeSeriesToXmlConverter());
    }

    @Override
    public <T extends AbstractData> boolean write(TimeSeries<T> timeSeriesTowrite, File file) {
        TimeSeriesType type = timeSeriesTowrite.getType();
        Document timeSeriesDocument = null;
        if (converter.containsKey(type)) {
            @SuppressWarnings("unchecked")
            TimeSeriesToXmlDocumentConverter<T> actualConcerter = (TimeSeriesToXmlDocumentConverter<T>) converter.get(type);
            try {
                timeSeriesDocument = actualConcerter.convertTimeSeries(timeSeriesTowrite);
            } catch (Exception exception) {
                System.out.println("timeseries conversion failed " + exception.getLocalizedMessage());
                exception.printStackTrace();
            }
            if (timeSeriesDocument == null) {
                return false;
            }
            try {
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                Serializer s = new Serializer(fileOutputStream, "UTF-8");
                s.setLineSeparator(System.getProperty("line.separator"));
                s.setIndent(3);
                s.write(timeSeriesDocument);
                s.flush();
                fileOutputStream.close();
                System.out.println("timeseries saved to " + file.getAbsolutePath());
            } catch (IOException exception) {
                exception.printStackTrace();
                return false;
            }
            return true;
        }
        return false;
    }
}

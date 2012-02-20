package org.bham.aucom.data.io.xml.in;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.io.TimeSeriesInput;
import org.bham.aucom.data.io.xml.in.converter.*;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesType;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

public class XmlTimeSeriesInput implements TimeSeriesInput {
    private static final String TYPE = "type";
    HashMap<TimeSeriesType, XmlToTimeSeriesConverter<?>> converter;

    public XmlTimeSeriesInput() {
        converter = new HashMap<TimeSeriesType, XmlToTimeSeriesConverter<?>>();
        converter.put(TimeSeriesType.obs, new XmlToObservationTimeSeriesConverter());
        converter.put(TimeSeriesType.dtp, new XmlToDatatypeTimeSeriesConverter());
        converter.put(TimeSeriesType.tdf, new XmlToTemporalDurationFeatureTimeSeriesConverter());
        converter.put(TimeSeriesType.tpf, new XmlToTemporalProbabilityFeatureTimeSeriesConverter());
        converter.put(TimeSeriesType.score, new XmlToScoreTimeSeriesConverter());
        converter.put(TimeSeriesType.cl, new XmlToClassificationTimeSeriesConverter());
    }

    @Override
    public TimeSeries<?> read(File file) throws ValidityException, ParsingException, IOException {
        Document doc = new Builder().build(file);
        TimeSeriesType type = getTimeSeriestype(doc);
        if (converter.containsKey(type)) {
            return converter.get(type).convert(doc);
        }
        return null;
    }

    private TimeSeriesType getTimeSeriestype(Document doc) {
        return Enum.valueOf(TimeSeriesType.class, doc.getRootElement().getAttributeValue(TYPE));
    } // TODO move this function to a new place
}
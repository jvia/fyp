package org.bham.aucom.data.io.csv.out.converter;

import org.bham.aucom.data.TemporalDurationFeature;

import java.util.Collections;
import java.util.List;

public class TemporalDurationFeatureTimeSeriesToCSVConverter extends TimeSeriesToCSVConverter<TemporalDurationFeature> {

    private static final String seperator = ";";

    @Override
    protected String convertTimeSeriesElement(TemporalDurationFeature e) {
        StringBuilder out = new StringBuilder();
        out.append(e.getEventTypeIdAsString()).append(seperator);
        out.append(e.getTimestamp()).append(seperator);
        List<Integer> predecessorDataTypes = e.getPrecedessorDataTypes();
        Collections.sort(predecessorDataTypes);

        for (Integer predecessor : predecessorDataTypes) {
            out.append(e.getDurationFor(predecessor)).append(seperator);
        }
        return out.substring(0, out.length() - 1);
    }
}

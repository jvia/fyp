package org.bham.aucom.data.io.csv.out.converter;

import java.util.Collections;
import java.util.List;

import org.bham.aucom.data.TemporalDurationFeature;

public class TemporalDurationFeatureTimeSeriesToCSVConverter extends TimeSeriesToCSVConverter<TemporalDurationFeature> {

	private static final String seperator = ";";

	@Override
    protected String convertTimeSeriesElement(TemporalDurationFeature e) {
        String out = "";
        out += e.getEventTypeIdAsString();
        out += seperator;
        out += e.getTimestamp();
        out += seperator;
        List<Integer>  predecessorDataTypes = e.getPrecedessorDataTypes();
        Collections.sort(predecessorDataTypes);
        
        for(Integer  predecessor :  predecessorDataTypes){
            out += e.getDurationFor(predecessor) + seperator;
        }
        return out.substring(0, out.length()-1);
    }
}

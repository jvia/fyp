package org.bham.aucom.data.timeseries;

import java.util.List;
import java.util.UUID;

import org.bham.aucom.data.TemporalDurationFeature;


public class TemporalDurationFeatureTimeSeries extends TimeSeries<TemporalDurationFeature> {

       private static final long serialVersionUID = 0L;
    
        public TemporalDurationFeatureTimeSeries() {
		setId(UUID.randomUUID());
		setType(TimeSeriesType.tdf);
	}
	private TemporalDurationFeatureTimeSeries(UUID generatoId, UUID generatedFromID) {
		setGenerator(generatoId);
		setGeneratedFrom(generatedFromID);
		setType(TimeSeriesType.tdf);
	}
	public TemporalDurationFeatureTimeSeries(UUID generatoId, UUID dataTypeTimeSeriesId, UUID id, List<TemporalDurationFeature> content) {
		this(generatoId, dataTypeTimeSeriesId);
		setId(id);
		addAll(content);
		setType(TimeSeriesType.tdf);
	}
}

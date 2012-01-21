package org.bham.aucom.data.timeseries;

import java.util.List;
import java.util.UUID;

import org.bham.aucom.data.TemporalProbabilityFeature;


public class TemporalProbabilityFeatureTimeSeries extends TimeSeries<TemporalProbabilityFeature> {
    
       private static final long serialVersionUID = 0L;

	public TemporalProbabilityFeatureTimeSeries() {
		setId(UUID.randomUUID());
		setType(TimeSeriesType.tpf);
	}

	public TemporalProbabilityFeatureTimeSeries(UUID generatoId, UUID generatedFromID) {
		super();
		setGenerator(generatoId);
		setGeneratedFrom(generatedFromID);
		setType(TimeSeriesType.tpf);
	}

	public TemporalProbabilityFeatureTimeSeries(UUID generatoId, UUID generatedFromId, UUID id, List<TemporalProbabilityFeature> content) {
		this(generatoId, generatedFromId);
		setId(id);
		addAll(content);
		setType(TimeSeriesType.tpf);
	}


}

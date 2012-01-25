package org.bham.aucom.data.io.xml.out.converter;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeriesType;
import org.bham.aucom.data.util.DataManager;


public class TimeSeriesToXmlDocumentConverterFactory {

	@SuppressWarnings("unchecked")
    <T extends AbstractData> TimeSeriesToXmlDocumentConverter<T> getConverter(TimeSeriesType tst) {
		Object converter = null;
		switch (tst) {
		case cl: {
			converter = new ClassificationTimeSeriesToXmlConverter();
			break;
		}
		case dtp: {
			converter = new DataTypeTimeSeriesToXmlConverter();
			break;
		}
		case obs: {
			converter = new ObservationTimeSeriesToXmlConverter();
			break;
		}
		case score: {
			converter = new ScoreTimeSeriesToXmlConverter();
			break;
		}
		case tdf: {
			converter = new TemporalDurationTimeSeriesFeatureToXmlConverter();
			break;
		}
		case tpf: {
			converter = new TemporalProbabilityFeatureTimeSeriesToXmlConverter();
			break;
		}

		}
		return (TimeSeriesToXmlDocumentConverter<T>) converter;

	}

//	public <T extends AbstractData> TimeSeriesToXmlDocumentConverter<T> getConverter(TimeSeries<?> ts) {
//		return getConverter(ts.getType());
//	}

	public <T extends AbstractData> TimeSeriesToXmlDocumentConverter<T> getConverter(AbstractData data) {
		TimeSeriesType tsType = DataManager.getInstance().getTimeSeriesTypeFor(data);
		return getConverter(tsType);
	}
}
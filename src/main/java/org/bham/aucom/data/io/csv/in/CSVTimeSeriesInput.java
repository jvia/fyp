package org.bham.aucom.data.io.csv.in;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.io.TimeSeriesInput;
import org.bham.aucom.data.io.csv.in.converter.CSVToTimeSeriesConverter;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesType;

// TODO finish this here

public class CSVTimeSeriesInput implements TimeSeriesInput {
	HashMap<TimeSeriesType, CSVToTimeSeriesConverter<?>> converter;
	public CSVTimeSeriesInput() {
		converter = new HashMap<TimeSeriesType, CSVToTimeSeriesConverter<?>>();
	}
	@Override
	public TimeSeries<?> read(File file) throws ValidityException, ParsingException, IOException {
		return null;
	}
}

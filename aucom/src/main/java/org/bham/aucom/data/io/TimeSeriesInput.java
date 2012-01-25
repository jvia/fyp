package org.bham.aucom.data.io;

import java.io.File;
import java.io.IOException;

import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.bham.aucom.data.timeseries.TimeSeries;

public interface TimeSeriesInput {
		public TimeSeries<?> read(File file) throws ParsingException, IOException ;
}

package org.bham.aucom.data.io;

import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.timeseries.TimeSeries;

import java.io.File;
import java.io.IOException;

public interface TimeSeriesInput {
    public TimeSeries<?> read(File file) throws ValidityException, ParsingException, IOException;
}

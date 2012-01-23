package org.bham.aucom.data.io;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.DataModel;

public class TimeSeriesIO implements IOInterface<TimeSeries<?>> {
	final TimeSeriesInput tsInput ;
	final TimeSeriesOutput tsOutput;
	
	public TimeSeriesIO(TimeSeriesInput in, TimeSeriesOutput out) {
		tsInput = in;
		tsOutput = out;
	}
	
	@Override
	public TimeSeries<?> read(File file) throws ValidityException, ParsingException, IOException {
		Logger.getLogger(this.getClass().getCanonicalName()).info("loading timeseries from file " + file.getAbsolutePath());
		TimeSeries<?> ts = tsInput.read(file);
		if(ts.size()==0){
				throw new IOException("couldn't load empty timeseries");
		}
		ts.get(0).markAsFirstElement();
		ts.get(ts.size() - 1).markAsLastElement();
		DataModel.getInstance().addTimeSeries(ts);
		Logger.getLogger(this.getClass().getCanonicalName()).info("loaded timeseries with " + ts.size() + " elements");
		return ts;
	}

	public static Document getXmlDocumentFromFile(File file) throws ValidityException, ParsingException, IOException {
		Builder b = new Builder();
		return b.build(file);
	}

	@Override
	public boolean write(TimeSeries<?> timeSeriesToWrite, File file) {
		try {
			return tsOutput.write(timeSeriesToWrite, file);
		} catch (Exception exception) {
			System.out.println("timeseries conversion failed " + exception.getLocalizedMessage());
			exception.printStackTrace();
			return false;
		}
	}
}
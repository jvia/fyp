package org.bham.aucom.data.management;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.io.xml.in.converter.XmlToDatatypeTimeSeriesConverter;
import org.bham.aucom.data.timeseries.TimeSeries;

public class XmlToDatatypeTimeSeriesConverterTest {
	XmlToDatatypeTimeSeriesConverter loader;
	File observationFile = new File("data"+File.separatorChar+"unittest" + File.separatorChar+"UTDataTypeTimeSeries.dtp");
	@Before
	public void setUp() throws Exception {
		this.loader = new XmlToDatatypeTimeSeriesConverter();
	}

	@Test
	public void testCreateScoreFromElementElement() {
		Document doc = getDocument();
		TimeSeries<DataType> dataTypeTimeSeries = this.loader.convert(doc);
		// check if all elements of the series has been loader
		Assert.assertEquals(3, dataTypeTimeSeries.size());
		// check if ids are correct
		Assert.assertEquals(1, dataTypeTimeSeries.get(0).getEventType());
		Assert.assertEquals(2, dataTypeTimeSeries.get(1).getEventType());
		Assert.assertEquals(3, dataTypeTimeSeries.get(2).getEventType());
		// check is domain features are correct
	}
	private Document getDocument() {
		Builder b = new Builder();
		try {
			return b.build(this.observationFile);
		} catch (ValidityException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		} catch (ParsingException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		} catch (IOException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		return null;
	}
}

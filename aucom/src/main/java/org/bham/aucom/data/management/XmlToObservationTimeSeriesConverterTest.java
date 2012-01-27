package org.bham.aucom.data.management;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

import junit.framework.Assert;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.io.xml.in.converter.XmlToObservationTimeSeriesConverter;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesType;

public class XmlToObservationTimeSeriesConverterTest {
	XmlToObservationTimeSeriesConverter converter;
	String filePathString = "data"+File.separatorChar+"unittest" + File.separatorChar+"UTObservationTimeSeries.obs";
	File observationFile = new File(filePathString);
	final long OBSERVATION_TIMESTAMP_1 = 1295266966695l;
	final long OBSERVATION_TIMESTAMP_2 = 1295266976695l;
	final UUID OBSERVATION_TIMESERIES_ID = UUID.fromString("02011f0a-8b10-4e27-a621-9ebd8f63f1fb");
	private Document doc;
	private ObservationTimeSeries observationTimeSeries;
	@Before
	public void setUp() throws Exception {
		this.converter = new XmlToObservationTimeSeriesConverter();
		doc = getDocument();
		observationTimeSeries = (ObservationTimeSeries)this.converter.convert(doc);
	}
	@Test 
	public void testId(){
		Assert.assertEquals(OBSERVATION_TIMESERIES_ID, observationTimeSeries.getId());
	}

	@Test
	public void testTimestampType(){
		Assert.assertEquals(OBSERVATION_TIMESTAMP_1, observationTimeSeries.get(0).getTimestamp());
		Assert.assertEquals(OBSERVATION_TIMESTAMP_2, observationTimeSeries.get(1).getTimestamp());
	}

	@Test
	public void testTimeSeriesType(){
		Assert.assertEquals(TimeSeriesType.obs, observationTimeSeries.getType());
	}
	@Test
	public void testCreateObservationTimeSeries(){
//		UUID id;
//		try {
//			id = new TimeSeriesIO().read(this.observationFile2).getId();
//			Assert.assertNotNull(id);
//			ObservationTimeSeries ts = (ObservationTimeSeries)DataManager.getInstance().getTimeSeriesById(id);
//			Assert.assertNotNull(ts);
//			Assert.assertEquals("d7c46ff1-4a3b-46e3-b7f2-0ce23f24f648", ts.getId().toString());
//		} catch (ValidityException exception) {
//			exception.printStackTrace();
//		} catch (ParsingException exception) {
//			// TODO Auto-generated catch block
//			exception.printStackTrace();
//		} catch (IOException exception) {
//			// TODO Auto-generated catch block
//			exception.printStackTrace();
//		} catch (TimeSeriesNotFoundException exception) {
//			// TODO Auto-generated catch block
//			exception.printStackTrace();
//		}
	}
	@Test
	public void testGetcontentFrom() {
		Document doc = getDocument();
		Assert.assertNotNull(doc);
//		System.out.println(doc.getRootElement().getChild(1).getChild(1).toXML());
	}
	/**
	 * @return
	 */
	private Document getDocument() {
		try {
			return new Builder().build(this.observationFile);
		} catch (ValidityException exception) {
			exception.printStackTrace();
		} catch (ParsingException exception) {
			exception.printStackTrace();
		} catch (IOException exception) {
			exception.printStackTrace();
		}
		return null;
	}
}

package org.bham.aucom.data.io.xml.out.converter;


import junit.framework.Assert;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.xml.in.converter.XmlToTimeSeriesConverter;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.util.Constants;

import static junit.framework.Assert.fail;

public class ObservationTimeSeriesToXmlConverterTest {
	ObservationTimeSeries obsTs =  null;
	@Before
	public void setUp() throws Exception {
		this.obsTs =  createObservationTimeSeries();
	}
	
	@Test
	public void testConvertElementElementObservation(){
		Element e = new Element("ts:element", Constants.URI);
		new ObservationTimeSeriesToXmlConverter().appendElementContentAsXml(e, this.obsTs.get(0));
		Assert.assertTrue(e.toXML().length() > 10);
		String expectedContent = this.obsTs.get(0).getContent().getValue();
		String actualContent = e.query("./ts:observation/*", XmlToTimeSeriesConverter.getContext() ).get(0).getValue();
		Assert.assertEquals(expectedContent,actualContent);
	}

	private ObservationTimeSeries createObservationTimeSeries() {
		 ObservationTimeSeries obs = new ObservationTimeSeries();
		 obs.add(Observation.createRandomObservation());
		 obs.add(Observation.createRandomObservation());
		 obs.add(Observation.createRandomObservation());
		 obs.add(Observation.createRandomObservation());
		return obs;
	}

    /**
     * Test of appendElementContentAsXml method, of class ObservationTimeSeriesToXmlConverter.
     */
    @Test
    public void testAppendElementContentAsXml() {
        System.out.println("appendElementContentAsXml");
        Element containerElement = null;
        Observation data = null;
        ObservationTimeSeriesToXmlConverter instance = new ObservationTimeSeriesToXmlConverter();
        instance.appendElementContentAsXml(containerElement, data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
	

}

package org.bham.aucom.data.io.xml.out.converter;

import junit.framework.Assert;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.io.xml.in.converter.XmlToTimeSeriesConverter;
import org.bham.aucom.data.timeseries.DataTypeTimeSeries;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.data.timeseries.TemporalDurationFeatureTimeSeries;
import org.bham.aucom.util.Constants;

public class TemporalDurationTimeSeriesFeatureToXmlConverterTest {
	TemporalDurationFeatureTimeSeries tdTs = null;
	DataTypeTimeSeries dtpTs= null;
	ObservationTimeSeries obsTs =  null;
	@Before
	public void setUp() throws Exception {
		this.tdTs = createTemporalDurationTimeseries();
		this.dtpTs = new DataTypeTimeSeries();
		this.obsTs =  createObservationTimeSeries();
	}

	private TemporalDurationFeatureTimeSeries createTemporalDurationTimeseries() {
		TemporalDurationFeatureTimeSeries tdTs = new TemporalDurationFeatureTimeSeries();
		tdTs.add(TemporalDurationFeature.createRandomTemporalDurationFeature());
		tdTs.add(TemporalDurationFeature.createRandomTemporalDurationFeature());
		tdTs.add(TemporalDurationFeature.createRandomTemporalDurationFeature());
		tdTs.add(TemporalDurationFeature.createRandomTemporalDurationFeature());
		tdTs.add(TemporalDurationFeature.createRandomTemporalDurationFeature());
		return tdTs;
	}

	@Test
	public void testConvertElementElementTemporalDurationFeature() {
		Element containerElement = new Element("ts:element", Constants.URI);
		new TemporalDurationTimeSeriesFeatureToXmlConverter().appendElementContentAsXml(containerElement, this.tdTs.get(0));
	    Element temporalDurationFeature = (Element)containerElement.query("./ts:temporaldurationfeature/ts:predecessors/ts:element", XmlToTimeSeriesConverter.getContext()).get(0);
	    Assert.assertNotNull(temporalDurationFeature);
		long transformedDuration = Long.valueOf(temporalDurationFeature.getAttributeValue("duration"));
		Assert.assertEquals(this.tdTs.get(0).getDurationFor(this.tdTs.get(0).getPredecessors().get(0)), transformedDuration);
	}
	@Test
	public void testConvertElementElementDataType() {
		this.dtpTs.add(DataType.createRandomDataType());
		this.dtpTs.add(DataType.createRandomDataType());
		this.dtpTs.add(DataType.createRandomDataType());
		this.dtpTs.add(DataType.createRandomDataType());
		Element element = new Element("ts:element", Constants.URI);
		new DataTypeTimeSeriesToXmlConverter().appendElementContentAsXml(element, dtpTs.get(0));
		Assert.assertEquals("domainfeature", ((Element)element.query("./ts:datatype/ts:parameter", XmlToTimeSeriesConverter.getContext()).get(0)).getAttributeValue("type"));
		Assert.assertEquals("source", ((Element)element.query("./ts:datatype/ts:parameter", XmlToTimeSeriesConverter.getContext()).get(0)).getAttributeValue("name"));
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
     * Test of appendElementContentAsXml method, of class TemporalDurationTimeSeriesFeatureToXmlConverter.
     */
    @Test
    public void testAppendElementContentAsXml() {
        System.out.println("appendElementContentAsXml");
        Element containerElement = null;
        TemporalDurationFeature data = null;
        TemporalDurationTimeSeriesFeatureToXmlConverter instance = new TemporalDurationTimeSeriesFeatureToXmlConverter();
        instance.appendElementContentAsXml(containerElement, data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

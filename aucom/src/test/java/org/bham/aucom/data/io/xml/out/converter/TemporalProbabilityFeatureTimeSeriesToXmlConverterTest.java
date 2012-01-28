package org.bham.aucom.data.io.xml.out.converter;

import junit.framework.Assert;
import nu.xom.Element;
import nu.xom.Nodes;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.data.io.xml.in.converter.XmlToTimeSeriesConverter;
import org.bham.aucom.data.timeseries.TemporalProbabilityFeatureTimeSeries;
import org.bham.aucom.util.Constants;

public class TemporalProbabilityFeatureTimeSeriesToXmlConverterTest {
	/*
	 * <ts:temporalprobabilityfeature> <ts:predecessors> <ts:element
	 * type="probability" eventType="1" value="0.1"></ts:element> <ts:element
	 * type="probability" eventType="2" value="0.2"></ts:element> <ts:element
	 * type="probability" eventType="3" value="0.3"></ts:element> <ts:element
	 * type="probability" eventType="4" value="0.4"></ts:element>
	 * </ts:predecessors> </ts:temporalprobabilityfeature>
	 */
	TemporalProbabilityFeatureTimeSeries tpTs = null;

	@Before
	public void setUp() throws Exception {
		this.tpTs = createTemporalProbabilityFeatureTimeSeries();

	}

	private TemporalProbabilityFeatureTimeSeries createTemporalProbabilityFeatureTimeSeries() {
		TemporalProbabilityFeatureTimeSeries tpts = new TemporalProbabilityFeatureTimeSeries();
		tpts.add(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature());
		tpts.add(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature());
		tpts.add(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature());
		tpts.add(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature());
		tpts.add(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature());
		return tpts;
	}

	@Test
	public void testConvertElementElementTemporalProbabilityFeature() {
		for (int j = 0; j < this.tpTs.size(); j++) {
			TemporalProbabilityFeature tpf = this.tpTs.get(j);
			Element containerElement = new Element("ts:element", Constants.URI); //
			new TemporalProbabilityFeatureTimeSeriesToXmlConverter().appendElementContentAsXml(containerElement, tpf);
			Nodes elements = containerElement.query("./ts:temporalprobabilityfeature/ts:predecessors/ts:element", XmlToTimeSeriesConverter.getContext());
			for (int i = 0; i < elements.size(); i++) {
				Element tmp = (Element) elements.get(i);
				Double convertedProbabilityValue = Double.valueOf(tmp.getAttributeValue("value"));
				Assert.assertEquals(Double.valueOf(tpf.getProbabilityFor(tpf.getPredecessors().get(i))), convertedProbabilityValue);
			}
		}

	}

    /**
     * Test of appendElementContentAsXml method, of class TemporalProbabilityFeatureTimeSeriesToXmlConverter.
     */
    @Test
    public void testAppendElementContentAsXml() {
        System.out.println("appendElementContentAsXml");
        Element containerElement = null;
        TemporalProbabilityFeature data = null;
        TemporalProbabilityFeatureTimeSeriesToXmlConverter instance = new TemporalProbabilityFeatureTimeSeriesToXmlConverter();
        instance.appendElementContentAsXml(containerElement, data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

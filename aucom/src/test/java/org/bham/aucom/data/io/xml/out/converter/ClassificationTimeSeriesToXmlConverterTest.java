package org.bham.aucom.data.io.xml.out.converter;

import junit.framework.Assert;
import nu.xom.Element;
import nu.xom.Nodes;
import org.bham.aucom.data.Classification;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.SystemFaultStatus;
import org.bham.aucom.data.io.xml.in.converter.XmlToTimeSeriesConverter;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.util.Constants;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static junit.framework.Assert.fail;

public class ClassificationTimeSeriesToXmlConverterTest {
	ClassificationTimeSeries clTs = null;

	@Before
	public void setUp() throws Exception {
		this.clTs = ClassificationTimeSeries.createRandomTimeSeries();
	}
	@Test
	public void testConvertElementElementClassification() {
		for (int i = 0; i < this.clTs.size(); i++) {
			Element containerElement = new Element("ts:element", Constants.URI);
			Classification clElement = this.clTs.get(i);
			new ClassificationTimeSeriesToXmlConverter().appendElementContentAsXml(containerElement, clElement);
			// test observation content
			String expectedContent = this.clTs.get(i).getContent().getValue();
			String actualContent = containerElement.query("./ts:observation/*", XmlToTimeSeriesConverter.getContext() ).get(0).getValue();
			Assert.assertEquals(expectedContent,actualContent);
			// test observation content
			
			// test data type content
			List<DomainFeature> df =clElement.getFeatures();
			int z = 0;
			for (DomainFeature feature: df){
				Assert.assertEquals("domainfeature", ((Element)containerElement.query("./ts:datatype/ts:parameter", XmlToTimeSeriesConverter.getContext()).get(0)).getAttributeValue("type"));
				Assert.assertEquals(df.get(z).getFeatureName(), ((Element)containerElement.query("./ts:datatype/ts:parameter", XmlToTimeSeriesConverter.getContext()).get(z)).getAttributeValue("name"));
				Assert.assertEquals(df.get(z).getFeatureValue(), ((Element)containerElement.query("./ts:datatype/ts:parameter[@name=\""+df.get(z).getFeatureName()+"\"]", XmlToTimeSeriesConverter.getContext()).get(0)).getAttributeValue("value"));
				z++;
			}
			// test data type content

			// test duration content
			Nodes temporalDurationElements = containerElement.query("./ts:temporaldurationfeature/ts:predecessors/ts:element", XmlToTimeSeriesConverter.getContext());
			for (int j = 0; j < temporalDurationElements.size(); j++) {
				Assert.assertNotNull(temporalDurationElements);
				long transformedDuration = Long.valueOf(((Element)temporalDurationElements.get(j)).getAttributeValue("duration"));
				Assert.assertEquals(clElement.getDurationFor(clElement.getPredecessors().get(j)), transformedDuration);
			}			
			// test duration content
			
			// test probability content
			Nodes elements = containerElement.query("./ts:temporalprobabilityfeature/ts:predecessors/ts:element", XmlToTimeSeriesConverter.getContext());
			for (int j = 0; j < elements.size(); j++) {
				Element tmp = (Element) elements.get(j);
				Double convertedProbabilityValue = Double.valueOf(tmp.getAttributeValue("value"));
				Assert.assertEquals(Double.valueOf(clElement.getProbabilityFor(clElement.getPredecessors().get(j))), convertedProbabilityValue);
			}
			// test probability content
			
			// test classification content
			SystemFaultStatus expectedSystemStatus = clElement.getStatus();
			String ss = ((Element) containerElement.query("./ts:classification", XmlToTimeSeriesConverter.getContext()).get(0)).getAttributeValue("systemstatus");
			SystemFaultStatus actualSystemStatus = SystemFaultStatus.valueOf(ss);
			Assert.assertEquals(expectedSystemStatus, actualSystemStatus);
			// test classification content
			
		}
	}

    /**
     * Test of appendElementContentAsXml method, of class ClassificationTimeSeriesToXmlConverter.
     */
    @Test
    public void testAppendElementContentAsXml() {
        System.out.println("appendElementContentAsXml");
        Element containerElement = null;
        Classification data = null;
        ClassificationTimeSeriesToXmlConverter instance = new ClassificationTimeSeriesToXmlConverter();
        instance.appendElementContentAsXml(containerElement, data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

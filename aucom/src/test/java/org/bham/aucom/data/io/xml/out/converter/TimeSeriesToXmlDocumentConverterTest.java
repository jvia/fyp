package org.bham.aucom.data.io.xml.out.converter;

import junit.framework.Assert;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.XPathContext;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.StatisticalAnomalyClassificator;
import org.bham.aucom.util.Constants;

public class TimeSeriesToXmlDocumentConverterTest {
	ClassificationTimeSeriesToXmlConverter converter;
	
	
	@Before
	public void setUp() throws Exception {
		converter = new ClassificationTimeSeriesToXmlConverter();
	}

	/*
	 * -- tests -- 
	 */
	@Test
	public void testAddElementAttributesAsXml() {
		ClassificationTimeSeries clTs = createClassificationTimeseriesForAddElementAttributesTest();
		Document doc = converter.convertTimeSeries(clTs);
		Nodes nodes = doc.query("//ts:attribute[@"+StatisticalAnomalyClassificator.THRESHOLD_USED+"]", new XPathContext("ts", Constants.URI));
		Assert.assertEquals(10, nodes.size());
		for(int i = 0 ; i < nodes.size(); i ++){
			double actual = Double.valueOf(((Element)nodes.get(i)).getAttributeValue(StatisticalAnomalyClassificator.THRESHOLD_USED)).doubleValue();
			double expected = 0.1+i*0.1;
			Assert.assertEquals(expected, actual, 0.000001);
		}
	}

	
	
	/*
	 * -- support functions --
	 */
	/**
	 * 
	 * @return
	 */
	private ClassificationTimeSeries createClassificationTimeseriesForAddElementAttributesTest() {
		ClassificationTimeSeries clTs = new ClassificationTimeSeries();
		Classification cl ;
		for (int i = 0; i < 10; i++){
			cl = Classification.createRandomClassification(); 
			cl.addAttribute(StatisticalAnomalyClassificator.THRESHOLD_USED, String.valueOf(0.1 + i*0.1));
			clTs.add(cl);
			
		}
		return clTs;
	}

    /**
     * Test of convertTimeSeries method, of class TimeSeriesToXmlDocumentConverter.
     */
    @Test
    public void testConvertTimeSeries() {
        System.out.println("convertTimeSeries");
        TimeSeries<T> timeSeriesToWrite = null;
        TimeSeriesToXmlDocumentConverter instance = new TimeSeriesToXmlDocumentConverterImpl();
        Document expResult = null;
        Document result = instance.convertTimeSeries(timeSeriesToWrite);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTimeSeriesProperties method, of class TimeSeriesToXmlDocumentConverter.
     */
    @Test
    public void testAddTimeSeriesProperties() {
        System.out.println("addTimeSeriesProperties");
        Document xmlDocumentToWrite = null;
        TimeSeries<T> timeSeriesToWrite = null;
        TimeSeriesToXmlDocumentConverter instance = new TimeSeriesToXmlDocumentConverterImpl();
        Document expResult = null;
        Document result = instance.addTimeSeriesProperties(xmlDocumentToWrite, timeSeriesToWrite);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendElementContentAsXml method, of class TimeSeriesToXmlDocumentConverter.
     */
    @Test
    public void testAppendElementContentAsXml() {
        System.out.println("appendElementContentAsXml");
        Element e = null;
        AbstractData data = null;
        TimeSeriesToXmlDocumentConverter instance = new TimeSeriesToXmlDocumentConverterImpl();
        instance.appendElementContentAsXml(e, data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TimeSeriesToXmlDocumentConverterImpl extends TimeSeriesToXmlDocumentConverter {

        public void appendElementContentAsXml(Element e, T data) {
        }
    }
}

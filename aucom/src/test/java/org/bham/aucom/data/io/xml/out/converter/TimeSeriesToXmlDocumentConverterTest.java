package org.bham.aucom.data.io.xml.out.converter;

import junit.framework.Assert;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.XPathContext;

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
}

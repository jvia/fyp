package org.bham.aucom.data.io.xml.out.converter;

import junit.framework.Assert;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.SingleScore;
import org.bham.aucom.data.io.xml.in.converter.XmlToTimeSeriesConverter;
import org.bham.aucom.data.timeseries.ScoreTimeSeries;
import org.bham.aucom.util.Constants;

public class ScoreTimeSeriesToXmlConverterTest {
	ScoreTimeSeries sTs = null;
	@Before
	public void setUp() throws Exception {
		this.sTs = createScoreTimeSeries();
		
	}

	private ScoreTimeSeries createScoreTimeSeries() {
		ScoreTimeSeries scTs = new ScoreTimeSeries();
		scTs.add(Score.createRandomScore());
		scTs.add(Score.createRandomScore());
		scTs.add(Score.createRandomScore());
		scTs.add(Score.createRandomScore());
		scTs.add(Score.createRandomScore());
		return scTs;
	}

	@Test
	public void testConvertElementElementScore() {
		
		for(int i= 0; i < this.sTs.size(); i++){
			Element containerElement = new Element("ts:element", Constants.URI);
			Score scoreElementToTest = this.sTs.get(i);
			new ScoreTimeSeriesToXmlConverter().appendElementContentAsXml(containerElement, scoreElementToTest);
			if(scoreElementToTest.getClass().equals(SingleScore.class)){
				testSingleScoreElement(containerElement, scoreElementToTest);
			}else{
				testRangeScoreElement(containerElement, scoreElementToTest);
			}
		}
	}

	private void testRangeScoreElement(Element containerElement, Score scoreElementToTest) {
		Element e = (Element)containerElement.query("./ts:score/ts:element", XmlToTimeSeriesConverter.getContext()).get(0);
		
	}

	private void testSingleScoreElement(Element containerElement, Score scoreElementToTest) {
		Element e = (Element)containerElement.query("./ts:score", XmlToTimeSeriesConverter.getContext()).get(0);
		double expectedScoreValue = scoreElementToTest.getValue();
		double actualScoreValue = Double.valueOf(e.getAttributeValue("value")).doubleValue();
		Assert.assertEquals(expectedScoreValue, actualScoreValue);  

	}

    /**
     * Test of appendElementContentAsXml method, of class ScoreTimeSeriesToXmlConverter.
     */
    @Test
    public void testAppendElementContentAsXml() {
        System.out.println("appendElementContentAsXml");
        Element containerElement = null;
        Score data = null;
        ScoreTimeSeriesToXmlConverter instance = new ScoreTimeSeriesToXmlConverter();
        instance.appendElementContentAsXml(containerElement, data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}


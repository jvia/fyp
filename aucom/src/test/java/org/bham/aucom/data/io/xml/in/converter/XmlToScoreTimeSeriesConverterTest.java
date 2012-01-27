package org.bham.aucom.data.io.xml.in.converter;

import java.io.File;

import junit.framework.Assert;
import nu.xom.Document;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.RangeScore;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.io.TimeSeriesIO;
import org.bham.aucom.data.timeseries.ScoreTimeSeries;

public class XmlToScoreTimeSeriesConverterTest {
	File scoreFile = new File("data"+File.separatorChar+"unittest" + File.separatorChar+"UTScoreTimeSeries.score");
	ScoreTimeSeries scoreTs = null;
	@Before
	public void setUp() throws Exception {
		Document doc = TimeSeriesIO.getXmlDocumentFromFile(this.scoreFile);
		Element element = getFirstEelement(doc.getRootElement());
		Assert.assertNotNull(element);
		this.scoreTs = (ScoreTimeSeries) new XmlToScoreTimeSeriesConverter().convert(doc);
	}

	@Test
	public void testCreateTimeSeries() {
		Assert.assertTrue(this.scoreTs.size()>0);
	}
	@Test
	public void testCreateDataFromElementElement() {
		Assert.assertNotNull(this.scoreTs.get(0));
		Assert.assertNotNull(this.scoreTs.get(1));
	}

	@Test
	public void testCreateScoreFrom() {
		Score Score = (Score)this.scoreTs.get(0);
		Assert.assertEquals(0.1d, Score.getValue());
	}

	@Test
	public void testCreateRangeScoreFrom() {
		RangeScore rangeScore = (RangeScore)this.scoreTs.get(1);
		Assert.assertEquals(0.25d, rangeScore.getValue());
		Assert.assertEquals(0.005d, rangeScore.getVariance(), 0.0001d);
	}

	private Element getFirstEelement(Element e) {
		return (Element)e.query("./ts:elements/ts:element", XmlToTimeSeriesConverter.getContext()).get(0);
	}
}

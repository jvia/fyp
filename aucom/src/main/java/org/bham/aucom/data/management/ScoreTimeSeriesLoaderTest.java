package org.bham.aucom.data.management;

import java.util.List;

import junit.framework.Assert;
import nu.xom.Attribute;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.RangeScore;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.SingleScore;
import org.bham.aucom.data.io.xml.in.converter.XmlToScoreTimeSeriesConverter;
import org.bham.aucom.data.io.xml.in.converter.XmlToTimeSeriesConverter;

public class ScoreTimeSeriesLoaderTest {
	private static final String RANGEDSCORE_SINGLESCORE_TIMESTAMP_ID1 = "123456789";
	private static final String RANGEDSCORE_SINGLESCORE_TIMESTAMP_ID2 = "234567890";
	private static final String RANGEDSCORE_SINGLESCORE_GENERATEFROM_ID1 = "da44273b-d284-4aac-8931-c2779efd1e1f";
	private static final String RANGEDSCORE_SINGLESCORE_ID1 = "cbf827db-166d-487f-a130-2dd6fb1935b8";
	private static final String SINGLESCORE_ID = "a5184d0d-c64a-4010-9c4b-f7834cf2022b";
	private static final String GENERATED_FROM_ID_SINGLESCORE = "a5184d0d-c64a-4010-9c4b-f7834cf2022c";
	private static final String ID_RANGEDSCORE = "b5f1e600-c6a0-4e7e-9c09-5d4e626de90d";
	private static final String RANGEDSCORE_SINGLESCORE_ID2 = "c48fec2d-107c-41c4-a74a-ef7e8abe71e5";
	private static final String RANGEDSCORE_SINGLESCORE_GENERATEFROM_ID2 = "4682841f-32a9-45c8-8734-3ecdd0875a1b";
	XmlToTimeSeriesConverter<Score> converter = new XmlToScoreTimeSeriesConverter();
	private Element singleScore;
	private Element rangedScore;

	@Before
	public void setUp() {
		this.singleScore = createSingleScoreElement("0987654321", "0.99", SINGLESCORE_ID, GENERATED_FROM_ID_SINGLESCORE);
		this.rangedScore = createRangedScoreElement();
	}

	private Element createRangedScoreElement() {
		Element element = new Element("ts:element", "http://www.cor-lab.de/formats/ts/1.0");
		element.addAttribute(new Attribute("type", "rangedScore"));
		element.addAttribute(new Attribute("id", ID_RANGEDSCORE));
		element.appendChild(createSingleScoreElement(RANGEDSCORE_SINGLESCORE_TIMESTAMP_ID1, "0.1", RANGEDSCORE_SINGLESCORE_ID1, RANGEDSCORE_SINGLESCORE_GENERATEFROM_ID1));
		element.appendChild(createSingleScoreElement(RANGEDSCORE_SINGLESCORE_TIMESTAMP_ID2, "0.2", RANGEDSCORE_SINGLESCORE_ID2, RANGEDSCORE_SINGLESCORE_GENERATEFROM_ID2));
		return element;
	}

	/**
	 * 
	 */
	private Element createSingleScoreElement(String timestamp, String value, String id, String generatedFrom_id) {
		Element element = new Element("ts:element", "http://www.cor-lab.de/formats/ts/1.0");
		element.addAttribute(new Attribute("timestamp", timestamp));
		element.addAttribute(new Attribute("type", "singleScore"));
		element.addAttribute(new Attribute("id", id));
		element.addAttribute(new Attribute("generatedFromId", generatedFrom_id));
		Element parameter = new Element("ts:parameter", "http://www.cor-lab.de/formats/ts/1.0");
		parameter.addAttribute(new Attribute("type", "scoreValue"));
		parameter.appendChild(value);
		element.appendChild(parameter);
		return element;
	}

	@Test
	public void testCreateScoreFromElementElement() {
		Score score = this.converter.createDataFromElement(this.singleScore);
		Assert.assertNotNull(score);
		Assert.assertEquals(SingleScore.class, score.getClass());
		score = this.converter.createDataFromElement(this.rangedScore);
		Assert.assertNotNull(score);
		Assert.assertEquals(RangeScore.class, score.getClass());
	}
	
	@Test
	public void testCreateSingleScoreFromElementElement() {
		Score score = this.converter.createDataFromElement(this.singleScore);
		Assert.assertEquals(0.99, score.getValue());
		Assert.assertEquals(987654321, score.getTimestamp());
	}
	
	@Test
	public void testCreateRangeScoreFromElementElement() {
		Score score = this.converter.createDataFromElement(this.rangedScore);
		Assert.assertEquals(Long.valueOf(RANGEDSCORE_SINGLESCORE_TIMESTAMP_ID1).longValue(), score.getTimestamp());
		List<Score> scores = ((RangeScore)score).getScores();
		Assert.assertEquals(2, scores.size());
		Score s1 = scores.get(0);
		Score s2 = scores.get(1);
		Assert.assertEquals(0.1, s1.getValue());
		Assert.assertEquals(Long.valueOf(RANGEDSCORE_SINGLESCORE_TIMESTAMP_ID1).longValue(), s1.getTimestamp());

		Assert.assertEquals(0.2, s2.getValue());
		Assert.assertEquals(Long.valueOf(RANGEDSCORE_SINGLESCORE_TIMESTAMP_ID2).longValue(), s2.getTimestamp());
	}

	// @Test
	// public void testCreateSingleScoreFrom() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testCreateRangeScoreFrom() {
	// fail("Not yet implemented");
	// }
	//
	// @Test
	// public void testIsSingle() {
	// fail("Not yet implemented");
	// }

}

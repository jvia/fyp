package org.bham.aucom.data.io.xml.in.converter;

import static org.junit.Assert.fail;

import java.io.File;

import junit.framework.Assert;
import nu.xom.Document;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.io.TimeSeriesIO;

public class XmlToTemporalDurationFeatureTimeSeriesConverterTest {
	File observationFile = new File("data"+File.separatorChar+"unittest" + File.separatorChar+"UTTemporalDurationFeatureTimeSeries.tdf");
//	File observationFile = new File("data"+File.separatorChar+"unittestdata" + File.separatorChar+"UTDataTypeTimeSeries.dtp");
	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCreateDataFromElementElement() {
		Document doc;
		try {
			doc = TimeSeriesIO.getXmlDocumentFromFile(this.observationFile);
			Element e = XmlToTimeSeriesConverter.getRootElement(doc);
			Element firstElement = getFirstEelement(e);
			TemporalDurationFeature f = new XmlToTemporalDurationFeatureTimeSeriesConverter().createDataFromElement(firstElement);
			Assert.assertNotNull(f);
			Assert.assertEquals(1295266966695L, f.getTimestamp());
			Assert.assertEquals(4, f.getPredecessors().size());
			Assert.assertEquals(10, f.getDurationFor(f.getPredecessors().get(0)));
			Assert.assertEquals(12, f.getDurationFor(f.getPredecessors().get(1)));
			Assert.assertEquals(12, f.getDurationFor(f.getPredecessors().get(2)));
			Assert.assertEquals(13, f.getDurationFor(f.getPredecessors().get(3)));
			
		} catch (Exception exception) {
			exception.printStackTrace();
			fail("exception caught");
		}
		
	}

	private Element getFirstEelement(Element e) {
		return (Element)e.query("./ts:elements/ts:element", XmlToTimeSeriesConverter.getContext()).get(0);
	}

}

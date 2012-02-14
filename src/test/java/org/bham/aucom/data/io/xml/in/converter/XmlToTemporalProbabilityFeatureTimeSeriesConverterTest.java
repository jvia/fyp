package org.bham.aucom.data.io.xml.in.converter;

import junit.framework.Assert;
import nu.xom.Document;
import nu.xom.Element;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.data.io.TimeSeriesIO;
import org.bham.aucom.data.timeseries.TemporalProbabilityFeatureTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
public class XmlToTemporalProbabilityFeatureTimeSeriesConverterTest {
	File observationFile = new File("data"+File.separatorChar+"unittestdata" + File.separatorChar+"UTTemporalProbabilityFeatureTimeSeries.tpf");
	TemporalProbabilityFeatureTimeSeries tpfTs = null;
	@Before
	public void setUp() throws Exception {
		Document doc = TimeSeriesIO.getXmlDocumentFromFile(this.observationFile);
		Element element = getFirstEelement(doc.getRootElement());
		Assert.assertNotNull(element);
		this.tpfTs = (TemporalProbabilityFeatureTimeSeries) new XmlToTemporalProbabilityFeatureTimeSeriesConverter().convert(doc);
	}

	@Test
	public void testCreateDataFromElementElement() {
		TemporalProbabilityFeature tpf = this.tpfTs.get(0);
		Assert.assertEquals(4, tpf.getDurationProbabilities().size());
		Assert.assertEquals(0.2, tpf.getProbabilityFor(tpf.getPredecessors().get(0)));
		Assert.assertEquals(2, tpf.getPredecessors().get(0).getEventType());
		Assert.assertEquals(0.3, tpf.getProbabilityFor(tpf.getPredecessors().get(1)));
		Assert.assertEquals(3, tpf.getPredecessors().get(1).getEventType());
		Assert.assertEquals(0.4, tpf.getProbabilityFor(tpf.getPredecessors().get(2)));
		Assert.assertEquals(4, tpf.getPredecessors().get(2).getEventType());
		Assert.assertEquals(0.1, tpf.getProbabilityFor(tpf.getPredecessors().get(3)));
		Assert.assertEquals(1, tpf.getPredecessors().get(3).getEventType());
	}

	@Test
	public void testCreateTimeSeriesTemporalProbabilityFeature() {
		 Assert.assertEquals(2, this.tpfTs.size());
	}
	private Element getFirstEelement(Element e) {
		return (Element)e.query("./ts:elements/ts:element", XmlToTimeSeriesConverter.getContext()).get(0);
	}

    /**
     * Test of createDataFromElement method, of class XmlToTemporalProbabilityFeatureTimeSeriesConverter.
     */
    @Test
    public void testCreateDataFromElement() {
        System.out.println("createDataFromElement");
        Element e = null;
        XmlToTemporalProbabilityFeatureTimeSeriesConverter instance = new XmlToTemporalProbabilityFeatureTimeSeriesConverter();
        TemporalProbabilityFeature expResult = null;
        TemporalProbabilityFeature result = instance.createDataFromElement(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTimeSeries method, of class XmlToTemporalProbabilityFeatureTimeSeriesConverter.
     */
    @Test
    public void testCreateTimeSeries() {
        System.out.println("createTimeSeries");
        UUID id = null;
        UUID generatorID = null;
        UUID generatedFromID = null;
        ArrayList<TemporalProbabilityFeature> items = null;
        XmlToTemporalProbabilityFeatureTimeSeriesConverter instance = new XmlToTemporalProbabilityFeatureTimeSeriesConverter();
        TimeSeries expResult = null;
        TimeSeries result = instance.createTimeSeries(id, generatorID, generatedFromID, items);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

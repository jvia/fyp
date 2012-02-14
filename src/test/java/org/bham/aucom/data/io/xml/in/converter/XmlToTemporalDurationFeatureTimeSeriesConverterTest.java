package org.bham.aucom.data.io.xml.in.converter;

import junit.framework.Assert;
import nu.xom.Document;
import nu.xom.Element;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.io.TimeSeriesIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.ArrayList;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class XmlToTemporalDurationFeatureTimeSeriesConverterTest {
    File observationFile = new File("data" + File.separatorChar + "unittest" + File.separatorChar + "UTTemporalDurationFeatureTimeSeries.tdf");

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
        return (Element) e.query("./ts:elements/ts:element", XmlToTimeSeriesConverter.getContext()).get(0);
    }

    /**
     * Test of createDataFromElement method, of class XmlToTemporalDurationFeatureTimeSeriesConverter.
     */
    @Test
    public void testCreateDataFromElement() {
        System.out.println("createDataFromElement");
        Element e = null;
        XmlToTemporalDurationFeatureTimeSeriesConverter instance = new XmlToTemporalDurationFeatureTimeSeriesConverter();
        TemporalDurationFeature expResult = null;
        TemporalDurationFeature result = instance.createDataFromElement(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTimeSeries method, of class XmlToTemporalDurationFeatureTimeSeriesConverter.
     */
    @Test
    public void testCreateTimeSeries() {
        System.out.println("createTimeSeries");
        UUID id = null;
        UUID generatorID = null;
        UUID generatedFromID = null;
        ArrayList<TemporalDurationFeature> items = null;
        XmlToTemporalDurationFeatureTimeSeriesConverter instance = new XmlToTemporalDurationFeatureTimeSeriesConverter();
        TimeSeries expResult = null;
        TimeSeries result = instance.createTimeSeries(id, generatorID, generatedFromID, items);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

package org.bham.aucom.data.io.xml.in.converter;

import junit.framework.Assert;
import nu.xom.*;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.LinkEnum;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.util.Constants;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.UUID;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class XmlToTimeSeriesConverterTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testHasFaultInducedAttribute() {
        TimeSeries<Observation> tsObs = new XmlToObservationTimeSeriesConverter().convert(getDoc());
        Assert.assertEquals(1, tsObs.getAttributes().size());
    }

    @Test
    public void testHasFaultInducedAttributeWithValue() {
        TimeSeries<Observation> tsObs = new XmlToObservationTimeSeriesConverter().convert(getDoc());
        Assert.assertEquals("123456789", tsObs.getAttributeValue(Constants.FAULT_INDUCED));
    }

    @Test
    public void testElementsHaveAttributes() {
        TimeSeries<Observation> tsObs = new XmlToObservationTimeSeriesConverter().convert(getDoc());
        Assert.assertEquals(2, tsObs.get(0).getAttributes().size());
    }

    @Test
    public void testGetElements() {
        TimeSeries<Observation> tsObs = new XmlToObservationTimeSeriesConverter().convert(getDoc());
        Assert.assertEquals(7, tsObs.size());
    }

    public Document getDoc() {
        String str = "";
        str += "<?xml version=\"1.0\"?>";
        str += "<ts:timeseries id=\"6576bc02-0dd0-4a61-9c94-07363f914ed1\" type=\"obs\" xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\">";
        str += "<ts:attributes xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\">" +
               "<ts:attribute xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\" " + Constants.FAULT_INDUCED + "=\"123456789\"></ts:attribute >" +
               "</ts:attributes>";
        str += "<ts:elements>";
        str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.1\"/><ts:attribute testattribue=\"testvalue\"/></ts:attributes><ts:observation timestamp=\"1826\"><cast eventType=\"OVERWRITE\" generatorType=\"odd\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
        str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.2\"/></ts:attributes><ts:observation timestamp=\"1863\"><cast eventType=\"OVERWRITE\" generatorType=\"even\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
        str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.3\"/></ts:attributes><ts:observation timestamp=\"1883\"><cast eventType=\"OVERWRITE\" generatorType=\"odd\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
        str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.4\"/></ts:attributes><ts:observation timestamp=\"1939\"><cast eventType=\"OVERWRITE\" generatorType=\"even\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
        str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.5\"/></ts:attributes><ts:observation timestamp=\"1973\"><cast eventType=\"OVERWRITE\" generatorType=\"even\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
        str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.6\"/></ts:attributes><ts:observation timestamp=\"1991\"><cast eventType=\"OVERWRITE\" generatorType=\"odd\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
        str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.7\"/></ts:attributes><ts:observation timestamp=\"2039\"><cast eventType=\"OVERWRITE\" generatorType=\"even\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
        str += "</ts:elements>";
        str += "</ts:timeseries>";
        System.out.println(str);
        try {
            return new Builder().build(new StringReader(str));
        } catch (ValidityException e) {
            e.printStackTrace();
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;

    }

    /**
     * Test of getRootElement method, of class XmlToTimeSeriesConverter.
     */
    @Test
    public void testGetRootElement() {
        System.out.println("getRootElement");
        Document timeSeriesDocument = null;
        Element expResult = null;
        Element result = XmlToTimeSeriesConverter.getRootElement(timeSeriesDocument);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convert method, of class XmlToTimeSeriesConverter.
     */
    @Test
    public void testConvert() {
        System.out.println("convert");
        Document timeSeriesDocument = null;
        XmlToTimeSeriesConverter instance = new XmlToTimeSeriesConverterImpl();
        TimeSeries expResult = null;
        TimeSeries result = instance.convert(timeSeriesDocument);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeSeriesAttributes method, of class XmlToTimeSeriesConverter.
     */
    @Test
    public void testGetTimeSeriesAttributes() {
        System.out.println("getTimeSeriesAttributes");
        Document doc = null;
        XmlToTimeSeriesConverter instance = new XmlToTimeSeriesConverterImpl();
        HashMap expResult = null;
        HashMap result = instance.getTimeSeriesAttributes(doc);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createDataFromElement method, of class XmlToTimeSeriesConverter.
     */
    @Test
    public void testCreateDataFromElement() {
        System.out.println("createDataFromElement");
        Element e = null;
        XmlToTimeSeriesConverter instance = new XmlToTimeSeriesConverterImpl();
        AbstractData expResult = null;
        AbstractData result = instance.createDataFromElement(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTimeSeries method, of class XmlToTimeSeriesConverter.
     */
    @Test
    public void testCreateTimeSeries() {
        System.out.println("createTimeSeries");
        UUID id = null;
        UUID generatorID = null;
        UUID generatedFromID = null;
        ArrayList<AbstractData> items = null;
        XmlToTimeSeriesConverter instance = new XmlToTimeSeriesConverterImpl();
        TimeSeries expResult = null;
        TimeSeries result = instance.createTimeSeries(id, generatorID, generatedFromID, items);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGeneratedFromID method, of class XmlToTimeSeriesConverter.
     */
    @Test
    public void testGetGeneratedFromID() {
        System.out.println("getGeneratedFromID");
        Element oElement = null;
        XmlToTimeSeriesConverter instance = new XmlToTimeSeriesConverterImpl();
        UUID expResult = null;
        UUID result = instance.getGeneratedFromID(oElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGeneratorID method, of class XmlToTimeSeriesConverter.
     */
    @Test
    public void testGetGeneratorID() {
        System.out.println("getGeneratorID");
        Element oElement = null;
        XmlToTimeSeriesConverter instance = new XmlToTimeSeriesConverterImpl();
        UUID expResult = null;
        UUID result = instance.getGeneratorID(oElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOwnId method, of class XmlToTimeSeriesConverter.
     */
    @Test
    public void testGetOwnId() {
        System.out.println("getOwnId");
        Element ctsElement = null;
        XmlToTimeSeriesConverter instance = new XmlToTimeSeriesConverterImpl();
        UUID expResult = null;
        UUID result = instance.getOwnId(ctsElement);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class XmlToTimeSeriesConverter.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Element ctsElement = null;
        LinkEnum id = null;
        XmlToTimeSeriesConverter instance = new XmlToTimeSeriesConverterImpl();
        UUID expResult = null;
        UUID result = instance.getId(ctsElement, id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimestamp method, of class XmlToTimeSeriesConverter.
     */
    @Test
    public void testGetTimestamp() {
        System.out.println("getTimestamp");
        Element e = null;
        XmlToTimeSeriesConverter instance = new XmlToTimeSeriesConverterImpl();
        long expResult = 0L;
        long result = instance.getTimestamp(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContext method, of class XmlToTimeSeriesConverter.
     */
    @Test
    public void testGetContext() {
        System.out.println("getContext");
        XPathContext expResult = null;
        XPathContext result = XmlToTimeSeriesConverter.getContext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class XmlToTimeSeriesConverterImpl extends XmlToTimeSeriesConverter<AbstractData> {

        public AbstractData createDataFromElement(Element e) {
            return null;
        }

        public TimeSeries<AbstractData> createTimeSeries(UUID id, UUID generatorID, UUID generatedFromID, ArrayList<AbstractData> items) {
            return null;
        }
    }
}

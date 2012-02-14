/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io.xml.in.converter;

import java.util.ArrayList;
import java.util.UUID;
import nu.xom.Element;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jxv911
 */
public class XmlToObservationTimeSeriesConverterTest {

    public XmlToObservationTimeSeriesConverterTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of createDataFromElement method, of class XmlToObservationTimeSeriesConverter.
     */
    @Test
    public void testCreateDataFromElement() {
        System.out.println("createDataFromElement");
        Element e = null;
        XmlToObservationTimeSeriesConverter instance = new XmlToObservationTimeSeriesConverter();
        Observation expResult = null;
        Observation result = instance.createDataFromElement(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTimeSeries method, of class XmlToObservationTimeSeriesConverter.
     */
    @Test
    public void testCreateTimeSeries() {
        System.out.println("createTimeSeries");
        UUID id = null;
        UUID generatorID = null;
        UUID generatedFromID = null;
        ArrayList<Observation> items = null;
        XmlToObservationTimeSeriesConverter instance = new XmlToObservationTimeSeriesConverter();
        TimeSeries expResult = null;
        TimeSeries result = instance.createTimeSeries(id, generatorID, generatedFromID, items);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getcontentFrom method, of class XmlToObservationTimeSeriesConverter.
     */
    @Test
    public void testGetcontentFrom() {
        System.out.println("getcontentFrom");
        Element e = null;
        XmlToObservationTimeSeriesConverter instance = new XmlToObservationTimeSeriesConverter();
        Element expResult = null;
        Element result = instance.getcontentFrom(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
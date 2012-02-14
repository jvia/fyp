/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io.xml.out.converter;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesType;
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
public class TimeSeriesToXmlDocumentConverterFactoryTest {

    public TimeSeriesToXmlDocumentConverterFactoryTest() {
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
     * Test of getConverter method, of class TimeSeriesToXmlDocumentConverterFactory.
     */
    @Test
    public void testGetConverter_TimeSeriesType() {
        System.out.println("getConverter");
        TimeSeriesType tst = null;
        TimeSeriesToXmlDocumentConverterFactory instance = new TimeSeriesToXmlDocumentConverterFactory();
        TimeSeriesToXmlDocumentConverter expResult = null;
        TimeSeriesToXmlDocumentConverter result = instance.getConverter(tst);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConverter method, of class TimeSeriesToXmlDocumentConverterFactory.
     */
    @Test
    public void testGetConverter_TimeSeries() {
        System.out.println("getConverter");
        TimeSeries<?> ts = null;
        TimeSeriesToXmlDocumentConverterFactory instance = new TimeSeriesToXmlDocumentConverterFactory();
        TimeSeriesToXmlDocumentConverter expResult = null;
        TimeSeriesToXmlDocumentConverter result = instance.getConverter(ts);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConverter method, of class TimeSeriesToXmlDocumentConverterFactory.
     */
    @Test
    public void testGetConverter_AbstractData() {
        System.out.println("getConverter");
        AbstractData data = null;
        TimeSeriesToXmlDocumentConverterFactory instance = new TimeSeriesToXmlDocumentConverterFactory();
        TimeSeriesToXmlDocumentConverter expResult = null;
        TimeSeriesToXmlDocumentConverter result = instance.getConverter(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
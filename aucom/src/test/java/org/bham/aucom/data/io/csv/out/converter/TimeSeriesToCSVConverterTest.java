/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io.csv.out.converter;

import org.bham.aucom.data.AbstractData;
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
public class TimeSeriesToCSVConverterTest {

    public TimeSeriesToCSVConverterTest() {
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
     * Test of convertTimeSeries method, of class TimeSeriesToCSVConverter.
     */
    @Test
    public void testConvertTimeSeries() {
        System.out.println("convertTimeSeries");
        TimeSeries<T> timeSeriesToWrite = null;
        TimeSeriesToCSVConverter instance = new TimeSeriesToCSVConverterImpl();
        String expResult = "";
        String result = instance.convertTimeSeries(timeSeriesToWrite);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertTimeSeriesElement method, of class TimeSeriesToCSVConverter.
     */
    @Test
    public void testConvertTimeSeriesElement() {
        System.out.println("convertTimeSeriesElement");
        AbstractData e = null;
        TimeSeriesToCSVConverter instance = new TimeSeriesToCSVConverterImpl();
        String expResult = "";
        String result = instance.convertTimeSeriesElement(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TimeSeriesToCSVConverterImpl extends TimeSeriesToCSVConverter {

        public String convertTimeSeriesElement(T e) {
            return "";
        }
    }

}
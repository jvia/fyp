/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io;

import java.io.File;
import nu.xom.Document;
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
public class TimeSeriesIOTest {

    public TimeSeriesIOTest() {
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
     * Test of read method, of class TimeSeriesIO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        File file = null;
        TimeSeriesIO instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.read(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getXmlDocumentFromFile method, of class TimeSeriesIO.
     */
    @Test
    public void testGetXmlDocumentFromFile() throws Exception {
        System.out.println("getXmlDocumentFromFile");
        File file = null;
        Document expResult = null;
        Document result = TimeSeriesIO.getXmlDocumentFromFile(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class TimeSeriesIO.
     */
    @Test
    public void testWrite() {
        System.out.println("write");
        TimeSeries<?> timeSeriesTowrite = null;
        File file = null;
        TimeSeriesIO instance = null;
        boolean expResult = false;
        boolean result = instance.write(timeSeriesTowrite, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io.csv.out;

import java.io.File;
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
public class CSVTimeSeriesOutputTest {

    public CSVTimeSeriesOutputTest() {
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
     * Test of write method, of class CSVTimeSeriesOutput.
     */
    @Test
    public void testWrite() {
        System.out.println("write");
        TimeSeries<T> timeSeriesToWrite = null;
        File file = null;
        CSVTimeSeriesOutput instance = new CSVTimeSeriesOutput();
        boolean expResult = false;
        boolean result = instance.write(timeSeriesToWrite, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
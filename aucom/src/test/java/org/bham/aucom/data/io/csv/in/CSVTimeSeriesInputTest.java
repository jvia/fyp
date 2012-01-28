/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io.csv.in;

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
public class CSVTimeSeriesInputTest {

    public CSVTimeSeriesInputTest() {
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
     * Test of read method, of class CSVTimeSeriesInput.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        File file = null;
        CSVTimeSeriesInput instance = new CSVTimeSeriesInput();
        TimeSeries expResult = null;
        TimeSeries result = instance.read(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
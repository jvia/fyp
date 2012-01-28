/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io;

import java.io.File;
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
public class TimeSeriesOutputTest {

    public TimeSeriesOutputTest() {
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
     * Test of write method, of class TimeSeriesOutput.
     */
    @Test
    public void testWrite() {
        System.out.println("write");
        TimeSeries<T> timeSeriesTowrite = null;
        File file = null;
        TimeSeriesOutput instance = new TimeSeriesOutputImpl();
        boolean expResult = false;
        boolean result = instance.write(timeSeriesTowrite, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TimeSeriesOutputImpl implements TimeSeriesOutput {

        public <T extends AbstractData> boolean write(TimeSeries<T> timeSeriesTowrite, File file) {
            return false;
        }
    }

}
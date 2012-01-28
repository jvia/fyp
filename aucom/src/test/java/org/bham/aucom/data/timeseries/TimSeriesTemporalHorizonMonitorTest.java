/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.timeseries;

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
public class TimSeriesTemporalHorizonMonitorTest {

    public TimSeriesTemporalHorizonMonitorTest() {
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
     * Test of trim method, of class TimSeriesTemporalHorizonMonitor.
     */
    @Test
    public void testTrim() {
        System.out.println("trim");
        TimSeriesTemporalHorizonMonitor instance = null;
        instance.trim();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of finalize method, of class TimSeriesTemporalHorizonMonitor.
     */
    @Test
    public void testFinalize() throws Exception {
        System.out.println("finalize");
        TimSeriesTemporalHorizonMonitor instance = null;
        instance.finalize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHorizonInMilliseconds method, of class TimSeriesTemporalHorizonMonitor.
     */
    @Test
    public void testSetHorizonInMilliseconds() {
        System.out.println("setHorizonInMilliseconds");
        long horizonInMilliseconds = 0L;
        TimSeriesTemporalHorizonMonitor instance = null;
        instance.setHorizonInMilliseconds(horizonInMilliseconds);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHorizonInMilliseconds method, of class TimSeriesTemporalHorizonMonitor.
     */
    @Test
    public void testGetHorizonInMilliseconds() {
        System.out.println("getHorizonInMilliseconds");
        TimSeriesTemporalHorizonMonitor instance = null;
        long expResult = 0L;
        long result = instance.getHorizonInMilliseconds();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
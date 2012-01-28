/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.sink;

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
public class TimeSeriesSinkTest {

    public TimeSeriesSinkTest() {
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
     * Test of pushItem method, of class TimeSeriesSink.
     */
    @Test
    public void testPushItem() throws Exception {
        System.out.println("pushItem");
        AbstractData arg = null;
        TimeSeriesSink instance = new TimeSeriesSink();
        instance.pushItem(arg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setOutput method, of class TimeSeriesSink.
     */
    @Test
    public void testSetOutput() {
        System.out.println("setOutput");
//        TimeSeries<TIn> data = null;
//        TimeSeriesSink instance = new TimeSeriesSink();
//        instance.setOutput(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutput method, of class TimeSeriesSink.
     */
    @Test
    public void testGetOutput() {
        System.out.println("getOutput");
        TimeSeriesSink instance = new TimeSeriesSink();
        TimeSeries expResult = null;
        TimeSeries result = instance.getOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
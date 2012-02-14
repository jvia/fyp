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
public class TimeSeriesStatusListenerTest {

    public TimeSeriesStatusListenerTest() {
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
     * Test of timeseriesStatusChanged method, of class TimeSeriesStatusListener.
     */
    @Test
    public void testTimeseriesStatusChanged() {
        System.out.println("timeseriesStatusChanged");
        TimeseriesStatusEvent status = null;
        TimeSeriesStatusListener instance = new TimeSeriesStatusListenerImpl();
        instance.timeseriesStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TimeSeriesStatusListenerImpl implements TimeSeriesStatusListener {

        public void timeseriesStatusChanged(TimeseriesStatusEvent status) {
        }
    }

}
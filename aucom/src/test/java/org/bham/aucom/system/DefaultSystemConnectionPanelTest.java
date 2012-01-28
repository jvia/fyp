/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.system;

import org.bham.aucom.data.timeseries.TimeseriesStatusEvent;
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
public class DefaultSystemConnectionPanelTest {

    public DefaultSystemConnectionPanelTest() {
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
     * Test of handleSystemConnectionEvent method, of class DefaultSystemConnectionPanel.
     */
    @Test
    public void testHandleSystemConnectionEvent() {
        System.out.println("handleSystemConnectionEvent");
        SystemConnectionStatusChangedEvent event = null;
        DefaultSystemConnectionPanel instance = null;
        instance.handleSystemConnectionEvent(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of timeseriesStatusChanged method, of class DefaultSystemConnectionPanel.
     */
    @Test
    public void testTimeseriesStatusChanged() {
        System.out.println("timeseriesStatusChanged");
        TimeseriesStatusEvent status = null;
        DefaultSystemConnectionPanel instance = null;
        instance.timeseriesStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetCounter method, of class DefaultSystemConnectionPanel.
     */
    @Test
    public void testResetCounter() {
        System.out.println("resetCounter");
        DefaultSystemConnectionPanel instance = null;
        instance.resetCounter();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isReady method, of class DefaultSystemConnectionPanel.
     */
    @Test
    public void testIsReady() {
        System.out.println("isReady");
        DefaultSystemConnectionPanel instance = null;
        boolean expResult = false;
        boolean result = instance.isReady();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
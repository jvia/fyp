/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeseriesStatusEvent;
import org.bham.aucom.gui.ClassificatorOptimizationDataCollector.ClOpDataCollectorStatus;
import org.bham.aucom.gui.ClassificatorOptimizationDataCollector.ClOpDataCollectorStatusListener;
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
public class ClassificatorOptimizationDataCollectorTest {

    public ClassificatorOptimizationDataCollectorTest() {
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
     * Test of start method, of class ClassificatorOptimizationDataCollector.
     */
    @Test
    public void testStart() {
        System.out.println("start");
        TimeSeries<? extends Score> inTimseriesToCollectFrom = null;
        ClassificatorOptimizationDataCollector instance = new ClassificatorOptimizationDataCollector();
        instance.start(inTimseriesToCollectFrom);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class ClassificatorOptimizationDataCollector.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        ClassificatorOptimizationDataCollector instance = new ClassificatorOptimizationDataCollector();
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStatusListener method, of class ClassificatorOptimizationDataCollector.
     */
    @Test
    public void testAddStatusListener() {
        System.out.println("addStatusListener");
        ClOpDataCollectorStatusListener listener = null;
        ClassificatorOptimizationDataCollector instance = new ClassificatorOptimizationDataCollector();
        instance.addStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTimeseriesStatusListener method, of class ClassificatorOptimizationDataCollector.
     */
    @Test
    public void testRemoveTimeseriesStatusListener() {
        System.out.println("removeTimeseriesStatusListener");
        ClOpDataCollectorStatusListener listener = null;
        ClassificatorOptimizationDataCollector instance = new ClassificatorOptimizationDataCollector();
        instance.removeTimeseriesStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireTimeseriesStatusChangedEvent method, of class ClassificatorOptimizationDataCollector.
     */
    @Test
    public void testFireTimeseriesStatusChangedEvent() {
        System.out.println("fireTimeseriesStatusChangedEvent");
        ClOpDataCollectorStatus evt = null;
        ClassificatorOptimizationDataCollector instance = new ClassificatorOptimizationDataCollector();
        instance.fireTimeseriesStatusChangedEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of timeseriesStatusChanged method, of class ClassificatorOptimizationDataCollector.
     */
    @Test
    public void testTimeseriesStatusChanged() {
        System.out.println("timeseriesStatusChanged");
        TimeseriesStatusEvent status = null;
        ClassificatorOptimizationDataCollector instance = new ClassificatorOptimizationDataCollector();
        instance.timeseriesStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCollectedData method, of class ClassificatorOptimizationDataCollector.
     */
    @Test
    public void testGetCollectedData() {
        System.out.println("getCollectedData");
        ClassificatorOptimizationDataCollector instance = new ClassificatorOptimizationDataCollector();
        TimeSeries expResult = null;
        TimeSeries result = instance.getCollectedData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isReady method, of class ClassificatorOptimizationDataCollector.
     */
    @Test
    public void testIsReady() {
        System.out.println("isReady");
        ClassificatorOptimizationDataCollector instance = new ClassificatorOptimizationDataCollector();
        boolean expResult = false;
        boolean result = instance.isReady();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
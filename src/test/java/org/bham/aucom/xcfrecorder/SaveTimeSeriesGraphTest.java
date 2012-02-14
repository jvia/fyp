/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.xcfrecorder;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
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
public class SaveTimeSeriesGraphTest {

    public SaveTimeSeriesGraphTest() {
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
     * Test of initGraph method, of class SaveTimeSeriesGraph.
     */
    @Test
    public void testInitGraph() {
        System.out.println("initGraph");
        SaveTimeSeriesGraph instance = null;
        instance.initGraph();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberRecordedEvents method, of class SaveTimeSeriesGraph.
     */
    @Test
    public void testGetNumberRecordedEvents() {
        System.out.println("getNumberRecordedEvents");
        SaveTimeSeriesGraph instance = null;
        int expResult = 0;
        int result = instance.getNumberRecordedEvents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stopGraph method, of class SaveTimeSeriesGraph.
     */
    @Test
    public void testStopGraph() {
        System.out.println("stopGraph");
        SaveTimeSeriesGraph instance = null;
        instance.stopGraph();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of timeseriesStatusChanged method, of class SaveTimeSeriesGraph.
     */
    @Test
    public void testTimeseriesStatusChanged() {
        System.out.println("timeseriesStatusChanged");
        TimeseriesStatusEvent status = null;
        SaveTimeSeriesGraph instance = null;
        instance.timeseriesStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanUp method, of class SaveTimeSeriesGraph.
     */
    @Test
    public void testCleanUp() {
        System.out.println("cleanUp");
        SaveTimeSeriesGraph instance = null;
        instance.cleanUp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preconditionsSatisfied method, of class SaveTimeSeriesGraph.
     */
    @Test
    public void testPreconditionsSatisfied() {
        System.out.println("preconditionsSatisfied");
        SaveTimeSeriesGraph instance = null;
        boolean expResult = false;
        boolean result = instance.preconditionsSatisfied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReason method, of class SaveTimeSeriesGraph.
     */
    @Test
    public void testGetReason() {
        System.out.println("getReason");
        SaveTimeSeriesGraph instance = null;
        String expResult = "";
        String result = instance.getReason();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeSeriesToSave method, of class SaveTimeSeriesGraph.
     */
    @Test
    public void testGetTimeSeriesToSave() {
        System.out.println("getTimeSeriesToSave");
        SaveTimeSeriesGraph instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.getTimeSeriesToSave();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimeSeriesToSave method, of class SaveTimeSeriesGraph.
     */
    @Test
    public void testSetTimeSeriesToSave() {
        System.out.println("setTimeSeriesToSave");
        TimeSeries<Observation> timeSeriesToSave = null;
        SaveTimeSeriesGraph instance = null;
        instance.setTimeSeriesToSave(timeSeriesToSave);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
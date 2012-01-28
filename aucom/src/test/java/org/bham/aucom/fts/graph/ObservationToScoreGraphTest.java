/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.graph;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.Model;
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
public class ObservationToScoreGraphTest {

    public ObservationToScoreGraphTest() {
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
     * Test of initGraph method, of class ObservationToScoreGraph.
     */
    @Test
    public void testInitGraph() {
        System.out.println("initGraph");
        ObservationToScoreGraph instance = new ObservationToScoreGraph();
        instance.initGraph();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReason method, of class ObservationToScoreGraph.
     */
    @Test
    public void testGetReason() {
        System.out.println("getReason");
        ObservationToScoreGraph instance = new ObservationToScoreGraph();
        String expResult = "";
        String result = instance.getReason();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preconditionsSatisfied method, of class ObservationToScoreGraph.
     */
    @Test
    public void testPreconditionsSatisfied() {
        System.out.println("preconditionsSatisfied");
        ObservationToScoreGraph instance = new ObservationToScoreGraph();
        boolean expResult = false;
        boolean result = instance.preconditionsSatisfied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanUp method, of class ObservationToScoreGraph.
     */
    @Test
    public void testCleanUp() {
        System.out.println("cleanUp");
        ObservationToScoreGraph instance = new ObservationToScoreGraph();
        instance.cleanUp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInput method, of class ObservationToScoreGraph.
     */
    @Test
    public void testSetInput() {
        System.out.println("setInput");
        TimeSeries<Observation> ts = null;
        ObservationToScoreGraph instance = new ObservationToScoreGraph();
        instance.setInput(ts);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutput method, of class ObservationToScoreGraph.
     */
    @Test
    public void testGetOutput() {
        System.out.println("getOutput");
        ObservationToScoreGraph instance = new ObservationToScoreGraph();
        TimeSeries expResult = null;
        TimeSeries result = instance.getOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModel method, of class ObservationToScoreGraph.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        Model inModel = null;
        ObservationToScoreGraph instance = new ObservationToScoreGraph();
        instance.setModel(inModel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
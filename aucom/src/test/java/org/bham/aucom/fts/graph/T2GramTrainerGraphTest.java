/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.graph;

import org.bham.aucom.data.Observation;
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
public class T2GramTrainerGraphTest {

    public T2GramTrainerGraphTest() {
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
     * Test of initGraph method, of class T2GramTrainerGraph.
     */
    @Test
    public void testInitGraph() {
        System.out.println("initGraph");
        T2GramTrainerGraph instance = new T2GramTrainerGraph();
        instance.initGraph();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInput method, of class T2GramTrainerGraph.
     */
    @Test
    public void testSetInput() {
        System.out.println("setInput");
        TimeSeries<Observation> trainingData = null;
        T2GramTrainerGraph instance = new T2GramTrainerGraph();
        instance.setInput(trainingData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInput method, of class T2GramTrainerGraph.
     */
    @Test
    public void testGetInput() {
        System.out.println("getInput");
        T2GramTrainerGraph instance = new T2GramTrainerGraph();
        TimeSeries expResult = null;
        TimeSeries result = instance.getInput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class T2GramTrainerGraph.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        T2GramTrainerGraph instance = new T2GramTrainerGraph();
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanUp method, of class T2GramTrainerGraph.
     */
    @Test
    public void testCleanUp() {
        System.out.println("cleanUp");
        T2GramTrainerGraph instance = new T2GramTrainerGraph();
        instance.cleanUp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutput method, of class T2GramTrainerGraph.
     */
    @Test
    public void testGetOutput() {
        System.out.println("getOutput");
        T2GramTrainerGraph instance = new T2GramTrainerGraph();
        TimeSeries expResult = null;
        TimeSeries result = instance.getOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preconditionsSatisfied method, of class T2GramTrainerGraph.
     */
    @Test
    public void testPreconditionsSatisfied() {
        System.out.println("preconditionsSatisfied");
        T2GramTrainerGraph instance = new T2GramTrainerGraph();
        boolean expResult = false;
        boolean result = instance.preconditionsSatisfied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReason method, of class T2GramTrainerGraph.
     */
    @Test
    public void testGetReason() {
        System.out.println("getReason");
        T2GramTrainerGraph instance = new T2GramTrainerGraph();
        String expResult = "";
        String result = instance.getReason();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.visualizer;

import java.util.concurrent.LinkedBlockingQueue;
import org.bham.aucom.data.Classification;
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
public class T2GramVisualizerGraphTest {

    public T2GramVisualizerGraphTest() {
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
     * Test of initGraph method, of class T2GramVisualizerGraph.
     */
    @Test
    public void testInitGraph() {
        System.out.println("initGraph");
        T2GramVisualizerGraph instance = null;
        instance.initGraph();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInput method, of class T2GramVisualizerGraph.
     */
    @Test
    public void testSetInput() {
        System.out.println("setInput");
        TimeSeries<Classification> newInput = null;
        T2GramVisualizerGraph instance = null;
        instance.setInput(newInput);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReason method, of class T2GramVisualizerGraph.
     */
    @Test
    public void testGetReason() {
        System.out.println("getReason");
        T2GramVisualizerGraph instance = null;
        String expResult = "";
        String result = instance.getReason();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preconditionsSatisfied method, of class T2GramVisualizerGraph.
     */
    @Test
    public void testPreconditionsSatisfied() {
        System.out.println("preconditionsSatisfied");
        T2GramVisualizerGraph instance = null;
        boolean expResult = false;
        boolean result = instance.preconditionsSatisfied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanUp method, of class T2GramVisualizerGraph.
     */
    @Test
    public void testCleanUp() {
        System.out.println("cleanUp");
        T2GramVisualizerGraph instance = null;
        instance.cleanUp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOuput method, of class T2GramVisualizerGraph.
     */
    @Test
    public void testGetOuput() {
        System.out.println("getOuput");
        T2GramVisualizerGraph instance = null;
        LinkedBlockingQueue expResult = null;
        LinkedBlockingQueue result = instance.getOuput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
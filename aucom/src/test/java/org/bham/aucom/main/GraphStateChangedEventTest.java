/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.main;

import org.bham.aucom.fts.graph.AbstractAucomGraph.GraphStatus;
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
public class GraphStateChangedEventTest {

    public GraphStateChangedEventTest() {
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
     * Test of getPreviousState method, of class GraphStateChangedEvent.
     */
    @Test
    public void testGetPreviousState() {
        System.out.println("getPreviousState");
        GraphStateChangedEvent instance = null;
        GraphStatus expResult = null;
        GraphStatus result = instance.getPreviousState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNewState method, of class GraphStateChangedEvent.
     */
    @Test
    public void testGetNewState() {
        System.out.println("getNewState");
        GraphStateChangedEvent instance = null;
        GraphStatus expResult = null;
        GraphStatus result = instance.getNewState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class GraphStateChangedEvent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        GraphStateChangedEvent instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
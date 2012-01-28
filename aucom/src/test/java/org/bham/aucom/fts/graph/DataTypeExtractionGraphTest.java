/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.graph;

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
public class DataTypeExtractionGraphTest {

    public DataTypeExtractionGraphTest() {
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
     * Test of initGraph method, of class DataTypeExtractionGraph.
     */
    @Test
    public void testInitGraph() {
        System.out.println("initGraph");
        DataTypeExtractionGraph instance = new DataTypeExtractionGraph();
        instance.initGraph();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanUp method, of class DataTypeExtractionGraph.
     */
    @Test
    public void testCleanUp() {
        System.out.println("cleanUp");
        DataTypeExtractionGraph instance = new DataTypeExtractionGraph();
        instance.cleanUp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preconditionsSatisfied method, of class DataTypeExtractionGraph.
     */
    @Test
    public void testPreconditionsSatisfied() {
        System.out.println("preconditionsSatisfied");
        DataTypeExtractionGraph instance = new DataTypeExtractionGraph();
        boolean expResult = false;
        boolean result = instance.preconditionsSatisfied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReason method, of class DataTypeExtractionGraph.
     */
    @Test
    public void testGetReason() {
        System.out.println("getReason");
        DataTypeExtractionGraph instance = new DataTypeExtractionGraph();
        String expResult = "";
        String result = instance.getReason();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
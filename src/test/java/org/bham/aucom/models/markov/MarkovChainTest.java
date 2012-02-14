/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.models.markov;

import java.util.ArrayList;
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
public class MarkovChainTest {

    public MarkovChainTest() {
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
     * Test of put method, of class MarkovChain.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        int classId = 0;
        MarkovChain instance = new MarkovChain();
        instance.put(classId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInitialProbability method, of class MarkovChain.
     */
    @Test
    public void testGetInitialProbability() {
        System.out.println("getInitialProbability");
        int classId = 0;
        MarkovChain instance = new MarkovChain();
        double expResult = 0.0;
        double result = instance.getInitialProbability(classId);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTransitionProbability method, of class MarkovChain.
     */
    @Test
    public void testGetTransitionProbability() {
        System.out.println("getTransitionProbability");
        int fromt = 0;
        int to = 0;
        MarkovChain instance = new MarkovChain();
        double expResult = 0.0;
        double result = instance.getTransitionProbability(fromt, to);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbability method, of class MarkovChain.
     */
    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        ArrayList<Integer> classIds = null;
        MarkovChain instance = new MarkovChain();
        double expResult = 0.0;
        double result = instance.getProbability(classIds);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
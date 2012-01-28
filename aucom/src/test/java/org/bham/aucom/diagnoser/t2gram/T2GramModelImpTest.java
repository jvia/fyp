/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram;

import java.util.Collection;
import org.bham.aucom.util.HashMatrix;
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
public class T2GramModelImpTest {

    public T2GramModelImpTest() {
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
     * Test of getDimensions method, of class T2GramModelImp.
     */
    @Test
    public void testGetDimensions() {
        System.out.println("getDimensions");
        T2GramModelImp instance = null;
        Collection expResult = null;
        Collection result = instance.getDimensions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberDistirbutions method, of class T2GramModelImp.
     */
    @Test
    public void testGetNumberDistirbutions() {
        System.out.println("getNumberDistirbutions");
        T2GramModelImp instance = null;
        int expResult = 0;
        int result = instance.getNumberDistirbutions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasDistributionFor method, of class T2GramModelImp.
     */
    @Test
    public void testHasDistributionFor() {
        System.out.println("hasDistributionFor");
        Integer from = null;
        Integer to = null;
        T2GramModelImp instance = null;
        boolean expResult = false;
        boolean result = instance.hasDistributionFor(from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistributionFor method, of class T2GramModelImp.
     */
    @Test
    public void testGetDistributionFor() {
        System.out.println("getDistributionFor");
        Integer from = null;
        Integer to = null;
        T2GramModelImp instance = null;
        ProbabilityDistribution expResult = null;
        ProbabilityDistribution result = instance.getDistributionFor(from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEntropyOfDistribution method, of class T2GramModelImp.
     */
    @Test
    public void testGetEntropyOfDistribution() {
        System.out.println("getEntropyOfDistribution");
        int indexOne = 0;
        int indexTwo = 0;
        T2GramModelImp instance = null;
        double expResult = 0.0;
        double result = instance.getEntropyOfDistribution(indexOne, indexTwo);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxProbabilityFor method, of class T2GramModelImp.
     */
    @Test
    public void testGetMaxProbabilityFor() {
        System.out.println("getMaxProbabilityFor");
        int from = 0;
        int to = 0;
        T2GramModelImp instance = null;
        double expResult = 0.0;
        double result = instance.getMaxProbabilityFor(from, to);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbability method, of class T2GramModelImp.
     */
    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        int from = 0;
        int to = 0;
        long timespan = 0L;
        T2GramModelImp instance = null;
        double expResult = 0.0;
        double result = instance.getProbability(from, to, timespan);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDistribution method, of class T2GramModelImp.
     */
    @Test
    public void testAddDistribution() {
        System.out.println("addDistribution");
        Integer from = null;
        Integer to = null;
        ProbabilityDistribution dist = null;
        T2GramModelImp instance = null;
        instance.addDistribution(from, to, dist);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class T2GramModelImp.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        T2GramModelImp instance = null;
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isTrained method, of class T2GramModelImp.
     */
    @Test
    public void testIsTrained() {
        System.out.println("isTrained");
        T2GramModelImp instance = null;
        boolean expResult = false;
        boolean result = instance.isTrained();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTransitionMatrix method, of class T2GramModelImp.
     */
    @Test
    public void testGetTransitionMatrix() {
        System.out.println("getTransitionMatrix");
        T2GramModelImp instance = null;
        HashMatrix expResult = null;
        HashMatrix result = instance.getTransitionMatrix();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class T2GramModelImp.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        T2GramModelImp instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class T2GramModelImp.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        T2GramModelImp instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class T2GramModelImp.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        T2GramModelImp instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistributionFactory method, of class T2GramModelImp.
     */
    @Test
    public void testGetDistributionFactory() {
        System.out.println("getDistributionFactory");
        T2GramModelImp instance = null;
        ProbabilityFactory expResult = null;
        ProbabilityFactory result = instance.getDistributionFactory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addModelListener method, of class T2GramModelImp.
     */
    @Test
    public void testAddModelListener() {
        System.out.println("addModelListener");
        T2GramModelImp instance = null;
        instance.addModelListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeModelListener method, of class T2GramModelImp.
     */
    @Test
    public void testRemoveModelListener() {
        System.out.println("removeModelListener");
        T2GramModelImp instance = null;
        instance.removeModelListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
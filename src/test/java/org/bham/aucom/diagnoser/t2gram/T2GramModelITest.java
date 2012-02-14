/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram;

import org.bham.aucom.util.HashMatrix;
import org.junit.*;

import java.util.Collection;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author jxv911
 */
public class T2GramModelITest {

    public T2GramModelITest() {
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
     * Test of getDimensions method, of class T2GramModelI.
     */
    @Test
    public void testGetDimensions() {
        System.out.println("getDimensions");
        T2GramModelI instance = new T2GramModelIImpl();
        Collection expResult = null;
        Collection result = instance.getDimensions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistributionFactory method, of class T2GramModelI.
     */
    @Test
    public void testGetDistributionFactory() {
        System.out.println("getDistributionFactory");
        T2GramModelI instance = new T2GramModelIImpl();
        ProbabilityFactory expResult = null;
        ProbabilityFactory result = instance.getDistributionFactory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberDistirbutions method, of class T2GramModelI.
     */
    @Test
    public void testGetNumberDistirbutions() {
        System.out.println("getNumberDistirbutions");
        T2GramModelI instance = new T2GramModelIImpl();
        int expResult = 0;
        int result = instance.getNumberDistirbutions();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasDistributionFor method, of class T2GramModelI.
     */
    @Test
    public void testHasDistributionFor() {
        System.out.println("hasDistributionFor");
        Integer from = null;
        Integer to = null;
        T2GramModelI instance = new T2GramModelIImpl();
        boolean expResult = false;
        boolean result = instance.hasDistributionFor(from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistributionFor method, of class T2GramModelI.
     */
    @Test
    public void testGetDistributionFor() {
        System.out.println("getDistributionFor");
        Integer from = null;
        Integer to = null;
        T2GramModelI instance = new T2GramModelIImpl();
        ProbabilityDistribution expResult = null;
        ProbabilityDistribution result = instance.getDistributionFor(from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEntropyOfDistribution method, of class T2GramModelI.
     */
    @Test
    public void testGetEntropyOfDistribution() {
        System.out.println("getEntropyOfDistribution");
        int indexOne = 0;
        int indexTwo = 0;
        T2GramModelI instance = new T2GramModelIImpl();
        double expResult = 0.0;
        double result = instance.getEntropyOfDistribution(indexOne, indexTwo);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxProbabilityFor method, of class T2GramModelI.
     */
    @Test
    public void testGetMaxProbabilityFor() {
        System.out.println("getMaxProbabilityFor");
        int from = 0;
        int to = 0;
        T2GramModelI instance = new T2GramModelIImpl();
        double expResult = 0.0;
        double result = instance.getMaxProbabilityFor(from, to);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbability method, of class T2GramModelI.
     */
    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        int from = 0;
        int to = 0;
        long timespan = 0L;
        T2GramModelI instance = new T2GramModelIImpl();
        double expResult = 0.0;
        double result = instance.getProbability(from, to, timespan);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDistribution method, of class T2GramModelI.
     */
    @Test
    public void testAddDistribution() {
        System.out.println("addDistribution");
        Integer from = null;
        Integer to = null;
        ProbabilityDistribution dist = null;
        T2GramModelI instance = new T2GramModelIImpl();
        instance.addDistribution(from, to, dist);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class T2GramModelI.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        T2GramModelI instance = new T2GramModelIImpl();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTransitionMatrix method, of class T2GramModelI.
     */
    @Test
    public void testGetTransitionMatrix() {
        System.out.println("getTransitionMatrix");
        T2GramModelI instance = new T2GramModelIImpl();
        HashMatrix expResult = null;
        HashMatrix result = instance.getTransitionMatrix();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class T2GramModelIImpl implements T2GramModelI {

        public Collection<Integer> getDimensions() {
            return null;
        }

        public ProbabilityFactory getDistributionFactory() {
            return null;
        }

        public int getNumberDistirbutions() {
            return 0;
        }

        public boolean hasDistributionFor(Integer from, Integer to) {
            return false;
        }

        public ProbabilityDistribution getDistributionFor(Integer from, Integer to) {
            return null;
        }

        public double getEntropyOfDistribution(int indexOne, int indexTwo) {
            return 0.0;
        }

        public double getMaxProbabilityFor(int from, int to) {
            return 0.0;
        }

        public double getProbability(int from, int to, long timespan) {
            return 0.0;
        }

        public void addDistribution(Integer from, Integer to, ProbabilityDistribution dist) {
        }

        public int size() {
            return 0;
        }

        public HashMatrix<Integer, Integer, ProbabilityDistribution> getTransitionMatrix() {
            return null;
        }

        @Override
        public boolean isEmpty() {
            return size() == 0;
        }

        @Override
        public boolean isTrained() {
            throw new UnsupportedOperationException("isTrained() not implemented yet");
        }

        @Override
        public String getName() {
            throw new UnsupportedOperationException("getName() not implemented yet");
        }

        @Override
        public UUID getId() {
            throw new UnsupportedOperationException("getId() not implemented yet");
        }

        @Override
        public void addModelListener() {
            throw new UnsupportedOperationException("addModelListener() not implemented yet");
        }

        @Override
        public void removeModelListener() {
            throw new UnsupportedOperationException("removeModelListener() not implemented yet");
        }
    }

}
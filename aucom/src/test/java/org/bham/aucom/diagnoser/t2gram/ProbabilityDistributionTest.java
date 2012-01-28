/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram;

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
public class ProbabilityDistributionTest {

    public ProbabilityDistributionTest() {
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
     * Test of getProbability method, of class ProbabilityDistribution.
     */
    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        double val = 0.0;
        ProbabilityDistribution instance = new ProbabilityDistributionImpl();
        double expResult = 0.0;
        double result = instance.getProbability(val);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxProbability method, of class ProbabilityDistribution.
     */
    @Test
    public void testGetMaxProbability() {
        System.out.println("getMaxProbability");
        ProbabilityDistribution instance = new ProbabilityDistributionImpl();
        double expResult = 0.0;
        double result = instance.getMaxProbability();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCount method, of class ProbabilityDistribution.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        ProbabilityDistribution instance = new ProbabilityDistributionImpl();
        int expResult = 0;
        int result = instance.getCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class ProbabilityDistribution.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        ProbabilityDistribution instance = new ProbabilityDistributionImpl();
        String expResult = "";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEntropy method, of class ProbabilityDistribution.
     */
    @Test
    public void testGetEntropy() {
        System.out.println("getEntropy");
        ProbabilityDistribution instance = new ProbabilityDistributionImpl();
        double expResult = 0.0;
        double result = instance.getEntropy();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sampleProbability method, of class ProbabilityDistribution.
     */
    @Test
    public void testSampleProbability() {
        System.out.println("sampleProbability");
        ProbabilityDistribution instance = new ProbabilityDistributionImpl();
        double[] expResult = null;
        double[] result = instance.sampleProbability();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sample method, of class ProbabilityDistribution.
     */
    @Test
    public void testSample() {
        System.out.println("sample");
        ProbabilityDistribution instance = new ProbabilityDistributionImpl();
        double[] expResult = null;
        double[] result = instance.sample();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of variance method, of class ProbabilityDistribution.
     */
    @Test
    public void testVariance() {
        System.out.println("variance");
        ProbabilityDistribution instance = new ProbabilityDistributionImpl();
        double expResult = 0.0;
        double result = instance.variance();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of expectedValue method, of class ProbabilityDistribution.
     */
    @Test
    public void testExpectedValue() {
        System.out.println("expectedValue");
        ProbabilityDistribution instance = new ProbabilityDistributionImpl();
        double expResult = 0.0;
        double result = instance.expectedValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ProbabilityDistribution.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        double[] val = null;
        ProbabilityDistribution instance = new ProbabilityDistributionImpl();
        instance.update(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ProbabilityDistributionImpl implements ProbabilityDistribution {

        public double getProbability(double val) {
            return 0.0;
        }

        public double getMaxProbability() {
            return 0.0;
        }

        public int getCount() {
            return 0;
        }

        public String getType() {
            return "";
        }

        public double getEntropy() {
            return 0.0;
        }

        public double[] sampleProbability() {
            return null;
        }

        public double[] sample() {
            return null;
        }

        public double variance() {
            return 0.0;
        }

        public double expectedValue() {
            return 0.0;
        }

        public void update(double[] val) {
        }
    }

}
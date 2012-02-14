/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.models.probability;

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
public class KDELogProbabilityDistributionTest {

    public KDELogProbabilityDistributionTest() {
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
     * Test of update method, of class KDELogProbabilityDistribution.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        double[] val = null;
        KDELogProbabilityDistribution instance = null;
        instance.update(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLogValue method, of class KDELogProbabilityDistribution.
     */
    @Test
    public void testGetLogValue() {
        System.out.println("getLogValue");
        double value = 0.0;
        KDELogProbabilityDistribution instance = null;
        double expResult = 0.0;
        double result = instance.getLogValue(value);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbability method, of class KDELogProbabilityDistribution.
     */
    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        double value = 0.0;
        KDELogProbabilityDistribution instance = null;
        double expResult = 0.0;
        double result = instance.getProbability(value);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
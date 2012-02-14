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
public class LinearBinCalculatorTest {

    public LinearBinCalculatorTest() {
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
     * Test of calculateBin method, of class LinearBinCalculator.
     */
    @Test
    public void testCalculateBin() {
        System.out.println("calculateBin");
        double value = 0.0;
        LinearBinCalculator instance = null;
        int expResult = 0;
        int result = instance.calculateBin(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIntervalSize method, of class LinearBinCalculator.
     */
    @Test
    public void testSetIntervalSize() {
        System.out.println("setIntervalSize");
        double intervalSize = 0.0;
        LinearBinCalculator instance = null;
        instance.setIntervalSize(intervalSize);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIntervalSize method, of class LinearBinCalculator.
     */
    @Test
    public void testGetIntervalSize() {
        System.out.println("getIntervalSize");
        LinearBinCalculator instance = null;
        double expResult = 0.0;
        double result = instance.getIntervalSize();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHighBoundary method, of class LinearBinCalculator.
     */
    @Test
    public void testGetHighBoundary() {
        System.out.println("getHighBoundary");
        double value = 0.0;
        LinearBinCalculator instance = null;
        double expResult = 0.0;
        double result = instance.getHighBoundary(value);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLowBoundary method, of class LinearBinCalculator.
     */
    @Test
    public void testGetLowBoundary() {
        System.out.println("getLowBoundary");
        double value = 0.0;
        LinearBinCalculator instance = null;
        double expResult = 0.0;
        double result = instance.getLowBoundary(value);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
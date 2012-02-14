/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

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
public class BinCaluclatorTest {

    public BinCaluclatorTest() {
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
     * Test of calculateBin method, of class BinCaluclator.
     */
    @Test
    public void testCalculateBin() {
        System.out.println("calculateBin");
        double value = 0.0;
        BinCaluclator instance = new BinCaluclatorImpl();
        int expResult = 0;
        int result = instance.calculateBin(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLowBoundary method, of class BinCaluclator.
     */
    @Test
    public void testGetLowBoundary() {
        System.out.println("getLowBoundary");
        double value = 0.0;
        BinCaluclator instance = new BinCaluclatorImpl();
        double expResult = 0.0;
        double result = instance.getLowBoundary(value);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHighBoundary method, of class BinCaluclator.
     */
    @Test
    public void testGetHighBoundary() {
        System.out.println("getHighBoundary");
        double value = 0.0;
        BinCaluclator instance = new BinCaluclatorImpl();
        double expResult = 0.0;
        double result = instance.getHighBoundary(value);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class BinCaluclatorImpl implements BinCaluclator {

        public int calculateBin(double value) {
            return 0;
        }

        public double getLowBoundary(double value) {
            return 0.0;
        }

        public double getHighBoundary(double value) {
            return 0.0;
        }
    }

}
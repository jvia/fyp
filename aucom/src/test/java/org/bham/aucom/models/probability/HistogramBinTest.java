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
public class HistogramBinTest {

    public HistogramBinTest() {
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
     * Test of getValue method, of class HistogramBin.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        HistogramBin instance = null;
        double expResult = 0.0;
        double result = instance.getValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of increase method, of class HistogramBin.
     */
    @Test
    public void testIncrease() {
        System.out.println("increase");
        HistogramBin instance = null;
        instance.increase();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class HistogramBin.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        HistogramBin instance = null;
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBorders method, of class HistogramBin.
     */
    @Test
    public void testSetBorders() {
        System.out.println("setBorders");
        double inLowBorder = 0.0;
        double inHighBorder = 0.0;
        HistogramBin instance = null;
        instance.setBorders(inLowBorder, inHighBorder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBinLabel method, of class HistogramBin.
     */
    @Test
    public void testSetBinLabel() {
        System.out.println("setBinLabel");
        String binLabel = "";
        HistogramBin instance = null;
        instance.setBinLabel(binLabel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBinLabel method, of class HistogramBin.
     */
    @Test
    public void testGetBinLabel() {
        System.out.println("getBinLabel");
        HistogramBin instance = null;
        String expResult = "";
        String result = instance.getBinLabel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBinNumber method, of class HistogramBin.
     */
    @Test
    public void testSetBinNumber() {
        System.out.println("setBinNumber");
        int binNumber = 0;
        HistogramBin instance = null;
        instance.setBinNumber(binNumber);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBinNumber method, of class HistogramBin.
     */
    @Test
    public void testGetBinNumber() {
        System.out.println("getBinNumber");
        HistogramBin instance = null;
        int expResult = 0;
        int result = instance.getBinNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class HistogramBin.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        HistogramBin instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLowBorder method, of class HistogramBin.
     */
    @Test
    public void testSetLowBorder() {
        System.out.println("setLowBorder");
        double lowBorder = 0.0;
        HistogramBin instance = null;
        instance.setLowBorder(lowBorder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLowBorder method, of class HistogramBin.
     */
    @Test
    public void testGetLowBorder() {
        System.out.println("getLowBorder");
        HistogramBin instance = null;
        double expResult = 0.0;
        double result = instance.getLowBorder();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setHighBorder method, of class HistogramBin.
     */
    @Test
    public void testSetHighBorder() {
        System.out.println("setHighBorder");
        double highBorder = 0.0;
        HistogramBin instance = null;
        instance.setHighBorder(highBorder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHighBorder method, of class HistogramBin.
     */
    @Test
    public void testGetHighBorder() {
        System.out.println("getHighBorder");
        HistogramBin instance = null;
        double expResult = 0.0;
        double result = instance.getHighBorder();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
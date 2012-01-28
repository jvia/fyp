/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.models.probability;

import java.util.LinkedHashMap;
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
public class HistogramDataTest {

    public HistogramDataTest() {
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
     * Test of getBinNumberForValue method, of class HistogramData.
     */
    @Test
    public void testGetBinNumberForValue() {
        System.out.println("getBinNumberForValue");
        double value = 0.0;
        HistogramData instance = null;
        int expResult = 0;
        int result = instance.getBinNumberForValue(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of severe method, of class HistogramData.
     */
    @Test
    public void testSevere() {
        System.out.println("severe");
        String msg = "";
        HistogramData instance = null;
        instance.severe(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of info method, of class HistogramData.
     */
    @Test
    public void testInfo() {
        System.out.println("info");
        String info = "";
        HistogramData instance = null;
        instance.info(info);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNearestBinNumberForValue method, of class HistogramData.
     */
    @Test
    public void testGetNearestBinNumberForValue() {
        System.out.println("getNearestBinNumberForValue");
        double value = 0.0;
        HistogramData instance = null;
        int expResult = 0;
        int result = instance.getNearestBinNumberForValue(value);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsBinWithNumber method, of class HistogramData.
     */
    @Test
    public void testContainsBinWithNumber() {
        System.out.println("containsBinWithNumber");
        int binNumber = 0;
        HistogramData instance = null;
        boolean expResult = false;
        boolean result = instance.containsBinWithNumber(binNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isBinMissingWithNumber method, of class HistogramData.
     */
    @Test
    public void testIsBinMissingWithNumber() {
        System.out.println("isBinMissingWithNumber");
        int binNumber = 0;
        HistogramData instance = null;
        boolean expResult = false;
        boolean result = instance.isBinMissingWithNumber(binNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of put method, of class HistogramData.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        double value = 0.0;
        HistogramData instance = null;
        instance.put(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValues method, of class HistogramData.
     */
    @Test
    public void testGetValues() {
        System.out.println("getValues");
        HistogramData instance = null;
        double[] expResult = null;
        double[] result = instance.getValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueOfBinWithNumber method, of class HistogramData.
     */
    @Test
    public void testGetValueOfBinWithNumber() {
        System.out.println("getValueOfBinWithNumber");
        int binNumber = 0;
        HistogramData instance = null;
        double expResult = 0.0;
        double result = instance.getValueOfBinWithNumber(binNumber);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLabelOfBinWithNumber method, of class HistogramData.
     */
    @Test
    public void testGetLabelOfBinWithNumber() {
        System.out.println("getLabelOfBinWithNumber");
        int binNumber = 0;
        HistogramData instance = null;
        String expResult = "";
        String result = instance.getLabelOfBinWithNumber(binNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBinWithBinNumber method, of class HistogramData.
     */
    @Test
    public void testGetBinWithBinNumber() {
        System.out.println("getBinWithBinNumber");
        int binNumber = 0;
        HistogramData instance = null;
        HistogramBin expResult = null;
        HistogramBin result = instance.getBinWithBinNumber(binNumber);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class HistogramData.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        int id = 0;
        HistogramData instance = null;
        instance.reset(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetHist method, of class HistogramData.
     */
    @Test
    public void testResetHist() {
        System.out.println("resetHist");
        HistogramData instance = null;
        instance.resetHist();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumBins method, of class HistogramData.
     */
    @Test
    public void testGetNumBins() {
        System.out.println("getNumBins");
        HistogramData instance = null;
        int expResult = 0;
        int result = instance.getNumBins();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBinLabel method, of class HistogramData.
     */
    @Test
    public void testGetBinLabel() {
        System.out.println("getBinLabel");
        HistogramData instance = null;
        String[] expResult = null;
        String[] result = instance.getBinLabel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxValue method, of class HistogramData.
     */
    @Test
    public void testGetMaxValue() {
        System.out.println("getMaxValue");
        HistogramData instance = null;
        double expResult = 0.0;
        double result = instance.getMaxValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBinNumberOfMaxValue method, of class HistogramData.
     */
    @Test
    public void testGetBinNumberOfMaxValue() {
        System.out.println("getBinNumberOfMaxValue");
        HistogramData instance = null;
        int expResult = 0;
        int result = instance.getBinNumberOfMaxValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSumValues method, of class HistogramData.
     */
    @Test
    public void testGetSumValues() {
        System.out.println("getSumValues");
        HistogramData instance = null;
        int expResult = 0;
        int result = instance.getSumValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class HistogramData.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        HistogramData instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBins method, of class HistogramData.
     */
    @Test
    public void testGetBins() {
        System.out.println("getBins");
        HistogramData instance = null;
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.getBins();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBinSize method, of class HistogramData.
     */
    @Test
    public void testSetBinSize() {
        System.out.println("setBinSize");
        double binSize = 0.0;
        HistogramData instance = null;
        instance.setBinSize(binSize);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBinSize method, of class HistogramData.
     */
    @Test
    public void testGetBinSize() {
        System.out.println("getBinSize");
        HistogramData instance = null;
        double expResult = 0.0;
        double result = instance.getBinSize();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.models.probability;

import java.util.Iterator;
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
public class HistogramDistributionWithGaussTest {

    public HistogramDistributionWithGaussTest() {
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
     * Test of validate method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        HistogramDistributionWithGauss instance = null;
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbabilities method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testGetProbabilities() {
        System.out.println("getProbabilities");
        HistogramDistributionWithGauss instance = null;
        Iterator expResult = null;
        Iterator result = instance.getProbabilities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbByBinNumber method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testGetProbByBinNumber() {
        System.out.println("getProbByBinNumber");
        int binNumber = 0;
        HistogramDistributionWithGauss instance = null;
        double expResult = 0.0;
        double result = instance.getProbByBinNumber(binNumber);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbByBinLabel method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testGetProbByBinLabel() {
        System.out.println("getProbByBinLabel");
        String binLabel = "";
        HistogramDistributionWithGauss instance = null;
        double expResult = 0.0;
        double result = instance.getProbByBinLabel(binLabel);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        HistogramDistributionWithGauss instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        HistogramDistributionWithGauss instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        HistogramDistributionWithGauss instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBinSize method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testSetBinSize() {
        System.out.println("setBinSize");
        double binSize = 0.0;
        HistogramDistributionWithGauss instance = null;
        instance.setBinSize(binSize);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBinSize method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testGetBinSize() {
        System.out.println("getBinSize");
        HistogramDistributionWithGauss instance = null;
        double expResult = 0.0;
        double result = instance.getBinSize();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEntropy method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testGetEntropy() {
        System.out.println("getEntropy");
        HistogramDistributionWithGauss instance = null;
        double expResult = 0.0;
        double result = instance.getEntropy();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbability method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        double val = 0.0;
        HistogramDistributionWithGauss instance = null;
        double expResult = 0.0;
        double result = instance.getProbability(val);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        HistogramDistributionWithGauss instance = null;
        String expResult = "";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        double[] val = null;
        HistogramDistributionWithGauss instance = null;
        instance.update(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of severe method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testSevere() {
        System.out.println("severe");
        String msg = "";
        HistogramDistributionWithGauss instance = null;
        instance.severe(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of info method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testInfo() {
        System.out.println("info");
        String info = "";
        HistogramDistributionWithGauss instance = null;
        instance.info(info);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCount method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        HistogramDistributionWithGauss instance = null;
        int expResult = 0;
        int result = instance.getCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of expectedValue method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testExpectedValue() {
        System.out.println("expectedValue");
        HistogramDistributionWithGauss instance = null;
        double expResult = 0.0;
        double result = instance.expectedValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sample method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testSample() {
        System.out.println("sample");
        HistogramDistributionWithGauss instance = null;
        double[] expResult = null;
        double[] result = instance.sample();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sampleProbability method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testSampleProbability() {
        System.out.println("sampleProbability");
        HistogramDistributionWithGauss instance = null;
        double[] expResult = null;
        double[] result = instance.sampleProbability();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of variance method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testVariance() {
        System.out.println("variance");
        HistogramDistributionWithGauss instance = null;
        double expResult = 0.0;
        double result = instance.variance();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxProbability method, of class HistogramDistributionWithGauss.
     */
    @Test
    public void testGetMaxProbability() {
        System.out.println("getMaxProbability");
        HistogramDistributionWithGauss instance = null;
        double expResult = 0.0;
        double result = instance.getMaxProbability();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
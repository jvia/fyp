/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

import java.util.LinkedList;
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
public class AnomalyClassificatorGeneratorTest {

    public AnomalyClassificatorGeneratorTest() {
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
     * Test of getInitialThresholdValue method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testGetInitialThresholdValue() {
        System.out.println("getInitialThresholdValue");
        AnomalyClassificatorGenerator instance = null;
        double expResult = 0.0;
        double result = instance.getInitialThresholdValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInitialThresholdValue method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testSetInitialThresholdValue() {
        System.out.println("setInitialThresholdValue");
        double initialThresholdValue = 0.0;
        AnomalyClassificatorGenerator instance = null;
        instance.setInitialThresholdValue(initialThresholdValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxThresholdValue method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testGetMaxThresholdValue() {
        System.out.println("getMaxThresholdValue");
        AnomalyClassificatorGenerator instance = null;
        double expResult = 0.0;
        double result = instance.getMaxThresholdValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaxThresholdValue method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testSetMaxThresholdValue() {
        System.out.println("setMaxThresholdValue");
        double maxThresholdValue = 0.0;
        AnomalyClassificatorGenerator instance = null;
        instance.setMaxThresholdValue(maxThresholdValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThresholdValueStep method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testGetThresholdValueStep() {
        System.out.println("getThresholdValueStep");
        AnomalyClassificatorGenerator instance = null;
        double expResult = 0.0;
        double result = instance.getThresholdValueStep();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setThresholdValueStep method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testSetThresholdValueStep() {
        System.out.println("setThresholdValueStep");
        double thresholdValueStep = 0.0;
        AnomalyClassificatorGenerator instance = null;
        instance.setThresholdValueStep(thresholdValueStep);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInitialVarianceValue method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testGetInitialVarianceValue() {
        System.out.println("getInitialVarianceValue");
        AnomalyClassificatorGenerator instance = null;
        double expResult = 0.0;
        double result = instance.getInitialVarianceValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInitialVarianceValue method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testSetInitialVarianceValue() {
        System.out.println("setInitialVarianceValue");
        double initialVarianceValue = 0.0;
        AnomalyClassificatorGenerator instance = null;
        instance.setInitialVarianceValue(initialVarianceValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxVarianceValue method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testGetMaxVarianceValue() {
        System.out.println("getMaxVarianceValue");
        AnomalyClassificatorGenerator instance = null;
        double expResult = 0.0;
        double result = instance.getMaxVarianceValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaxVarianceValue method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testSetMaxVarianceValue() {
        System.out.println("setMaxVarianceValue");
        double maxVarianceValue = 0.0;
        AnomalyClassificatorGenerator instance = null;
        instance.setMaxVarianceValue(maxVarianceValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVarianceValueStep method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testGetVarianceValueStep() {
        System.out.println("getVarianceValueStep");
        AnomalyClassificatorGenerator instance = null;
        double expResult = 0.0;
        double result = instance.getVarianceValueStep();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVarianceValueStep method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testSetVarianceValueStep() {
        System.out.println("setVarianceValueStep");
        double varianceValueStep = 0.0;
        AnomalyClassificatorGenerator instance = null;
        instance.setVarianceValueStep(varianceValueStep);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateClassificatorsToTest method, of class AnomalyClassificatorGenerator.
     */
    @Test
    public void testGenerateClassificatorsToTest() {
        System.out.println("generateClassificatorsToTest");
        AnomalyClassificatorGenerator instance = null;
        LinkedList expResult = null;
        LinkedList result = instance.generateClassificatorsToTest();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data;

import java.util.ArrayList;
import java.util.HashMap;
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
public class TemporalProbabilityFeatureTest {

    public TemporalProbabilityFeatureTest() {
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
     * Test of getDurationProbabilities method, of class TemporalProbabilityFeature.
     */
    @Test
    public void testGetDurationProbabilities() {
        System.out.println("getDurationProbabilities");
        TemporalProbabilityFeature instance = new TemporalProbabilityFeature();
        ArrayList expResult = null;
        ArrayList result = instance.getDurationProbabilities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbabilityFor method, of class TemporalProbabilityFeature.
     */
    @Test
    public void testGetProbabilityFor_DataType() {
        System.out.println("getProbabilityFor");
        DataType eventType = null;
        TemporalProbabilityFeature instance = new TemporalProbabilityFeature();
        double expResult = 0.0;
        double result = instance.getProbabilityFor(eventType);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbabilityFor method, of class TemporalProbabilityFeature.
     */
    @Test
    public void testGetProbabilityFor_int() {
        System.out.println("getProbabilityFor");
        int eventTypeId = 0;
        TemporalProbabilityFeature instance = new TemporalProbabilityFeature();
        double expResult = 0.0;
        double result = instance.getProbabilityFor(eventTypeId);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataTypeToProbabilityMapping method, of class TemporalProbabilityFeature.
     */
    @Test
    public void testSetDataTypeToProbabilityMapping() {
        System.out.println("setDataTypeToProbabilityMapping");
        HashMap<DataType, Double> dataTypeToProbabilityMapping = null;
        TemporalProbabilityFeature instance = new TemporalProbabilityFeature();
        instance.setDataTypeToProbabilityMapping(dataTypeToProbabilityMapping);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataTypeToProbabilityMapping method, of class TemporalProbabilityFeature.
     */
    @Test
    public void testGetDataTypeToProbabilityMapping() {
        System.out.println("getDataTypeToProbabilityMapping");
        TemporalProbabilityFeature instance = new TemporalProbabilityFeature();
        HashMap expResult = null;
        HashMap result = instance.getDataTypeToProbabilityMapping();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRandomTemporalProbabilityFeature method, of class TemporalProbabilityFeature.
     */
    @Test
    public void testCreateRandomTemporalProbabilityFeature() {
        System.out.println("createRandomTemporalProbabilityFeature");
        TemporalProbabilityFeature expResult = null;
        TemporalProbabilityFeature result = TemporalProbabilityFeature.createRandomTemporalProbabilityFeature();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class TemporalProbabilityFeature.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        TemporalProbabilityFeature instance = new TemporalProbabilityFeature();
        Object expResult = null;
        Object result = instance.copy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class TemporalProbabilityFeature.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TemporalProbabilityFeature instance = new TemporalProbabilityFeature();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
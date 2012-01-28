/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data;

import java.util.ArrayList;
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
public class TemporalDurationFeatureTest {

    public TemporalDurationFeatureTest() {
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
     * Test of getDurationFor method, of class TemporalDurationFeature.
     */
    @Test
    public void testGetDurationFor_DataType() {
        System.out.println("getDurationFor");
        DataType inPrecedessorDataType = null;
        TemporalDurationFeature instance = new TemporalDurationFeature();
        long expResult = 0L;
        long result = instance.getDurationFor(inPrecedessorDataType);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDurationFor method, of class TemporalDurationFeature.
     */
    @Test
    public void testGetDurationFor_int() {
        System.out.println("getDurationFor");
        int eventTypeId = 0;
        TemporalDurationFeature instance = new TemporalDurationFeature();
        long expResult = 0L;
        long result = instance.getDurationFor(eventTypeId);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPredecessors method, of class TemporalDurationFeature.
     */
    @Test
    public void testGetPredecessors() {
        System.out.println("getPredecessors");
        TemporalDurationFeature instance = new TemporalDurationFeature();
        ArrayList expResult = null;
        ArrayList result = instance.getPredecessors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPrecedessorDataTypes method, of class TemporalDurationFeature.
     */
    @Test
    public void testGetPrecedessorDataTypes() {
        System.out.println("getPrecedessorDataTypes");
        TemporalDurationFeature instance = new TemporalDurationFeature();
        ArrayList expResult = null;
        ArrayList result = instance.getPrecedessorDataTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPredecessorIdToDurationsMapping method, of class TemporalDurationFeature.
     */
    @Test
    public void testSetPredecessorIdToDurationsMapping() {
        System.out.println("setPredecessorIdToDurationsMapping");
        LinkedHashMap<DataType, Long> predecessorIdToDurationsMapping = null;
        TemporalDurationFeature instance = new TemporalDurationFeature();
        instance.setPredecessorIdToDurationsMapping(predecessorIdToDurationsMapping);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPredecessorIdToDurationsMapping method, of class TemporalDurationFeature.
     */
    @Test
    public void testGetPredecessorIdToDurationsMapping() {
        System.out.println("getPredecessorIdToDurationsMapping");
        TemporalDurationFeature instance = new TemporalDurationFeature();
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.getPredecessorIdToDurationsMapping();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRandomTemporalDurationFeature method, of class TemporalDurationFeature.
     */
    @Test
    public void testCreateRandomTemporalDurationFeature() {
        System.out.println("createRandomTemporalDurationFeature");
        TemporalDurationFeature expResult = null;
        TemporalDurationFeature result = TemporalDurationFeature.createRandomTemporalDurationFeature();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class TemporalDurationFeature.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        TemporalDurationFeature instance = new TemporalDurationFeature();
        Object expResult = null;
        Object result = instance.copy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
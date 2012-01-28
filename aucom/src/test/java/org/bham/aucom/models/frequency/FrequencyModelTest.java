/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.models.frequency;

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
public class FrequencyModelTest {

    public FrequencyModelTest() {
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
     * Test of put method, of class FrequencyModel.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Integer classId = null;
        FrequencyModel instance = new FrequencyModel();
        instance.put(classId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbability method, of class FrequencyModel.
     */
    @Test
    public void testGetProbability() {
        System.out.println("getProbability");
        Integer classId = null;
        FrequencyModel instance = new FrequencyModel();
        double expResult = 0.0;
        double result = instance.getProbability(classId);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setData method, of class FrequencyModel.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        LinkedHashMap<Integer, Integer> data = null;
        FrequencyModel instance = new FrequencyModel();
        instance.setData(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class FrequencyModel.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        FrequencyModel instance = new FrequencyModel();
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.getData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumValues method, of class FrequencyModel.
     */
    @Test
    public void testSetNumValues() {
        System.out.println("setNumValues");
        int numValues = 0;
        FrequencyModel instance = new FrequencyModel();
        instance.setNumValues(numValues);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumValues method, of class FrequencyModel.
     */
    @Test
    public void testGetNumValues() {
        System.out.println("getNumValues");
        FrequencyModel instance = new FrequencyModel();
        int expResult = 0;
        int result = instance.getNumValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
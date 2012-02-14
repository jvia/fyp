/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import org.bham.aucom.data.Score;
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
public class HysteresisAnomalyClassificatorTest {

    public HysteresisAnomalyClassificatorTest() {
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
     * Test of satisfies method, of class HysteresisAnomalyClassificator.
     */
    @Test
    public void testSatisfies() {
        System.out.println("satisfies");
        Score s = null;
        HysteresisAnomalyClassificator instance = null;
        boolean expResult = false;
        boolean result = instance.satisfies(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class HysteresisAnomalyClassificator.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        HysteresisAnomalyClassificator instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLower method, of class HysteresisAnomalyClassificator.
     */
    @Test
    public void testGetLower() {
        System.out.println("getLower");
        HysteresisAnomalyClassificator instance = null;
        Double expResult = null;
        Double result = instance.getLower();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getUpper method, of class HysteresisAnomalyClassificator.
     */
    @Test
    public void testGetUpper() {
        System.out.println("getUpper");
        HysteresisAnomalyClassificator instance = null;
        Double expResult = null;
        Double result = instance.getUpper();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassificator method, of class HysteresisAnomalyClassificator.
     */
    @Test
    public void testSetClassificator() {
        System.out.println("setClassificator");
        AnomalyClassificator threshold = null;
        HysteresisAnomalyClassificator instance = null;
        instance.setClassificator(threshold);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastElementSatisfies method, of class HysteresisAnomalyClassificator.
     */
    @Test
    public void testSetLastElementSatisfies() {
        System.out.println("setLastElementSatisfies");
        boolean isLastElementSatisfies = false;
        HysteresisAnomalyClassificator instance = null;
        instance.setLastElementSatisfies(isLastElementSatisfies);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLastElementSatisfies method, of class HysteresisAnomalyClassificator.
     */
    @Test
    public void testIsLastElementSatisfies() {
        System.out.println("isLastElementSatisfies");
        HysteresisAnomalyClassificator instance = null;
        boolean expResult = false;
        boolean result = instance.isLastElementSatisfies();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class HysteresisAnomalyClassificator.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object obj = null;
        HysteresisAnomalyClassificator instance = null;
        boolean expResult = false;
        boolean result = instance.equals(obj);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class HysteresisAnomalyClassificator.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        AnomalyClassificator classificator = null;
        HysteresisAnomalyClassificator instance = null;
        instance.copy(classificator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of duplicate method, of class HysteresisAnomalyClassificator.
     */
    @Test
    public void testDuplicate() {
        System.out.println("duplicate");
        HysteresisAnomalyClassificator instance = null;
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.duplicate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class HysteresisAnomalyClassificator.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        HysteresisAnomalyClassificator instance = null;
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
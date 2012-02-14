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
public class MeanAnomalyClassificatorTest {

    public MeanAnomalyClassificatorTest() {
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
     * Test of setMeanValue method, of class MeanAnomalyClassificator.
     */
    @Test
    public void testSetMeanValue() {
        System.out.println("setMeanValue");
        double in = 0.0;
        MeanAnomalyClassificator instance = null;
        instance.setMeanValue(in);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMeanValue method, of class MeanAnomalyClassificator.
     */
    @Test
    public void testGetMeanValue() {
        System.out.println("getMeanValue");
        MeanAnomalyClassificator instance = null;
        double expResult = 0.0;
        double result = instance.getMeanValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class MeanAnomalyClassificator.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        MeanAnomalyClassificator instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of satisfies method, of class MeanAnomalyClassificator.
     */
    @Test
    public void testSatisfies() {
        System.out.println("satisfies");
        Score s = null;
        MeanAnomalyClassificator instance = null;
        boolean expResult = false;
        boolean result = instance.satisfies(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassificator method, of class MeanAnomalyClassificator.
     */
    @Test
    public void testSetClassificator() {
        System.out.println("setClassificator");
        AnomalyClassificator threshold = null;
        MeanAnomalyClassificator instance = null;
        instance.setClassificator(threshold);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class MeanAnomalyClassificator.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        AnomalyClassificator classificator = null;
        MeanAnomalyClassificator instance = null;
        instance.copy(classificator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of duplicate method, of class MeanAnomalyClassificator.
     */
    @Test
    public void testDuplicate() {
        System.out.println("duplicate");
        MeanAnomalyClassificator instance = null;
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.duplicate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class MeanAnomalyClassificator.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        MeanAnomalyClassificator instance = null;
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
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
public class StatisticalHysterisisAnomalyClassificatorTest {

    public StatisticalHysterisisAnomalyClassificatorTest() {
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
     * Test of satisfies method, of class StatisticalHysterisisAnomalyClassificator.
     */
    @Test
    public void testSatisfies() {
        System.out.println("satisfies");
        Score scoreToCheck = null;
        StatisticalHysterisisAnomalyClassificator instance = null;
        boolean expResult = false;
        boolean result = instance.satisfies(scoreToCheck);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassificator method, of class StatisticalHysterisisAnomalyClassificator.
     */
    @Test
    public void testSetClassificator() {
        System.out.println("setClassificator");
        AnomalyClassificator threshold = null;
        StatisticalHysterisisAnomalyClassificator instance = null;
        instance.setClassificator(threshold);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class StatisticalHysterisisAnomalyClassificator.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        AnomalyClassificator classificator = null;
        StatisticalHysterisisAnomalyClassificator instance = null;
        instance.copy(classificator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of duplicate method, of class StatisticalHysterisisAnomalyClassificator.
     */
    @Test
    public void testDuplicate() {
        System.out.println("duplicate");
        StatisticalHysterisisAnomalyClassificator instance = null;
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.duplicate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class StatisticalHysterisisAnomalyClassificator.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        StatisticalHysterisisAnomalyClassificator instance = null;
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
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
public class ClassificatorOptimizerStatusEventTest {

    public ClassificatorOptimizerStatusEventTest() {
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
     * Test of getTestedClassificator method, of class ClassificatorOptimizerStatusEvent.
     */
    @Test
    public void testGetTestedClassificator() {
        System.out.println("getTestedClassificator");
        ClassificatorOptimizerStatusEvent instance = null;
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.getTestedClassificator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBestClassificator method, of class ClassificatorOptimizerStatusEvent.
     */
    @Test
    public void testGetBestClassificator() {
        System.out.println("getBestClassificator");
        ClassificatorOptimizerStatusEvent instance = null;
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.getBestClassificator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFalsePositiveRate method, of class ClassificatorOptimizerStatusEvent.
     */
    @Test
    public void testGetFalsePositiveRate() {
        System.out.println("getFalsePositiveRate");
        ClassificatorOptimizerStatusEvent instance = null;
        double expResult = 0.0;
        double result = instance.getFalsePositiveRate();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistanceToMean method, of class ClassificatorOptimizerStatusEvent.
     */
    @Test
    public void testGetDistanceToMean() {
        System.out.println("getDistanceToMean");
        ClassificatorOptimizerStatusEvent instance = null;
        double expResult = 0.0;
        double result = instance.getDistanceToMean();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setNumber method, of class ClassificatorOptimizerStatusEvent.
     */
    @Test
    public void testSetNumber() {
        System.out.println("setNumber");
        int number = 0;
        ClassificatorOptimizerStatusEvent instance = null;
        instance.setNumber(number);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumber method, of class ClassificatorOptimizerStatusEvent.
     */
    @Test
    public void testGetNumber() {
        System.out.println("getNumber");
        ClassificatorOptimizerStatusEvent instance = null;
        int expResult = 0;
        int result = instance.getNumber();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTotal method, of class ClassificatorOptimizerStatusEvent.
     */
    @Test
    public void testSetTotal() {
        System.out.println("setTotal");
        int total = 0;
        ClassificatorOptimizerStatusEvent instance = null;
        instance.setTotal(total);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTotal method, of class ClassificatorOptimizerStatusEvent.
     */
    @Test
    public void testGetTotal() {
        System.out.println("getTotal");
        ClassificatorOptimizerStatusEvent instance = null;
        int expResult = 0;
        int result = instance.getTotal();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of markAsFinished method, of class ClassificatorOptimizerStatusEvent.
     */
    @Test
    public void testMarkAsFinished() {
        System.out.println("markAsFinished");
        ClassificatorOptimizerStatusEvent instance = null;
        instance.markAsFinished();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFinished method, of class ClassificatorOptimizerStatusEvent.
     */
    @Test
    public void testIsFinished() {
        System.out.println("isFinished");
        ClassificatorOptimizerStatusEvent instance = null;
        boolean expResult = false;
        boolean result = instance.isFinished();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ClassificatorOptimizerStatusEvent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ClassificatorOptimizerStatusEvent instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
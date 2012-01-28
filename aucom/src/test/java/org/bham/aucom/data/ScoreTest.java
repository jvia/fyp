/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data;

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
public class ScoreTest {

    public ScoreTest() {
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
     * Test of setValue method, of class Score.
     */
    @Test
    public void testSetValue() {
        System.out.println("setValue");
        double value = 0.0;
        Score instance = new ScoreImpl();
        instance.setValue(value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValue method, of class Score.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        Score instance = new ScoreImpl();
        double expResult = 0.0;
        double result = instance.getValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVariance method, of class Score.
     */
    @Test
    public void testSetVariance() {
        System.out.println("setVariance");
        double variance = 0.0;
        Score instance = new ScoreImpl();
        instance.setVariance(variance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVariance method, of class Score.
     */
    @Test
    public void testGetVariance() {
        System.out.println("getVariance");
        Score instance = new ScoreImpl();
        double expResult = 0.0;
        double result = instance.getVariance();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRandomScore method, of class Score.
     */
    @Test
    public void testCreateRandomScore() {
        System.out.println("createRandomScore");
        Score expResult = null;
        Score result = Score.createRandomScore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRandomRangeScore method, of class Score.
     */
    @Test
    public void testCreateRandomRangeScore() {
        System.out.println("createRandomRangeScore");
        Score expResult = null;
        Score result = Score.createRandomRangeScore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createRandomSingleScore method, of class Score.
     */
    @Test
    public void testCreateRandomSingleScore() {
        System.out.println("createRandomSingleScore");
        Score expResult = null;
        Score result = Score.createRandomSingleScore();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Score.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Score instance = new ScoreImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ScoreImpl extends Score {
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.tranform;

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
public class CalcDummyScoreTest {

    public CalcDummyScoreTest() {
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
     * Test of calc method, of class CalcDummyScore.
     */
    @Test
    public void testCalc() throws Exception {
        System.out.println("calc");
        Score inData = null;
        CalcDummyScore instance = new CalcDummyScore();
        Score expResult = null;
        Score result = instance.calc(inData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of transform method, of class CalcDummyScore.
     */
    @Test
    public void testTransform() throws Exception {
        System.out.println("transform");
        Score arg0 = null;
        CalcDummyScore instance = new CalcDummyScore();
        Score expResult = null;
        Score result = instance.transform(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
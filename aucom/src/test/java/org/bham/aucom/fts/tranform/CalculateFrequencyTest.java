/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.AbstractData;
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
public class CalculateFrequencyTest {

    public CalculateFrequencyTest() {
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
     * Test of iTransform method, of class CalculateFrequency.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        AbstractData input = null;
        CalculateFrequency instance = null;
        AbstractData expResult = null;
        AbstractData result = instance.iTransform(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFrequency method, of class CalculateFrequency.
     */
    @Test
    public void testGetFrequency() {
        System.out.println("getFrequency");
        CalculateFrequency instance = null;
        double expResult = 0.0;
        double result = instance.getFrequency();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElementCount method, of class CalculateFrequency.
     */
    @Test
    public void testGetElementCount() {
        System.out.println("getElementCount");
        CalculateFrequency instance = null;
        int expResult = 0;
        int result = instance.getElementCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
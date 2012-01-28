/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.xcfrecorder;

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
public class CounterTest {

    public CounterTest() {
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
     * Test of iTransform method, of class Counter.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        AbstractData input = null;
        Counter instance = new Counter();
        AbstractData expResult = null;
        AbstractData result = instance.iTransform(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCounter method, of class Counter.
     */
    @Test
    public void testGetCounter() {
        System.out.println("getCounter");
        Counter instance = new Counter();
        int expResult = 0;
        int result = instance.getCounter();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class Counter.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Counter instance = new Counter();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
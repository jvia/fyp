/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.xcfrecorder;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 *
 * @author jxv911
 */
public class RecorderStateTest {

    public RecorderStateTest() {
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
     * Test of values method, of class RecorderState.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        RecorderState[] expResult = null;
        RecorderState[] result = RecorderState.values();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class RecorderState.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        RecorderState expResult = null;
        RecorderState result = RecorderState.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
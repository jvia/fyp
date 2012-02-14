/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

import java.util.ArrayList;
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
public class RangedArrayListTest {

    public RangedArrayListTest() {
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
     * Test of setIndex method, of class RangedArrayList.
     */
    @Test
    public void testSetIndex() {
        System.out.println("setIndex");
        int index = 0;
        RangedArrayList instance = null;
        instance.setIndex(index);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIndex method, of class RangedArrayList.
     */
    @Test
    public void testGetIndex() {
        System.out.println("getIndex");
        RangedArrayList instance = null;
        int expResult = 0;
        int result = instance.getIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRange method, of class RangedArrayList.
     */
    @Test
    public void testSetRange() {
        System.out.println("setRange");
        int range = 0;
        RangedArrayList instance = null;
        instance.setRange(range);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRange method, of class RangedArrayList.
     */
    @Test
    public void testGetRange() {
        System.out.println("getRange");
        RangedArrayList instance = null;
        int expResult = 0;
        int result = instance.getRange();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getList method, of class RangedArrayList.
     */
    @Test
    public void testGetList() {
        System.out.println("getList");
        RangedArrayList instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
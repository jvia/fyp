/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui;

import javax.swing.JTextField;
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
public class SlidingWindowDialogTest {

    public SlidingWindowDialogTest() {
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
     * Test of main method, of class SlidingWindowDialog.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        SlidingWindowDialog.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setjTextField2 method, of class SlidingWindowDialog.
     */
    @Test
    public void testSetjTextField2() {
        System.out.println("setjTextField2");
        JTextField jTextField2 = null;
        SlidingWindowDialog instance = null;
        instance.setjTextField2(jTextField2);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getjTextField2 method, of class SlidingWindowDialog.
     */
    @Test
    public void testGetjTextField2() {
        System.out.println("getjTextField2");
        SlidingWindowDialog instance = null;
        JTextField expResult = null;
        JTextField result = instance.getjTextField2();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSlidingWindowSize method, of class SlidingWindowDialog.
     */
    @Test
    public void testGetSlidingWindowSize() {
        System.out.println("getSlidingWindowSize");
        SlidingWindowDialog instance = null;
        int expResult = 0;
        int result = instance.getSlidingWindowSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWindowOverlapSize method, of class SlidingWindowDialog.
     */
    @Test
    public void testGetWindowOverlapSize() {
        System.out.println("getWindowOverlapSize");
        SlidingWindowDialog instance = null;
        int expResult = 0;
        int result = instance.getWindowOverlapSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isApproved method, of class SlidingWindowDialog.
     */
    @Test
    public void testIsApproved() {
        System.out.println("isApproved");
        SlidingWindowDialog instance = null;
        boolean expResult = false;
        boolean result = instance.isApproved();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

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
public class TupelTest {

    public TupelTest() {
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
     * Test of getFirstElement method, of class Tupel.
     */
    @Test
    public void testGetFirstElement() {
        System.out.println("getFirstElement");
        Tupel instance = null;
        Object expResult = null;
        Object result = instance.getFirstElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSecondElement method, of class Tupel.
     */
    @Test
    public void testSetSecondElement() {
        System.out.println("setSecondElement");
        Object secondElement = null;
        Tupel instance = null;
        instance.setSecondElement(secondElement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSecondElement method, of class Tupel.
     */
    @Test
    public void testGetSecondElement() {
        System.out.println("getSecondElement");
        Tupel instance = null;
        Object expResult = null;
        Object result = instance.getSecondElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Tupel.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Tupel instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
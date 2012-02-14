/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.tranform;

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
public class PostofixToStringTest {

    public PostofixToStringTest() {
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
     * Test of transform method, of class PostofixToString.
     */
    @Test
    public void testTransform() throws Exception {
        System.out.println("transform");
        String arg0 = "";
        PostofixToString instance = null;
        String expResult = "";
        String result = instance.transform(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPostfix method, of class PostofixToString.
     */
    @Test
    public void testSetPostfix() {
        System.out.println("setPostfix");
        String inNewPostfix = "";
        PostofixToString instance = null;
        instance.setPostfix(inNewPostfix);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPostfix method, of class PostofixToString.
     */
    @Test
    public void testGetPostfix() {
        System.out.println("getPostfix");
        PostofixToString instance = null;
        String expResult = "";
        String result = instance.getPostfix();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.util;

import java.util.List;
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
public class DataModelStatusEventTest {

    public DataModelStatusEventTest() {
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
     * Test of getStatus method, of class DataModelStatusEvent.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        DataModelStatusEvent instance = null;
        DataModelStatus expResult = null;
        DataModelStatus result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DataModelStatusEvent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DataModelStatusEvent instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElements method, of class DataModelStatusEvent.
     */
    @Test
    public void testGetElements() {
        System.out.println("getElements");
        DataModelStatusEvent instance = null;
        List expResult = null;
        List result = instance.getElements();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFilterDataClass method, of class DataModelStatusEvent.
     */
    @Test
    public void testGetFilterDataClass() {
        System.out.println("getFilterDataClass");
        DataModelStatusEvent instance = null;
        Class expResult = null;
        Class result = instance.getFilterDataClass();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
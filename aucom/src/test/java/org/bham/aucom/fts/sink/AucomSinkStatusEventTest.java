/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.sink;

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
public class AucomSinkStatusEventTest {

    public AucomSinkStatusEventTest() {
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
     * Test of setStatus method, of class AucomSinkStatusEvent.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        NodeStatus status = null;
        AucomSinkStatusEvent instance = null;
        instance.setStatus(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class AucomSinkStatusEvent.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        AucomSinkStatusEvent instance = null;
        NodeStatus expResult = null;
        NodeStatus result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class AucomSinkStatusEvent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AucomSinkStatusEvent instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
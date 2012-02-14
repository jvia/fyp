/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.system;

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
public class SystemConnectionStatusChangedEventTest {

    public SystemConnectionStatusChangedEventTest() {
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
     * Test of getNewStatus method, of class SystemConnectionStatusChangedEvent.
     */
    @Test
    public void testGetNewStatus() {
        System.out.println("getNewStatus");
        SystemConnectionStatusChangedEvent instance = null;
        SystemConnectionStatus expResult = null;
        SystemConnectionStatus result = instance.getNewStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOldStatus method, of class SystemConnectionStatusChangedEvent.
     */
    @Test
    public void testGetOldStatus() {
        System.out.println("getOldStatus");
        SystemConnectionStatusChangedEvent instance = null;
        SystemConnectionStatus expResult = null;
        SystemConnectionStatus result = instance.getOldStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class SystemConnectionStatusChangedEvent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SystemConnectionStatusChangedEvent instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser;

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
public class StatusChangedEventTest {

    public StatusChangedEventTest() {
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
     * Test of getPreviousStatus method, of class StatusChangedEvent.
     */
    @Test
    public void testGetPreviousStatus() {
        System.out.println("getPreviousStatus");
        StatusChangedEvent instance = null;
        TrainerStatus expResult = null;
        TrainerStatus result = instance.getPreviousStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentStatus method, of class StatusChangedEvent.
     */
    @Test
    public void testGetCurrentStatus() {
        System.out.println("getCurrentStatus");
        StatusChangedEvent instance = null;
        TrainerStatus expResult = null;
        TrainerStatus result = instance.getCurrentStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class StatusChangedEvent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        StatusChangedEvent instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
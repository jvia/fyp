/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser;

import org.bham.aucom.diagnoser.AbstractDetector.DetectorStatus;
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
public class DetectorStatusChangedEventTest {

    public DetectorStatusChangedEventTest() {
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
     * Test of getPreviousStatus method, of class DetectorStatusChangedEvent.
     */
    @Test
    public void testGetPreviousStatus() {
        System.out.println("getPreviousStatus");
        DetectorStatusChangedEvent instance = null;
        DetectorStatus expResult = null;
        DetectorStatus result = instance.getPreviousStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentStatus method, of class DetectorStatusChangedEvent.
     */
    @Test
    public void testGetCurrentStatus() {
        System.out.println("getCurrentStatus");
        DetectorStatusChangedEvent instance = null;
        DetectorStatus expResult = null;
        DetectorStatus result = instance.getCurrentStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class DetectorStatusChangedEvent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        DetectorStatusChangedEvent instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
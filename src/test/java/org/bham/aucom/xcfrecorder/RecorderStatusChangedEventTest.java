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
import static org.junit.Assert.*;

/**
 *
 * @author jxv911
 */
public class RecorderStatusChangedEventTest {

    public RecorderStatusChangedEventTest() {
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
     * Test of getOldState method, of class RecorderStatusChangedEvent.
     */
    @Test
    public void testGetOldState() {
        System.out.println("getOldState");
        RecorderStatusChangedEvent instance = null;
        RecorderState expResult = null;
        RecorderState result = instance.getOldState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNewState method, of class RecorderStatusChangedEvent.
     */
    @Test
    public void testGetNewState() {
        System.out.println("getNewState");
        RecorderStatusChangedEvent instance = null;
        RecorderState expResult = null;
        RecorderState result = instance.getNewState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.timeseries;

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
public class TimeseriesStatusEventTest {

    public TimeseriesStatusEventTest() {
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
     * Test of getStartIndex method, of class TimeseriesStatusEvent.
     */
    @Test
    public void testGetStartIndex() {
        System.out.println("getStartIndex");
        TimeseriesStatusEvent instance = null;
        int expResult = 0;
        int result = instance.getStartIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEndIndex method, of class TimeseriesStatusEvent.
     */
    @Test
    public void testGetEndIndex() {
        System.out.println("getEndIndex");
        TimeseriesStatusEvent instance = null;
        int expResult = 0;
        int result = instance.getEndIndex();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class TimeseriesStatusEvent.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        TimeseriesStatusEvent instance = null;
        TimeseriesStatus expResult = null;
        TimeseriesStatus result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class TimeseriesStatusEvent.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TimeseriesStatusEvent instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
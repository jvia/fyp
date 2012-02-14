/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.util;

import java.util.UUID;
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
public class SlidingWindowTest {

    public SlidingWindowTest() {
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
     * Test of getIntervalSize method, of class SlidingWindow.
     */
    @Test
    public void testGetIntervalSize() {
        System.out.println("getIntervalSize");
        SlidingWindow instance = null;
        long expResult = 0L;
        long result = instance.getIntervalSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIntervalSize method, of class SlidingWindow.
     */
    @Test
    public void testSetIntervalSize() {
        System.out.println("setIntervalSize");
        long intervalSize = 0L;
        SlidingWindow instance = null;
        instance.setIntervalSize(intervalSize);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getIntervalOverlapSize method, of class SlidingWindow.
     */
    @Test
    public void testGetIntervalOverlapSize() {
        System.out.println("getIntervalOverlapSize");
        SlidingWindow instance = null;
        long expResult = 0L;
        long result = instance.getIntervalOverlapSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setIntervalOverlapSize method, of class SlidingWindow.
     */
    @Test
    public void testSetIntervalOverlapSize() {
        System.out.println("setIntervalOverlapSize");
        long intervalOverlapSize = 0L;
        SlidingWindow instance = null;
        instance.setIntervalOverlapSize(intervalOverlapSize);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class SlidingWindow.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        SlidingWindow slidingWindow = null;
        SlidingWindow instance = null;
        instance.copy(slidingWindow);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class SlidingWindow.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        UUID id = null;
        SlidingWindow instance = null;
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class SlidingWindow.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        SlidingWindow instance = null;
        UUID expResult = null;
        UUID result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class SlidingWindow.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SlidingWindow instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
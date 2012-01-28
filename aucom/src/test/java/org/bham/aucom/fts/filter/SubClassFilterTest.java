/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.filter;

import net.sf.xcf.fts.Event;
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
public class SubClassFilterTest {

    public SubClassFilterTest() {
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
     * Test of isMatch method, of class SubClassFilter.
     */
    @Test
    public void testIsMatch() throws Exception {
        System.out.println("isMatch");
        Event<? extends Tin> e = null;
        SubClassFilter instance = null;
        boolean expResult = false;
        boolean result = instance.isMatch(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleEvent method, of class SubClassFilter.
     */
    @Test
    public void testHandleEvent() throws Exception {
        System.out.println("handleEvent");
        Event<? extends Tin> input = null;
        SubClassFilter instance = null;
        Event expResult = null;
        Event result = instance.handleEvent(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
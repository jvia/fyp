/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.select;

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
public class BooleanSwitchTest {

    public BooleanSwitchTest() {
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
     * Test of isMatch method, of class BooleanSwitch.
     */
    @Test
    public void testIsMatch() throws Exception {
        System.out.println("isMatch");
        Event e = null;
        BooleanSwitch instance = new BooleanSwitch();
        boolean expResult = false;
        boolean result = instance.isMatch(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of enable method, of class BooleanSwitch.
     */
    @Test
    public void testEnable() {
        System.out.println("enable");
        BooleanSwitch instance = new BooleanSwitch();
        instance.enable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disable method, of class BooleanSwitch.
     */
    @Test
    public void testDisable() {
        System.out.println("disable");
        BooleanSwitch instance = new BooleanSwitch();
        instance.disable();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
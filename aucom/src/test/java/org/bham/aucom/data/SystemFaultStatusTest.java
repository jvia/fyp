/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data;

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
public class SystemFaultStatusTest {

    public SystemFaultStatusTest() {
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
     * Test of values method, of class SystemFaultStatus.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        SystemFaultStatus[] expResult = null;
        SystemFaultStatus[] result = SystemFaultStatus.values();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class SystemFaultStatus.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        SystemFaultStatus expResult = null;
        SystemFaultStatus result = SystemFaultStatus.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
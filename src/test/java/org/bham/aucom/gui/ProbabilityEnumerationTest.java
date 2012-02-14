/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

/**
 *
 * @author jxv911
 */
public class ProbabilityEnumerationTest {

    public ProbabilityEnumerationTest() {
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
     * Test of values method, of class ProbabilityEnumeration.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        ProbabilityEnumeration[] expResult = null;
        ProbabilityEnumeration[] result = ProbabilityEnumeration.values();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class ProbabilityEnumeration.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        ProbabilityEnumeration expResult = null;
        ProbabilityEnumeration result = ProbabilityEnumeration.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui;

import org.bham.aucom.diagnoser.t2gram.ProbabilityFactory;
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
public class KernelDensityInputPanelTest {

    public KernelDensityInputPanelTest() {
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
     * Test of getProbabilityFactory method, of class KernelDensityInputPanel.
     */
    @Test
    public void testGetProbabilityFactory() {
        System.out.println("getProbabilityFactory");
        KernelDensityInputPanel instance = new KernelDensityInputPanel();
        ProbabilityFactory expResult = null;
        ProbabilityFactory result = instance.getProbabilityFactory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
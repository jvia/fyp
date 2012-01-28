/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.models.probability;

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
public class ProbabilityInputPanelTest {

    public ProbabilityInputPanelTest() {
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
     * Test of getProbabilityFactory method, of class ProbabilityInputPanel.
     */
    @Test
    public void testGetProbabilityFactory() {
        System.out.println("getProbabilityFactory");
        ProbabilityInputPanel instance = new ProbabilityInputPanelImpl();
        ProbabilityFactory expResult = null;
        ProbabilityFactory result = instance.getProbabilityFactory();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ProbabilityInputPanelImpl extends ProbabilityInputPanel {

        public ProbabilityFactory getProbabilityFactory() {
            return null;
        }
    }

}
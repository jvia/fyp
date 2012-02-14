/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer;

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
public class OptimizerPanelTest {

    public OptimizerPanelTest() {
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
     * Test of classificatorOptimizatorStatusChanged method, of class OptimizerPanel.
     */
    @Test
    public void testClassificatorOptimizatorStatusChanged() {
        System.out.println("classificatorOptimizatorStatusChanged");
        ClassificatorOptimizerStatusEvent status = null;
        OptimizerPanel instance = null;
        instance.classificatorOptimizatorStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateButtonStatus method, of class OptimizerPanel.
     */
    @Test
    public void testUpdateButtonStatus() {
        System.out.println("updateButtonStatus");
        OptimizerPanel instance = null;
        instance.updateButtonStatus();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
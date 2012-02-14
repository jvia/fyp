/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.app.experiment;

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
public class OptimizeClassificatorExperimentTest {

    public OptimizeClassificatorExperimentTest() {
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
     * Test of call method, of class OptimizeClassificatorExperiment.
     */
    @Test
    public void testCall() throws Exception {
        System.out.println("call");
        OptimizeClassificatorExperiment instance = null;
        Result expResult = null;
        Result result = instance.call();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isPreprocessFinished method, of class OptimizeClassificatorExperiment.
     */
    @Test
    public void testIsPreprocessFinished() {
        System.out.println("isPreprocessFinished");
        OptimizeClassificatorExperiment instance = null;
        boolean expResult = false;
        boolean result = instance.isPreprocessFinished();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isProcessFinished method, of class OptimizeClassificatorExperiment.
     */
    @Test
    public void testIsProcessFinished() {
        System.out.println("isProcessFinished");
        OptimizeClassificatorExperiment instance = null;
        boolean expResult = false;
        boolean result = instance.isProcessFinished();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preprocess method, of class OptimizeClassificatorExperiment.
     */
    @Test
    public void testPreprocess() {
        System.out.println("preprocess");
        OptimizeClassificatorExperiment instance = null;
        instance.preprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of co_experiment method, of class OptimizeClassificatorExperiment.
     */
    @Test
    public void testCo_experiment() {
        System.out.println("co_experiment");
        String string = "";
        OptimizeClassificatorExperiment instance = null;
        instance.co_experiment(string);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of process method, of class OptimizeClassificatorExperiment.
     */
    @Test
    public void testProcess() {
        System.out.println("process");
        OptimizeClassificatorExperiment instance = null;
        instance.process();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postprocess method, of class OptimizeClassificatorExperiment.
     */
    @Test
    public void testPostprocess() {
        System.out.println("postprocess");
        OptimizeClassificatorExperiment instance = null;
        instance.postprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
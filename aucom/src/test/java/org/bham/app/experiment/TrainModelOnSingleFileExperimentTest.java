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
public class TrainModelOnSingleFileExperimentTest {

    public TrainModelOnSingleFileExperimentTest() {
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
     * Test of call method, of class TrainModelOnSingleFileExperiment.
     */
    @Test
    public void testCall() throws Exception {
        System.out.println("call");
        TrainModelOnSingleFileExperiment instance = null;
        Result expResult = null;
        Result result = instance.call();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preprocess method, of class TrainModelOnSingleFileExperiment.
     */
    @Test
    public void testPreprocess() {
        System.out.println("preprocess");
        TrainModelOnSingleFileExperiment instance = null;
        instance.preprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of process method, of class TrainModelOnSingleFileExperiment.
     */
    @Test
    public void testProcess() {
        System.out.println("process");
        TrainModelOnSingleFileExperiment instance = null;
        instance.process();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postprocess method, of class TrainModelOnSingleFileExperiment.
     */
    @Test
    public void testPostprocess() {
        System.out.println("postprocess");
        TrainModelOnSingleFileExperiment instance = null;
        instance.postprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
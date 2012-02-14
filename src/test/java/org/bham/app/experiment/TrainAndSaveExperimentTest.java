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
public class TrainAndSaveExperimentTest {

    public TrainAndSaveExperimentTest() {
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
     * Test of call method, of class TrainAndSaveExperiment.
     */
    @Test
    public void testCall() throws Exception {
        System.out.println("call");
        TrainAndSaveExperiment instance = new TrainAndSaveExperiment();
        Result expResult = null;
        Result result = instance.call();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preprocess method, of class TrainAndSaveExperiment.
     */
    @Test
    public void testPreprocess() {
        System.out.println("preprocess");
        TrainAndSaveExperiment instance = new TrainAndSaveExperiment();
        instance.preprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of process method, of class TrainAndSaveExperiment.
     */
    @Test
    public void testProcess() {
        System.out.println("process");
        TrainAndSaveExperiment instance = new TrainAndSaveExperiment();
        instance.process();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postprocess method, of class TrainAndSaveExperiment.
     */
    @Test
    public void testPostprocess() {
        System.out.println("postprocess");
        TrainAndSaveExperiment instance = new TrainAndSaveExperiment();
        instance.postprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
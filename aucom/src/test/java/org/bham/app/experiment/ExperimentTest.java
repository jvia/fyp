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
public class ExperimentTest {

    public ExperimentTest() {
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
     * Test of preprocess method, of class Experiment.
     */
    @Test
    public void testPreprocess() throws Exception {
        System.out.println("preprocess");
        Experiment instance = new ExperimentImpl();
        instance.preprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of process method, of class Experiment.
     */
    @Test
    public void testProcess() throws Exception {
        System.out.println("process");
        Experiment instance = new ExperimentImpl();
        instance.process();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postprocess method, of class Experiment.
     */
    @Test
    public void testPostprocess() throws Exception {
        System.out.println("postprocess");
        Experiment instance = new ExperimentImpl();
        instance.postprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ExperimentImpl implements Experiment {

        public void preprocess() throws Exception {
        }

        public void process() throws Exception {
        }

        public void postprocess() throws Exception {
        }
    }

}
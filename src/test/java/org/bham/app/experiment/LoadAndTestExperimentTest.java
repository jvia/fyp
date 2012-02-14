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
public class LoadAndTestExperimentTest {

    public LoadAndTestExperimentTest() {
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
     * Test of preprocess method, of class LoadAndTestExperiment.
     */
    @Test
    public void testPreprocess() {
        System.out.println("preprocess");
        LoadAndTestExperiment instance = new LoadAndTestExperiment();
        instance.preprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of process method, of class LoadAndTestExperiment.
     */
    @Test
    public void testProcess() {
        System.out.println("process");
        LoadAndTestExperiment instance = new LoadAndTestExperiment();
        instance.process();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postprocess method, of class LoadAndTestExperiment.
     */
    @Test
    public void testPostprocess() {
        System.out.println("postprocess");
        LoadAndTestExperiment instance = new LoadAndTestExperiment();
        instance.postprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of call method, of class LoadAndTestExperiment.
     */
    @Test
    public void testCall() throws Exception {
        System.out.println("call");
        LoadAndTestExperiment instance = new LoadAndTestExperiment();
        Result expResult = null;
        Result result = instance.call();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
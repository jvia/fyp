/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.app.experiment;

import nu.xom.Element;
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
public class CastExperimentFactoryTest {

    public CastExperimentFactoryTest() {
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
     * Test of createExperiment method, of class CastExperimentFactory.
     */
    @Test
    public void testCreateExperiment() throws Exception {
        System.out.println("createExperiment");
        Element element = null;
        CastExperimentFactory instance = new CastExperimentFactory();
        Experiment expResult = null;
        Experiment result = instance.createExperiment(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
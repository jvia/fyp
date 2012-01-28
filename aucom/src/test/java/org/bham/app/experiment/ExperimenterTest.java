/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.app.experiment;

import java.io.File;
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
public class ExperimenterTest {

    public ExperimenterTest() {
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
     * Test of load method, of class Experimenter.
     */
    @Test
    public void testLoad() throws Exception {
        System.out.println("load");
        File xmlExperimentDescription = null;
        Experimenter instance = new Experimenter();
        instance.load(xmlExperimentDescription);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFactory method, of class Experimenter.
     */
    @Test
    public void testGetFactory() throws Exception {
        System.out.println("getFactory");
        Element experimentDefinition = null;
        Experimenter instance = new Experimenter();
        ExperimentFactory expResult = null;
        ExperimentFactory result = instance.getFactory(experimentDefinition);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class Experimenter.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        Experimenter instance = new Experimenter();
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Experimenter.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Experimenter.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
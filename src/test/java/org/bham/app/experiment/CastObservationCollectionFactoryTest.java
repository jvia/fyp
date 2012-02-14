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
public class CastObservationCollectionFactoryTest {

    public CastObservationCollectionFactoryTest() {
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
     * Test of createExperiment method, of class CastObservationCollectionFactory.
     */
    @Test
    public void testCreateExperiment() throws Exception {
        System.out.println("createExperiment");
        Element element = null;
        CastObservationCollectionFactory instance = new CastObservationCollectionFactory();
        Experiment expResult = null;
        Experiment result = instance.createExperiment(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObservationSize method, of class CastObservationCollectionFactory.
     */
    @Test
    public void testGetObservationSize() {
        System.out.println("getObservationSize");
        Element element = null;
        CastObservationCollectionFactory instance = new CastObservationCollectionFactory();
        int expResult = 0;
        int result = instance.getObservationSize(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
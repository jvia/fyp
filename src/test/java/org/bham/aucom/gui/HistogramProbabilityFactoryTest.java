/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui;

import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
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
public class HistogramProbabilityFactoryTest {

    public HistogramProbabilityFactoryTest() {
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
     * Test of create method, of class HistogramProbabilityFactory.
     */
    @Test
    public void testCreate_doubleArr() {
        System.out.println("create");
        double[] trainingValues = null;
        HistogramProbabilityFactory instance = null;
        ProbabilityDistribution expResult = null;
        ProbabilityDistribution result = instance.create(trainingValues);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class HistogramProbabilityFactory.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        HistogramProbabilityFactory instance = null;
        ProbabilityDistribution expResult = null;
        ProbabilityDistribution result = instance.create();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.models.probability;

import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
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
public class KernelDensityEstimateProbabilityFamilyFactoryTest {

    public KernelDensityEstimateProbabilityFamilyFactoryTest() {
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
     * Test of getFactory method, of class KernelDensityEstimateProbabilityFamilyFactory.
     */
    @Test
    public void testGetFactory() {
        System.out.println("getFactory");
        Double[] parameters = null;
        KernelDensityEstimateProbabilityFamilyFactory instance = new KernelDensityEstimateProbabilityFamilyFactory();
        KDEProbabilityFactory expResult = null;
        KDEProbabilityFactory result = instance.getFactory(parameters);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInputPanel method, of class KernelDensityEstimateProbabilityFamilyFactory.
     */
    @Test
    public void testGetInputPanel() {
        System.out.println("getInputPanel");
        KernelDensityEstimateProbabilityFamilyFactory instance = new KernelDensityEstimateProbabilityFamilyFactory();
        ProbabilityInputPanel expResult = null;
        ProbabilityInputPanel result = instance.getInputPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
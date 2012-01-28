/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.models.probability;

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
public class KDELogProbabilityDistributionFactoryTest {

    public KDELogProbabilityDistributionFactoryTest() {
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
     * Test of create method, of class KDELogProbabilityDistributionFactory.
     */
    @Test
    public void testCreate_doubleArr() {
        System.out.println("create");
        double[] trainingValues = null;
        KDELogProbabilityDistributionFactory instance = new KDELogProbabilityDistributionFactory();
        ProbabilityDistribution expResult = null;
        ProbabilityDistribution result = instance.create(trainingValues);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class KDELogProbabilityDistributionFactory.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        KDELogProbabilityDistributionFactory instance = new KDELogProbabilityDistributionFactory();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class KDELogProbabilityDistributionFactory.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        KDELogProbabilityDistributionFactory instance = new KDELogProbabilityDistributionFactory();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class KDELogProbabilityDistributionFactory.
     */
    @Test
    public void testCreate_0args() {
        System.out.println("create");
        KDELogProbabilityDistributionFactory instance = new KDELogProbabilityDistributionFactory();
        ProbabilityDistribution expResult = null;
        ProbabilityDistribution result = instance.create();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
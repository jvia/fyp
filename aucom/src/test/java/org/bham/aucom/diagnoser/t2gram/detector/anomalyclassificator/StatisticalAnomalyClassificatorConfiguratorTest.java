/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

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
public class StatisticalAnomalyClassificatorConfiguratorTest {

    public StatisticalAnomalyClassificatorConfiguratorTest() {
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
     * Test of configure method, of class StatisticalAnomalyClassificatorConfigurator.
     */
    @Test
    public void testConfigure() throws Exception {
        System.out.println("configure");
        AnomalyClassificator in = null;
        StatisticalAnomalyClassificatorConfigurator instance = new StatisticalAnomalyClassificatorConfigurator();
        instance.configure(in);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVariance method, of class StatisticalAnomalyClassificatorConfigurator.
     */
    @Test
    public void testGetVariance() {
        System.out.println("getVariance");
        StatisticalAnomalyClassificatorConfigurator instance = new StatisticalAnomalyClassificatorConfigurator();
        double expResult = 0.0;
        double result = instance.getVariance();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVariance method, of class StatisticalAnomalyClassificatorConfigurator.
     */
    @Test
    public void testSetVariance() {
        System.out.println("setVariance");
        double variance = 0.0;
        StatisticalAnomalyClassificatorConfigurator instance = new StatisticalAnomalyClassificatorConfigurator();
        instance.setVariance(variance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMean method, of class StatisticalAnomalyClassificatorConfigurator.
     */
    @Test
    public void testGetMean() {
        System.out.println("getMean");
        StatisticalAnomalyClassificatorConfigurator instance = new StatisticalAnomalyClassificatorConfigurator();
        double expResult = 0.0;
        double result = instance.getMean();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMean method, of class StatisticalAnomalyClassificatorConfigurator.
     */
    @Test
    public void testSetMean() {
        System.out.println("setMean");
        double mean = 0.0;
        StatisticalAnomalyClassificatorConfigurator instance = new StatisticalAnomalyClassificatorConfigurator();
        instance.setMean(mean);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
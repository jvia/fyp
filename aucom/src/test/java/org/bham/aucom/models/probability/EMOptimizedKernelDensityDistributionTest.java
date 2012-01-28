/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.models.probability;

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
public class EMOptimizedKernelDensityDistributionTest {

    public EMOptimizedKernelDensityDistributionTest() {
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
     * Test of update method, of class EMOptimizedKernelDensityDistribution.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        double[] val = null;
        EMOptimizedKernelDensityDistribution instance = new EMOptimizedKernelDensityDistribution();
        instance.update(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of optimize method, of class EMOptimizedKernelDensityDistribution.
     */
    @Test
    public void testOptimize() {
        System.out.println("optimize");
        EMOptimizedKernelDensityDistribution instance = new EMOptimizedKernelDensityDistribution();
        instance.optimize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
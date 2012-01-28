package org.bham.aucom.models.probability;


import java.util.HashMap;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class KernelDensityDistributionTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetProbabilityReturnsAllwaysSameValue(){
		double[] values = new double[100];
		for(int i=0; i < 100; i++){
			values[i] = Math.rint(Math.random()*100.0);
		}
		KernelDensityDistribution dist = new KernelDensityDistribution(1,values);
		double firstProbability = dist.getProbability(300);
		System.out.println(firstProbability);
		for(int i=0;i < 1000; i++){
			Assert.assertEquals(firstProbability, dist.getProbability(300));
		}
	}
	@Test
	public void testGetProbability(){
		double[] values = {1, 2};
        KernelDensityDistribution dist = new KernelDensityDistribution(1, values);
        int size = dist.getQueriedValues().size();
        dist.getProbability(1);
        Assert.assertEquals(size, dist.getQueriedValues().size());
        dist.getProbability(10000);
        Assert.assertEquals(size + 1, dist.getQueriedValues().size());
        dist.getProbability(10000);
        Assert.assertEquals(size + 1, dist.getQueriedValues().size());
	}
	@Test
	public void testGetEntropy(){
		double[] values = {1,2};
		KernelDensityDistribution dist = new KernelDensityDistribution(1,values);
	}

    /**
     * Test of log method, of class KernelDensityDistribution.
     */
    @Test
    public void testLog() {
        System.out.println("log");
        double value = 0.0;
        double base = 0.0;
        KernelDensityDistribution instance = null;
        double expResult = 0.0;
        double result = instance.log(value, base);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class KernelDensityDistribution.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        KernelDensityDistribution instance = null;
        String expResult = "";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class KernelDensityDistribution.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        double[] val = null;
        KernelDensityDistribution instance = null;
        instance.update(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCount method, of class KernelDensityDistribution.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        KernelDensityDistribution instance = null;
        int expResult = 0;
        int result = instance.getCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sampleProbability method, of class KernelDensityDistribution.
     */
    @Test
    public void testSampleProbability() {
        System.out.println("sampleProbability");
        KernelDensityDistribution instance = null;
        double[] expResult = null;
        double[] result = instance.sampleProbability();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of expectedValue method, of class KernelDensityDistribution.
     */
    @Test
    public void testExpectedValue() {
        System.out.println("expectedValue");
        KernelDensityDistribution instance = null;
        double expResult = 0.0;
        double result = instance.expectedValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of variance method, of class KernelDensityDistribution.
     */
    @Test
    public void testVariance() {
        System.out.println("variance");
        KernelDensityDistribution instance = null;
        double expResult = 0.0;
        double result = instance.variance();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sample method, of class KernelDensityDistribution.
     */
    @Test
    public void testSample() {
        System.out.println("sample");
        KernelDensityDistribution instance = null;
        double[] expResult = null;
        double[] result = instance.sample();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxProbability method, of class KernelDensityDistribution.
     */
    @Test
    public void testGetMaxProbability() {
        System.out.println("getMaxProbability");
        KernelDensityDistribution instance = null;
        double expResult = 0.0;
        double result = instance.getMaxProbability();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getQueriedValues method, of class KernelDensityDistribution.
     */
    @Test
    public void testGetQueriedValues() {
        System.out.println("getQueriedValues");
        KernelDensityDistribution instance = null;
        HashMap expResult = null;
        HashMap result = instance.getQueriedValues();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMaxSeenValue method, of class KernelDensityDistribution.
     */
    @Test
    public void testSetMaxSeenValue() {
        System.out.println("setMaxSeenValue");
        double maxSeenValue = 0.0;
        KernelDensityDistribution instance = null;
        instance.setMaxSeenValue(maxSeenValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxSeenValue method, of class KernelDensityDistribution.
     */
    @Test
    public void testGetMaxSeenValue() {
        System.out.println("getMaxSeenValue");
        KernelDensityDistribution instance = null;
        double expResult = 0.0;
        double result = instance.getMaxSeenValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

package org.bham.aucom.models.probability;


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
}

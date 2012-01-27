package org.bham.aucom.models.probability;
import static org.bham.aucom.util.Constants.LOWESTPROBABILITY;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

public class HistogramDistributionTest {
	HistogramDistribution dist;
	double binSize;
	@Before
	public void setUp() throws Exception {
		binSize  = 100.0;
		dist = new HistogramDistribution("test", binSize);
		
	}
	@Test
	public void testBinSize(){
		Assert.assertEquals(binSize, dist.getBinSize());
	}
	@Test
	public void testGetEntropy() {
		Assert.assertEquals(dist.getEntropy(), 0.0, 0.001);
		double[] values = {90};
//		dist.update(val)
		
	}

	@Test
	public void testGetProbability() {
		for(int i=0;i< 10000; i++){
			Assert.assertEquals(LOWESTPROBABILITY, dist.getProbability(Math.random()), LOWESTPROBABILITY);
			Assert.assertEquals(LOWESTPROBABILITY, dist.getProbability(Math.random()*10), LOWESTPROBABILITY);
			Assert.assertEquals(LOWESTPROBABILITY, dist.getProbability(Math.random()*1000), LOWESTPROBABILITY);
		}
		double[] values = {90, 190,290,390};
		dist.update(values);
		for(int i=0;i< 400; i++){
			Assert.assertEquals(0.25, dist.getProbability(i));
		}
		Assert.assertEquals(LOWESTPROBABILITY, dist.getProbability(400), LOWESTPROBABILITY);
	}
	
}

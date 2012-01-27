package org.bham.aucom.fts.tranform;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;
import org.bham.aucom.util.Constants;

public class TestModelTest {
	GenerateTemporalProbabilityFeature testModelNode = null;
	@Before
	public void setUp() throws Exception {
		testModelNode = new GenerateTemporalProbabilityFeature();
		testModelNode.setModel(new T2GramModelImp(new KDEProbabilityFactory()));
		testModelNode.getModel().addDistribution(1, 1, testModelNode.getModel().getDistributionFactory().create());
		testModelNode.getModel().addDistribution(1, 2, testModelNode.getModel().getDistributionFactory().create());
		testModelNode.getModel().addDistribution(2, 1, testModelNode.getModel().getDistributionFactory().create());
		testModelNode.getModel().addDistribution(2, 2, testModelNode.getModel().getDistributionFactory().create());
		
	}

	@Test
	public void testTest() {
		TemporalProbabilityFeature f = this.testModelNode.generate(TemporalDurationFeature.createRandomTemporalDurationFeature());
		System.out.println("TemporalProbabilityFeature " +f);
		for (double prob : f.getDurationProbabilities()){
			Assert.assertEquals(Constants.LOWESTPROBABILITY, prob, Constants.LOWESTPROBABILITY );
		}
	}

}

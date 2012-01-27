package org.bham.aucom.fts.tranform;

import java.util.LinkedHashMap;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;
import org.bham.aucom.util.Constants;

public class CalcEntropyAvgScoreTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testCalculateReturnsValidDouble() {
		TemporalProbabilityFeature tpf = null;
		CalcEntropyAvgScore ceAvgScore = null;
		Score s = null;
		tpf = TemporalProbabilityFeature.createRandomTemporalProbabilityFeature();
		ceAvgScore = new CalcEntropyAvgScore(new T2GramModelImp(new KDEProbabilityFactory()));
		s = ceAvgScore.calculate(tpf);
		Assert.assertFalse(Double.isNaN(s.getValue()));
	}
	
	
	@Test
	public void testCalculateSingleEntropyForZeroProbability(){
		CalcEntropyAvgScore ceAvgScore = new CalcEntropyAvgScore(new T2GramModelImp(new KDEProbabilityFactory()));
		TemporalProbabilityFeature tpf =createTPFWithZereoProbabilities(); 
		for(int i = 0 ;i<tpf.getPredecessors().size(); i++){
			DataType prede = tpf.getPredecessors().get(i);
			prede.setEventTypeId(101 + (int)Math.random()*100);
			double singleScoreValue = ceAvgScore.calculateSingleScoreValue(prede, tpf, ceAvgScore.calculateDenominator(0.0));
			System.out.println(singleScoreValue);
		}
		Score s = ceAvgScore.calculate(tpf);
		System.out.println(s);
	}
	@Test
	public void testCalculateSingleEntropyForLOWESTProbability(){
		CalcEntropyAvgScore ceAvgScore = new CalcEntropyAvgScore(new T2GramModelImp(new KDEProbabilityFactory()));
		TemporalProbabilityFeature tpf =createTPFWithLOWESTPROBABILITYProbabilities(); 
		for(int i = 0 ;i<tpf.getPredecessors().size(); i++){
			DataType prede = tpf.getPredecessors().get(i);
			double singleScoreValue = ceAvgScore.calculateSingleScoreValue(prede, tpf, ceAvgScore.calculateDenominator(0.0));
			System.out.println(singleScoreValue);
		}
		Score s = ceAvgScore.calculate(tpf);
		System.out.println(s);
	}
	@Test
	public void testCalculateEntropyWithoutPredecessors(){
		CalcEntropyAvgScore ceAvgScore = new CalcEntropyAvgScore(new T2GramModelImp(new KDEProbabilityFactory()));
		TemporalProbabilityFeature tpf =createTPFWithoutPredecessors(); 
		Score s = ceAvgScore.calculate(tpf);
		Assert.assertEquals(Constants.LOWESTPROBABILITY, s.getValue(), Constants.LOWESTPROBABILITY);
		System.out.println(s);
	}

	/*
	 * helper functions
	 */
	public TemporalProbabilityFeature createTPFWithZereoProbabilities(){
		TemporalProbabilityFeature tpf = TemporalProbabilityFeature.createRandomTemporalProbabilityFeature();
		LinkedHashMap<DataType, Double> predecessors = new LinkedHashMap<DataType, Double>();
		for(DataType dtp: tpf.getPredecessors()){
			predecessors.put(dtp, Double.valueOf(0.0d));
		}
		return new TemporalProbabilityFeature(tpf, predecessors);
	}
	public TemporalProbabilityFeature createTPFWithLOWESTPROBABILITYProbabilities(){
		TemporalProbabilityFeature tpf = TemporalProbabilityFeature.createRandomTemporalProbabilityFeature();
		LinkedHashMap<DataType, Double> predecessors = new LinkedHashMap<DataType, Double>();
		for(DataType dtp: tpf.getPredecessors()){
			predecessors.put(dtp, Double.valueOf(Constants.LOWESTPROBABILITY));
		}
		return new TemporalProbabilityFeature(tpf, predecessors);
	}
	public TemporalProbabilityFeature createTPFWithoutPredecessors(){
		TemporalProbabilityFeature tpf = TemporalProbabilityFeature.createRandomTemporalProbabilityFeature();
		LinkedHashMap<DataType, Double> predecessors = new LinkedHashMap<DataType, Double>();
		tpf.getPredecessors().clear();
		return new TemporalProbabilityFeature(tpf, predecessors);
	}
}

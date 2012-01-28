package org.bham.aucom.fts.tranform;

import java.util.LinkedHashMap;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;

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

    /**
     * Test of iTransform method, of class CalcEntropyAvgScore.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        TemporalProbabilityFeature arg0 = null;
        CalcEntropyAvgScore instance = new CalcEntropyAvgScore();
        Score expResult = null;
        Score result = instance.iTransform(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculate method, of class CalcEntropyAvgScore.
     */
    @Test
    public void testCalculate() {
        System.out.println("calculate");
        TemporalProbabilityFeature inData = null;
        CalcEntropyAvgScore instance = new CalcEntropyAvgScore();
        Score expResult = null;
        Score result = instance.calculate(inData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateScore method, of class CalcEntropyAvgScore.
     */
    @Test
    public void testCalculateScore() {
        System.out.println("calculateScore");
        TemporalProbabilityFeature inData = null;
        CalcEntropyAvgScore instance = new CalcEntropyAvgScore();
        Score expResult = null;
        Score result = instance.calculateScore(inData);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateDenominator method, of class CalcEntropyAvgScore.
     */
    @Test
    public void testCalculateDenominator() {
        System.out.println("calculateDenominator");
        double sum_entropy = 0.0;
        CalcEntropyAvgScore instance = new CalcEntropyAvgScore();
        double expResult = 0.0;
        double result = instance.calculateDenominator(sum_entropy);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateAbsoluteScoreValue method, of class CalcEntropyAvgScore.
     */
    @Test
    public void testCalculateAbsoluteScoreValue() {
        System.out.println("calculateAbsoluteScoreValue");
        TemporalProbabilityFeature currentData = null;
        double denominator = 0.0;
        CalcEntropyAvgScore instance = new CalcEntropyAvgScore();
        double expResult = 0.0;
        double result = instance.calculateAbsoluteScoreValue(currentData, denominator);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSingleScoreValue method, of class CalcEntropyAvgScore.
     */
    @Test
    public void testCalculateSingleScoreValue() {
        System.out.println("calculateSingleScoreValue");
        DataType predecessor = null;
        TemporalProbabilityFeature current = null;
        double denominator = 0.0;
        CalcEntropyAvgScore instance = new CalcEntropyAvgScore();
        double expResult = 0.0;
        double result = instance.calculateSingleScoreValue(predecessor, current, denominator);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alg_calculateSingleScore method, of class CalcEntropyAvgScore.
     */
    @Test
    public void testAlg_calculateSingleScore() {
        System.out.println("alg_calculateSingleScore");
        double probability = 0.0;
        double entropy = 0.0;
        double denominator = 0.0;
        CalcEntropyAvgScore instance = new CalcEntropyAvgScore();
        double expResult = 0.0;
        double result = instance.alg_calculateSingleScore(probability, entropy, denominator);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of normalize method, of class CalcEntropyAvgScore.
     */
    @Test
    public void testNormalize() {
        System.out.println("normalize");
        TemporalProbabilityFeature inData = null;
        double absoluteScore = 0.0;
        CalcEntropyAvgScore instance = new CalcEntropyAvgScore();
        double expResult = 0.0;
        double result = instance.normalize(inData, absoluteScore);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateSumEntropy method, of class CalcEntropyAvgScore.
     */
    @Test
    public void testCalculateSumEntropy() {
        System.out.println("calculateSumEntropy");
        TemporalProbabilityFeature inData = null;
        CalcEntropyAvgScore instance = new CalcEntropyAvgScore();
        double expResult = 0.0;
        double result = instance.calculateSumEntropy(inData);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of alg_calculateSumEntropy method, of class CalcEntropyAvgScore.
     */
    @Test
    public void testAlg_calculateSumEntropy() {
        System.out.println("alg_calculateSumEntropy");
        double[] values = null;
        CalcEntropyAvgScore instance = new CalcEntropyAvgScore();
        double expResult = 0.0;
        double result = instance.alg_calculateSumEntropy(values);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModel method, of class CalcEntropyAvgScore.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        T2GramModelI model = null;
        CalcEntropyAvgScore instance = new CalcEntropyAvgScore();
        instance.setModel(model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModel method, of class CalcEntropyAvgScore.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        CalcEntropyAvgScore instance = new CalcEntropyAvgScore();
        T2GramModelI expResult = null;
        T2GramModelI result = instance.getModel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

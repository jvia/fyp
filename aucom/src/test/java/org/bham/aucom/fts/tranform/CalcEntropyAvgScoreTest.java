package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.util.Constants;
import org.junit.Before;
import org.junit.Test;

import java.util.LinkedHashMap;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class CalcEntropyAvgScoreTest {

    @Before
    public void setUp() throws Exception {
    }

    /*
    * helper functions
    */
    public TemporalProbabilityFeature createTPFWithZereoProbabilities() {
        TemporalProbabilityFeature tpf = TemporalProbabilityFeature.createRandomTemporalProbabilityFeature();
        LinkedHashMap<DataType, Double> predecessors = new LinkedHashMap<DataType, Double>();
        for (DataType dtp : tpf.getPredecessors()) {
            predecessors.put(dtp, 0.0d);
        }
        return new TemporalProbabilityFeature(tpf, predecessors);
    }

    public TemporalProbabilityFeature createTPFWithLOWESTPROBABILITYProbabilities() {
        TemporalProbabilityFeature tpf = TemporalProbabilityFeature.createRandomTemporalProbabilityFeature();
        LinkedHashMap<DataType, Double> predecessors = new LinkedHashMap<DataType, Double>();
        for (DataType dtp : tpf.getPredecessors()) {
            predecessors.put(dtp, (double) Constants.LOWESTPROBABILITY);
        }
        return new TemporalProbabilityFeature(tpf, predecessors);
    }

    public TemporalProbabilityFeature createTPFWithoutPredecessors() {
        TemporalProbabilityFeature tpf = TemporalProbabilityFeature.createRandomTemporalProbabilityFeature();
        LinkedHashMap<DataType, Double> predecessors = new LinkedHashMap<DataType, Double>();
        tpf.getPredecessors().clear();
        return new TemporalProbabilityFeature(tpf, predecessors);
    }

    /**
     * Test of iTransform method, of class CalcEntropyAvgScore.
     *
     * @throws Exception
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
     * Test of calculateAbsoluteScoreValue method, of class
     * CalcEntropyAvgScore.
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

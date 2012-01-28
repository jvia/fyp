/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer;

import java.util.HashMap;
import java.util.LinkedList;
import org.bham.aucom.data.ClassificationTimeSeriesDescriptiveStatistics;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.StatisticalAnomalyClassificator;
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
public class ClassificatorOptimizationMethodTest {

    public ClassificatorOptimizationMethodTest() {
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
     * Test of initializeClassificators method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testInitializeClassificators() {
        System.out.println("initializeClassificators");
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        instance.initializeClassificators();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllowedFalsePositiveRate method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testGetAllowedFalsePositiveRate() {
        System.out.println("getAllowedFalsePositiveRate");
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        double expResult = 0.0;
        double result = instance.getAllowedFalsePositiveRate();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAllowedFalsePositiveRate method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testSetAllowedFalsePositiveRate() {
        System.out.println("setAllowedFalsePositiveRate");
        double allowedFalsePositiveRate = 0.0;
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        instance.setAllowedFalsePositiveRate(allowedFalsePositiveRate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBestClassificator method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testGetBestClassificator() {
        System.out.println("getBestClassificator");
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        StatisticalAnomalyClassificator expResult = null;
        StatisticalAnomalyClassificator result = instance.getBestClassificator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBestClassificator method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testSetBestClassificator() {
        System.out.println("setBestClassificator");
        StatisticalAnomalyClassificator bestClassificator = null;
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        instance.setBestClassificator(bestClassificator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentClassificator method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testGetCurrentClassificator() {
        System.out.println("getCurrentClassificator");
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        StatisticalAnomalyClassificator expResult = null;
        StatisticalAnomalyClassificator result = instance.getCurrentClassificator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentClassificator method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testSetCurrentClassificator() {
        System.out.println("setCurrentClassificator");
        StatisticalAnomalyClassificator currentClassificator = null;
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        instance.setCurrentClassificator(currentClassificator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatistic method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testGetStatistic() {
        System.out.println("getStatistic");
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        ClassificationTimeSeriesDescriptiveStatistics expResult = null;
        ClassificationTimeSeriesDescriptiveStatistics result = instance.getStatistic();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatistic method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testSetStatistic() {
        System.out.println("setStatistic");
        ClassificationTimeSeriesDescriptiveStatistics statistic = null;
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        instance.setStatistic(statistic);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBestFalsePositiveRate method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testGetBestFalsePositiveRate() {
        System.out.println("getBestFalsePositiveRate");
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        double expResult = 0.0;
        double result = instance.getBestFalsePositiveRate();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBestFalsePositiveRate method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testSetBestFalsePositiveRate() {
        System.out.println("setBestFalsePositiveRate");
        double bestFalsePositiveRate = 0.0;
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        instance.setBestFalsePositiveRate(bestFalsePositiveRate);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBestPositiveQuadraticDistance method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testGetBestPositiveQuadraticDistance() {
        System.out.println("getBestPositiveQuadraticDistance");
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        double expResult = 0.0;
        double result = instance.getBestPositiveQuadraticDistance();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBestPositiveQuadraticDistance method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testSetBestPositiveQuadraticDistance() {
        System.out.println("setBestPositiveQuadraticDistance");
        double bestPositiveQuadraticDistance = 0.0;
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        instance.setBestPositiveQuadraticDistance(bestPositiveQuadraticDistance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassificatorsToTest method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testGetClassificatorsToTest() {
        System.out.println("getClassificatorsToTest");
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        LinkedList expResult = null;
        LinkedList result = instance.getClassificatorsToTest();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassificatorsToTest method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testSetClassificatorsToTest() {
        System.out.println("setClassificatorsToTest");
        LinkedList<AnomalyClassificator> classificatorsToTest = null;
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        instance.setClassificatorsToTest(classificatorsToTest);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTestedClassificators method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testGetTestedClassificators() {
        System.out.println("getTestedClassificators");
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        HashMap expResult = null;
        HashMap result = instance.getTestedClassificators();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTestedClassificators method, of class ClassificatorOptimizationMethod.
     */
    @Test
    public void testSetTestedClassificators() {
        System.out.println("setTestedClassificators");
        HashMap<AnomalyClassificator, Double> testedClassificators = null;
        ClassificatorOptimizationMethod instance = new ClassificatorOptimizationMethod();
        instance.setTestedClassificators(testedClassificators);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
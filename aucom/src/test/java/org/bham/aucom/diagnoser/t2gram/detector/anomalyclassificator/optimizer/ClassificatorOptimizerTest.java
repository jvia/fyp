/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer;

import javax.swing.JPanel;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.detector.T2GramDetector;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
import org.bham.aucom.main.GraphStateChangedEvent;
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
public class ClassificatorOptimizerTest {

    public ClassificatorOptimizerTest() {
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
     * Test of setModel method, of class ClassificatorOptimizer.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        Model inModel = null;
        ClassificatorOptimizer instance = null;
        instance.setModel(inModel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimeseries method, of class ClassificatorOptimizer.
     */
    @Test
    public void testSetTimeseries() {
        System.out.println("setTimeseries");
        TimeSeries<Observation> inTs = null;
        ClassificatorOptimizer instance = null;
        instance.setTimeseries(inTs);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class ClassificatorOptimizer.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        ClassificatorOptimizer instance = null;
        instance.start();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class ClassificatorOptimizer.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        ClassificatorOptimizer instance = null;
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBestAnomalyClassificator method, of class ClassificatorOptimizer.
     */
    @Test
    public void testGetBestAnomalyClassificator() {
        System.out.println("getBestAnomalyClassificator");
        ClassificatorOptimizer instance = null;
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.getBestAnomalyClassificator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBestFalsPositveRate method, of class ClassificatorOptimizer.
     */
    @Test
    public void testGetBestFalsPositveRate() {
        System.out.println("getBestFalsPositveRate");
        ClassificatorOptimizer instance = null;
        double expResult = 0.0;
        double result = instance.getBestFalsPositveRate();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of graphStatusChanged method, of class ClassificatorOptimizer.
     */
    @Test
    public void testGraphStatusChanged() {
        System.out.println("graphStatusChanged");
        GraphStateChangedEvent evt = null;
        ClassificatorOptimizer instance = null;
        instance.graphStatusChanged(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStatusListener method, of class ClassificatorOptimizer.
     */
    @Test
    public void testAddStatusListener() {
        System.out.println("addStatusListener");
        ClassificatorOptimizerStatusListener listener = null;
        ClassificatorOptimizer instance = null;
        instance.addStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTimeseriesStatusListener method, of class ClassificatorOptimizer.
     */
    @Test
    public void testRemoveTimeseriesStatusListener() {
        System.out.println("removeTimeseriesStatusListener");
        ClassificatorOptimizerStatusListener listener = null;
        ClassificatorOptimizer instance = null;
        instance.removeTimeseriesStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllStatusListeners method, of class ClassificatorOptimizer.
     */
    @Test
    public void testRemoveAllStatusListeners() {
        System.out.println("removeAllStatusListeners");
        ClassificatorOptimizer instance = null;
        instance.removeAllStatusListeners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireClassificatorOptimizerEvent method, of class ClassificatorOptimizer.
     */
    @Test
    public void testFireClassificatorOptimizerEvent() {
        System.out.println("fireClassificatorOptimizerEvent");
        ClassificatorOptimizerStatusEvent evt = null;
        ClassificatorOptimizer instance = null;
        instance.fireClassificatorOptimizerEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPanel method, of class ClassificatorOptimizer.
     */
    @Test
    public void testGetPanel() {
        System.out.println("getPanel");
        ClassificatorOptimizer instance = null;
        JPanel expResult = null;
        JPanel result = instance.getPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetector method, of class ClassificatorOptimizer.
     */
    @Test
    public void testGetDetector() {
        System.out.println("getDetector");
        ClassificatorOptimizer instance = null;
        T2GramDetector expResult = null;
        T2GramDetector result = instance.getDetector();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDetector method, of class ClassificatorOptimizer.
     */
    @Test
    public void testSetDetector() {
        System.out.println("setDetector");
        T2GramDetector detector = null;
        ClassificatorOptimizer instance = null;
        instance.setDetector(detector);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copyBestClassificatorToDetector method, of class ClassificatorOptimizer.
     */
    @Test
    public void testCopyBestClassificatorToDetector() {
        System.out.println("copyBestClassificatorToDetector");
        ClassificatorOptimizer instance = null;
        instance.copyBestClassificatorToDetector();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preconditionsSatisfied method, of class ClassificatorOptimizer.
     */
    @Test
    public void testPreconditionsSatisfied() {
        System.out.println("preconditionsSatisfied");
        ClassificatorOptimizer instance = null;
        boolean expResult = false;
        boolean result = instance.preconditionsSatisfied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
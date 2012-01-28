/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector;

import javax.swing.JPanel;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.SlidingWindow;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
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
public class T2GramDetectorTest {

    public T2GramDetectorTest() {
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
     * Test of getPanel method, of class T2GramDetector.
     */
    @Test
    public void testGetPanel() {
        System.out.println("getPanel");
        T2GramDetector instance = new T2GramDetector();
        JPanel expResult = null;
        JPanel result = instance.getPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pause method, of class T2GramDetector.
     */
    @Test
    public void testPause() throws Exception {
        System.out.println("pause");
        T2GramDetector instance = new T2GramDetector();
        instance.pause();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class T2GramDetector.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        T2GramDetector instance = new T2GramDetector();
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModel method, of class T2GramDetector.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        Model m = null;
        T2GramDetector instance = new T2GramDetector();
        instance.setModel(m);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassificator method, of class T2GramDetector.
     */
    @Test
    public void testSetClassificator() {
        System.out.println("setClassificator");
        AnomalyClassificator classificatorToSet = null;
        T2GramDetector instance = new T2GramDetector();
        instance.setClassificator(classificatorToSet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassificator method, of class T2GramDetector.
     */
    @Test
    public void testGetClassificator() {
        System.out.println("getClassificator");
        T2GramDetector instance = new T2GramDetector();
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.getClassificator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSlidingWindow method, of class T2GramDetector.
     */
    @Test
    public void testSetSlidingWindow() {
        System.out.println("setSlidingWindow");
        SlidingWindow slidingWindow = null;
        T2GramDetector instance = new T2GramDetector();
        instance.setSlidingWindow(slidingWindow);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModel method, of class T2GramDetector.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        T2GramDetector instance = new T2GramDetector();
        T2GramModelI expResult = null;
        T2GramModelI result = instance.getModel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSlidingWindow method, of class T2GramDetector.
     */
    @Test
    public void testGetSlidingWindow() {
        System.out.println("getSlidingWindow");
        T2GramDetector instance = new T2GramDetector();
        SlidingWindow expResult = null;
        SlidingWindow result = instance.getSlidingWindow();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resume method, of class T2GramDetector.
     */
    @Test
    public void testResume() {
        System.out.println("resume");
        T2GramDetector instance = new T2GramDetector();
        instance.resume();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutput method, of class T2GramDetector.
     */
    @Test
    public void testGetOutput() {
        System.out.println("getOutput");
        T2GramDetector instance = new T2GramDetector();
        TimeSeries expResult = null;
        TimeSeries result = instance.getOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class T2GramDetector.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        TimeSeries<Observation> inTimeSeries = null;
        T2GramDetector instance = new T2GramDetector();
        instance.start(inTimeSeries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
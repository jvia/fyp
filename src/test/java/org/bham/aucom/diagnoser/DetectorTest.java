/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.source.IllegalStateChange;
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
public class DetectorTest {

    public DetectorTest() {
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
     * Test of start method, of class Detector.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        TimeSeries<Observation> inTimeSeries = null;
        Detector instance = new DetectorImpl();
        instance.start(inTimeSeries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pause method, of class Detector.
     */
    @Test
    public void testPause() throws Exception {
        System.out.println("pause");
        Detector instance = new DetectorImpl();
        instance.pause();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resume method, of class Detector.
     */
    @Test
    public void testResume() {
        System.out.println("resume");
        Detector instance = new DetectorImpl();
        instance.resume();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class Detector.
     */
    @Test
    public void testStop() throws Exception {
        System.out.println("stop");
        Detector instance = new DetectorImpl();
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStatusListener method, of class Detector.
     */
    @Test
    public void testAddStatusListener() {
        System.out.println("addStatusListener");
        DetectorStatusChangedListener listener = null;
        Detector instance = new DetectorImpl();
        instance.addStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeStatusListener method, of class Detector.
     */
    @Test
    public void testRemoveStatusListener() {
        System.out.println("removeStatusListener");
        DetectorStatusChangedListener listener = null;
        Detector instance = new DetectorImpl();
        instance.removeStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllStatusListeners method, of class Detector.
     */
    @Test
    public void testRemoveAllStatusListeners() {
        System.out.println("removeAllStatusListeners");
        Detector instance = new DetectorImpl();
        instance.removeAllStatusListeners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDetectionListener method, of class Detector.
     */
    @Test
    public void testAddDetectionListener() {
        System.out.println("addDetectionListener");
        DetectionListener listener = null;
        Detector instance = new DetectorImpl();
        instance.addDetectionListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeDetectionListener method, of class Detector.
     */
    @Test
    public void testRemoveDetectionListener() {
        System.out.println("removeDetectionListener");
        DetectionListener listener = null;
        Detector instance = new DetectorImpl();
        instance.removeDetectionListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllDetectionListeners method, of class Detector.
     */
    @Test
    public void testRemoveAllDetectionListeners() {
        System.out.println("removeAllDetectionListeners");
        Detector instance = new DetectorImpl();
        instance.removeAllDetectionListeners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOutput method, of class Detector.
     */
    @Test
    public void testGetOutput() {
        System.out.println("getOutput");
        Detector instance = new DetectorImpl();
        TimeSeries expResult = null;
        TimeSeries result = instance.getOutput();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DetectorImpl implements Detector {

        public void start(TimeSeries<Observation> inTimeSeries) throws ActionFailedException {
        }

        public void pause() throws ActionFailedException, IllegalStateChange {
        }

        public void resume() {
        }

        public void stop() throws ActionFailedException {
        }

        public void addStatusListener(DetectorStatusChangedListener listener) {
        }

        public void removeStatusListener(DetectorStatusChangedListener listener) {
        }

        public void removeAllStatusListeners() {
        }

        public void addDetectionListener(DetectionListener listener) {
        }

        public void removeDetectionListener(DetectionListener listener) {
        }

        public void removeAllDetectionListeners() {
        }

        public TimeSeries<Classification> getOutput() {
            return null;
        }
    }

}
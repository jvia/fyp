package org.bham.aucom.diagnoser;

import junit.framework.Assert;
import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.AbstractDetector.DetectorStatus;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.source.IllegalStateChange;
import org.junit.Before;
import org.junit.Test;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class AbstractDetectorTest {
    class TestDetector extends AbstractDetector {

        @Override
        public void start(TimeSeries<Observation> inTimeSeries) throws ActionFailedException {
        }

        @Override
        public void pause() throws ActionFailedException, IllegalStateChange {
            // TODO Auto-generated method stub

        }

        @Override
        public void resume() {
            // TODO Auto-generated method stub

        }

        @Override
        public void stop() throws ActionFailedException {
            // TODO Auto-generated method stub

        }

        @Override
        public TimeSeries<Classification> getOutput() {
            // TODO Auto-generated method stub
            return null;
        }

    }

    TestDetector dt;

    @Before
    public void setUp() throws Exception {
        dt = new TestDetector();
    }


    @Test
    public void testEventHandling() {
        final List<Integer> numbers = new ArrayList<Integer>();
        DetectorStatusChangedListener listener2 = new DetectorStatusChangedListener() {

            @SuppressWarnings("boxing")
            @Override
            public void handleDetectorStatusChangedEvent(DetectorStatusChangedEvent evt) {
                numbers.add(1);
            }
        };
        dt.addStatusListener(listener2);
        dt.addStatusListener(listener2);
        dt.fireDetectorStatusChangedEvent(new DetectorStatusChangedEvent(this, DetectorStatus.NOTREADY, DetectorStatus.READY));
        Assert.assertEquals(1, numbers.size());
    }

    /**
     * Test of setStatus method, of class AbstractDetector.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        DetectorStatus newStatus = null;
        AbstractDetector instance = new TestDetector();
        instance.setStatus(newStatus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addStatusListener method, of class AbstractDetector.
     */
    @Test
    public void testAddStatusListener() {
        System.out.println("addStatusListener");
        DetectorStatusChangedListener listener = null;
        AbstractDetector instance = new TestDetector();
        instance.addStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSinkStatusListenerRegistered method, of class
     * AbstractDetector.
     */
    @Test
    public void testIsSinkStatusListenerRegistered() {
        System.out.println("isSinkStatusListenerRegistered");
        DetectorStatusChangedListener listener = null;
        AbstractDetector instance = new TestDetector();
        boolean expResult = false;
        boolean result = instance.isSinkStatusListenerRegistered(listener);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeStatusListener method, of class AbstractDetector.
     */
    @Test
    public void testRemoveStatusListener() {
        System.out.println("removeStatusListener");
        DetectorStatusChangedListener listener = null;
        AbstractDetector instance = new TestDetector();
        instance.removeStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllStatusListeners method, of class AbstractDetector.
     */
    @Test
    public void testRemoveAllStatusListeners() {
        System.out.println("removeAllStatusListeners");
        AbstractDetector instance = new TestDetector();
        instance.removeAllStatusListeners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireDetectorEvent method, of class AbstractDetector.
     */
    @Test
    public void testFireDetectorEvent() {
        System.out.println("fireDetectorEvent");
        DetectorEvent evt = null;
        AbstractDetector instance = new TestDetector();
        instance.fireDetectorEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireDetectorStatusChangedEvent method, of class
     * AbstractDetector.
     */
    @Test
    public void testFireDetectorStatusChangedEvent() {
        System.out.println("fireDetectorStatusChangedEvent");
        DetectorStatusChangedEvent evt = null;
        AbstractDetector instance = new TestDetector();
        instance.fireDetectorStatusChangedEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDetectionListener method, of class AbstractDetector.
     */
    @Test
    public void testAddDetectionListener() {
        System.out.println("addDetectionListener");
        DetectionListener listener = null;
        AbstractDetector instance = new TestDetector();
        instance.addDetectionListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeDetectionListener method, of class AbstractDetector.
     */
    @Test
    public void testRemoveDetectionListener() {
        System.out.println("removeDetectionListener");
        DetectionListener listener = null;
        AbstractDetector instance = new TestDetector();
        instance.removeDetectionListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllDetectionListeners method, of class AbstractDetector.
     */
    @Test
    public void testRemoveAllDetectionListeners() {
        System.out.println("removeAllDetectionListeners");
        AbstractDetector instance = new TestDetector();
        instance.removeAllDetectionListeners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPanel method, of class AbstractDetector.
     */
    @Test
    public void testGetPanel() {
        System.out.println("getPanel");
        AbstractDetector instance = new TestDetector();
        JPanel expResult = null;
        JPanel result = instance.getPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentStatus method, of class AbstractDetector.
     */
    @Test
    public void testSetCurrentStatus() {
        System.out.println("setCurrentStatus");
        DetectorStatus currentStatus = null;
        AbstractDetector instance = new TestDetector();
        instance.setCurrentStatus(currentStatus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCurrentStatus method, of class AbstractDetector.
     */
    @Test
    public void testGetCurrentStatus() {
        System.out.println("getCurrentStatus");
        AbstractDetector instance = new TestDetector();
        DetectorStatus expResult = null;
        DetectorStatus result = instance.getCurrentStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

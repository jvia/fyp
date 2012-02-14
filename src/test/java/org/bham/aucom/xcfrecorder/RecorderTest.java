/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.xcfrecorder;

import java.io.File;
import javax.swing.JPanel;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeseriesStatusEvent;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.system.SystemConnection;
import org.bham.aucom.system.SystemConnectionInfo;
import org.bham.aucom.system.SystemConnectionStatusChangedEvent;
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
public class RecorderTest {

    public RecorderTest() {
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
     * Test of isReady method, of class Recorder.
     */
    @Test
    public void testIsReady() {
        System.out.println("isReady");
        Recorder instance = null;
        boolean expResult = false;
        boolean result = instance.isReady();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVisible method, of class Recorder.
     */
    @Test
    public void testSetVisible() {
        System.out.println("setVisible");
        boolean b = false;
        Recorder instance = null;
        instance.setVisible(b);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isVisible method, of class Recorder.
     */
    @Test
    public void testIsVisible() {
        System.out.println("isVisible");
        Recorder instance = null;
        boolean expResult = false;
        boolean result = instance.isVisible();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextFileName method, of class Recorder.
     */
    @Test
    public void testGetNextFileName() {
        System.out.println("getNextFileName");
        Recorder instance = null;
        String expResult = "";
        String result = instance.getNextFileName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of record method, of class Recorder.
     */
    @Test
    public void testRecord() throws Exception {
        System.out.println("record");
        Recorder instance = null;
        instance.record();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRecording method, of class Recorder.
     */
    @Test
    public void testIsRecording() {
        System.out.println("isRecording");
        Recorder instance = null;
        boolean expResult = false;
        boolean result = instance.isRecording();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class Recorder.
     */
    @Test
    public void testStop() throws Exception {
        System.out.println("stop");
        Recorder instance = null;
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Recorder.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Recorder.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextFileNumber method, of class Recorder.
     */
    @Test
    public void testGetNextFileNumber() {
        System.out.println("getNextFileNumber");
        File folder = null;
        Recorder instance = null;
        int expResult = 0;
        int result = instance.getNextFileNumber(folder);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFileName method, of class Recorder.
     */
    @Test
    public void testSetFileName() {
        System.out.println("setFileName");
        File fileName = null;
        Recorder instance = null;
        instance.setFileName(fileName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFileName method, of class Recorder.
     */
    @Test
    public void testGetFileName() {
        System.out.println("getFileName");
        Recorder instance = null;
        File expResult = null;
        File result = instance.getFileName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startNewFile method, of class Recorder.
     */
    @Test
    public void testStartNewFile() throws Exception {
        System.out.println("startNewFile");
        Recorder instance = null;
        instance.startNewFile();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of changeFolder method, of class Recorder.
     */
    @Test
    public void testChangeFolder() throws Exception {
        System.out.println("changeFolder");
        File newFolder = null;
        Recorder instance = null;
        instance.changeFolder(newFolder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFolder method, of class Recorder.
     */
    @Test
    public void testGetFolder() {
        System.out.println("getFolder");
        Recorder instance = null;
        File expResult = null;
        File result = instance.getFolder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberRecordedEvents method, of class Recorder.
     */
    @Test
    public void testGetNumberRecordedEvents() {
        System.out.println("getNumberRecordedEvents");
        Recorder instance = null;
        int expResult = 0;
        int result = instance.getNumberRecordedEvents();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimeSeries method, of class Recorder.
     */
    @Test
    public void testSetTimeSeries() {
        System.out.println("setTimeSeries");
        TimeSeries<Observation> bufferObservationTimeSeries = null;
        Recorder instance = null;
        instance.setTimeSeries(bufferObservationTimeSeries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeSeries method, of class Recorder.
     */
    @Test
    public void testGetTimeSeries() {
        System.out.println("getTimeSeries");
        Recorder instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.getTimeSeries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPanel method, of class Recorder.
     */
    @Test
    public void testGetPanel() {
        System.out.println("getPanel");
        Recorder instance = null;
        JPanel expResult = null;
        JPanel result = instance.getPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSystemConnection method, of class Recorder.
     */
    @Test
    public void testSetSystemConnection() {
        System.out.println("setSystemConnection");
        SystemConnection inSystemConnection = null;
        Recorder instance = null;
        instance.setSystemConnection(inSystemConnection);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSystemConnection method, of class Recorder.
     */
    @Test
    public void testGetSystemConnection() {
        System.out.println("getSystemConnection");
        Recorder instance = null;
        SystemConnection expResult = null;
        SystemConnection result = instance.getSystemConnection();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addRecorderStatusListener method, of class Recorder.
     */
    @Test
    public void testAddRecorderStatusListener() {
        System.out.println("addRecorderStatusListener");
        RecorderStatusListener listener = null;
        Recorder instance = null;
        instance.addRecorderStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeRecorderStatusListener method, of class Recorder.
     */
    @Test
    public void testRemoveRecorderStatusListener() {
        System.out.println("removeRecorderStatusListener");
        RecorderStatusListener listener = null;
        Recorder instance = null;
        instance.removeRecorderStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireRecorderStatusChangedEvent method, of class Recorder.
     */
    @Test
    public void testFireRecorderStatusChangedEvent() {
        System.out.println("fireRecorderStatusChangedEvent");
        RecorderStatusChangedEvent evt = null;
        Recorder instance = null;
        instance.fireRecorderStatusChangedEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInfo method, of class Recorder.
     */
    @Test
    public void testGetInfo() {
        System.out.println("getInfo");
        Recorder instance = null;
        SystemConnectionInfo expResult = null;
        SystemConnectionInfo result = instance.getInfo();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getState method, of class Recorder.
     */
    @Test
    public void testGetState() {
        System.out.println("getState");
        Recorder instance = null;
        RecorderState expResult = null;
        RecorderState result = instance.getState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOldState method, of class Recorder.
     */
    @Test
    public void testGetOldState() {
        System.out.println("getOldState");
        Recorder instance = null;
        RecorderState expResult = null;
        RecorderState result = instance.getOldState();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleSystemConnectionEvent method, of class Recorder.
     */
    @Test
    public void testHandleSystemConnectionEvent() {
        System.out.println("handleSystemConnectionEvent");
        SystemConnectionStatusChangedEvent event = null;
        Recorder instance = null;
        instance.handleSystemConnectionEvent(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of graphStatusChanged method, of class Recorder.
     */
    @Test
    public void testGraphStatusChanged() {
        System.out.println("graphStatusChanged");
        GraphStateChangedEvent evt = null;
        Recorder instance = null;
        instance.graphStatusChanged(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of timeseriesStatusChanged method, of class Recorder.
     */
    @Test
    public void testTimeseriesStatusChanged() {
        System.out.println("timeseriesStatusChanged");
        TimeseriesStatusEvent status = null;
        Recorder instance = null;
        instance.timeseriesStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
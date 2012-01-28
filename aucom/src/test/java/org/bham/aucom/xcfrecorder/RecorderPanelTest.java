/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.xcfrecorder;

import java.awt.event.ActionListener;
import javax.swing.JButton;
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
public class RecorderPanelTest {

    public RecorderPanelTest() {
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
     * Test of isGuiActivated method, of class RecorderPanel.
     */
    @Test
    public void testIsGuiActivated() {
        System.out.println("isGuiActivated");
        RecorderPanel instance = null;
        boolean expResult = false;
        boolean result = instance.isGuiActivated();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of synchronizeStatusWithRecorder method, of class RecorderPanel.
     */
    @Test
    public void testSynchronizeStatusWithRecorder() {
        System.out.println("synchronizeStatusWithRecorder");
        RecorderPanel instance = null;
        instance.synchronizeStatusWithRecorder();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of refreshGuiLabels method, of class RecorderPanel.
     */
    @Test
    public void testRefreshGuiLabels() {
        System.out.println("refreshGuiLabels");
        RecorderPanel instance = null;
        instance.refreshGuiLabels();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeNavigationButton method, of class RecorderPanel.
     */
    @Test
    public void testMakeNavigationButton() {
        System.out.println("makeNavigationButton");
        String imgLocation = "";
        String actionCommand = "";
        String toolTipText = "";
        String altText = "";
        ActionListener buttonActionListener = null;
        RecorderPanel instance = null;
        JButton expResult = null;
        JButton result = instance.makeNavigationButton(imgLocation, actionCommand, toolTipText, altText, buttonActionListener);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setRecorder method, of class RecorderPanel.
     */
    @Test
    public void testSetRecorder() {
        System.out.println("setRecorder");
        Recorder recorder = null;
        RecorderPanel instance = null;
        instance.setRecorder(recorder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getRecorder method, of class RecorderPanel.
     */
    @Test
    public void testGetRecorder() {
        System.out.println("getRecorder");
        RecorderPanel instance = null;
        Recorder expResult = null;
        Recorder result = instance.getRecorder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleRecorderStatusEvent method, of class RecorderPanel.
     */
    @Test
    public void testHandleRecorderStatusEvent() {
        System.out.println("handleRecorderStatusEvent");
        RecorderStatusChangedEvent evt = null;
        RecorderPanel instance = null;
        instance.handleRecorderStatusEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
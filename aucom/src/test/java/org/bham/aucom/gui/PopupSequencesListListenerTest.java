/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui;

import java.awt.event.MouseEvent;
import javax.swing.JList;
import org.bham.aucom.gui.charts.ProbabilityChartFrame;
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
public class PopupSequencesListListenerTest {

    public PopupSequencesListListenerTest() {
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
     * Test of mousePressed method, of class PopupSequencesListListener.
     */
    @Test
    public void testMousePressed() {
        System.out.println("mousePressed");
        MouseEvent arg0 = null;
        PopupSequencesListListener instance = null;
        instance.mousePressed(arg0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of mouseReleased method, of class PopupSequencesListListener.
     */
    @Test
    public void testMouseReleased() {
        System.out.println("mouseReleased");
        MouseEvent arg0 = null;
        PopupSequencesListListener instance = null;
        instance.mouseReleased(arg0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of maybePopup method, of class PopupSequencesListListener.
     */
    @Test
    public void testMaybePopup() {
        System.out.println("maybePopup");
        MouseEvent arg0 = null;
        PopupSequencesListListener instance = null;
        instance.maybePopup(arg0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFrame method, of class PopupSequencesListListener.
     */
    @Test
    public void testSetFrame() {
        System.out.println("setFrame");
        ProbabilityChartFrame frame = null;
        PopupSequencesListListener instance = null;
        instance.setFrame(frame);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFrame method, of class PopupSequencesListListener.
     */
    @Test
    public void testGetFrame() {
        System.out.println("getFrame");
        PopupSequencesListListener instance = null;
        ProbabilityChartFrame expResult = null;
        ProbabilityChartFrame result = instance.getFrame();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSequenceList method, of class PopupSequencesListListener.
     */
    @Test
    public void testSetSequenceList() {
        System.out.println("setSequenceList");
        JList sequenceList = null;
        PopupSequencesListListener instance = null;
        instance.setSequenceList(sequenceList);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSequenceList method, of class PopupSequencesListListener.
     */
    @Test
    public void testGetSequenceList() {
        System.out.println("getSequenceList");
        PopupSequencesListListener instance = null;
        JList expResult = null;
        JList result = instance.getSequenceList();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
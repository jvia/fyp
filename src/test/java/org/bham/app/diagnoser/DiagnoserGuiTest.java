/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.app.diagnoser;

import java.awt.event.ActionListener;
import javax.swing.JButton;
import org.bham.aucom.Presentable;
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
public class DiagnoserGuiTest {

    public DiagnoserGuiTest() {
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
     * Test of registerPresentable method, of class DiagnoserGui.
     */
    @Test
    public void testRegisterPresentable() {
        System.out.println("registerPresentable");
        Presentable newPresentable = null;
        DiagnoserGui instance = null;
        instance.registerPresentable(newPresentable);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deregisterPresentable method, of class DiagnoserGui.
     */
    @Test
    public void testDeregisterPresentable() {
        System.out.println("deregisterPresentable");
        Presentable newPresentable = null;
        DiagnoserGui instance = null;
        instance.deregisterPresentable(newPresentable);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of makeNavigationButton method, of class DiagnoserGui.
     */
    @Test
    public void testMakeNavigationButton() {
        System.out.println("makeNavigationButton");
        String imgLocation = "";
        String actionCommand = "";
        String toolTipText = "";
        String altText = "";
        ActionListener buttonActionListener = null;
        DiagnoserGui instance = null;
        JButton expResult = null;
        JButton result = instance.makeNavigationButton(imgLocation, actionCommand, toolTipText, altText, buttonActionListener);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class DiagnoserGui.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        DiagnoserGui.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
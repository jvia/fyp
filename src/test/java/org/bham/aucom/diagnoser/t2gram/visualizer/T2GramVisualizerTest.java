/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.visualizer;

import javax.swing.JPanel;
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
public class T2GramVisualizerTest {

    public T2GramVisualizerTest() {
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
     * Test of start method, of class T2GramVisualizer.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        T2GramVisualizer instance = null;
        instance.start();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPanel method, of class T2GramVisualizer.
     */
    @Test
    public void testGetPanel() {
        System.out.println("getPanel");
        T2GramVisualizer instance = null;
        JPanel expResult = null;
        JPanel result = instance.getPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of scoreChartIsVisisble method, of class T2GramVisualizer.
     */
    @Test
    public void testScoreChartIsVisisble() {
        System.out.println("scoreChartIsVisisble");
        T2GramVisualizer instance = null;
        boolean expResult = false;
        boolean result = instance.scoreChartIsVisisble();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of showScoreChart method, of class T2GramVisualizer.
     */
    @Test
    public void testShowScoreChart() {
        System.out.println("showScoreChart");
        T2GramVisualizer instance = null;
        instance.showScoreChart();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hideScoreChart method, of class T2GramVisualizer.
     */
    @Test
    public void testHideScoreChart() {
        System.out.println("hideScoreChart");
        T2GramVisualizer instance = null;
        instance.hideScoreChart();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
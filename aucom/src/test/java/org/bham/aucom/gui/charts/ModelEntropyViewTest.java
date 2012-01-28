/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui.charts;

import javax.swing.JFrame;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.category.DefaultCategoryDataset;
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
public class ModelEntropyViewTest {

    public ModelEntropyViewTest() {
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
     * Test of main method, of class ModelEntropyView.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ModelEntropyView.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFrame method, of class ModelEntropyView.
     */
    @Test
    public void testSetFrame() {
        System.out.println("setFrame");
        JFrame frame = null;
        ModelEntropyView instance = null;
        instance.setFrame(frame);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFrame method, of class ModelEntropyView.
     */
    @Test
    public void testGetFrame() {
        System.out.println("getFrame");
        ModelEntropyView instance = null;
        JFrame expResult = null;
        JFrame result = instance.getFrame();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setPanel method, of class ModelEntropyView.
     */
    @Test
    public void testSetPanel() {
        System.out.println("setPanel");
        ChartPanel panel = null;
        ModelEntropyView instance = null;
        instance.setPanel(panel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPanel method, of class ModelEntropyView.
     */
    @Test
    public void testGetPanel() {
        System.out.println("getPanel");
        ModelEntropyView instance = null;
        ChartPanel expResult = null;
        ChartPanel result = instance.getPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChart method, of class ModelEntropyView.
     */
    @Test
    public void testSetChart() {
        System.out.println("setChart");
        JFreeChart chart = null;
        ModelEntropyView instance = null;
        instance.setChart(chart);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChart method, of class ModelEntropyView.
     */
    @Test
    public void testGetChart() {
        System.out.println("getChart");
        ModelEntropyView instance = null;
        JFreeChart expResult = null;
        JFreeChart result = instance.getChart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataset method, of class ModelEntropyView.
     */
    @Test
    public void testSetDataset() {
        System.out.println("setDataset");
        DefaultCategoryDataset dataset = null;
        ModelEntropyView instance = null;
        instance.setDataset(dataset);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataset method, of class ModelEntropyView.
     */
    @Test
    public void testGetDataset() {
        System.out.println("getDataset");
        ModelEntropyView instance = null;
        DefaultCategoryDataset expResult = null;
        DefaultCategoryDataset result = instance.getDataset();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
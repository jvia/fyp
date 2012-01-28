/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui.charts;

import java.util.ArrayList;
import java.util.List;
import javax.swing.event.ListDataEvent;
import org.bham.aucom.data.Score;
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
public class AnomalyBarChartFrameTest {

    public AnomalyBarChartFrameTest() {
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
     * Test of setBarChart method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testSetBarChart() {
        System.out.println("setBarChart");
        JFreeChart barChart = null;
        AnomalyBarChartFrame instance = null;
        instance.setBarChart(barChart);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBarChart method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testGetBarChart() {
        System.out.println("getBarChart");
        AnomalyBarChartFrame instance = null;
        JFreeChart expResult = null;
        JFreeChart result = instance.getBarChart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataSet method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testSetDataSet() {
        System.out.println("setDataSet");
        DefaultCategoryDataset dataSet = null;
        AnomalyBarChartFrame instance = null;
        instance.setDataSet(dataSet);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataSet method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testGetDataSet() {
        System.out.println("getDataSet");
        AnomalyBarChartFrame instance = null;
        DefaultCategoryDataset expResult = null;
        DefaultCategoryDataset result = instance.getDataSet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateBarchartOutput method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testGenerateBarchartOutput() {
        System.out.println("generateBarchartOutput");
        ArrayList<Score> list = null;
        int binSize = 0;
        AnomalyBarChartFrame instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.generateBarchartOutput(list, binSize);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addValue method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testAddValue() {
        System.out.println("addValue");
        Score inScore = null;
        AnomalyBarChartFrame instance = null;
        instance.addValue(inScore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addValues method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testAddValues() {
        System.out.println("addValues");
        List<Score> inScores = null;
        AnomalyBarChartFrame instance = null;
        instance.addValues(inScores);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of syncDataWithDataSet method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testSyncDataWithDataSet() {
        System.out.println("syncDataWithDataSet");
        AnomalyBarChartFrame instance = null;
        instance.syncDataWithDataSet();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AnomalyBarChartFrame.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setData method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        ArrayList<Score> data = null;
        AnomalyBarChartFrame instance = null;
        instance.setData(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        AnomalyBarChartFrame instance = null;
        List expResult = null;
        List result = instance.getData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contentsChanged method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testContentsChanged() {
        System.out.println("contentsChanged");
        ListDataEvent arg0 = null;
        AnomalyBarChartFrame instance = null;
        instance.contentsChanged(arg0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intervalAdded method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testIntervalAdded() {
        System.out.println("intervalAdded");
        ListDataEvent arg0 = null;
        AnomalyBarChartFrame instance = null;
        instance.intervalAdded(arg0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intervalRemoved method, of class AnomalyBarChartFrame.
     */
    @Test
    public void testIntervalRemoved() {
        System.out.println("intervalRemoved");
        ListDataEvent arg0 = null;
        AnomalyBarChartFrame instance = null;
        instance.intervalRemoved(arg0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
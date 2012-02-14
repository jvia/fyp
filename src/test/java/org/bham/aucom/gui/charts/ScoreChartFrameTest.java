/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui.charts;

import java.io.File;
import java.util.List;
import javax.swing.event.ListDataEvent;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.util.MonitorableArrayList;
import org.jfree.chart.JFreeChart;
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
public class ScoreChartFrameTest {

    public ScoreChartFrameTest() {
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
     * Test of syncDataWithDataSet method, of class ScoreChartFrame.
     */
    @Test
    public void testSyncDataWithDataSet() {
        System.out.println("syncDataWithDataSet");
        ScoreChartFrame instance = null;
        instance.syncDataWithDataSet();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getHideableScores method, of class ScoreChartFrame.
     */
    @Test
    public void testGetHideableScores() {
        System.out.println("getHideableScores");
        ScoreChartFrame instance = null;
        List expResult = null;
        List result = instance.getHideableScores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDisplayableScores method, of class ScoreChartFrame.
     */
    @Test
    public void testGetDisplayableScores() {
        System.out.println("getDisplayableScores");
        ScoreChartFrame instance = null;
        List expResult = null;
        List result = instance.getDisplayableScores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScoresNotInRange method, of class ScoreChartFrame.
     */
    @Test
    public void testGetScoresNotInRange() {
        System.out.println("getScoresNotInRange");
        List<Score> data = null;
        int from = 0;
        int to = 0;
        ScoreChartFrame instance = null;
        List expResult = null;
        List result = instance.getScoresNotInRange(data, from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScoresInRange method, of class ScoreChartFrame.
     */
    @Test
    public void testGetScoresInRange() {
        System.out.println("getScoresInRange");
        List<Score> data = null;
        int from = 0;
        int to = 0;
        ScoreChartFrame instance = null;
        List expResult = null;
        List result = instance.getScoresInRange(data, from, to);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contentsChanged method, of class ScoreChartFrame.
     */
    @Test
    public void testContentsChanged() {
        System.out.println("contentsChanged");
        ListDataEvent e = null;
        ScoreChartFrame instance = null;
        instance.contentsChanged(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSliderRanges method, of class ScoreChartFrame.
     */
    @Test
    public void testUpdateSliderRanges() {
        System.out.println("updateSliderRanges");
        ScoreChartFrame instance = null;
        instance.updateSliderRanges();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intervalAdded method, of class ScoreChartFrame.
     */
    @Test
    public void testIntervalAdded() {
        System.out.println("intervalAdded");
        ListDataEvent e = null;
        ScoreChartFrame instance = null;
        instance.intervalAdded(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intervalRemoved method, of class ScoreChartFrame.
     */
    @Test
    public void testIntervalRemoved() {
        System.out.println("intervalRemoved");
        ListDataEvent e = null;
        ScoreChartFrame instance = null;
        instance.intervalRemoved(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initChart method, of class ScoreChartFrame.
     */
    @Test
    public void testInitChart() {
        System.out.println("initChart");
        MonitorableArrayList<Score> inData = null;
        ScoreChartFrame instance = null;
        instance.initChart(inData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeV method, of class ScoreChartFrame.
     */
    @Test
    public void testRemoveV() {
        System.out.println("removeV");
        Score inScore = null;
        ScoreChartFrame instance = null;
        instance.removeV(inScore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addV method, of class ScoreChartFrame.
     */
    @Test
    public void testAddV() {
        System.out.println("addV");
        Score inScore = null;
        ScoreChartFrame instance = null;
        instance.addV(inScore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addValue method, of class ScoreChartFrame.
     */
    @Test
    public void testAddValue() {
        System.out.println("addValue");
        Score inScore = null;
        ScoreChartFrame instance = null;
        instance.addValue(inScore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeValue method, of class ScoreChartFrame.
     */
    @Test
    public void testRemoveValue() {
        System.out.println("removeValue");
        Score inScore = null;
        ScoreChartFrame instance = null;
        instance.removeValue(inScore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addValues method, of class ScoreChartFrame.
     */
    @Test
    public void testAddValues() {
        System.out.println("addValues");
        List<Score> inScores = null;
        ScoreChartFrame instance = null;
        instance.addValues(inScores);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChart method, of class ScoreChartFrame.
     */
    @Test
    public void testSetChart() {
        System.out.println("setChart");
        JFreeChart chart = null;
        ScoreChartFrame instance = null;
        instance.setChart(chart);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChart method, of class ScoreChartFrame.
     */
    @Test
    public void testGetChart() {
        System.out.println("getChart");
        ScoreChartFrame instance = null;
        JFreeChart expResult = null;
        JFreeChart result = instance.getChart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class ScoreChartFrame.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        ScoreChartFrame instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of load method, of class ScoreChartFrame.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        File f = null;
        ScoreChartFrame instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.load(f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class ScoreChartFrame.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ScoreChartFrame.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
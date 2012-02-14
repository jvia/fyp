/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui.charts;

import javax.swing.event.ListDataEvent;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.jfree.chart.JFreeChart;
import org.jfree.data.Range;
import org.jfree.data.xy.XYSeries;
import org.jfree.data.xy.XYSeriesCollection;
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
public class AucomChartTest {

    public AucomChartTest() {
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
     * Test of registerData method, of class AucomChart.
     */
    @Test
    public void testRegisterData() {
        System.out.println("registerData");
        TimeSeries<Score> sequence = null;
        AucomChart instance = null;
        instance.registerData(sequence);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unregisterData method, of class AucomChart.
     */
    @Test
    public void testUnregisterData() {
        System.out.println("unregisterData");
        TimeSeries<Score> sequence = null;
        AucomChart instance = null;
        instance.unregisterData(sequence);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startUpdating method, of class AucomChart.
     */
    @Test
    public void testStartUpdating() {
        System.out.println("startUpdating");
        AucomChart instance = null;
        instance.startUpdating();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDataToseriesInTimespan method, of class AucomChart.
     */
    @Test
    public void testAddDataToseriesInTimespan() {
        System.out.println("addDataToseriesInTimespan");
        TimeSeries<Score> sequence = null;
        int from = 0;
        int to = 0;
        AucomChart instance = null;
        instance.addDataToseriesInTimespan(sequence, from, to);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeDataFromSeries method, of class AucomChart.
     */
    @Test
    public void testRemoveDataFromSeries() {
        System.out.println("removeDataFromSeries");
        TimeSeries<Score> sequence = null;
        int from = 0;
        int to = 0;
        AucomChart instance = null;
        instance.removeDataFromSeries(sequence, from, to);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVisibleIntervalDomainAxis method, of class AucomChart.
     */
    @Test
    public void testGetVisibleIntervalDomainAxis() {
        System.out.println("getVisibleIntervalDomainAxis");
        AucomChart instance = null;
        Range expResult = null;
        Range result = instance.getVisibleIntervalDomainAxis();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScoreXYSeries method, of class AucomChart.
     */
    @Test
    public void testGetScoreXYSeries() {
        System.out.println("getScoreXYSeries");
        TimeSeries<Score> sequence = null;
        AucomChart instance = null;
        XYSeries expResult = null;
        XYSeries result = instance.getScoreXYSeries(sequence);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScoreSequence method, of class AucomChart.
     */
    @Test
    public void testGetScoreSequence() {
        System.out.println("getScoreSequence");
        XYSeries ser = null;
        AucomChart instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.getScoreSequence(ser);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeline method, of class AucomChart.
     */
    @Test
    public void testRemoveline() {
        System.out.println("removeline");
        AucomChart instance = null;
        instance.removeline();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of drawLine method, of class AucomChart.
     */
    @Test
    public void testDrawLine() {
        System.out.println("drawLine");
        AucomChart instance = null;
        instance.drawLine();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDomainAxis method, of class AucomChart.
     */
    @Test
    public void testUpdateDomainAxis() {
        System.out.println("updateDomainAxis");
        AucomChart instance = null;
        instance.updateDomainAxis();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSliderRanges method, of class AucomChart.
     */
    @Test
    public void testUpdateSliderRanges() {
        System.out.println("updateSliderRanges");
        AucomChart instance = null;
        instance.updateSliderRanges();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initChart method, of class AucomChart.
     */
    @Test
    public void testInitChart() {
        System.out.println("initChart");
        AucomChart instance = null;
        instance.initChart();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChart method, of class AucomChart.
     */
    @Test
    public void testSetChart() {
        System.out.println("setChart");
        JFreeChart chart = null;
        AucomChart instance = null;
        instance.setChart(chart);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChart method, of class AucomChart.
     */
    @Test
    public void testGetChart() {
        System.out.println("getChart");
        AucomChart instance = null;
        JFreeChart expResult = null;
        JFreeChart result = instance.getChart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class AucomChart.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        AucomChart instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setThresholdValue method, of class AucomChart.
     */
    @Test
    public void testSetThresholdValue() {
        System.out.println("setThresholdValue");
        double thresholdValue = 0.0;
        AucomChart instance = null;
        instance.setThresholdValue(thresholdValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThresholdValue method, of class AucomChart.
     */
    @Test
    public void testGetThresholdValue() {
        System.out.println("getThresholdValue");
        AucomChart instance = null;
        double expResult = 0.0;
        double result = instance.getThresholdValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contentsChanged method, of class AucomChart.
     */
    @Test
    public void testContentsChanged() {
        System.out.println("contentsChanged");
        ListDataEvent e = null;
        AucomChart instance = null;
        instance.contentsChanged(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of info method, of class AucomChart.
     */
    @Test
    public void testInfo() {
        System.out.println("info");
        String msg = "";
        AucomChart instance = null;
        instance.info(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of severe method, of class AucomChart.
     */
    @Test
    public void testSevere() {
        System.out.println("severe");
        String msg = "";
        AucomChart instance = null;
        instance.severe(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class AucomChart.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        AucomChart instance = null;
        String expResult = "";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDataset method, of class AucomChart.
     */
    @Test
    public void testSetDataset() {
        System.out.println("setDataset");
        XYSeriesCollection dataset = null;
        AucomChart instance = null;
        instance.setDataset(dataset);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataset method, of class AucomChart.
     */
    @Test
    public void testGetDataset() {
        System.out.println("getDataset");
        AucomChart instance = null;
        XYSeriesCollection expResult = null;
        XYSeriesCollection result = instance.getDataset();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
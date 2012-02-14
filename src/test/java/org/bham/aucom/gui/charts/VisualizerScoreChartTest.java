/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui.charts;

import java.util.concurrent.BlockingQueue;
import javax.swing.event.ListDataEvent;
import net.sf.xcf.fts.Triple;
import org.bham.aucom.data.Classification;
import org.bham.aucom.data.SystemFaultStatus;
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
public class VisualizerScoreChartTest {

    public VisualizerScoreChartTest() {
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
     * Test of main method, of class VisualizerScoreChart.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        VisualizerScoreChart.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of registerData method, of class VisualizerScoreChart.
     */
    @Test
    public void testRegisterData() {
        System.out.println("registerData");
        BlockingQueue<Triple<Long, Double, SystemFaultStatus>> input = null;
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.registerData(input);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unregisterData method, of class VisualizerScoreChart.
     */
    @Test
    public void testUnregisterData() {
        System.out.println("unregisterData");
        BlockingQueue<Triple<Long, Double, SystemFaultStatus>> input = null;
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.unregisterData(input);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of startUpdating method, of class VisualizerScoreChart.
     */
    @Test
    public void testStartUpdating() {
        System.out.println("startUpdating");
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.startUpdating();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateDomainAxis method, of class VisualizerScoreChart.
     */
    @Test
    public void testUpdateDomainAxis() {
        System.out.println("updateDomainAxis");
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.updateDomainAxis();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reportIfYourAreOnEventDispatcherThread method, of class VisualizerScoreChart.
     */
    @Test
    public void testReportIfYourAreOnEventDispatcherThread() {
        System.out.println("reportIfYourAreOnEventDispatcherThread");
        String str = "";
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.reportIfYourAreOnEventDispatcherThread(str);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateSliderRanges method, of class VisualizerScoreChart.
     */
    @Test
    public void testUpdateSliderRanges() {
        System.out.println("updateSliderRanges");
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.updateSliderRanges();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initChart method, of class VisualizerScoreChart.
     */
    @Test
    public void testInitChart() {
        System.out.println("initChart");
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.initChart();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setChart method, of class VisualizerScoreChart.
     */
    @Test
    public void testSetChart() {
        System.out.println("setChart");
        JFreeChart chart = null;
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.setChart(chart);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getChart method, of class VisualizerScoreChart.
     */
    @Test
    public void testGetChart() {
        System.out.println("getChart");
        VisualizerScoreChart instance = new VisualizerScoreChart();
        JFreeChart expResult = null;
        JFreeChart result = instance.getChart();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class VisualizerScoreChart.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        VisualizerScoreChart instance = new VisualizerScoreChart();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getThresholdValue method, of class VisualizerScoreChart.
     */
    @Test
    public void testGetThresholdValue() {
        System.out.println("getThresholdValue");
        VisualizerScoreChart instance = new VisualizerScoreChart();
        double expResult = 0.0;
        double result = instance.getThresholdValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of contentsChanged method, of class VisualizerScoreChart.
     */
    @Test
    public void testContentsChanged() {
        System.out.println("contentsChanged");
        ListDataEvent e = null;
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.contentsChanged(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of info method, of class VisualizerScoreChart.
     */
    @Test
    public void testInfo() {
        System.out.println("info");
        String msg = "";
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.info(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of severe method, of class VisualizerScoreChart.
     */
    @Test
    public void testSevere() {
        System.out.println("severe");
        String msg = "";
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.severe(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of annotate_FAULTINDUCED_IfNecessary method, of class VisualizerScoreChart.
     */
    @Test
    public void testAnnotate_FAULTINDUCED_IfNecessary() {
        System.out.println("annotate_FAULTINDUCED_IfNecessary");
        Classification classification = null;
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.annotate_FAULTINDUCED_IfNecessary(classification);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of annotateFault_END_IfNecessary method, of class VisualizerScoreChart.
     */
    @Test
    public void testAnnotateFault_END_IfNecessary() {
        System.out.println("annotateFault_END_IfNecessary");
        Classification classification = null;
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.annotateFault_END_IfNecessary(classification);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of annotateFault_BEGIN_IfNecessary method, of class VisualizerScoreChart.
     */
    @Test
    public void testAnnotateFault_BEGIN_IfNecessary() {
        System.out.println("annotateFault_BEGIN_IfNecessary");
        Classification classification = null;
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.annotateFault_BEGIN_IfNecessary(classification);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class VisualizerScoreChart.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCurrentTimeSeriesFaulty method, of class VisualizerScoreChart.
     */
    @Test
    public void testSetCurrentTimeSeriesFaulty() {
        System.out.println("setCurrentTimeSeriesFaulty");
        boolean isCurrentTimeSeriesFaulty = false;
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.setCurrentTimeSeriesFaulty(isCurrentTimeSeriesFaulty);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isCurrentTimeSeriesFaulty method, of class VisualizerScoreChart.
     */
    @Test
    public void testIsCurrentTimeSeriesFaulty() {
        System.out.println("isCurrentTimeSeriesFaulty");
        VisualizerScoreChart instance = new VisualizerScoreChart();
        boolean expResult = false;
        boolean result = instance.isCurrentTimeSeriesFaulty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isReady method, of class VisualizerScoreChart.
     */
    @Test
    public void testIsReady() {
        System.out.println("isReady");
        VisualizerScoreChart instance = new VisualizerScoreChart();
        boolean expResult = false;
        boolean result = instance.isReady();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isShowSnapShot method, of class VisualizerScoreChart.
     */
    @Test
    public void testIsShowSnapShot() {
        System.out.println("isShowSnapShot");
        VisualizerScoreChart instance = new VisualizerScoreChart();
        boolean expResult = false;
        boolean result = instance.isShowSnapShot();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setShowSnapShot method, of class VisualizerScoreChart.
     */
    @Test
    public void testSetShowSnapShot() {
        System.out.println("setShowSnapShot");
        boolean showSnapShot = false;
        VisualizerScoreChart instance = new VisualizerScoreChart();
        instance.setShowSnapShot(showSnapShot);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
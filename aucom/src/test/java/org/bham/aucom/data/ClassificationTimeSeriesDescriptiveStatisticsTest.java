package org.bham.aucom.data;

import junit.framework.Assert;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.StatisticalAnomalyClassificator;
import org.bham.aucom.util.Constants;
import org.junit.Before;
import org.junit.Test;

public class ClassificationTimeSeriesDescriptiveStatisticsTest {
    TimeSeries<Classification> clTsFalseAlarm;
    ClassificationTimeSeriesDescriptiveStatistics ctsds;

    @Before
    public void setUp() throws Exception {
        clTsFalseAlarm = getTimeSeriesWithFalseAlarm();
        ctsds = new ClassificationTimeSeriesDescriptiveStatistics(clTsFalseAlarm);
    }

    @Test
    public void testGetDuration() {
        Assert.assertEquals(18, ctsds.getDuration());
    }

    @Test
    public void testGetHeadDuration() {
        Assert.assertEquals(8, ctsds.getHeadDuration());
    }

    @Test
    public void testGetTailDuration() {
        Assert.assertEquals(8, ctsds.getTailDuration());
    }

    @Test
    public void testGetAnomalyValueCount() {
        Assert.assertEquals(7, ctsds.getAnomalyValueCount());
    }

    @Test
    public void testGetHeadAnomalyValueCount() {
        Assert.assertEquals(2, ctsds.getHeadAnomalyValueCount());
    }

    @Test
    public void testGetTailAnomalyValueCount() {
        Assert.assertEquals(5, ctsds.getTailAnomalyValueCount());
    }

    @Test
    public void testGetAnomalyValuePercent() {
        Assert.assertEquals(0.7, ctsds.getAnomalyValuePercent(), 0.0);
    }

    @Test
    public void testGetHeadAnomalyValuePercent() {
        Assert.assertEquals(0.4, ctsds.getHeadAnomalyValuePercent(), 0.0);
    }

    @Test
    public void testGetTailAnomalyValuePercent() {
        Assert.assertEquals(1.0, ctsds.getTailAnomalyValuePercent(), 0.0);
    }

    @Test
    public void testHasInducedFaultTimestamp() {
        Assert.assertEquals(true, ctsds.hasInducedFaultTimestamp());
    }


    @Test
    public void testGetTotalMeanScoreValue() {
        Assert.assertEquals(0.25, ctsds.getTotalMeanScoreValue(), 0.0);
    }

    @Test
    public void testGetHeadMeanScoreValue() {
        Assert.assertEquals(0.3, ctsds.getHeadMeanScoreValue(), 0.0);
    }

    @Test
    public void testGetTailMeanScoreValue() {
        Assert.assertEquals(0.2, ctsds.getTailMeanScoreValue(), 0.0);
    }

    @Test
    public void testGetTotalScoreVarianceValue() {
        Assert.assertEquals(0.025, ctsds.getTotalScoreVarianceValue(), 0.00001);
    }

    @Test
    public void testGetHeadScoreVarianceValue() {
        Assert.assertEquals(0.025, ctsds.getHeadScoreVarianceValue(), 0.00001);
    }

    @Test
    public void testGetTailScoreVarianceValue() {
        Assert.assertEquals(0.025, ctsds.getTailScoreVarianceValue(), 0.00001);
    }

    @Test
    public void testGetPositiveQuadraticDistance() {
        Assert.assertEquals(0.03, ctsds.getPositiveQuadraticDistance(), 0.0001);
    }

    @Test
    public void testGetTimeSeries() {
        Assert.assertNotNull(ctsds.getTimeSeries());
    }

    @Test
    public void testGetFautlTimestamp() {
        Assert.assertEquals(9, ctsds.getFautlTimestamp());
    }

    @Test
    public void testGetHead() {
        Assert.assertNotNull(ctsds.getHead());
    }

    @Test
    public void testGetTail() {
        Assert.assertNotNull(ctsds.getTail());
    }

    public TimeSeries<Classification> getTimeSeriesWithFalseAlarm() {
        TimeSeries<Classification> clTs = new ClassificationTimeSeries();
        Classification cl = null;

        cl = Classification.createRandomClassification();
        cl.setTimestamp(0);
        cl.setValue(0.5);
        cl.addAttribute(StatisticalAnomalyClassificator.THRESHOLD_USED, "0.4");
        cl.setIsNormal();
        clTs.add(cl);
        cl = Classification.createRandomClassification();
        cl.setTimestamp(2);
        cl.setValue(0.4);
        cl.addAttribute(StatisticalAnomalyClassificator.THRESHOLD_USED, "0.3");
        cl.setIsNormal();
        clTs.add(cl);
        cl = Classification.createRandomClassification();
        cl.setTimestamp(4);
        cl.setValue(0.3);
        cl.addAttribute(StatisticalAnomalyClassificator.THRESHOLD_USED, "0.4");
        cl.setIsAbnormal();
        clTs.add(cl);
        cl = Classification.createRandomClassification();
        cl.setTimestamp(6);
        cl.setValue(0.2);
        cl.addAttribute(StatisticalAnomalyClassificator.THRESHOLD_USED, "0.3");
        cl.setIsAbnormal();
        clTs.add(cl);
        cl = Classification.createRandomClassification();
        cl.setTimestamp(8);
        cl.setValue(0.1);
        cl.addAttribute(StatisticalAnomalyClassificator.THRESHOLD_USED, "0.0");
        cl.setIsNormal();
        clTs.add(cl);

        clTs.addAttribute(Constants.FAULT_INDUCED, "9");

        cl = Classification.createRandomClassification();
        cl.setTimestamp(10);
        cl.setIsAbnormal();
        cl.setValue(0.0);
        cl.addAttribute(StatisticalAnomalyClassificator.THRESHOLD_USED, "0.6");
        clTs.add(cl);
        cl = Classification.createRandomClassification();
        cl.setTimestamp(12);
        cl.setValue(0.1);
        cl.addAttribute(StatisticalAnomalyClassificator.THRESHOLD_USED, "0.6");
        cl.setIsAbnormal();
        clTs.add(cl);
        cl = Classification.createRandomClassification();
        cl.setTimestamp(14);
        cl.setValue(0.2);
        cl.addAttribute(StatisticalAnomalyClassificator.THRESHOLD_USED, "0.6");
        cl.setIsAbnormal();
        clTs.add(cl);
        cl = Classification.createRandomClassification();
        cl.setTimestamp(16);
        cl.setValue(0.3);
        cl.addAttribute(StatisticalAnomalyClassificator.THRESHOLD_USED, "0.6");
        cl.setIsAbnormal();
        clTs.add(cl);
        cl = Classification.createRandomClassification();
        cl.setTimestamp(18);
        cl.setValue(0.4);
        cl.addAttribute(StatisticalAnomalyClassificator.THRESHOLD_USED, "0.6");
        cl.setIsAbnormal();
        clTs.add(cl);
        return clTs;
    }

    /**
     * Test of setTimeSeries method, of class ClassificationTimeSeriesDescriptiveStatistics.
     */
    @Test
    public void testSetTimeSeries() {
        System.out.println("setTimeSeries");
        TimeSeries<Classification> classificationTimeSeries = null;
        ClassificationTimeSeriesDescriptiveStatistics instance = new ClassificationTimeSeriesDescriptiveStatistics();
        instance.setTimeSeries(classificationTimeSeries);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

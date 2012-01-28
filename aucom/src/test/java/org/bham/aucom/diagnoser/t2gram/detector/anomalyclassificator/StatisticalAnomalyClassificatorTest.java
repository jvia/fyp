package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import junit.framework.Assert;
import org.bham.aucom.data.Score;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class StatisticalAnomalyClassificatorTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testAproximatelyDuplicateThreadSafety() {
        try {

            ScheduledExecutorService service = Executors.newScheduledThreadPool(100);
            for (int i = 0; i < 50; i++) {
                System.out.println("duplicator " + i);
                service.scheduleAtFixedRate(new Runnable() {
                    public void run() {
//					while(true){
//						System.out.println("duplicating");
//						StatisticalAnomalyClassificator duplicatedClassificatior  = (StatisticalAnomalyClassificator) templateClassificatior.duplicate();
//					}
                    }
                }, (int) (10 * Math.random()), (int) (20 + 20 * Math.random()), TimeUnit.MILLISECONDS);
            }
            Thread.sleep(100);
            service.shutdownNow();
        } catch (Exception exception) {
            Assert.fail("exception occurred " + exception.getLocalizedMessage());
        }
    }

    /**
     * Test of satisfies method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testSatisfies() {
        System.out.println("satisfies");
        Score scoreToCheck = null;
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        boolean expResult = false;
        boolean result = instance.satisfies(scoreToCheck);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getValueToCompare method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testGetValueToCompare() {
        System.out.println("getValueToCompare");
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        double expResult = 0.0;
        double result = instance.getValueToCompare();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calculateMeanOnHistoryElements method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testCalculateMeanOnHistoryElements() {
        System.out.println("calculateMeanOnHistoryElements");
        List<Score> sequence = null;
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        double expResult = 0.0;
        double result = instance.calculateMeanOnHistoryElements(sequence);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMean method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testSetMean() {
        System.out.println("setMean");
        double mean = 0.0;
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        instance.setMean(mean);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMean method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testGetMean() {
        System.out.println("getMean");
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        double expResult = 0.0;
        double result = instance.getMean();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMeanAndVariance method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testSetMeanAndVariance() {
        System.out.println("setMeanAndVariance");
        double mean = 0.0;
        double variance = 0.0;
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        instance.setMeanAndVariance(mean, variance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setVariance method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testSetVariance() {
        System.out.println("setVariance");
        double variance = 0.0;
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        instance.setVariance(variance);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getVariance method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testGetVariance() {
        System.out.println("getVariance");
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        double expResult = 0.0;
        double result = instance.getVariance();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassificator method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testSetClassificator() {
        System.out.println("setClassificator");
        AnomalyClassificator classificator = null;
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        instance.setClassificator(classificator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        AnomalyClassificator classificator = null;
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        instance.copy(classificator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of duplicate method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testDuplicate() {
        System.out.println("duplicate");
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.duplicate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class StatisticalAnomalyClassificator.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        StatisticalAnomalyClassificator instance = new StatisticalAnomalyClassificator();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

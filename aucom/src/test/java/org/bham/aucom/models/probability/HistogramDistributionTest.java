package org.bham.aucom.models.probability;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Iterator;
import java.util.logging.Level;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;
import static org.bham.aucom.util.Constants.LOWESTPROBABILITY;

public class HistogramDistributionTest {
    HistogramDistribution dist;
    double binSize;

    @Before
    public void setUp() throws Exception {
        binSize = 100.0;
        dist = new HistogramDistribution("test", binSize);

    }

    @Test
    public void testBinSize() {
        Assert.assertEquals(binSize, dist.getBinSize());
    }

    @Test
    public void testGetEntropy() {
        Assert.assertEquals(dist.getEntropy(), 0.0, 0.001);
        double[] values = {90};
//		dist.update(val)

    }

    @Test
    public void testGetProbability() {
        for (int i = 0; i < 10000; i++) {
            Assert.assertEquals(LOWESTPROBABILITY, dist.getProbability(Math.random()), LOWESTPROBABILITY);
            Assert.assertEquals(LOWESTPROBABILITY, dist.getProbability(Math.random() * 10), LOWESTPROBABILITY);
            Assert.assertEquals(LOWESTPROBABILITY, dist.getProbability(Math.random() * 1000), LOWESTPROBABILITY);
        }
        double[] values = {90, 190, 290, 390};
        dist.update(values);
        for (int i = 0; i < 400; i++) {
            Assert.assertEquals(0.25, dist.getProbability(i));
        }
        Assert.assertEquals(LOWESTPROBABILITY, dist.getProbability(400), LOWESTPROBABILITY);
    }

    /**
     * Test of validate method, of class HistogramDistribution.
     */
    @Test
    public void testValidate() {
        System.out.println("validate");
        HistogramDistribution instance = null;
        boolean expResult = false;
        boolean result = instance.validate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbabilities method, of class HistogramDistribution.
     */
    @Test
    public void testGetProbabilities() {
        System.out.println("getProbabilities");
        HistogramDistribution instance = null;
        Iterator expResult = null;
        Iterator result = instance.getProbabilities();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbByBinNumber method, of class HistogramDistribution.
     */
    @Test
    public void testGetProbByBinNumber() {
        System.out.println("getProbByBinNumber");
        int binNumber = 0;
        HistogramDistribution instance = null;
        double expResult = 0.0;
        double result = instance.getProbByBinNumber(binNumber);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getProbByBinLabel method, of class HistogramDistribution.
     */
    @Test
    public void testGetProbByBinLabel() {
        System.out.println("getProbByBinLabel");
        String binLabel = "";
        HistogramDistribution instance = null;
        double expResult = 0.0;
        double result = instance.getProbByBinLabel(binLabel);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class HistogramDistribution.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        HistogramDistribution instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setName method, of class HistogramDistribution.
     */
    @Test
    public void testSetName() {
        System.out.println("setName");
        String name = "";
        HistogramDistribution instance = null;
        instance.setName(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class HistogramDistribution.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        HistogramDistribution instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDistributionEntropy method, of class HistogramDistribution.
     */
    @Test
    public void testGetDistributionEntropy() {
        System.out.println("getDistributionEntropy");
        HistogramDistribution instance = null;
        double expResult = 0.0;
        double result = instance.getDistributionEntropy();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of log method, of class HistogramDistribution.
     */
    @Test
    public void testLog() {
        System.out.println("log");
        double value = 0.0;
        double base = 0.0;
        HistogramDistribution instance = null;
        double expResult = 0.0;
        double result = instance.log(value, base);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setBinSize method, of class HistogramDistribution.
     */
    @Test
    public void testSetBinSize() {
        System.out.println("setBinSize");
        int binSize = 0;
        HistogramDistribution instance = null;
        instance.setBinSize(binSize);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getBinSize method, of class HistogramDistribution.
     */
    @Test
    public void testGetBinSize() {
        System.out.println("getBinSize");
        HistogramDistribution instance = null;
        double expResult = 0.0;
        double result = instance.getBinSize();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class HistogramDistribution.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        HistogramDistribution instance = null;
        String expResult = "";
        String result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class HistogramDistribution.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        double[] val = null;
        HistogramDistribution instance = null;
        instance.update(val);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of severe method, of class HistogramDistribution.
     */
    @Test
    public void testSevere() {
        System.out.println("severe");
        String msg = "";
        HistogramDistribution instance = null;
        instance.severe(msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of info method, of class HistogramDistribution.
     */
    @Test
    public void testInfo() {
        System.out.println("info");
        String info = "";
        HistogramDistribution instance = null;
        instance.info(info);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDebugLevel method, of class HistogramDistribution.
     */
    @Test
    public void testSetDebugLevel() {
        System.out.println("setDebugLevel");
        Level level = null;
        HistogramDistribution instance = null;
        instance.setDebugLevel(level);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDebugLevel method, of class HistogramDistribution.
     */
    @Test
    public void testGetDebugLevel() {
        System.out.println("getDebugLevel");
        HistogramDistribution instance = null;
        Level expResult = null;
        Level result = instance.getDebugLevel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setInfoDebugLevel method, of class HistogramDistribution.
     */
    @Test
    public void testSetInfoDebugLevel() {
        System.out.println("setInfoDebugLevel");
        HistogramDistribution instance = null;
        instance.setInfoDebugLevel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSevereDebugLevel method, of class HistogramDistribution.
     */
    @Test
    public void testSetSevereDebugLevel() {
        System.out.println("setSevereDebugLevel");
        HistogramDistribution instance = null;
        instance.setSevereDebugLevel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAllDebugLevel method, of class HistogramDistribution.
     */
    @Test
    public void testSetAllDebugLevel() {
        System.out.println("setAllDebugLevel");
        HistogramDistribution instance = null;
        instance.setAllDebugLevel();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCount method, of class HistogramDistribution.
     */
    @Test
    public void testGetCount() {
        System.out.println("getCount");
        HistogramDistribution instance = null;
        int expResult = 0;
        int result = instance.getCount();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of expectedValue method, of class HistogramDistribution.
     */
    @Test
    public void testExpectedValue() {
        System.out.println("expectedValue");
        HistogramDistribution instance = null;
        double expResult = 0.0;
        double result = instance.expectedValue();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sample method, of class HistogramDistribution.
     */
    @Test
    public void testSample() {
        System.out.println("sample");
        HistogramDistribution instance = null;
        double[] expResult = null;
        double[] result = instance.sample();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sampleProbability method, of class HistogramDistribution.
     */
    @Test
    public void testSampleProbability() {
        System.out.println("sampleProbability");
        HistogramDistribution instance = null;
        double[] expResult = null;
        double[] result = instance.sampleProbability();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of variance method, of class HistogramDistribution.
     */
    @Test
    public void testVariance() {
        System.out.println("variance");
        HistogramDistribution instance = null;
        double expResult = 0.0;
        double result = instance.variance();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getMaxProbability method, of class HistogramDistribution.
     */
    @Test
    public void testGetMaxProbability() {
        System.out.println("getMaxProbability");
        HistogramDistribution instance = null;
        double expResult = 0.0;
        double result = instance.getMaxProbability();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

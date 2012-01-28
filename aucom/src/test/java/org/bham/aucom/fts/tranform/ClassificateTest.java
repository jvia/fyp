package org.bham.aucom.fts.tranform;

import junit.framework.Assert;
import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Score;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.timeseries.ObservationTimeSeries;

public class ClassificateTest {
	Classificate absTs;
	@Before
	public void setUp() throws Exception {
		absTs = new Classificate();
	}
	@Test
	public void testSetTimeSeries() {
		absTs.setTimeSeries(new ObservationTimeSeries());
		Assert.assertNotNull(absTs.ts);
	}

	@Test
	public void testGetTimeSeries() {
		absTs.setTimeSeries(new ObservationTimeSeries());
		Assert.assertNotNull(absTs.getTimeSeries());
	}

    /**
     * Test of iTransform method, of class Classificate.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        Score arg0 = null;
        Classificate instance = new Classificate();
        Classification expResult = null;
        Classification result = instance.iTransform(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassificator method, of class Classificate.
     */
    @Test
    public void testSetClassificator() {
        System.out.println("setClassificator");
        AnomalyClassificator threshold = null;
        Classificate instance = new Classificate();
        instance.setClassificator(threshold);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassificator method, of class Classificate.
     */
    @Test
    public void testGetClassificator() {
        System.out.println("getClassificator");
        Classificate instance = new Classificate();
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.getClassificator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class Classificate.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        Classificate instance = new Classificate();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

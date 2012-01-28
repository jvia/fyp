package org.bham.aucom.fts.tranform;

import junit.framework.Assert;
import nu.xom.Element;
import org.bham.aucom.data.*;
import org.bham.aucom.data.util.SlidingWindow;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class CalcMeanvalueTest extends CalcMeanvalue {

	public CalcMeanvalueTest() {
		super();
		this.setSlidingWindow(new SlidingWindow(1, 0));
	}

	@Test
	public void checkIfLastTimestampOfTheWindowIsValidTest() {
		CalcMeanvalueTest test = new CalcMeanvalueTest();
	}

	public ArrayList<DomainFeature> getFeatures() {
		ArrayList<DomainFeature> f = new ArrayList<DomainFeature>();
		f.add(new DomainFeature("source", "unknown"));
		f.add(new DomainFeature("scope", "unknown"));
		f.add(new DomainFeature("type", "unknown"));
		return f;
	}

	@Test
	public void tranformTest() {
		CalcMeanvalueTest test = new CalcMeanvalueTest();
		try {
			long timestampOfInserstetElement = 1;
			Assert.assertEquals(0, test.tmpFirstTimestampOfTheWindow);
			Assert.assertEquals(1, test.tmpLastTimestampOfTheWindow);
			Assert.assertEquals(0, test.getOldestTimestamp());
			ArrayList<DomainFeature> features = new ArrayList<DomainFeature>();
			features.add(new DomainFeature("scope", "a"));
			features.add(new DomainFeature("type", "b"));
			features.add(new DomainFeature("source", "c"));
			DataType d = new DataType(features,1,  new Observation(new Element("element"), 1));
			TemporalDurationFeature t2g = new TemporalDurationFeature(d, new LinkedHashMap<DataType, Long>());
			TemporalProbabilityFeature tpd = new TemporalProbabilityFeature(t2g, new LinkedHashMap<DataType, Double>());
			
			double value = 0.1;
			Score s = new SingleScore(tpd, value);
			Assert.assertNull(test.transform(s));
			Assert.assertEquals(0, test.tmpFirstTimestampOfTheWindow);

			// transform berechnet ein window und kalkuliert den naechsten
			// timestamp, daher 2
			Assert.assertEquals(1, test.tmpLastTimestampOfTheWindow);

			Assert.assertEquals(false, test.hasEnoughElementsForNextSlidingWindow());

			// test whether the approach is time stamp invariant
			test = new CalcMeanvalueTest();
			timestampOfInserstetElement = 3;

			Assert.assertEquals(0, test.tmpFirstTimestampOfTheWindow);
			Assert.assertEquals(1, test.tmpLastTimestampOfTheWindow);
			Assert.assertEquals(0, test.getOldestTimestamp());

			features.add(new DomainFeature("scope", "a"));
			features.add(new DomainFeature("type", "b"));
			features.add(new DomainFeature("source", "c"));
			d = new DataType(features,1,  new Observation(new Element(""), 1));
			t2g = new TemporalDurationFeature(d, new LinkedHashMap<DataType, Long>());
			tpd = new TemporalProbabilityFeature(t2g, new HashMap<DataType, Double>());
			value = 0.1;
			s = new SingleScore(tpd, value);

			test.transform(s);
			Assert.assertEquals(1, test.tmpFirstTimestampOfTheWindow);
			Assert.assertEquals(2, test.tmpLastTimestampOfTheWindow);
			Assert.assertEquals(timestampOfInserstetElement, test.getOldestTimestamp());
			Assert.assertEquals(false, test.hasEnoughElementsForNextSlidingWindow());
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}

	}

	@Test
	public void isCalculateNextWindowTest() {
		CalcMeanvalue calcMeanValuetestObject = new CalcMeanvalue();
		calcMeanValuetestObject.setSlidingWindow(new SlidingWindow(10, 1));
		try {
			Assert.assertEquals(0, calcMeanValuetestObject.getFirstTimestampOfTheWindow());
			Assert.assertEquals(1, calcMeanValuetestObject.tmpLastTimestampOfTheWindow);
			Assert.assertEquals(0, calcMeanValuetestObject.getOldestTimestamp());
			ArrayList<DomainFeature> features = new ArrayList<DomainFeature>();
			features.add(new DomainFeature("scope", "a"));
			features.add(new DomainFeature("type", "b"));
			features.add(new DomainFeature("source", "c"));
			DataType d = new DataType(features,1,  new Observation(new Element(""),1 ));
			TemporalDurationFeature t2g = new TemporalDurationFeature(d, new LinkedHashMap<DataType, Long>());
			TemporalProbabilityFeature tpd = new TemporalProbabilityFeature(t2g, new HashMap<DataType, Double>());
			double value = 0.1;
			Score s = new SingleScore(tpd, value);

			calcMeanValuetestObject.store(s);
			Assert.assertEquals(0, calcMeanValuetestObject.tmpFirstTimestampOfTheWindow);
			Assert.assertEquals(2, calcMeanValuetestObject.getOldestTimestamp());
			Assert.assertEquals(true, calcMeanValuetestObject.hasEnoughElementsForNextSlidingWindow());

		} catch (Exception exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
	}

    /**
     * Test of reset method, of class CalcMeanvalue.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        CalcMeanvalue instance = new CalcMeanvalue();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of store method, of class CalcMeanvalue.
     */
    @Test
    public void testStore() {
        System.out.println("store");
        Score dataToStore = null;
        CalcMeanvalue instance = new CalcMeanvalue();
        instance.store(dataToStore);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of calcMeanScore method, of class CalcMeanvalue.
     */
    @Test
    public void testCalcMeanScore() {
        System.out.println("calcMeanScore");
        ArrayList<Score> list = null;
        CalcMeanvalue instance = new CalcMeanvalue();
        RangeScore expResult = null;
        RangeScore result = instance.calcMeanScore(list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasReceivedLastSequenceElement method, of class CalcMeanvalue.
     */
    @Test
    public void testHasReceivedLastSequenceElement() {
        System.out.println("hasReceivedLastSequenceElement");
        Score element = null;
        CalcMeanvalue instance = new CalcMeanvalue();
        boolean expResult = false;
        boolean result = instance.hasReceivedLastSequenceElement(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iTransform method, of class CalcMeanvalue.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        Score arg0 = null;
        CalcMeanvalue instance = new CalcMeanvalue();
        Score expResult = null;
        Score result = instance.iTransform(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElementsToRemove method, of class CalcMeanvalue.
     */
    @Test
    public void testGetElementsToRemove() {
        System.out.println("getElementsToRemove");
        ArrayList<Score> tmp2 = null;
        long tmpLastTimestampOfTheWindow2 = 0L;
        CalcMeanvalue instance = new CalcMeanvalue();
        ArrayList expResult = null;
        ArrayList result = instance.getElementsToRemove(tmp2, tmpLastTimestampOfTheWindow2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of checkIfLastTimestampOfTheWindowIsValid method, of class CalcMeanvalue.
     */
    @Test
    public void testCheckIfLastTimestampOfTheWindowIsValid() {
        System.out.println("checkIfLastTimestampOfTheWindowIsValid");
        CalcMeanvalue instance = new CalcMeanvalue();
        instance.checkIfLastTimestampOfTheWindowIsValid();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNextLastTimestampOfTheWindow method, of class CalcMeanvalue.
     */
    @Test
    public void testGetNextLastTimestampOfTheWindow() {
        System.out.println("getNextLastTimestampOfTheWindow");
        CalcMeanvalue instance = new CalcMeanvalue();
        long expResult = 0L;
        long result = instance.getNextLastTimestampOfTheWindow();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getWindowElements method, of class CalcMeanvalue.
     */
    @Test
    public void testGetWindowElements() {
        System.out.println("getWindowElements");
        ArrayList<Score> tmp2 = null;
        long tmpLastTimestampOfTheWindow2 = 0L;
        CalcMeanvalue instance = new CalcMeanvalue();
        ArrayList expResult = null;
        ArrayList result = instance.getWindowElements(tmp2, tmpLastTimestampOfTheWindow2);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getOldestTimestamp method, of class CalcMeanvalue.
     */
    @Test
    public void testGetOldestTimestamp() {
        System.out.println("getOldestTimestamp");
        CalcMeanvalue instance = new CalcMeanvalue();
        long expResult = 0L;
        long result = instance.getOldestTimestamp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasEnoughElementsForNextSlidingWindow method, of class CalcMeanvalue.
     */
    @Test
    public void testHasEnoughElementsForNextSlidingWindow() {
        System.out.println("hasEnoughElementsForNextSlidingWindow");
        CalcMeanvalue instance = new CalcMeanvalue();
        boolean expResult = false;
        boolean result = instance.hasEnoughElementsForNextSlidingWindow();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstTimestampOfTheWindow method, of class CalcMeanvalue.
     */
    @Test
    public void testGetFirstTimestampOfTheWindow() {
        System.out.println("getFirstTimestampOfTheWindow");
        CalcMeanvalue instance = new CalcMeanvalue();
        long expResult = 0L;
        long result = instance.getFirstTimestampOfTheWindow();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setSlidingWindow method, of class CalcMeanvalue.
     */
    @Test
    public void testSetSlidingWindow() {
        System.out.println("setSlidingWindow");
        SlidingWindow slidingWindow = null;
        CalcMeanvalue instance = new CalcMeanvalue();
        instance.setSlidingWindow(slidingWindow);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSlidingWindow method, of class CalcMeanvalue.
     */
    @Test
    public void testGetSlidingWindow() {
        System.out.println("getSlidingWindow");
        CalcMeanvalue instance = new CalcMeanvalue();
        SlidingWindow expResult = null;
        SlidingWindow result = instance.getSlidingWindow();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

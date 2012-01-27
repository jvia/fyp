package org.bham.aucom.fts.tranform;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import junit.framework.Assert;
import nu.xom.Element;

import org.junit.Test;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.SingleScore;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.data.util.SlidingWindow;

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
}

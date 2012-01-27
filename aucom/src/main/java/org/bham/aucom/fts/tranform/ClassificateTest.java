package org.bham.aucom.fts.tranform;

import junit.framework.Assert;

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

}

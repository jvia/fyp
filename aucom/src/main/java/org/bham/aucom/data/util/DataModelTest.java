package org.bham.aucom.data.util;

import java.util.UUID;

import junit.framework.Assert;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;

public class DataModelTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testAddTimeSeries() {
		ObservationTimeSeries obsts = new ObservationTimeSeries();
		UUID obststID = obsts.getId();
		obsts.setGeneratedFrom(UUID.randomUUID());
		Observation  obs = new Observation(new Element("testElement"), 1);
		obsts.add(obs);
		DataModel.getInstance().addTimeSeries(obsts);
		Assert.assertEquals(1, DataModel.getInstance().getNumerTimeseries());
		Assert.assertEquals(true, DataModel.getInstance().containsTimeSeriesWithId(obststID));
	}

}

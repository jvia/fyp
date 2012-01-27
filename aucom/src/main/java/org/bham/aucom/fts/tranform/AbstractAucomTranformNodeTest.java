package org.bham.aucom.fts.tranform;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;

public class AbstractAucomTranformNodeTest {
	AbstractAucomTranformNode<Observation, Observation> absTs;
	@Before
	public void setUp() throws Exception {
		absTs= new  AbstractAucomTranformNode<Observation, Observation>("") {
			
			@Override
			protected Observation iTransform(Observation input) throws Exception {
				return null;
			}
		};
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
	@Test 
	public void testAddTransformNodeListener(){
		TransformNodeEventListener l = new TransformNodeEventListener() {
			
			@Override
			public void handleTransformNodeEvent(TransformNodeEvent evt) {
			}
		};
		absTs.addTransformNodeListener(l);
		Assert.assertEquals(1, absTs.getNumberListeners());
		absTs.addTransformNodeListener(l);
		Assert.assertEquals(1, absTs.getNumberListeners());
	}
}

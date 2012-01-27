package org.bham.aucom.diagnoser;

import java.util.ArrayList;
import java.util.List;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.AbstractDetector.DetectorStatus;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.source.IllegalStateChange;

public class AbstractDetectorTest {
	class TestDetector extends AbstractDetector{

		@Override
		public void start(TimeSeries<Observation> inTimeSeries) throws ActionFailedException {
		}

		@Override
		public void pause() throws ActionFailedException, IllegalStateChange {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void resume() {
			// TODO Auto-generated method stub
			
		}

		@Override
		public void stop() throws ActionFailedException {
			// TODO Auto-generated method stub
			
		}

		@Override
		public TimeSeries<Classification> getOutput() {
			// TODO Auto-generated method stub
			return null;
		}
		
	}
	TestDetector dt;
	@Before
	public void setUp() throws Exception {
		dt = new TestDetector();
	}

	
	@Test
	public void testEventHandling() {
		final List<Integer> numbers = new ArrayList<Integer>();
		DetectorStatusChangedListener listener2 = new DetectorStatusChangedListener() {
			
			@SuppressWarnings("boxing")
			@Override
			public void handleDetectorStatusChangedEvent(DetectorStatusChangedEvent evt) {
				numbers.add(1);
			}
		};
		dt.addStatusListener(listener2);
		dt.addStatusListener(listener2);
		dt.fireDetectorStatusChangedEvent(new DetectorStatusChangedEvent(this, DetectorStatus.NOTREADY, DetectorStatus.READY));
		Assert.assertEquals(1, numbers.size());
	}
}

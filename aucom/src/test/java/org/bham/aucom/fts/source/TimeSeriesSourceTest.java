package org.bham.aucom.fts.source;

import java.util.ArrayList;

import junit.framework.Assert;
import net.sf.xcf.fts.Event;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;

public class TimeSeriesSourceTest implements SourceStatusListener {

	TimeSeriesSource<Observation> source;
	private TimeSeries<Observation> sequence;
	ArrayList<SourceStatusEvent> status;

	@Before
	public void setUp() throws Exception {
		this.sequence = new ObservationTimeSeries();
		this.source = new TimeSeriesSource<Observation>(this.sequence, "testSource");
		this.status = new ArrayList<SourceStatusEvent>();
		this.source.addSourceStatusListener(this);
	}

	@Test
	public void testAddingElements() {
		this.sequence.add(Observation.createRandomObservation());
		Assert.assertEquals(this.sequence.size(), this.source.size());
		ArrayList<Observation> list = new ArrayList<Observation>();
		list.add(Observation.createRandomObservation());
		list.add(Observation.createRandomObservation());
		this.sequence.addAll(list);
		Assert.assertEquals(this.sequence.size(), this.source.size());
	}

	@Test
	public void testFireQueueChangeEvents() {
		Assert.assertEquals(0, this.status.size());
		Observation d = getRandomObservation();
		this.sequence.add(d);
		Assert.assertEquals(0, this.status.size());
		try {
			this.status.clear();
			Event<? extends Observation> event = this.source.next();
			Assert.assertEquals(1, this.status.size());
			this.status.get(0).equals(SourceStatus.FIRST_ELEMENT_SENT);
		} catch (Exception exception1) {
			exception1.printStackTrace();
		}
	}

	@Test
	public void testReceivedFirstElementEventSent() {
		Assert.assertEquals(0, this.status.size());
		Observation d = getRandomObservation();
		this.sequence.add(d);
		d = getRandomObservation();
		Assert.assertEquals(0, this.status.size());
		try {
			this.status.clear();
			Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
			Event<? extends Observation> event = this.source.next();
			Assert.assertEquals(1, this.status.size());
			Assert.assertEquals(this.source.getStatus(), SourceStatus.RUNNING);
			Assert.assertEquals(SourceStatus.FIRST_ELEMENT_SENT, this.status.get(0).getStatus());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}
	@Test
	public void testReceivedFirstElementEventSent_ONLYONCE() {
		System.out.println("testReceivedFirstElementEventSent_ONLYONCE--------------------------------------------------------");
		Observation d = getRandomObservation();
		this.sequence.add(d);
		d = getRandomObservation();
		this.sequence.add(d);
		d = getRandomObservation();
		this.sequence.add(d);
		d = getRandomObservation();
		this.sequence.add(d);
		Assert.assertEquals(0, this.status.size());
		try {
			Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
			this.source.next();
			Assert.assertEquals(this.source.getStatus(), SourceStatus.RUNNING);
			Assert.assertEquals(1, this.status.size());
			Assert.assertEquals(SourceStatus.FIRST_ELEMENT_SENT, this.status.get(0).getStatus());
			this.source.next();
			this.source.next();
			Assert.assertEquals(1, this.status.size());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.println("testReceivedFirstElementEventSent_ONLYONCE--------------------------------------------------------");
	}

	@Test
	public void testReceivedLastElementEventSent() {
		Assert.assertEquals(0, this.status.size());
		Observation d = Observation.createRandomObservation();
		d.markAsLastElement();
		this.sequence.add(d);
		try {
			this.status.clear();
			Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
			Event<? extends Observation> event = this.source.next();
			Assert.assertEquals(2, this.status.size());
			Assert.assertEquals(SourceStatus.FIRST_ELEMENT_SENT, this.status.get(0).getStatus());
			Assert.assertEquals(SourceStatus.LAST_ELEMENT_SENT, this.status.get(1).getStatus());
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	@Test
	public void resumeAfterPausedTest() {
		Observation d = getRandomObservation();
		this.sequence.add(d);
		Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
		this.source.pause();
		Assert.assertEquals(this.source.getStatus(), SourceStatus.PAUSED);
		try {
			this.source.resume(); 
		} catch (IllegalStateChange exception) {
			Assert.fail("resume needs to throw an exception when called not in paused mode");
		}
		Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
	}
	@Test
	public void resumedBeforePausedTest() {
		Observation d = getRandomObservation();
		this.sequence.add(d);
		Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
		try {
			this.source.resume();
			Assert.fail("resume needs to throw an IllegalStateChange when called not in paused mode");
		} catch (IllegalStateChange exception) {
			// ignore
		}
		Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
	}

	@Test
	public void pauseBeforeFirstElementTest() {
		System.out.println("-------------------------------------------------------- pauseBeforeFirstElementTest");
		Observation d = getRandomObservation();
		Assert.assertEquals(this.source.getStatus(), SourceStatus.CONNECTED);
		// initial the source is ready
		this.source.pause(); // pause should work also in ready state
		Thread t = new Thread(new Runnable() {
			public void run() {
				try {
					System.out.println("blocked");
					source.next();
					System.out.println("unblocked");
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		});
		t.start();
		try {
			Thread.sleep(100);
			Assert.assertEquals(SourceStatus.PAUSED, this.source.getStatus());
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
		System.out.println("pauseBeforeFirstElementTest--------------------------------------------------------");
	}

	@Test
	public void pauseAfterFirstElementTest() {
		System.out.println("pauseAfterFirstElementTest--------------------------------------------------------");
		Observation d = getRandomObservation();
		this.sequence.add(d);
		try {
			source.next();
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		this.source.pause();
		Assert.assertEquals(SourceStatus.PAUSED, this.source.getStatus());
		System.out.println("--------------------------------------------------------pauseAfterFirstElementTest");
	}


	/**
	 * @return
	 */
	private Observation getRandomObservation() {
		Observation d = Observation.createRandomObservation();
//		d.markAsFirstElement();
		return d;
	}

	@Override
	public void sourceStatusChanged(SourceStatusEvent status) {
		System.out.println("sequenceQueueSourceStatusChanged: " + status.getStatus());
		this.status.add(status);
	}
}
package org.bham.aucom.fts.graph;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.graph.AbstractAucomGraph.GraphStatus;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.main.GraphStatusListener;

public class DetectorGraphTest implements GraphStatusListener {
	int zaehler = 0;
	DetectorGraph graph;
	TimeSeries<Score> scoreTimeSeries;
	ArrayList<GraphStateChangedEvent> status = new ArrayList<GraphStateChangedEvent>();
	Object waiterObject = new Object();

	@Before
	public void setUp() throws Exception {
		this.graph = new DetectorGraph();
		this.graph.addGraphListener(this);
		this.zaehler= 0;
	}

	@After
	public void cleanUp() {
		if (this.graph != null) {
			this.graph.removeAllListeners();
			this.status.clear();
		}
	}

	/*
	 * test state changes
	 */
	@Test
	public void testSwitchFromINITToReadyStatus() {
		System.out.println("----------------------1 testSwitchFromINITToReadyStatus--------------------------");
		Assert.assertEquals(this.graph.getStatus(), GraphStatus.NOTREADY);
		try {
			this.graph.start();
			Assert.assertEquals(this.graph.getStatus(), GraphStatus.READY);
		} catch (ActionFailedException exception) {
			Assert.fail(exception.getLocalizedMessage());
		}
		System.out.println("----------------------1 testSwitchFromINITToReadyStatus--------------------------");
	}

	@Test
	public void testSwitchFromReadyToRunningStatus() {
		try {
			System.out.println("----------------------2 testSwitchFromReadyToRunningStatus --------------------------");
			TimeSeries<Observation> s = creatTestScoreTimeSeriesWithOneElement();
			this.graph.setInput(s);
			this.graph.start();
			Thread.sleep(1000);
			Assert.assertEquals(this.graph.getStatus(), GraphStatus.RUNNING);
			Assert.assertEquals(2, this.status.size());
		} catch (InterruptedException exception) {
			exception.printStackTrace();
			Assert.fail("should not happen");
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		System.out.println("----------------------2 testSwitchFromReadyToRunningStatus --------------------------");
	}

	@Test
	public void testSwitchFromReadyToRunningToFinishedStatus() {
		try {
			System.out.println("----------------------3 testSwitchFromReadyToRunningToFinishedStatus --------------------------");
			TimeSeries<Observation> s = creatTestScoreTimeSeriesWithMoreThanTwoElements(5);
			this.graph.setInput(s);
			this.graph.start();
			Thread.sleep(2000);
			Assert.assertEquals(GraphStatus.RUNNING, this.graph.getStatus());
			Assert.assertEquals(2, this.status.size());
		} catch (InterruptedException exception) {
			exception.printStackTrace();
			Assert.fail("caught InterruptedException but thread should not be interrrupted");
		} catch (Exception exception) {
			Assert.fail("caught " + exception.getLocalizedMessage());
			exception.printStackTrace();
		}
		System.out.println("----------------------3 testSwitchFromReadyToRunningToFinishedStatus --------------------------");
	}

	@Test
	public void teststartGraphWithoutTimeseries() {
		try {
			System.out.println("----------------------4 testSwitchFromReadyToRunningToFinishedStatus --------------------------");
			this.graph.start();
			Thread.sleep(2000);
			Assert.assertEquals(GraphStatus.READY, this.graph.getStatus());
			Assert.assertEquals(1, this.status.size());
		} catch (InterruptedException exception) {
			exception.printStackTrace();
			Assert.fail("caught InterruptedException but thread should not be interrrupted");
		} catch (Exception exception) {
			Assert.fail("caught " + exception.getLocalizedMessage());
			exception.printStackTrace();
		}
		System.out.println("----------------------4 testSwitchFromReadyToRunningToFinishedStatus --------------------------");
	}

	/*
	 * helper functions
	 */
	private TimeSeries<Observation> creatTestScoreTimeSeriesWithOneElement() {
		TimeSeries<Observation> testScoreTs = new ObservationTimeSeries();
		testScoreTs.add(Observation.createRandomObservation());
		testScoreTs.get(0).markAsFirstElement();
		return testScoreTs;
	}

	private TimeSeries<Observation> creatTestScoreTimeSeriesWithTwoElements() {
		TimeSeries<Observation> testScoreTs = new ObservationTimeSeries();
		testScoreTs.add(Observation.createRandomObservation());
		testScoreTs.add(Observation.createRandomObservation());
		return testScoreTs;
	}

	private TimeSeries<Observation> creatTestScoreTimeSeriesWithMoreThanTwoElements(int numberElements) {
		TimeSeries<Observation> testScoreTs = new ObservationTimeSeries();
		for (int i = 0; i < numberElements; i++) {
			testScoreTs.add(Observation.createRandomObservation());
		}
		testScoreTs.get(0).markAsFirstElement();
		testScoreTs.get(testScoreTs.size()-1).markAsLastElement();
		return testScoreTs;
	}

	@Override
	public void graphStatusChanged(GraphStateChangedEvent evt) {
		this.zaehler++;
		System.out.println("tester got : " + evt + " " +this.zaehler);
		this.status.add(evt);
	}

}

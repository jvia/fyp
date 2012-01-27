package org.bham.aucom.fts.graph;


import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.ScoreTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.graph.AbstractAucomGraph.GraphStatus;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.main.GraphStatusListener;

public class OptimizerGraphTest implements GraphStatusListener {

	OptimizerGraph graph;
	TimeSeries<Score> scoreTimeSeries;
	ArrayList<GraphStateChangedEvent> status = new ArrayList<GraphStateChangedEvent>();
	Object waiterObject = new Object();
	@Before
	public void setUp() throws Exception {
		this.graph = new OptimizerGraph();//new StatisticalAnomalyClassificator(0.1, 0.02)
		this.graph.addGraphListener(this);
		status.clear();
	}

		
	/*
	 * test state changes
	 */
	@Test(expected=ActionFailedException.class)
	public void testSwitchFromINITToReadyStatus() throws ActionFailedException{
			Assert.assertEquals(this.graph.getStatus(), GraphStatus.NOTREADY);
			this.graph.start();
	}
	
	@Test
	public void testSwitchFromReadyToRunningStatus(){
		try {
			System.out.println("----------------------2 testSwitchFromReadyToRunningStatus --------------------------");
			TimeSeries<Score> s = creatTestScoreTimeSeriesWithOneElement();
			this.graph.setInput(s);
			this.graph.start();
			Thread.sleep(1000);
			Assert.assertEquals(this.graph.getStatus(), GraphStatus.RUNNING);
			Assert.assertEquals(2,status.size());
		} catch (InterruptedException exception) {
			exception.printStackTrace();
			Assert.fail("should not happen");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.println("----------------------2 testSwitchFromReadyToRunningStatus --------------------------");
	}
	
	@Test
	public void testSwitchFromReadyToRunningToFinishedStatus(){
		try {
			status.clear();
			System.out.println("---------------------- testSwitchFromReadyToRunningToFinishedStatus --------------------------");
			TimeSeries<Score> s = creatTestScoreTimeSeriesWithMoreThanTwoElements(5);
			this.graph.setInput(s);
			this.graph.start();
			Thread.sleep(2000);
			System.out.println(status);
			Assert.assertEquals(GraphStatus.READY,this.graph.getStatus());
			Assert.assertEquals(3,status.size());
		} catch (InterruptedException exception) {
			exception.printStackTrace();
			Assert.fail("should not happen");
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		System.out.println("---------------------- testSwitchFromReadyToRunningToFinishedStatus --------------------------");
	}
	
	// TODO write resume test
	// TODO write pause when ready Test
	@Test 
	public void pauseRunningGraphTest(){
		status.clear();
		System.out.println("---------------------- pauseRunningGraphTest --------------------------");
		TimeSeries<Score> s = creatTestScoreTimeSeriesWithMoreThanTwoElements(10);
		this.graph.setInput(s);
		try {
			this.graph.source.next();
			Assert.assertEquals(1,status.size());
			Assert.assertEquals(GraphStatus.RUNNING,this.graph.getStatus());
			this.graph.pause();
			Assert.assertEquals(GraphStatus.PAUSED,this.graph.getStatus());
		} catch (InterruptedException exception) {
			exception.printStackTrace();
			Assert.fail("should not happen");
		} catch (Exception exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		System.out.println("---------------------- pauseRunningGraphTest --------------------------");
		
	}
	@Test 
	public void resumeRunningGraphTest(){
		status.clear();
		System.out.println("---------------------- pauseRunningGraphTest --------------------------");
		TimeSeries<Score> s = creatTestScoreTimeSeriesWithMoreThanTwoElements(10);
		this.graph.setInput(s);
		try {
			this.graph.source.next();
			Assert.assertEquals(GraphStatus.RUNNING,this.graph.getStatus());
			this.graph.pause();
			Assert.assertEquals(GraphStatus.PAUSED,this.graph.getStatus());
			this.graph.resume();
			Assert.assertEquals(GraphStatus.RUNNING,this.graph.getStatus());
		} catch (InterruptedException exception) {
			exception.printStackTrace();
			Assert.fail("should not happen");
		} catch (Exception exception) {
			Assert.fail();
			exception.printStackTrace();
		}
		System.out.println("---------------------- pauseRunningGraphTest --------------------------");
		
	}
	@Test 
	public void resumeReadyGraphTest(){
		status.clear();
		System.out.println("---------------------- pauseRunningGraphTest --------------------------");
		TimeSeries<Score> s = creatTestScoreTimeSeriesWithMoreThanTwoElements(10);
		this.graph.setInput(s);
		try {
			Assert.assertEquals(GraphStatus.NOTREADY,this.graph.getStatus());
			this.graph.pause();
			Assert.assertEquals(GraphStatus.PAUSED,this.graph.getStatus());
			this.graph.resume();
			Assert.assertEquals(GraphStatus.NOTREADY,this.graph.getStatus());
		} catch (Exception exception) {
			Assert.fail();
			exception.printStackTrace();
		}
		System.out.println("---------------------- pauseRunningGraphTest --------------------------");
		
	}
	@Test 
	public void listenToAllNodesTest(){
		
	}
	


	/*
	 * helper functions
	 */
	private TimeSeries<Score> creatTestScoreTimeSeriesWithOneElement() {
		TimeSeries<Score> testScoreTs = new ScoreTimeSeries();
		testScoreTs.add(Score.createRandomScore());	
		testScoreTs.get(0).markAsFirstElement();
		return testScoreTs;
	}
	private TimeSeries<Score> creatTestScoreTimeSeriesWithTwoElements() {
		TimeSeries<Score> testScoreTs = new ScoreTimeSeries();
		testScoreTs.add(Score.createRandomScore());	
		testScoreTs.add(Score.createRandomScore());	
		return testScoreTs;
	}
	private TimeSeries<Score> creatTestScoreTimeSeriesWithMoreThanTwoElements(int numberElements) {
		TimeSeries<Score> testScoreTs = new ScoreTimeSeries();
		for(int i=0; i < numberElements; i++ ){
			testScoreTs.add(Score.createRandomScore());
		}
		testScoreTs.get(0).markAsFirstElement();
		testScoreTs.get(numberElements-1).markAsLastElement();
		return testScoreTs;
	}


	@Override
	public void graphStatusChanged(GraphStateChangedEvent evt) {
		System.out.println("tester got : " + evt);
		status.add(evt);
	}

}

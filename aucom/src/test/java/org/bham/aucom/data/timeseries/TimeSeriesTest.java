package org.bham.aucom.data.timeseries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import junit.framework.Assert;

import org.junit.Test;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Observation;

public class TimeSeriesTest {
	boolean hasReceivedEvent = false;
	@Test
	public void testAddElement() {
		TimeSeries<Classification> clTs = createTimeSeriesWithNumElements(0);
		clTs.add(Classification.createRandomClassification());
		Assert.assertEquals(1, clTs.list.size());
	}
	@Test
	public void testAddAllElements(){
		TimeSeries<Classification> clTs = createTimeSeriesWithNumElements(0);
		Collection<Classification> elementsToAdd = new ArrayList<Classification>();
		elementsToAdd.add(Classification.createRandomClassification());
		elementsToAdd.add(Classification.createRandomClassification());
		elementsToAdd.add(Classification.createRandomClassification());
		elementsToAdd.add(Classification.createRandomClassification());
		elementsToAdd.add(Classification.createRandomClassification());
		elementsToAdd.add(Classification.createRandomClassification());
		clTs.addAll(elementsToAdd);
		Assert.assertEquals(6, clTs.size());
		clTs.get(0);
	}
	@Test 
	public void testGetAllElements(){
	    TimeSeries<Classification> clTs = createTimeSeriesWithNumElements(10);
	    Collection<Classification> elements = clTs.getall();
	    Assert.assertEquals(10, elements.size());
	}

	@Test
	public void testRemoveElement() {
//		TimeSeries<Classification> clTs = createTimeSeriesWithNumElements(1);
//		clTs.remove(0);
//		Assert.assertEquals(0, clTs.list.size());
//		Assert.assertEquals(0, clTs.timestampToElement.size());

	}
	
	@Test
	public void testFireTimeseriesChangedEventUsingAdd(){
		ObservationTimeSeries obsTs  = new ObservationTimeSeries(4);
		final List<TimeseriesStatusEvent> events = new ArrayList<TimeseriesStatusEvent>();
		obsTs.addTimeSeriesStatusListener(new TimeSeriesStatusListener() {
			
			@Override
			public void timeseriesStatusChanged(TimeseriesStatusEvent status) {
				events.add(status);
			}
		});
		
		obsTs.add(Observation.createRandomObservation());//1
		Assert.assertEquals(0, events.get(obsTs.size()-1).getStartIndex());
		Assert.assertEquals(0, events.get(obsTs.size()-1).getEndIndex());
		Assert.assertEquals(1, events.size());
		obsTs.add(Observation.createRandomObservation());//2
		Assert.assertEquals(1, events.get(obsTs.size()-1).getStartIndex());
		Assert.assertEquals(1, events.get(obsTs.size()-1).getEndIndex());
		Assert.assertEquals(2, events.size());
		obsTs.add(Observation.createRandomObservation());//3
		Assert.assertEquals(2, events.get(obsTs.size()-1).getStartIndex());
		Assert.assertEquals(2, events.get(obsTs.size()-1).getEndIndex());
		Assert.assertEquals(3, events.size());
		obsTs.add(Observation.createRandomObservation());//4
		Assert.assertEquals(3, events.get(obsTs.size()-1).getStartIndex());
		Assert.assertEquals(3, events.get(obsTs.size()-1).getEndIndex());
		Assert.assertEquals(4, events.size());
		obsTs.add(Observation.createRandomObservation());//5
		Assert.assertEquals(3, events.get(obsTs.size()-1).getStartIndex());
		Assert.assertEquals(3, events.get(obsTs.size()-1).getEndIndex());
		Assert.assertEquals(5, events.size());
		obsTs.add(Observation.createRandomObservation());//5
		Assert.assertEquals(3, events.get(obsTs.size()-1).getStartIndex());
		Assert.assertEquals(3, events.get(obsTs.size()-1).getEndIndex());
		Assert.assertEquals(6, events.size());
	}
	@Test
	public void testFireTimeseriesChangedEventUsingAddAll(){
		ObservationTimeSeries obsTs  = new ObservationTimeSeries(4);
		final List<TimeseriesStatusEvent> events = new ArrayList<TimeseriesStatusEvent>();
		List<Observation> elementsToAdd = new ArrayList<Observation>();
		
		obsTs.addTimeSeriesStatusListener(new TimeSeriesStatusListener() {
			
			@Override
			public void timeseriesStatusChanged(TimeseriesStatusEvent status) {
				events.add(status);
			}
		});

		elementsToAdd.add(Observation.createRandomObservation());
		obsTs.addAll(elementsToAdd);
		Assert.assertEquals(1, events.size());
		Assert.assertEquals(0, events.get(events.size()-1).getStartIndex());
		Assert.assertEquals(0, events.get(events.size()-1).getEndIndex());
		
		obsTs  = new ObservationTimeSeries(4);
		elementsToAdd = new ArrayList<Observation>();
		events.clear();
		obsTs.addTimeSeriesStatusListener(new TimeSeriesStatusListener() {
			
			@Override
			public void timeseriesStatusChanged(TimeseriesStatusEvent status) {
				events.add(status);
			}
		});
		elementsToAdd.add(Observation.createRandomObservation());
		elementsToAdd.add(Observation.createRandomObservation());
		elementsToAdd.add(Observation.createRandomObservation());
		elementsToAdd.add(Observation.createRandomObservation());
		obsTs.addAll(elementsToAdd);
		Assert.assertEquals(1, events.size());
		Assert.assertEquals(0, events.get(events.size()-1).getStartIndex());
		Assert.assertEquals(3, events.get(events.size()-1).getEndIndex());
		
		
		obsTs  = new ObservationTimeSeries(4);
		events.clear();
		elementsToAdd = new ArrayList<Observation>();
		
		obsTs.addTimeSeriesStatusListener(new TimeSeriesStatusListener() {
			
			@Override
			public void timeseriesStatusChanged(TimeseriesStatusEvent status) {
				events.add(status);
			}
		});
		elementsToAdd.add(Observation.createRandomObservation());
		elementsToAdd.add(Observation.createRandomObservation());
		elementsToAdd.add(Observation.createRandomObservation());
		elementsToAdd.add(Observation.createRandomObservation());
		elementsToAdd.add(Observation.createRandomObservation());
		elementsToAdd.add(Observation.createRandomObservation());
		elementsToAdd.add(Observation.createRandomObservation());
		obsTs.addAll(elementsToAdd);
		Assert.assertEquals(1, events.size());
		Assert.assertEquals(0, events.get(events.size()-1).getStartIndex());
		Assert.assertEquals(3, events.get(events.size()-1).getEndIndex());
		elementsToAdd.clear();
		elementsToAdd.add(Observation.createRandomObservation());
		obsTs.addAll(elementsToAdd);
		Assert.assertEquals(2, events.size());
		Assert.assertEquals(3, events.get(events.size()-1).getStartIndex());
		Assert.assertEquals(3, events.get(events.size()-1).getEndIndex());
		
	}
	
	@Test
	public void testTimeSeriesEvent(){
		TimeSeries<Classification> clTs = createTimeSeriesWithNumElements(10);
		clTs.addTimeSeriesStatusListener(new TimeSeriesStatusListener() {
			@Override
			
			public void timeseriesStatusChanged(TimeseriesStatusEvent status) {
				hasReceivedEvent = true;
			}
		});
		clTs.add(Classification.createRandomClassification());
		Assert.assertEquals(true, hasReceivedEvent);
	}
	
	@Test
	public void testGetHead(){
		TimeSeries<Classification> ts =  createTimeSeriesWithNumElements(10);
		long timestampOfFifthElement = ts.get(4).getTimestamp(); 
		List<Classification> head = ts.getHead(timestampOfFifthElement);
		Assert.assertEquals(4, head.size());
		for(Classification cl : head){
			Assert.assertTrue(cl.getTimestamp()<timestampOfFifthElement);
		}
	}

	@Test
	public void testGetTail(){
		TimeSeries<Classification> ts =  createTimeSeriesWithNumElements(10);
		long timestampOfFifthElement = ts.get(4).getTimestamp(); 
		List<Classification> tail = ts.getTail(timestampOfFifthElement);
		Assert.assertEquals(6, tail.size());
		for(Classification cl : tail){
			Assert.assertTrue(cl.getTimestamp()>=timestampOfFifthElement);
		}
	}
	


	/*
	 * Tool functions 
	 */
	
	/**
	 * @param numElementsToTest
	 * @return
	 */
	private TimeSeries<Classification> createTimeSeriesWithNumElements(int numElementsToTest) {
		TimeSeries<Classification> clTs = new ClassificationTimeSeries();
		for (int i = 0; i < numElementsToTest; i++) {
			try {
				Thread.sleep(2);
			} catch (InterruptedException exception) {
				exception.printStackTrace();
			}
			clTs.add(Classification.createRandomClassification());
		}
		return clTs;
	}
	
}

package org.bham.aucom.data.timeseries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

import junit.framework.Assert;
import org.bham.aucom.data.AbstractData;

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

    /**
     * Test of setGeneratedFrom method, of class TimeSeries.
     */
    @Test
    public void testSetGeneratedFrom() {
        System.out.println("setGeneratedFrom");
        UUID dataTypeTimeSeriesId = null;
        TimeSeries instance = new TimeSeriesImpl();
        instance.setGeneratedFrom(dataTypeTimeSeriesId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGenerator method, of class TimeSeries.
     */
    @Test
    public void testSetGenerator() {
        System.out.println("setGenerator");
        UUID generatoId = null;
        TimeSeries instance = new TimeSeriesImpl();
        instance.setGenerator(generatoId);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGeneratorID method, of class TimeSeries.
     */
    @Test
    public void testGetGeneratorID() {
        System.out.println("getGeneratorID");
        TimeSeries instance = new TimeSeriesImpl();
        UUID expResult = null;
        UUID result = instance.getGeneratorID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGeneratedFromID method, of class TimeSeries.
     */
    @Test
    public void testGetGeneratedFromID() {
        System.out.println("getGeneratedFromID");
        TimeSeries instance = new TimeSeriesImpl();
        UUID expResult = null;
        UUID result = instance.getGeneratedFromID();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasGenerator method, of class TimeSeries.
     */
    @Test
    public void testHasGenerator() {
        System.out.println("hasGenerator");
        TimeSeries instance = new TimeSeriesImpl();
        boolean expResult = false;
        boolean result = instance.hasGenerator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of hasgeneratedFrom method, of class TimeSeries.
     */
    @Test
    public void testHasgeneratedFrom() {
        System.out.println("hasgeneratedFrom");
        TimeSeries instance = new TimeSeriesImpl();
        boolean expResult = false;
        boolean result = instance.hasgeneratedFrom();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class TimeSeries.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 0;
        TimeSeries instance = new TimeSeriesImpl();
        AbstractData expResult = null;
        AbstractData result = instance.get(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class TimeSeries.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        AbstractData s = null;
        TimeSeries instance = new TimeSeriesImpl();
        instance.add(s);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAll method, of class TimeSeries.
     */
    @Test
    public void testAddAll() {
        System.out.println("addAll");
        Collection<T> c = null;
        TimeSeries instance = new TimeSeriesImpl();
        instance.addAll(c);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class TimeSeries.
     */
    @Test
    public void testRemove() {
        System.out.println("remove");
        int i = 0;
        TimeSeries instance = new TimeSeriesImpl();
        instance.remove(i);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class TimeSeries.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        TimeSeries instance = new TimeSeriesImpl();
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class TimeSeries.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        TimeSeries instance = new TimeSeriesImpl();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getall method, of class TimeSeries.
     */
    @Test
    public void testGetall() {
        System.out.println("getall");
        TimeSeries instance = new TimeSeriesImpl();
        Collection expResult = null;
        Collection result = instance.getall();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTimeSeriesStatusListener method, of class TimeSeries.
     */
    @Test
    public void testAddTimeSeriesStatusListener() {
        System.out.println("addTimeSeriesStatusListener");
        TimeSeriesStatusListener listener = null;
        TimeSeries instance = new TimeSeriesImpl();
        instance.addTimeSeriesStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTimeseriesStatusListener method, of class TimeSeries.
     */
    @Test
    public void testRemoveTimeseriesStatusListener() {
        System.out.println("removeTimeseriesStatusListener");
        TimeSeriesStatusListener listener = null;
        TimeSeries instance = new TimeSeriesImpl();
        instance.removeTimeseriesStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireTimeseriesStatusChangedEvent method, of class TimeSeries.
     */
    @Test
    public void testFireTimeseriesStatusChangedEvent() {
        System.out.println("fireTimeseriesStatusChangedEvent");
        TimeseriesStatusEvent evt = null;
        TimeSeries instance = new TimeSeriesImpl();
        instance.fireTimeseriesStatusChangedEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isEmpty method, of class TimeSeries.
     */
    @Test
    public void testIsEmpty() {
        System.out.println("isEmpty");
        TimeSeries instance = new TimeSeriesImpl();
        boolean expResult = false;
        boolean result = instance.isEmpty();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class TimeSeries.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object arg0 = null;
        TimeSeries instance = new TimeSeriesImpl();
        boolean expResult = false;
        boolean result = instance.equals(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class TimeSeries.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        TimeSeries instance = new TimeSeriesImpl();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isOfType method, of class TimeSeries.
     */
    @Test
    public void testIsOfType() {
        System.out.println("isOfType");
        Class<? extends AbstractData> classToCheck = null;
        TimeSeries instance = new TimeSeriesImpl();
        boolean expResult = false;
        boolean result = instance.isOfType(classToCheck);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setType method, of class TimeSeries.
     */
    @Test
    public void testSetType() {
        System.out.println("setType");
        TimeSeriesType type = null;
        TimeSeries instance = new TimeSeriesImpl();
        instance.setType(type);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getType method, of class TimeSeries.
     */
    @Test
    public void testGetType() {
        System.out.println("getType");
        TimeSeries instance = new TimeSeriesImpl();
        TimeSeriesType expResult = null;
        TimeSeriesType result = instance.getType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TimeSeriesImpl extends TimeSeries {
    }
	
}

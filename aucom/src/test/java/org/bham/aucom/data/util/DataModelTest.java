package org.bham.aucom.data.util;

import java.util.LinkedHashMap;
import java.util.UUID;

import junit.framework.Assert;
import nu.xom.Element;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeseriesStatusEvent;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;

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

    /**
     * Test of getInstance method, of class DataModel.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        DataModel expResult = null;
        DataModel result = DataModel.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addModel method, of class DataModel.
     */
    @Test
    public void testAddModel() {
        System.out.println("addModel");
        Model modelToAdd = null;
        DataModel instance = null;
        UUID expResult = null;
        UUID result = instance.addModel(modelToAdd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAnomalyClassificator method, of class DataModel.
     */
    @Test
    public void testAddAnomalyClassificator() {
        System.out.println("addAnomalyClassificator");
        AnomalyClassificator classificatiorToAdd = null;
        DataModel instance = null;
        UUID expResult = null;
        UUID result = instance.addAnomalyClassificator(classificatiorToAdd);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class DataModel.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        DataModel instance = null;
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModels method, of class DataModel.
     */
    @Test
    public void testGetModels() {
        System.out.println("getModels");
        DataModel instance = null;
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.getModels();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassificators method, of class DataModel.
     */
    @Test
    public void testGetClassificators() {
        System.out.println("getClassificators");
        DataModel instance = null;
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.getClassificators();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassificators method, of class DataModel.
     */
    @Test
    public void testSetClassificators() {
        System.out.println("setClassificators");
        LinkedHashMap<UUID, AnomalyClassificator> classificators = null;
        DataModel instance = null;
        instance.setClassificators(classificators);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addModelDataStatusListener method, of class DataModel.
     */
    @Test
    public void testAddModelDataStatusListener() {
        System.out.println("addModelDataStatusListener");
        DataModelStatusListener listener = null;
        DataModel instance = null;
        instance.addModelDataStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeModelDataStatusListener method, of class DataModel.
     */
    @Test
    public void testRemoveModelDataStatusListener() {
        System.out.println("removeModelDataStatusListener");
        DataModelStatusListener listener = null;
        DataModel instance = null;
        instance.removeModelDataStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireDataModelStatusChangedEvent method, of class DataModel.
     */
    @Test
    public void testFireDataModelStatusChangedEvent() {
        System.out.println("fireDataModelStatusChangedEvent");
        DataModelStatusEvent evt = null;
        DataModel instance = null;
        instance.fireDataModelStatusChangedEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeseries method, of class DataModel.
     */
    @Test
    public void testGetTimeseries() {
        System.out.println("getTimeseries");
        DataModel instance = null;
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.getTimeseries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsDataItemWith method, of class DataModel.
     */
    @Test
    public void testContainsDataItemWith() {
        System.out.println("containsDataItemWith");
        UUID id = null;
        DataModel instance = null;
        boolean expResult = false;
        boolean result = instance.containsDataItemWith(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDataItemById method, of class DataModel.
     */
    @Test
    public void testGetDataItemById() {
        System.out.println("getDataItemById");
        UUID id = null;
        DataModel instance = null;
        AbstractData expResult = null;
        AbstractData result = instance.getDataItemById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeSeriesById method, of class DataModel.
     */
    @Test
    public void testGetTimeSeriesById() throws Exception {
        System.out.println("getTimeSeriesById");
        UUID id = null;
        DataModel instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.getTimeSeriesById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsTimeSeriesWithId method, of class DataModel.
     */
    @Test
    public void testContainsTimeSeriesWithId() {
        System.out.println("containsTimeSeriesWithId");
        UUID id = null;
        DataModel instance = null;
        boolean expResult = false;
        boolean result = instance.containsTimeSeriesWithId(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumerTimeseries method, of class DataModel.
     */
    @Test
    public void testGetNumerTimeseries() {
        System.out.println("getNumerTimeseries");
        DataModel instance = null;
        int expResult = 0;
        int result = instance.getNumerTimeseries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of timeseriesStatusChanged method, of class DataModel.
     */
    @Test
    public void testTimeseriesStatusChanged() {
        System.out.println("timeseriesStatusChanged");
        TimeseriesStatusEvent status = null;
        DataModel instance = null;
        instance.timeseriesStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isReady method, of class DataModel.
     */
    @Test
    public void testIsReady() {
        System.out.println("isReady");
        DataModel instance = null;
        boolean expResult = false;
        boolean result = instance.isReady();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

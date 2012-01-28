package org.bham.aucom.diagnoser.t2gram;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.junit.Test;

import javax.swing.*;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class T2GramModelTrainerTest {

	@Test
	public void testT2GramModelTrainer() {
		T2GramModelTrainer modelt = new T2GramModelTrainer();
	}
	@Test 
	public void testStartModelTrainerWithoutModel() throws Exception{
		T2GramModelTrainer modelt = new T2GramModelTrainer();
		modelt.start(new ObservationTimeSeries());
	}

    /**
     * Test of getValuesFromTrainingData method, of class T2GramModelTrainer.
     */
    @Test
    public void testGetValuesFromTrainingData() {
        System.out.println("getValuesFromTrainingData");
        Integer firstKey = null;
        Integer secondKey = null;
        T2GramModelTrainer instance = new T2GramModelTrainer();
        double[] expResult = null;
        double[] result = instance.getValuesFromTrainingData(firstKey, secondKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of start method, of class T2GramModelTrainer.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        TimeSeries<Observation> inTrainingData = null;
        T2GramModelTrainer instance = new T2GramModelTrainer();
        instance.start(inTrainingData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class T2GramModelTrainer.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        T2GramModelTrainer instance = new T2GramModelTrainer();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPanel method, of class T2GramModelTrainer.
     */
    @Test
    public void testGetPanel() {
        System.out.println("getPanel");
        T2GramModelTrainer instance = new T2GramModelTrainer();
        JPanel expResult = null;
        JPanel result = instance.getPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModel method, of class T2GramModelTrainer.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        T2GramModelTrainer instance = new T2GramModelTrainer();
        Model expResult = null;
        Model result = instance.getModel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModel method, of class T2GramModelTrainer.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        Model inModel = null;
        T2GramModelTrainer instance = new T2GramModelTrainer();
        instance.setModel(inModel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of graphStatusChanged method, of class T2GramModelTrainer.
     */
    @Test
    public void testGraphStatusChanged() {
        System.out.println("graphStatusChanged");
        GraphStateChangedEvent evt = null;
        T2GramModelTrainer instance = new T2GramModelTrainer();
        instance.graphStatusChanged(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInputSize method, of class T2GramModelTrainer.
     */
    @Test
    public void testGetInputSize() {
        System.out.println("getInputSize");
        T2GramModelTrainer instance = new T2GramModelTrainer();
        int expResult = 0;
        int result = instance.getInputSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class T2GramModelTrainer.
     */
    @Test
    public void testStop() throws Exception {
        System.out.println("stop");
        T2GramModelTrainer instance = new T2GramModelTrainer();
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

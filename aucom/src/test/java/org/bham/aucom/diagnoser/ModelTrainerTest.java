/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jxv911
 */
public class ModelTrainerTest {

    public ModelTrainerTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of start method, of class ModelTrainer.
     */
    @Test
    public void testStart() throws Exception {
        System.out.println("start");
        TimeSeries<Observation> trainingData = null;
        ModelTrainer instance = new ModelTrainerImpl();
        instance.start(trainingData);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class ModelTrainer.
     */
    @Test
    public void testStop() throws Exception {
        System.out.println("stop");
        ModelTrainer instance = new ModelTrainerImpl();
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addModelTrainerListener method, of class ModelTrainer.
     */
    @Test
    public void testAddModelTrainerListener() {
        System.out.println("addModelTrainerListener");
        ModelTrainerListener listener = null;
        ModelTrainer instance = new ModelTrainerImpl();
        instance.addModelTrainerListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeModelTrainerListener method, of class ModelTrainer.
     */
    @Test
    public void testRemoveModelTrainerListener() {
        System.out.println("removeModelTrainerListener");
        ModelTrainerListener listener = null;
        ModelTrainer instance = new ModelTrainerImpl();
        instance.removeModelTrainerListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllListeners method, of class ModelTrainer.
     */
    @Test
    public void testRemoveAllListeners() {
        System.out.println("removeAllListeners");
        ModelTrainer instance = new ModelTrainerImpl();
        instance.removeAllListeners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getModel method, of class ModelTrainer.
     */
    @Test
    public void testGetModel() {
        System.out.println("getModel");
        ModelTrainer instance = new ModelTrainerImpl();
        Model expResult = null;
        Model result = instance.getModel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setModel method, of class ModelTrainer.
     */
    @Test
    public void testSetModel() {
        System.out.println("setModel");
        Model inModel = null;
        ModelTrainer instance = new ModelTrainerImpl();
        instance.setModel(inModel);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class ModelTrainer.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        ModelTrainer instance = new ModelTrainerImpl();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ModelTrainerImpl implements ModelTrainer {

        public void start(TimeSeries<Observation> trainingData) throws Exception {
        }

        public void stop() throws Exception {
        }

        public void addModelTrainerListener(ModelTrainerListener listener) {
        }

        public void removeModelTrainerListener(ModelTrainerListener listener) {
        }

        public void removeAllListeners() {
        }

        public Model getModel() {
            return null;
        }

        public void setModel(Model inModel) throws ClassCastException {
        }

        public void reset() {
        }
    }

}
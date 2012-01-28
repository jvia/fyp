package org.bham.aucom.diagnoser.t2gram;

import org.bham.aucom.diagnoser.TrainerStatus;
import static org.junit.Assert.*;

import javax.swing.JPanel;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.ModelTrainerListener;
import org.bham.aucom.diagnoser.StatusChangedEvent;

public class AbstractModelTrainerTest {
	AbstractModelTrainer t ;

	@Before
	public void setUp() throws Exception {
		t = new AbstractModelTrainer() {
			
			@Override
			public JPanel getPanel() {
				// TODO Auto-generated method stub
				return null;
			}
			
			@Override
			public void stop() throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void start(TimeSeries<Observation> trainingData) throws Exception {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void setModel(Model inModel) throws ClassCastException {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public void reset() {
				// TODO Auto-generated method stub
				
			}
			
			@Override
			public Model getModel() {
				// TODO Auto-generated method stub
				return null;
			}
		};
	}

	@Test
	public void testAddModelTrainerListener() {
		ModelTrainerListener l = new ModelTrainerListener() {
			
			@Override
			public void modelTrainerStatusChanged(StatusChangedEvent evt) {
				// TODO Auto-generated method stub
				
			}
		};
		t.addModelTrainerListener(l);
		t.addModelTrainerListener(l);
		Assert.assertEquals(1, t.listenerList.getListenerCount());
	}

    /**
     * Test of setStatus method, of class AbstractModelTrainer.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        TrainerStatus newStatus = null;
        AbstractModelTrainer instance = new AbstractModelTrainerImpl();
        instance.setStatus(newStatus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireStatusChangedEvent method, of class AbstractModelTrainer.
     */
    @Test
    public void testFireStatusChangedEvent() {
        System.out.println("fireStatusChangedEvent");
        StatusChangedEvent evt = null;
        AbstractModelTrainer instance = new AbstractModelTrainerImpl();
        instance.fireStatusChangedEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isListenerRegistered method, of class AbstractModelTrainer.
     */
    @Test
    public void testIsListenerRegistered() {
        System.out.println("isListenerRegistered");
        ModelTrainerListener listener = null;
        AbstractModelTrainer instance = new AbstractModelTrainerImpl();
        boolean expResult = false;
        boolean result = instance.isListenerRegistered(listener);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeModelTrainerListener method, of class AbstractModelTrainer.
     */
    @Test
    public void testRemoveModelTrainerListener() {
        System.out.println("removeModelTrainerListener");
        ModelTrainerListener listener = null;
        AbstractModelTrainer instance = new AbstractModelTrainerImpl();
        instance.removeModelTrainerListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllListeners method, of class AbstractModelTrainer.
     */
    @Test
    public void testRemoveAllListeners() {
        System.out.println("removeAllListeners");
        AbstractModelTrainer instance = new AbstractModelTrainerImpl();
        instance.removeAllListeners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AbstractModelTrainerImpl extends AbstractModelTrainer {
    }

}

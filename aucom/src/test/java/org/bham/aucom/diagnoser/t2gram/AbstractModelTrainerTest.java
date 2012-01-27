package org.bham.aucom.diagnoser.t2gram;

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

}

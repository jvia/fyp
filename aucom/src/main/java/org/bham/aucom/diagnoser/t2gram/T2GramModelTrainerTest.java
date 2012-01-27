package org.bham.aucom.diagnoser.t2gram;

import org.junit.Test;

import org.bham.aucom.data.timeseries.ObservationTimeSeries;

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

}

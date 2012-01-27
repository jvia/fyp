package org.bham.aucom.fts.tranform;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Observation;

public class EncodeDataTest {
	EncodeData ed;
	@Before
	public void setUp() throws Exception {
		ed = new EncodeData();
	}

	@Test
	public void testITransformObservation() {
		try {
			Observation obs = Observation.createRandomObservation();
			System.out.println(obs.getContent());
			DataType  dtp = ed.iTransform(obs);
			System.out.println(dtp);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

}

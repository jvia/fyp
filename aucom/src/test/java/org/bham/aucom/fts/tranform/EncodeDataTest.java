package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.encoder.Encoder;
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

    /**
     * Test of iTransform method, of class EncodeData.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        Observation input = null;
        EncodeData instance = new EncodeData();
        DataType expResult = null;
        DataType result = instance.iTransform(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setEncoder method, of class EncodeData.
     */
    @Test
    public void testSetEncoder() {
        System.out.println("setEncoder");
        Encoder encoder = null;
        EncodeData instance = new EncodeData();
        instance.setEncoder(encoder);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEncoder method, of class EncodeData.
     */
    @Test
    public void testGetEncoder() {
        System.out.println("getEncoder");
        EncodeData instance = new EncodeData();
        Encoder expResult = null;
        Encoder result = instance.getEncoder();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

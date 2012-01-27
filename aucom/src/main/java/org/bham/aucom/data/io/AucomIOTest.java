package org.bham.aucom.data.io;

import java.io.FileNotFoundException;
import java.io.IOException;

import junit.framework.Assert;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.management.DataAlreadyExistsException;

public class AucomIOTest {

	@Before
	public void setUp() throws Exception {
			
	}

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(AucomIO.getInstance());
	}

	@Test
	public void testWriteFaultDetectionModel() {
//		T2GramFaultDetectionModel d = new T2GramFaultDetectionModel(new T2GramModelConfiguration("", new KDEProbabilityFactory(1.0), new StatisticalHysterisisAnomalyClassificator(3.0, 0.1) , new SlidingWindow(100, 5))); 
//		boolean successfull = AucomIO.getInstance().writeFaultDetectionModel(d);
//		Assert.assertEquals(true, successfull);
	}

	@Test
	public void testReadFaultDetectionModel() throws FileNotFoundException, ValidityException, DataAlreadyExistsException, IOException, ParsingException {
//		T2GramModelI m = (T2GramModelI)AucomIO.getInstance().readFaultDetectionModel(new File("/home/rgolombe/workspace/diagnoser/4bd1830b-bd3b-4dcf-a5aa-c33996cdcb9c.ml"));
//		Assert.assertNotNull(m.getTransitionMatrix());
//		m = (T2GramModelI)AucomIO.getInstance().readFaultDetectionModel(new File("/home/rgolombe/workspace/diagnoser/2b0f3e45-3769-483c-aabb-c8c6a3724116.ml"));
//		Assert.assertNotNull(m.getTransitionMatrix());
//		m = (T2GramModelI)AucomIO.getInstance().readFaultDetectionModel(new File("/home/rgolombe/workspace/diagnoser/510d4eaf-e168-4978-81af-ba721a59c60d.ml"));
//		Assert.assertNotNull(m.getTransitionMatrix());
	}

//
//	@Test
//	public void testReadTimeSeries() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testWriteClassificator() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testReadClassificator() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testWriteSlidingWindow() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testReadSlidingWindow() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testChangeCurrentWorkingDirectory() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetCurrentWorkingDirectory() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGenerateFileNameFor() {
//		fail("Not yet implemented");
//	}

}

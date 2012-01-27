package org.bham.aucom.data.io;

import junit.framework.Assert;
import nu.xom.ParsingException;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

import static org.junit.Assert.fail;

public class AucomIOTest {

	@Before
	public void setUp() throws Exception {
			
	}

	@Test
	public void testGetInstance() {
		Assert.assertNotNull(AucomIO.getInstance());
	}

	@Test
	public void testReadFaultDetectionModel() throws DataAlreadyExistsException, IOException, ParsingException {
        // TODO :: make test
		T2GramModelI m = (T2GramModelI)AucomIO.getInstance().readFaultDetectionModel(new File("/home/rgolombe/workspace/diagnoser/4bd1830b-bd3b-4dcf-a5aa-c33996cdcb9c.ml"));
		Assert.assertNotNull(m.getTransitionMatrix());
		m = (T2GramModelI)AucomIO.getInstance().readFaultDetectionModel(new File("/home/rgolombe/workspace/diagnoser/2b0f3e45-3769-483c-aabb-c8c6a3724116.ml"));
		Assert.assertNotNull(m.getTransitionMatrix());
		m = (T2GramModelI)AucomIO.getInstance().readFaultDetectionModel(new File("/home/rgolombe/workspace/diagnoser/510d4eaf-e168-4978-81af-ba721a59c60d.ml"));
		Assert.assertNotNull(m.getTransitionMatrix());
	}


    @Test
    public void testReadTimeSeries() {
        fail("Not yet implemented");
    }

    @Test
    public void testWriteClassificator() {
        fail("Not yet implemented");
    }

    @Test
    public void testReadClassificator() {
        fail("Not yet implemented");
    }

    @Test
    public void testWriteSlidingWindow() {
        fail("Not yet implemented");
    }

    @Test
    public void testReadSlidingWindow() {
        fail("Not yet implemented");
    }

    @Test
    public void testChangeCurrentWorkingDirectory() {
        fail("Not yet implemented");
    }

    @Test
    public void testGetCurrentWorkingDirectory() {
        fail("Not yet implemented");
    }

    @Test
    public void testGenerateFileNameFor() {
        fail("Not yet implemented");
    }

}

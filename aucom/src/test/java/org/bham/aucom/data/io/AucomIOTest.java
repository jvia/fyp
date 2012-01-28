package org.bham.aucom.data.io;

import java.util.UUID;
import junit.framework.Assert;
import nu.xom.ParsingException;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.Model;
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

    /**
     * Test of writeFaultDetectionModel method, of class AucomIO.
     */
    @Test
    public void testWriteFaultDetectionModel_Model() {
        System.out.println("writeFaultDetectionModel");
        Model model = null;
        AucomIO instance = null;
        boolean expResult = false;
        boolean result = instance.writeFaultDetectionModel(model);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeFaultDetectionModel method, of class AucomIO.
     */
    @Test
    public void testWriteFaultDetectionModel_Model_File() {
        System.out.println("writeFaultDetectionModel");
        Model model = null;
        File f = null;
        AucomIO instance = null;
        boolean expResult = false;
        boolean result = instance.writeFaultDetectionModel(model, f);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeTimeSeries method, of class AucomIO.
     */
    @Test
    public void testWriteTimeSeries_TimeSeries() {
        System.out.println("writeTimeSeries");
        TimeSeries<T> timeSeries = null;
        AucomIO instance = null;
        boolean expResult = false;
        boolean result = instance.writeTimeSeries(timeSeries);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeTimeSeries method, of class AucomIO.
     */
    @Test
    public void testWriteTimeSeries_TimeSeries_File() {
        System.out.println("writeTimeSeries");
        TimeSeries<T> timeSeries = null;
        File fileToWriteTo = null;
        AucomIO instance = null;
        boolean expResult = false;
        boolean result = instance.writeTimeSeries(timeSeries, fileToWriteTo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeTimeSeries method, of class AucomIO.
     */
    @Test
    public void testWriteTimeSeries_TimeSeries_String() throws Exception {
        System.out.println("writeTimeSeries");
        TimeSeries<T> timeSeries = null;
        String type = "";
        AucomIO instance = null;
        boolean expResult = false;
        boolean result = instance.writeTimeSeries(timeSeries, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of writeTimeSeries method, of class AucomIO.
     */
    @Test
    public void testWriteTimeSeries_3args() throws Exception {
        System.out.println("writeTimeSeries");
        TimeSeries<T> timeSeries = null;
        File fileToWriteTo = null;
        String type = "";
        AucomIO instance = null;
        boolean expResult = false;
        boolean result = instance.writeTimeSeries(timeSeries, fileToWriteTo, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readTimeSeriesRelativeToCurrentWorking method, of class AucomIO.
     */
    @Test
    public void testReadTimeSeriesRelativeToCurrentWorking_String() throws Exception {
        System.out.println("readTimeSeriesRelativeToCurrentWorking");
        String file = "";
        AucomIO instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.readTimeSeriesRelativeToCurrentWorking(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readTimeSeries method, of class AucomIO.
     */
    @Test
    public void testReadTimeSeries_File() throws Exception {
        System.out.println("readTimeSeries");
        File file = null;
        AucomIO instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.readTimeSeries(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readTimeSeriesRelativeToCurrentWorking method, of class AucomIO.
     */
    @Test
    public void testReadTimeSeriesRelativeToCurrentWorking_String_String() throws Exception {
        System.out.println("readTimeSeriesRelativeToCurrentWorking");
        String file = "";
        String type = "";
        AucomIO instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.readTimeSeriesRelativeToCurrentWorking(file, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of readTimeSeries method, of class AucomIO.
     */
    @Test
    public void testReadTimeSeries_File_String() throws Exception {
        System.out.println("readTimeSeries");
        File file = null;
        String type = "";
        AucomIO instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.readTimeSeries(file, type);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateFileFor method, of class AucomIO.
     */
    @Test
    public void testGenerateFileFor() {
        System.out.println("generateFileFor");
        UUID id = null;
        String fileExtension = "";
        AucomIO instance = null;
        File expResult = null;
        File result = instance.generateFileFor(id, fileExtension);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

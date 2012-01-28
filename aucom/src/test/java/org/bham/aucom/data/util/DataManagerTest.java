package org.bham.aucom.data.util;

import java.util.List;
import javax.swing.JPanel;
import junit.framework.Assert;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesType;
import org.bham.aucom.diagnoser.Model;
import org.junit.Before;
import org.junit.Test;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.UUID;

public class DataManagerTest {

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void testContainsDataSequenceWithName() {
        // TODO :: fix test
        UUID id1;
        try {
            id1 = AucomIO.getInstance().readTimeSeriesRelativeToCurrentWorking("data/unittest/norm100_2.obs").getId();
            Assert.assertEquals(true, DataManager.getInstance().containsTimeSeriesWithID(id1));
            UUID id2 = AucomIO.getInstance().readTimeSeriesRelativeToCurrentWorking("data/unittest/norm100_3.obs").getId();
            Assert.assertTrue(DataManager.getInstance().containsTimeSeriesWithID((id2)));
            Assert.assertTrue(DataManager.getInstance().containsTimeSeriesWithID((id1)));
            DataManager.getInstance().reset();
            Assert.assertFalse(DataManager.getInstance().containsTimeSeriesWithID((id1)));
            Assert.assertFalse(DataManager.getInstance().containsTimeSeriesWithID((id2)));
        } catch (FileNotFoundException exception) {
            Assert.fail(exception.getLocalizedMessage());
        } catch (ValidityException exception) {
            Assert.fail(exception.getLocalizedMessage());
        } catch (DataAlreadyExistsException exception) {
            Assert.fail(exception.getLocalizedMessage());
        } catch (IOException exception) {
            Assert.fail(exception.getLocalizedMessage());
        } catch (ParsingException e) {
            Assert.fail(e.getLocalizedMessage());
        }
//	        content +='            <org.bham.aucom:train>/home/rgolombe/workspace/org.bham.aucom/data/tobi_iros10_session/records/csv/normal002.csv</org.bham.aucom:train>\n'
//	        content +='            <org.bham.aucom:train>/home/rgolombe/workspace/org.bham.aucom/data/tobi_iros10_session/records/csv/normal004.csv</org.bham.aucom:train>\n'
//	        content +='            <org.bham.aucom:test>/home/rgolombe/workspace/org.bham.aucom/data/tobi_iros10_session/records/csv/normal003.csv</org.bham.aucom:test>\n'

    }

    /**
     * Test of createResultTimeSeriesFor method, of class DataManager.
     */
    @Test
    public void testCreateResultTimeSeriesFor() {
        System.out.println("createResultTimeSeriesFor");
        TimeSeries<?> timeSeries = null;
        DataManager instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.createResultTimeSeriesFor(timeSeries);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class DataManager.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        DataManager instance = null;
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeSeriesById method, of class DataManager.
     */
    @Test
    public void testGetTimeSeriesById() throws Exception {
        System.out.println("getTimeSeriesById");
        UUID id = null;
        DataManager instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.getTimeSeriesById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addTimeSeries method, of class DataManager.
     */
    @Test
    public void testAddTimeSeries() {
        System.out.println("addTimeSeries");
        TimeSeries<?> in = null;
        DataManager instance = null;
        instance.addTimeSeries(in);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFaultDetectionModelById method, of class DataManager.
     */
    @Test
    public void testGetFaultDetectionModelById() {
        System.out.println("getFaultDetectionModelById");
        UUID id = null;
        DataManager instance = null;
        Model expResult = null;
        Model result = instance.getFaultDetectionModelById(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeSeriesTypeFor method, of class DataManager.
     */
    @Test
    public void testGetTimeSeriesTypeFor() {
        System.out.println("getTimeSeriesTypeFor");
        AbstractData data = null;
        DataManager instance = null;
        TimeSeriesType expResult = null;
        TimeSeriesType result = instance.getTimeSeriesTypeFor(data);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class DataManager.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        DataManager expResult = null;
        DataManager result = DataManager.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsTimeSeriesWithID method, of class DataManager.
     */
    @Test
    public void testContainsTimeSeriesWithID() {
        System.out.println("containsTimeSeriesWithID");
        UUID id = null;
        DataManager instance = null;
        boolean expResult = false;
        boolean result = instance.containsTimeSeriesWithID(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimeSeriesOfType method, of class DataManager.
     */
    @Test
    public void testGetTimeSeriesOfType() {
        System.out.println("getTimeSeriesOfType");
        Class<T> classToSelectAfter = null;
        DataManager instance = null;
        List expResult = null;
        List result = instance.getTimeSeriesOfType(classToSelectAfter);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deriveScoreSequenceFrom method, of class DataManager.
     */
    @Test
    public void testDeriveScoreSequenceFrom() {
        System.out.println("deriveScoreSequenceFrom");
        TimeSeries<Score> sequenceToDeriveFrom = null;
        String nameOfDerivedSequence = "";
        DataManager instance = null;
        TimeSeries expResult = null;
        TimeSeries result = instance.deriveScoreSequenceFrom(sequenceToDeriveFrom, nameOfDerivedSequence);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTestItemDataType method, of class DataManager.
     */
    @Test
    public void testGetTestItemDataType() {
        System.out.println("getTestItemDataType");
        DataManager instance = null;
        DataType expResult = null;
        DataType result = instance.getTestItemDataType();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTestObservation method, of class DataManager.
     */
    @Test
    public void testGetTestObservation() {
        System.out.println("getTestObservation");
        DataManager instance = null;
        Observation expResult = null;
        Observation result = instance.getTestObservation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPanel method, of class DataManager.
     */
    @Test
    public void testGetPanel() {
        System.out.println("getPanel");
        DataManager instance = null;
        JPanel expResult = null;
        JPanel result = instance.getPanel();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addDataModelStatusListener method, of class DataManager.
     */
    @Test
    public void testAddDataModelStatusListener() {
        System.out.println("addDataModelStatusListener");
        DataModelStatusListener listener = null;
        DataManager instance = null;
        instance.addDataModelStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeDataModelStatusListener method, of class DataManager.
     */
    @Test
    public void testRemoveDataModelStatusListener() {
        System.out.println("removeDataModelStatusListener");
        DataModelStatusListener listener = null;
        DataManager instance = null;
        instance.removeDataModelStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAllObservationTimeSeries method, of class DataManager.
     */
    @Test
    public void testGetAllObservationTimeSeries() {
        System.out.println("getAllObservationTimeSeries");
        DataManager instance = null;
        List expResult = null;
        List result = instance.getAllObservationTimeSeries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


}

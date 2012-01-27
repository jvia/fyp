package org.bham.aucom.data.util;

import junit.framework.Assert;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
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


}

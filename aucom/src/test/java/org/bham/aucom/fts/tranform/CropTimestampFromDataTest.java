package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.DefaultEvent;
import nu.xom.Element;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.fail;
import static org.junit.Assert.assertEquals;

public class CropTimestampFromDataTest {

    CropTimestampFromData crop;

    @Before
    public void setUp() throws Exception
    {
        crop = new CropTimestampFromData();
    }

    @Test
    public void testReset()
    {
        crop.reset();
        long timestamp = (long) Math.random() * Long.MAX_VALUE;
        ArrayList<DomainFeature> features = new ArrayList<DomainFeature>();
        features.add(new DomainFeature("scope", "a"));
        features.add(new DomainFeature("type", "b"));
        features.add(new DomainFeature("source", "c"));
        DataType d = new DataType(features, 1, new Observation(new Element("dt1"), timestamp));

        assertEquals(timestamp, d.getTimestamp());
        DefaultEvent instance = new DefaultEvent(d);
        try {
            crop.handleEvent(instance);
            assertEquals(0, d.getTimestamp());
            assertEquals(timestamp, crop.getFirstTimestamp());
            crop.reset();
            assertEquals(-1, crop.getFirstTimestamp());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    @Test
    public void testTransformRawData()
    {
        long timestamp = (long) Math.random() * Long.MAX_VALUE;
        ArrayList<DomainFeature> features = new ArrayList<DomainFeature>();
        features.add(new DomainFeature("scope", "a"));
        features.add(new DomainFeature("type", "b"));
        features.add(new DomainFeature("source", "c"));
        DataType d = new DataType(features, 1, new Observation(new Element("dt1"), timestamp));
        assertEquals(timestamp, d.getTimestamp());
        DefaultEvent instance = new DefaultEvent(d);
        try {
            crop.handleEvent(instance);
            assertEquals(0, d.getTimestamp());
            assertEquals(timestamp, crop.getFirstTimestamp());
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }

    /**
     * Test of setFirstTimestamp method, of class CropTimestampFromData.
     */
    @Test
    public void testSetFirstTimestamp() {
        System.out.println("setFirstTimestamp");
        long firstTimestamp = 0L;
        CropTimestampFromData instance = new CropTimestampFromData();
        instance.setFirstTimestamp(firstTimestamp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFirstTimestamp method, of class CropTimestampFromData.
     */
    @Test
    public void testGetFirstTimestamp() {
        System.out.println("getFirstTimestamp");
        CropTimestampFromData instance = new CropTimestampFromData();
        long expResult = 0L;
        long result = instance.getFirstTimestamp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iTransform method, of class CropTimestampFromData.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        AbstractData input = null;
        CropTimestampFromData instance = new CropTimestampFromData();
        AbstractData expResult = null;
        AbstractData result = instance.iTransform(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

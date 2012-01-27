package org.bham.aucom.fts.tranform;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import net.sf.xcf.fts.DefaultEvent;
import nu.xom.Element;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;

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
}

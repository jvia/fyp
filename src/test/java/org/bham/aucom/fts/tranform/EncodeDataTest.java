package org.bham.aucom.fts.tranform;

import nu.xom.Attribute;
import nu.xom.Element;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Observation;
import org.bham.aucom.util.Constants;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertThat;

public class EncodeDataTest {

    private EncodeData ed;

    /**
     * Use the same encoder for all
     *
     * @throws Exception could not create a valid encoding instance
     */
    @Before
    public void setUp() throws Exception {
        ed = new EncodeData();
    }

    /**
     * Test that an observation can be encoded into the correct format. The
     * current classes.txt file contains these entries:
     * <p/>
     * ADD:node0:three.sa    1
     * ADD:node1:three.sa    2
     * ADD:node2:three.sa    3
     * DELETE:node3:three.sa 4
     *
     * @throws Exception encoder error
     */
    @Test
    public void testITransformObservation() throws Exception {
        Element node0 = new Element("content");
        Element node3 = new Element("content");
        Element nodeX = new Element("content");
        DataType result;
        long timestamp;

        // Test for event type 1
        node0.addAttribute(new Attribute(Constants.EVENT_TYPE, "ADD"));
        node0.addAttribute(new Attribute(Constants.SOURCE_TYPE, "node0"));
        node0.addAttribute(new Attribute(Constants.SCOPE_TYPE, "three.sa"));
        node0.appendChild(String.valueOf(Math.random() * 1000));
        timestamp = System.currentTimeMillis();
        result = ed.iTransform(new Observation(node0, timestamp));
        assertThat(result.getEventType(), is(1));

        // Test for event type 4
        node3.addAttribute(new Attribute(Constants.EVENT_TYPE, "DELETE"));
        node3.addAttribute(new Attribute(Constants.SOURCE_TYPE, "node3"));
        node3.addAttribute(new Attribute(Constants.SCOPE_TYPE, "three.sa"));
        node3.appendChild(String.valueOf(Math.random() * 1000));
        timestamp = System.currentTimeMillis();
        result = ed.iTransform(new Observation(node3, timestamp));
        assertThat(result.getEventType(), is(4));


        // Test for bad event type
        nodeX.addAttribute(new Attribute(Constants.EVENT_TYPE, "ADD"));
        nodeX.addAttribute(new Attribute(Constants.SOURCE_TYPE, "nodeX"));
        nodeX.addAttribute(new Attribute(Constants.SCOPE_TYPE, "three.sa"));
        nodeX.appendChild(String.valueOf(Math.random() * 1000));
        timestamp = System.currentTimeMillis();
        result = ed.iTransform(new Observation(nodeX, timestamp));
        // it's 5 because the class will create new encodings on the fly
        assertThat(result.getEventType(), is(5));

    }
}

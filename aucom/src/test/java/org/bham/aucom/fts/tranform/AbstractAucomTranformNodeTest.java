package org.bham.aucom.fts.tranform;

import junit.framework.Assert;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class AbstractAucomTranformNodeTest {
    AbstractAucomTranformNode<Observation, Observation> absTs;

    @Before
    public void setUp() throws Exception {
        absTs = new AbstractAucomTranformNode<Observation, Observation>("") {

            @Override
            protected Observation iTransform(Observation input) throws Exception {
                return null;
            }
        };
    }

    @Test
    public void testSetTimeSeries() {
        absTs.setTimeSeries(new ObservationTimeSeries());
        Assert.assertNotNull(absTs.ts);
    }

    @Test
    public void testGetTimeSeries() {
        absTs.setTimeSeries(new ObservationTimeSeries());
        Assert.assertNotNull(absTs.getTimeSeries());
    }

    @Test
    public void testAddTransformNodeListener() {
        TransformNodeEventListener l = new TransformNodeEventListener() {

            @Override
            public void handleTransformNodeEvent(TransformNodeEvent evt) {
            }
        };
        absTs.addTransformNodeListener(l);
        Assert.assertEquals(1, absTs.getNumberListeners());
        absTs.addTransformNodeListener(l);
        Assert.assertEquals(1, absTs.getNumberListeners());
    }

    /**
     * Test of transform method, of class AbstractAucomTranformNode.
     */
    @Test
    public void testTransform() throws Exception {
        System.out.println("transform");
        AbstractData input = null;
        AbstractAucomTranformNode instance = null;
        AbstractData expResult = null;
        AbstractData result = instance.transform(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iTransform method, of class AbstractAucomTranformNode.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        AbstractData input = null;
        AbstractAucomTranformNode instance = null;
        AbstractData expResult = null;
        AbstractData result = instance.iTransform(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireStatusChangedEvent method, of class AbstractAucomTranformNode.
     */
    @Test
    public void testFireStatusChangedEvent() {
        System.out.println("fireStatusChangedEvent");
        TransformNodeEvent evt = null;
        AbstractAucomTranformNode instance = null;
        instance.fireStatusChangedEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberListeners method, of class AbstractAucomTranformNode.
     */
    @Test
    public void testGetNumberListeners() {
        System.out.println("getNumberListeners");
        AbstractAucomTranformNode instance = null;
        int expResult = 0;
        int result = instance.getNumberListeners();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isListenerRegistered method, of class AbstractAucomTranformNode.
     */
    @Test
    public void testIsListenerRegistered() {
        System.out.println("isListenerRegistered");
        TransformNodeEventListener listener = null;
        AbstractAucomTranformNode instance = null;
        boolean expResult = false;
        boolean result = instance.isListenerRegistered(listener);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeTransformNodeListener method, of class
     * AbstractAucomTranformNode.
     */
    @Test
    public void testRemoveTransformNodeListener() {
        System.out.println("removeTransformNodeListener");
        TransformNodeEventListener listener = null;
        AbstractAucomTranformNode instance = null;
        instance.removeTransformNodeListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeAllListeners method, of class AbstractAucomTranformNode.
     */
    @Test
    public void testRemoveAllListeners() {
        System.out.println("removeAllListeners");
        AbstractAucomTranformNode instance = null;
        instance.removeAllListeners();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AbstractAucomTranformNodeImpl extends AbstractAucomTranformNode<Score, Score> {

        public AbstractAucomTranformNodeImpl() {
            super("");
        }

        public Score iTransform(Score input) throws Exception {
            return null;
        }
    }
}

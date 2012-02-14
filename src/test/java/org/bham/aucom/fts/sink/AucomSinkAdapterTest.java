package org.bham.aucom.fts.sink;

import junit.framework.Assert;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.Observation;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class AucomSinkAdapterTest {
    class TestAdapter<TIn extends AbstractData> extends AucomSinkAdapter<TIn> {

        public TestAdapter() {
            super("TestAdapter");
        }

        @Override
        protected void pushItem(TIn e) throws Exception {

        }

    }

    public AucomSinkAdapter adapter;

    @Before
    public void setUp() throws Exception {
        adapter = new TestAdapter<Observation>();
    }

    @Test
    public void testIsSinkStatusListenerRegistered() {
        SinkStatusListener s = new SinkStatusListener() {

            @Override
            public void sinkStatusChanged(AucomSinkStatusEvent event) {
                // TODO Auto-generated method stub

            }
        };
        adapter.addSinkStatusListener(s);
        Assert.assertEquals(true, adapter.isSinkStatusListenerRegistered(s));
    }

    @Test
    public void testAddSinkStatusListener() {
        SinkStatusListener s = new SinkStatusListener() {

            @Override
            public void sinkStatusChanged(AucomSinkStatusEvent event) {
                // TODO Auto-generated method stub

            }
        };
        adapter.addSinkStatusListener(s);
        Assert.assertEquals(1, adapter.getNumberListeners());
        adapter.addSinkStatusListener(s);
        Assert.assertEquals(1, adapter.getNumberListeners());
    }

    @Test
    public void testRemoveAucomSinkStatusListener() {
        SinkStatusListener s = new SinkStatusListener() {

            @Override
            public void sinkStatusChanged(AucomSinkStatusEvent event) {
                // TODO Auto-generated method stub

            }
        };
        adapter.addSinkStatusListener(s);
        adapter.removeAucomSinkStatusListener(s);
        Assert.assertEquals(0, adapter.getNumberListeners());
    }

    /**
     * Test of fireAucomSinkStatusChangedEvent method, of class
     * AucomSinkAdapter.
     */
    @Test
    public void testFireAucomSinkStatusChangedEvent() {
        System.out.println("fireAucomSinkStatusChangedEvent");
        AucomSinkStatusEvent evt = null;
        AucomSinkAdapter instance = null;
        instance.fireAucomSinkStatusChangedEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }


    /**
     * Test of getNumberListeners method, of class AucomSinkAdapter.
     */
    @Test
    public void testGetNumberListeners() {
        System.out.println("getNumberListeners");
        AucomSinkAdapter instance = null;
        int expResult = 0;
        int result = instance.getNumberListeners();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AucomSinkAdapterImpl extends AucomSinkAdapter {

        public AucomSinkAdapterImpl() {
            super("");
        }

        @Override
        protected void pushItem(Object o) throws Exception {
            throw new UnsupportedOperationException("pushItem() not implemented yet");
        }
    }
}

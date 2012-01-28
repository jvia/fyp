package org.bham.aucom.fts.source;

import static org.junit.Assert.fail;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Observation;

public class AucomSourceAdapterTest {
	class TestSourceAdapter extends AucomSourceAdapter<Observation>{

		public TestSourceAdapter() {
			super("TestSourceAdapter");
		}

		@Override
		protected void iDisconnect() throws ActionFailedException {
			
		}

		@Override
		protected void iConnect() throws ActionFailedException {
			
		}

		@Override
		protected Observation iNextItem() throws Exception {
			return null;
		}
		
	}
	AucomSourceAdapter<Observation> adapter;
	@Before
	public void setUp() throws Exception {
		adapter = new TestSourceAdapter();
	}

	@Test
	public void testAddSourceStatusListener() {
		SourceStatusListener l = new SourceStatusListener() {
			
			@Override
			public void sourceStatusChanged(SourceStatusEvent event) {
				
			}
		};
		adapter.addSourceStatusListener(l);
		Assert.assertEquals(1, adapter.getNumberListeners());
		adapter.addSourceStatusListener(l);
		Assert.assertEquals(1, adapter.getNumberListeners());
		
	}

	@Test
	public void testRemoveSourceStatusListener() {
		SourceStatusListener l = new SourceStatusListener() {
			
			@Override
			public void sourceStatusChanged(SourceStatusEvent event) {
				
			}
		};
		adapter.addSourceStatusListener(l);
		adapter.removeSourceStatusListener(l);
		Assert.assertEquals(0, adapter.getNumberListeners());
	}

    /**
     * Test of getNumberListeners method, of class AucomSourceAdapter.
     */
    @Test
    public void testGetNumberListeners() {
        System.out.println("getNumberListeners");
        AucomSourceAdapter instance = null;
        int expResult = 0;
        int result = instance.getNumberListeners();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isListenerRegistered method, of class AucomSourceAdapter.
     */
    @Test
    public void testIsListenerRegistered() {
        System.out.println("isListenerRegistered");
        SourceStatusListener listener = null;
        AucomSourceAdapter instance = null;
        boolean expResult = false;
        boolean result = instance.isListenerRegistered(listener);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireSourceStatusChangedEvent method, of class AucomSourceAdapter.
     */
    @Test
    public void testFireSourceStatusChangedEvent() {
        System.out.println("fireSourceStatusChangedEvent");
        SourceStatusEvent evt = null;
        AucomSourceAdapter instance = null;
        instance.fireSourceStatusChangedEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setState method, of class AucomSourceAdapter.
     */
    @Test
    public void testSetState() {
        System.out.println("setState");
        SourceStatus state = null;
        AucomSourceAdapter instance = null;
        instance.setState(state);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class AucomSourceAdapter.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        AucomSourceAdapter instance = null;
        SourceStatus expResult = null;
        SourceStatus result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pause method, of class AucomSourceAdapter.
     */
    @Test
    public void testPause() {
        System.out.println("pause");
        AucomSourceAdapter instance = null;
        instance.pause();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resume method, of class AucomSourceAdapter.
     */
    @Test
    public void testResume() throws Exception {
        System.out.println("resume");
        AucomSourceAdapter instance = null;
        instance.resume();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setsendLastElement method, of class AucomSourceAdapter.
     */
    @Test
    public void testSetsendLastElement() {
        System.out.println("setsendLastElement");
        AucomSourceAdapter instance = null;
        instance.setsendLastElement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetSendLastElement method, of class AucomSourceAdapter.
     */
    @Test
    public void testResetSendLastElement() {
        System.out.println("resetSendLastElement");
        AucomSourceAdapter instance = null;
        instance.resetSendLastElement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isSentLastElement method, of class AucomSourceAdapter.
     */
    @Test
    public void testIsSentLastElement() {
        System.out.println("isSentLastElement");
        AucomSourceAdapter instance = null;
        boolean expResult = false;
        boolean result = instance.isSentLastElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iDisconnect method, of class AucomSourceAdapter.
     */
    @Test
    public void testIDisconnect() throws Exception {
        System.out.println("iDisconnect");
        AucomSourceAdapter instance = null;
        instance.iDisconnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iConnect method, of class AucomSourceAdapter.
     */
    @Test
    public void testIConnect() throws Exception {
        System.out.println("iConnect");
        AucomSourceAdapter instance = null;
        instance.iConnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of connect method, of class AucomSourceAdapter.
     */
    @Test
    public void testConnect() throws Exception {
        System.out.println("connect");
        AucomSourceAdapter instance = null;
        instance.connect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of disconnect method, of class AucomSourceAdapter.
     */
    @Test
    public void testDisconnect() throws Exception {
        System.out.println("disconnect");
        AucomSourceAdapter instance = null;
        instance.disconnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iNextItem method, of class AucomSourceAdapter.
     */
    @Test
    public void testINextItem() throws Exception {
        System.out.println("iNextItem");
        AucomSourceAdapter instance = null;
        Object expResult = null;
        Object result = instance.iNextItem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of nextItem method, of class AucomSourceAdapter.
     */
    @Test
    public void testNextItem() throws Exception {
        System.out.println("nextItem");
        AucomSourceAdapter instance = null;
        Object expResult = null;
        Object result = instance.nextItem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AucomSourceAdapterImpl extends AucomSourceAdapter {

        public AucomSourceAdapterImpl() {
            super("");
        }

        public void iDisconnect() throws ActionFailedException {
        }

        public void iConnect() throws ActionFailedException {
        }

        public T iNextItem() throws Exception {
            return null;
        }
    }

}

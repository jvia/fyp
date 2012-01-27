package org.bham.aucom.fts.sink;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.Observation;

public class AucomSinkAdapterTest {
	class TestAdapter<TIn extends AbstractData> extends AucomSinkAdapter<TIn>{

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
	public void testIsSinkStatusListenerRegistered(){
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
}

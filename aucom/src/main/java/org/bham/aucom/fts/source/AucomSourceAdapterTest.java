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

}

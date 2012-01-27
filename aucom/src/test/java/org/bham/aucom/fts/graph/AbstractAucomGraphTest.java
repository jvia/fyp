package org.bham.aucom.fts.graph;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.fts.sink.TimeSeriesSink;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.main.GraphStatusListener;

public class AbstractAucomGraphTest {
	class TestGraph extends AbstractAucomGraph{
		public TestGraph() {
			super("TestGraph");
		}

		public boolean pcs = true;
		TimeSeriesSource<Observation> obsSource; 
		TimeSeriesSink<Observation> obsSink;
		@Override
		public boolean preconditionsSatisfied() {
			return pcs;
		}
		
		@Override
		protected void initGraph() {
			obsSource = new TimeSeriesSource<Observation>("source");
			obsSink = new TimeSeriesSink<Observation>(new ObservationTimeSeries());
			graph.connect(obsSource, obsSink);
		}
		
		@Override
		protected String getReason() {
			return "no reason";
		}
		
		@Override
		protected void cleanUp() {
			// ingore
		}
	}
	AbstractAucomGraph aucomGraph;
	
	@Before
	public void setUp() throws Exception {
		aucomGraph = new TestGraph();
	}
	
	@Test
	public void testStart() throws ActionFailedException{
		aucomGraph.start();
	}
	
	@Test
	public void testAddGraphListener() {
		aucomGraph.addGraphListener(new GraphStatusListener() {
			
			@Override
			public void graphStatusChanged(GraphStateChangedEvent evt) {
				// TODO Auto-generated method stub
				
			}
		});
		Assert.assertEquals(1,aucomGraph.getNumberGraphListeners());
	}

	@Test
	public void testRemoveMyEventListener() {
		GraphStatusListener l = new GraphStatusListener() {
			@Override
			public void graphStatusChanged(GraphStateChangedEvent evt) {
			}
		};
		aucomGraph.addGraphListener(l);
		aucomGraph.removeMyEventListener(l);
		Assert.assertEquals(0,aucomGraph.getNumberGraphListeners());
	}

	@Test
	public void testRemoveAllListeners() {
		GraphStatusListener l = new GraphStatusListener() {
			@Override
			public void graphStatusChanged(GraphStateChangedEvent evt) {
			}
		};
		aucomGraph.addGraphListener(l);
		aucomGraph.removeAllListeners();
		Assert.assertEquals(0,aucomGraph.getNumberGraphListeners());
	}

	@Test
	public void testFireGraphStatusChangedEvent() {
		GraphStatusListener l = new GraphStatusListener() {
			@Override
			public void graphStatusChanged(GraphStateChangedEvent evt) {
				
			}
		};
		aucomGraph.addGraphListener(l);
	}
	@Test 
	public void testlistenToNodes(){
	}
		
}

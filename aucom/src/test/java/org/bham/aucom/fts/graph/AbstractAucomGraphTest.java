package org.bham.aucom.fts.graph;

import java.util.Iterator;
import junit.framework.Assert;
import net.sf.xcf.fts.Node;
import org.bham.aucom.fts.graph.AbstractAucomGraph.GraphStatus;
import org.bham.aucom.fts.sink.AucomSinkStatusEvent;
import org.bham.aucom.fts.source.SourceStatusEvent;
import org.bham.aucom.fts.tranform.TransformNodeEvent;

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

    /**
     * Test of initGraph method, of class AbstractAucomGraph.
     */
    @Test
    public void testInitGraph() {
        System.out.println("initGraph");
        AbstractAucomGraph instance = null;
        instance.initGraph();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of saveGraph method, of class AbstractAucomGraph.
     */
    @Test
    public void testSaveGraph() {
        System.out.println("saveGraph");
        AbstractAucomGraph instance = null;
        instance.saveGraph();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGraphName method, of class AbstractAucomGraph.
     */
    @Test
    public void testGetGraphName() {
        System.out.println("getGraphName");
        AbstractAucomGraph instance = null;
        String expResult = "";
        String result = instance.getGraphName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isNotReadyStatus method, of class AbstractAucomGraph.
     */
    @Test
    public void testIsNotReadyStatus() {
        System.out.println("isNotReadyStatus");
        AbstractAucomGraph instance = null;
        boolean expResult = false;
        boolean result = instance.isNotReadyStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isRunningStatus method, of class AbstractAucomGraph.
     */
    @Test
    public void testIsRunningStatus() {
        System.out.println("isRunningStatus");
        AbstractAucomGraph instance = null;
        boolean expResult = false;
        boolean result = instance.isRunningStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReason method, of class AbstractAucomGraph.
     */
    @Test
    public void testGetReason() {
        System.out.println("getReason");
        AbstractAucomGraph instance = null;
        String expResult = "";
        String result = instance.getReason();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of preconditionsSatisfied method, of class AbstractAucomGraph.
     */
    @Test
    public void testPreconditionsSatisfied() {
        System.out.println("preconditionsSatisfied");
        AbstractAucomGraph instance = null;
        boolean expResult = false;
        boolean result = instance.preconditionsSatisfied();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of listenToNodes method, of class AbstractAucomGraph.
     */
    @Test
    public void testListenToNodes() {
        System.out.println("listenToNodes");
        Iterator<Node<?, ?>> nodes = null;
        AbstractAucomGraph instance = null;
        instance.listenToNodes(nodes);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pause method, of class AbstractAucomGraph.
     */
    @Test
    public void testPause() throws Exception {
        System.out.println("pause");
        AbstractAucomGraph instance = null;
        instance.pause();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resume method, of class AbstractAucomGraph.
     */
    @Test
    public void testResume() {
        System.out.println("resume");
        AbstractAucomGraph instance = null;
        instance.resume();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of stop method, of class AbstractAucomGraph.
     */
    @Test
    public void testStop() {
        System.out.println("stop");
        AbstractAucomGraph instance = null;
        instance.stop();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanUp method, of class AbstractAucomGraph.
     */
    @Test
    public void testCleanUp() {
        System.out.println("cleanUp");
        AbstractAucomGraph instance = null;
        instance.cleanUp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sourceStatusChanged method, of class AbstractAucomGraph.
     */
    @Test
    public void testSourceStatusChanged() {
        System.out.println("sourceStatusChanged");
        SourceStatusEvent event = null;
        AbstractAucomGraph instance = null;
        instance.sourceStatusChanged(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of handleTransformNodeEvent method, of class AbstractAucomGraph.
     */
    @Test
    public void testHandleTransformNodeEvent() {
        System.out.println("handleTransformNodeEvent");
        TransformNodeEvent event = null;
        AbstractAucomGraph instance = null;
        instance.handleTransformNodeEvent(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sinkStatusChanged method, of class AbstractAucomGraph.
     */
    @Test
    public void testSinkStatusChanged() {
        System.out.println("sinkStatusChanged");
        AucomSinkStatusEvent event = null;
        AbstractAucomGraph instance = null;
        instance.sinkStatusChanged(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setStatus method, of class AbstractAucomGraph.
     */
    @Test
    public void testSetStatus() {
        System.out.println("setStatus");
        GraphStatus newStatus = null;
        AbstractAucomGraph instance = null;
        instance.setStatus(newStatus);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getStatus method, of class AbstractAucomGraph.
     */
    @Test
    public void testGetStatus() {
        System.out.println("getStatus");
        AbstractAucomGraph instance = null;
        GraphStatus expResult = null;
        GraphStatus result = instance.getStatus();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberGraphListeners method, of class AbstractAucomGraph.
     */
    @Test
    public void testGetNumberGraphListeners() {
        System.out.println("getNumberGraphListeners");
        AbstractAucomGraph instance = null;
        int expResult = 0;
        int result = instance.getNumberGraphListeners();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
		
}

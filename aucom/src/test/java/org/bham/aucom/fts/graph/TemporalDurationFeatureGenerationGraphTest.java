/**
 * 
 */
package org.bham.aucom.fts.graph;

import junit.framework.Assert;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.tranform.TemporalDurationFeatureGenerator;
import org.junit.Test;

import java.util.ArrayList;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;


/**
 * @author rgolombe
 *
 */
public class TemporalDurationFeatureGenerationGraphTest {

    /**
     * Test method for {@link org.bham.aucom.fts.graph.TemporalDurationFeatureGenerationGraph#preconditionsSatisfied()}.
     */
    @Test
    public void testPreconditionsSatisfied()
    {
        TemporalDurationFeatureGenerationGraph graph = new TemporalDurationFeatureGenerationGraph();
        Assert.assertEquals(false, graph.preconditionsSatisfied());
        graph.setGenerator(new TemporalDurationFeatureGenerator(new ArrayList<Integer>()));
        Assert.assertEquals(true, graph.preconditionsSatisfied());
    }

    /**
     * Test method for {@link org.bham.aucom.fts.graph.TemporalDurationFeatureGenerationGraph#stop()}.
     * @throws ActionFailedException 
     * @throws InterruptedException 
     */
    @Test
    public void testStop() throws ActionFailedException, InterruptedException{
        TemporalDurationFeatureGenerationGraph graph = new TemporalDurationFeatureGenerationGraph();
        graph.setGenerator(new TemporalDurationFeatureGenerator(new ArrayList<Integer>()));
        graph.setInputAndResetGraph(new ObservationTimeSeries());
        graph.start();
        Thread.sleep(100);
        graph.stop();
        Thread.sleep(2000);
    }
    /**
     * Test method for {@link org.bham.aucom.fts.graph.TemporalDurationFeatureGenerationGraph#getOutTimeSeries()}.
     */
    @Test
    public void testGetOutTimeSeries()
    {
        TemporalDurationFeatureGenerationGraph graph = new TemporalDurationFeatureGenerationGraph();
        Assert.assertNull(graph.getOutTimeSeries());
        graph.setInputAndResetGraph(new ObservationTimeSeries());
        Assert.assertNotNull(graph.getOutTimeSeries());
    }

    /**
     * Test method for {@link org.bham.aucom.fts.graph.TemporalDurationFeatureGenerationGraph#setInputAndResetGraph( org.bham.aucom.data.timeseries.TimeSeries)}.
     */
    @Test
    public void testSetInputAndResetGraph()
    {
        TemporalDurationFeatureGenerationGraph graph = new TemporalDurationFeatureGenerationGraph();
        graph.setInputAndResetGraph(new ObservationTimeSeries());
        graph.getOutTimeSeries().add(TemporalDurationFeature.createRandomTemporalDurationFeature());
        Assert.assertEquals(1, graph.getOutTimeSeries().size());
        graph.setInputAndResetGraph(new ObservationTimeSeries());
        Assert.assertEquals(true, graph.getGenerator().isLastOccurancesInitialized());
        Assert.assertEquals(0, graph.getOutTimeSeries().size());
    }

    /**
     * Test of initGraph method, of class TemporalDurationFeatureGenerationGraph.
     */
    @Test
    public void testInitGraph() {
        System.out.println("initGraph");
        TemporalDurationFeatureGenerationGraph instance = new TemporalDurationFeatureGenerationGraph();
        instance.initGraph();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of cleanUp method, of class TemporalDurationFeatureGenerationGraph.
     */
    @Test
    public void testCleanUp() {
        System.out.println("cleanUp");
        TemporalDurationFeatureGenerationGraph instance = new TemporalDurationFeatureGenerationGraph();
        instance.cleanUp();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getReason method, of class TemporalDurationFeatureGenerationGraph.
     */
    @Test
    public void testGetReason() {
        System.out.println("getReason");
        TemporalDurationFeatureGenerationGraph instance = new TemporalDurationFeatureGenerationGraph();
        String expResult = "";
        String result = instance.getReason();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setGenerator method, of class TemporalDurationFeatureGenerationGraph.
     */
    @Test
    public void testSetGenerator() {
        System.out.println("setGenerator");
        TemporalDurationFeatureGenerator inGenerator = null;
        TemporalDurationFeatureGenerationGraph instance = new TemporalDurationFeatureGenerationGraph();
        instance.setGenerator(inGenerator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getGenerator method, of class TemporalDurationFeatureGenerationGraph.
     */
    @Test
    public void testGetGenerator() {
        System.out.println("getGenerator");
        TemporalDurationFeatureGenerationGraph instance = new TemporalDurationFeatureGenerationGraph();
        TemporalDurationFeatureGenerator expResult = null;
        TemporalDurationFeatureGenerator result = instance.getGenerator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

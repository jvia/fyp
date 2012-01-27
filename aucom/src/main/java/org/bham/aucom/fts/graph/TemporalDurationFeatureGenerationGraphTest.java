/**
 * 
 */
package org.bham.aucom.fts.graph;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Test;

import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.tranform.TemporalDurationFeatureGenerator;


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
}

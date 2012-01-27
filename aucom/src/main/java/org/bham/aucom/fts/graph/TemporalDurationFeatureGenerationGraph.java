package org.bham.aucom.fts.graph;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.timeseries.TemporalDurationFeatureTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.sink.TimeSeriesSink;
import org.bham.aucom.fts.source.IllegalStateChange;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.EncodeData;
import org.bham.aucom.fts.tranform.GenerateTemporalDurationFeature;
import org.bham.aucom.fts.tranform.TemporalDurationFeatureGenerator;

/**
 * This class represents a graph based
 * 
 * @author rgolombe
 * @version 0.1
 */
public class TemporalDurationFeatureGenerationGraph extends AbstractAucomGraph {
    private static final long serialVersionUID = 0L;
    protected transient TimeSeriesSource<Observation> source;
    protected transient EncodeData encodeData;
    protected transient GenerateTemporalDurationFeature generateDurationFeature;
    protected transient TimeSeriesSink<TemporalDurationFeature> sink;

    public TemporalDurationFeatureGenerationGraph() {
        super("TemporalDurationFeatureGenerationGraph");
        initGraph();
    }

    @Override
    protected void initGraph()
    {
        source = new TimeSeriesSource<Observation>("observations");
        source.addSourceStatusListener(this);
        encodeData = new EncodeData();
        generateDurationFeature = new GenerateTemporalDurationFeature();
        sink = new TimeSeriesSink<TemporalDurationFeature>();
        sink.addSinkStatusListener(this);
        graph.connect(source, encodeData);
        graph.connect(encodeData, generateDurationFeature);
        graph.connect(generateDurationFeature, sink);
    }

    @Override
    protected void cleanUp()
    {
        source.reset();
        generateDurationFeature.setGenerator(null);
        sink.setOutput(null);
    }


    public TimeSeries<TemporalDurationFeature> getOutTimeSeries()
    {
        if(sink != null){
            return sink.getOutput();
        }
        return null;
    }

    @Override
    public boolean preconditionsSatisfied()
    {
        if (generateDurationFeature.getGenerator() == null) {
            return false;
        }
        return true;
    }

    @Override
    protected String getReason()
    {
        return "not implemented yet";
    }
    public void setGenerator(TemporalDurationFeatureGenerator inGenerator){
        if(generateDurationFeature != null){
            generateDurationFeature.setGenerator(inGenerator);
        }
    }
    public TemporalDurationFeatureGenerator getGenerator(){
        if(generateDurationFeature != null){
            return generateDurationFeature.getGenerator();
        }
        return null;
    }
    public void setInputAndResetGraph(TimeSeries<Observation> inObsTs)
    {
            try {
                pause();
                sink.setOutput(new TemporalDurationFeatureTimeSeries());
                generateDurationFeature.reset();
                source.setInput(inObsTs);
                resume();
            } catch (IllegalStateChange e) {
                e.printStackTrace();
            }
    }

}

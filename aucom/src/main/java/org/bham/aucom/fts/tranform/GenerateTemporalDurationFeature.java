package org.bham.aucom.fts.tranform;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.TemporalDurationFeature;


public class GenerateTemporalDurationFeature extends AbstractAucomTranformNode<DataType, TemporalDurationFeature> {
	private TemporalDurationFeatureGenerator generator;
	public GenerateTemporalDurationFeature() {
		super("GenerateDurationFeature");
		generator = null;
	}
	@Override
	protected TemporalDurationFeature iTransform(DataType input) throws Exception {
	    if(generator == null){
	        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.SEVERE, "generator is missing, cannot generate TemporalDurationFeature ");
	        return null;
	    }
		TemporalDurationFeature feature = getGenerator().generateFeature(input);
		if(feature == null){
			Logger.getLogger(this.getClass().getCanonicalName()).severe("TemporalDurationFeature is null");
		}
		Logger.getLogger(this.getClass().getCanonicalName()).info("returning TemporalDurationFeature ");
		return feature;
	}
	public void reset(){
	    if(getGenerator() != null){
	        getGenerator().initializeLastOccurances();
	    }
	}
	public synchronized void setGenerator(TemporalDurationFeatureGenerator generator) {
		this.generator = generator;
	}
	public synchronized  TemporalDurationFeatureGenerator getGenerator() {
		return generator;
	}
}

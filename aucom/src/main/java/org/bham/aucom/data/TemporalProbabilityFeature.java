package org.bham.aucom.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.bham.aucom.util.Constants.LOWEST_PROBABILITY;

public class TemporalProbabilityFeature extends TemporalDurationFeature {
	private HashMap<DataType, Double> dataTypeToProbabilityMapping = new LinkedHashMap<DataType, Double>();

	/*
	 * This constructor is used to load a saved version of a TemporalProbabilityFeature 
	 */
	public TemporalProbabilityFeature(TemporalDurationFeature  tdf, HashMap<DataType, Double> probabilities) {
		super(tdf);
		this.setDataTypeToProbabilityMapping(probabilities);
	}
	/*
	 * This constructor is used to load a saved version of a TemporalProbabilityFeature 
	 */
	public TemporalProbabilityFeature(TemporalDurationFeature  tdf, LinkedHashMap<Integer, Double> probabilities) {
		super(tdf);
		if(this.getDataTypeToProbabilityMapping() == null)
			this.setDataTypeToProbabilityMapping(new LinkedHashMap<DataType, Double>());
		for(DataType predecessor: this.getPredecessors()){
			this.getDataTypeToProbabilityMapping().put(predecessor, probabilities.get(Integer.valueOf(predecessor.getEventType())));
		}
	}
	
	/*
	 * 
	 */
    TemporalProbabilityFeature(TemporalProbabilityFeature tpf) {
		this(tpf, tpf.getDataTypeToProbabilityMapping());
}


	public TemporalProbabilityFeature() {
		
	}
	
	ArrayList<Double> getDurationProbabilities() {
		ArrayList<Double> out = new ArrayList<Double>();
		for (Double probability : this.dataTypeToProbabilityMapping.values()) {
			out.add(probability);
		}
		return out;
	}

	public double getProbabilityFor(DataType eventType) {
		if (isUnknownEventType(eventType)) {
			System.out.println("Warning: unknown predecessor");
			return LOWEST_PROBABILITY;
		}
		return this.getDataTypeToProbabilityMapping().get(eventType);
	}

    /*
      * @param eventType
      * @return
      */
	private boolean isUnknownEventType(DataType eventType) {
		return !this.getDataTypeToProbabilityMapping().containsKey(eventType);
	}


	void setDataTypeToProbabilityMapping(HashMap<DataType, Double> dataTypeToProbabilityMapping) {
		this.dataTypeToProbabilityMapping = dataTypeToProbabilityMapping;
	}

	HashMap<DataType, Double> getDataTypeToProbabilityMapping() {
		return this.dataTypeToProbabilityMapping;
	}

	static TemporalProbabilityFeature createRandomTemporalProbabilityFeature() {
		TemporalDurationFeature f = createRandomTemporalDurationFeature();
		LinkedHashMap<DataType, Double> probabilities = new LinkedHashMap<DataType, Double>();
		for(DataType dtp : f.getPredecessors()){
			probabilities.put(dtp, Math.random());
		}
        return new TemporalProbabilityFeature(f, probabilities);
	}
	@Override
	public Object copy() {
		TemporalDurationFeature tdf_copy = (TemporalDurationFeature)super.copy();
		HashMap<DataType, Double> dataTypeToProbabilityMapping_copy = new LinkedHashMap<DataType, Double>();
		for(DataType d : dataTypeToProbabilityMapping.keySet()){
			dataTypeToProbabilityMapping_copy.put((DataType)d.copy(), dataTypeToProbabilityMapping_copy.get(d));
		}
		return new TemporalProbabilityFeature(tdf_copy, dataTypeToProbabilityMapping_copy);
	}
	@Override
	public String toString() {
		String s = "";
		for (DataType dtp : getDataTypeToProbabilityMapping().keySet()){
			s += "p:"+dtp.getEventType()+ ";pr:"+getDataTypeToProbabilityMapping().get(dtp) + " ";
		}
		return super.toString() + s;
	}
}

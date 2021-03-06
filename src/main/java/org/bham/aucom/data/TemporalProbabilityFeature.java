package org.bham.aucom.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;

import static org.bham.aucom.util.Constants.LOWESTPROBABILITY;

public class TemporalProbabilityFeature extends TemporalDurationFeature {
    protected HashMap<DataType, Double> dataTypeToProbabilityMapping = new LinkedHashMap<DataType, Double>();

    /**
     * This constructor is used to load a saved version of a
     * TemporalProbabilityFeature
     */
    public TemporalProbabilityFeature(TemporalDurationFeature tdf, HashMap<DataType, Double> probabilities) {
        super(tdf);
        this.setDataTypeToProbabilityMapping(probabilities);
    }

    /**
     * This constructor is used to load a saved version of a
     * TemporalProbabilityFeature
     */
    public TemporalProbabilityFeature(TemporalDurationFeature tdf, LinkedHashMap<Integer, Double> probabilities) {
        super(tdf);
        if (this.getDataTypeToProbabilityMapping() == null) {
            this.setDataTypeToProbabilityMapping(new LinkedHashMap<DataType, Double>());
        }
        for (DataType predecessor : this.getPredecessors()) {
            this.getDataTypeToProbabilityMapping().put(predecessor, probabilities.get(Integer.valueOf(predecessor.getEventType())));
        }
    }

    public TemporalProbabilityFeature(TemporalProbabilityFeature tpf) {
        this(tpf, tpf.getDataTypeToProbabilityMapping());
    }

    public TemporalProbabilityFeature() {
        super();
    }

    public ArrayList<Double> getDurationProbabilities() {
        ArrayList<Double> out = new ArrayList<Double>();
        for (Double probability : this.dataTypeToProbabilityMapping.values()) {
            out.add(probability);
        }
        return out;
    }

    public double getProbabilityFor(DataType eventType) {
        if (isUnknownEventType(eventType)) {
            System.out.println("Warning: unknown predecessor");
            return LOWESTPROBABILITY;
        }
        return this.getDataTypeToProbabilityMapping().get(eventType);
    }

    public double getProbabilityFor(int eventTypeId) {
        for (DataType dtp : this.getDataTypeToProbabilityMapping().keySet()) {
            if (dtp.getEventType() == eventTypeId) {
                return this.getDataTypeToProbabilityMapping().get(dtp);
            }
        }
        System.out.println("Warning: unknown predecessor");
        return LOWESTPROBABILITY;
    }

    private boolean isUnknownEventType(DataType eventType) {
        return !this.getDataTypeToProbabilityMapping().containsKey(eventType);
    }


    protected void setDataTypeToProbabilityMapping(HashMap<DataType, Double> dataTypeToProbabilityMapping) {
        this.dataTypeToProbabilityMapping = dataTypeToProbabilityMapping;
    }

    protected HashMap<DataType, Double> getDataTypeToProbabilityMapping() {
        return this.dataTypeToProbabilityMapping;
    }

    public static TemporalProbabilityFeature createRandomTemporalProbabilityFeature() {
        TemporalDurationFeature f = createRandomTemporalDurationFeature();
        LinkedHashMap<DataType, Double> probabilities = new LinkedHashMap<DataType, Double>();
        for (DataType dtp : f.getPredecessors()) {
            probabilities.put(dtp, Math.random());
        }
        TemporalProbabilityFeature ff = new TemporalProbabilityFeature(f, probabilities);
        return ff;
    }

    @Override
    public Object copy() {
        TemporalDurationFeature tdf_copy = (TemporalDurationFeature) super.copy();
        HashMap<DataType, Double> dataTypeToProbabilityMapping_copy = new LinkedHashMap<DataType, Double>();
        for (DataType d : dataTypeToProbabilityMapping.keySet()) {
            dataTypeToProbabilityMapping_copy.put((DataType) d.copy(), dataTypeToProbabilityMapping_copy.get(d));
        }
        return new TemporalProbabilityFeature(tdf_copy, dataTypeToProbabilityMapping_copy);
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder();
        for (DataType dtp : getDataTypeToProbabilityMapping().keySet()) {
            s.append(String.format("p: %d; pr: %s",
                                   dtp.getEventType(),
                                   getDataTypeToProbabilityMapping().get(dtp)));
        }
        return super.toString() + s;
    }
}

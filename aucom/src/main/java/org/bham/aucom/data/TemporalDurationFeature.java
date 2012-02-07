package org.bham.aucom.data;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

public class TemporalDurationFeature extends DataType {

    /**
     * Holds durations between the occurrence of the data type represented by
     * this event and each last occurrence of all other known data types
     */
    protected LinkedHashMap<DataType, Long> predecessorIdToDurationsMapping;

    /**
     * Creates a non-initialized TemporalDurationFeature
     */
    public TemporalDurationFeature() {
        predecessorIdToDurationsMapping = new LinkedHashMap<DataType, Long>();
    }

    /**
     * Creates a TemporalDurationFeature form another object which is a (sub-)
     * class of TemporalDurationFeature
     *
     * @param tdf the object from which information is take in order to create
     *            a
     *            new TemporalDurationFeature object
     */
    public TemporalDurationFeature(TemporalDurationFeature tdf) {
        this(tdf, tdf.getPredecessorIdToDurationsMapping());
    }

    /**
     * Creates a TemporalDurationFeature from a given data type and a list of
     * durations. See {@see predecessorIdToDurationsMapping} for an explanation
     * of durations.
     *
     * @param dtt       the datatype
     * @param durations the list of durations
     */
    public TemporalDurationFeature(DataType dtt, LinkedHashMap<DataType, Long> durations) {
        super(dtt);
        predecessorIdToDurationsMapping = new LinkedHashMap<DataType, Long>();
        this.setPredecessorIdToDurationsMapping(new LinkedHashMap<DataType, Long>());
        if (durations != null) {
            predecessorIdToDurationsMapping.putAll(durations);
        }
    }

    /**
     * Returns the duration between the occurrence of the data type represented
     * by this event and inPrecedessorDataType. If inPrecedessorDataType is not
     * known 0L is returned.
     *
     * @param inPrecedessorDataType the feature whose timespan we're interested
     *                              in
     * @return the duration
     */
    public long getDurationFor(DataType inPrecedessorDataType) {
        if (!this.getPredecessorIdToDurationsMapping().containsKey(inPrecedessorDataType)) {
            Logger.getLogger(this.getClass().getCanonicalName()).info(" missing precedessor " + inPrecedessorDataType);
            return 0L;
        }
        return this.getPredecessorIdToDurationsMapping().get(inPrecedessorDataType);
    }

    public long getDurationFor(int eventTypeId) {
        for (DataType dtp : this.getPredecessorIdToDurationsMapping().keySet()) {
            if (dtp.getEventType() == eventTypeId) {
                return this.getPredecessorIdToDurationsMapping().get(dtp);
            }
        }
        System.out.println(" missing precedessor with id " + eventTypeId);
        return 0L;
    }

    /**
     * Returns a list of {@link DataType} Objects which are the predecessors of
     * the
     * DataType represented by this object.
     * The order is given by the order the predecessors were added to this
     * TemporalDurationFeature object.
     *
     * @return a list of the predecessors
     */
    public ArrayList<DataType> getPredecessors() {
        ArrayList<DataType> out = new ArrayList<DataType>();
        for (DataType dtt : this.predecessorIdToDurationsMapping.keySet()) {
            out.add(dtt);
        }
        return out;
    }

    /**
     * Returns a list of data type ids of last predecessors of the DataType
     * represented by this object.
     * The order is given by the order the predecessors were added to this
     * TemporalDurationFeature object.
     *
     * @return a list of dataype IDs
     */
    public ArrayList<Integer> getPrecedessorDataTypes() {
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (DataType dtt : this.predecessorIdToDurationsMapping.keySet()) {
            out.add(dtt.getEventType());
        }
        return out;
    }

    protected void setPredecessorIdToDurationsMapping(LinkedHashMap<DataType, Long> predecessorIdToDurationsMapping) {
        this.predecessorIdToDurationsMapping = predecessorIdToDurationsMapping;
    }

    protected LinkedHashMap<DataType, Long> getPredecessorIdToDurationsMapping() {
        return this.predecessorIdToDurationsMapping;
    }

    public static TemporalDurationFeature createRandomTemporalDurationFeature() {
        DataType dtp = createRandomDataType();
        LinkedHashMap<DataType, Long> durations = new LinkedHashMap<DataType, Long>();
        for (int i = 0; i < 5; i++) {
            double randomVlaue = Math.random() * 1000;
            Long duration = (long) randomVlaue;
            durations.put(createRandomDataType(), duration);
        }
        return new TemporalDurationFeature(dtp, durations);
    }

    @Override
    public Object copy() {
        DataType dtp_copy = (DataType) super.copy();
        LinkedHashMap<DataType, Long> predecessorIdToDurationsMapping_copy = new LinkedHashMap<DataType, Long>();

        for (DataType d : this.predecessorIdToDurationsMapping.keySet())
            predecessorIdToDurationsMapping_copy.put((DataType) d.copy(), this.predecessorIdToDurationsMapping.get(d));

        return new TemporalDurationFeature(dtp_copy, predecessorIdToDurationsMapping_copy);
    }
}

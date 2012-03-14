package org.bham.aucom.data;

import org.bham.aucom.fts.tranform.MatrixReducer;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.logging.Logger;

import static java.lang.String.format;

public class TemporalDurationFeature extends DataType {

    /**
     * Holds durations between the occurrence of the data type represented by
     * this event and each last occurrence of all other known data types
     */
    protected LinkedHashMap<DataType, Long> predecessorIdToDurationsMapping;
    private transient Logger log = Logger.getLogger(getClass().getName());

    /**
     * Creates a non-initialized TemporalDurationFeature
     */
    public TemporalDurationFeature() {
        predecessorIdToDurationsMapping = new LinkedHashMap<DataType, Long>();
        //log.config(format("Creating TemporalDurationFeature : %s", getName()));
    }

    /**
     * Creates a TemporalDurationFeature form another object which is a
     * sub-class of TemporalDurationFeature
     *
     * @param tdf the object from which information is take in order to create
     *            a new TemporalDurationFeature object
     */
    public TemporalDurationFeature(TemporalDurationFeature tdf) {
        this(tdf, tdf.getPredecessorIdToDurationsMapping());
    }

    /**
     * Creates a TemporalDurationFeature from a given data type and a list of
     * durations. See {@see predecessorIdToDurationsMapping} for an explanation
     * of durations.
     *
     * @param dtt       the data type
     * @param durations the list of durations
     */
    public TemporalDurationFeature(DataType dtt, LinkedHashMap<DataType, Long> durations) {
        super(dtt);
        predecessorIdToDurationsMapping = new LinkedHashMap<DataType, Long>();
        setPredecessorIdToDurationsMapping(new LinkedHashMap<DataType, Long>());
        if (durations != null) {
            for (Map.Entry<DataType, Long> duration : durations.entrySet()) {
                predecessorIdToDurationsMapping.put(duration.getKey(), duration.getValue());
            }
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
        if (!getPredecessorIdToDurationsMapping().containsKey(inPrecedessorDataType)) {
            log.warning(format("Missing precedessor {%s}", inPrecedessorDataType));
            return 0;
        }

        return getPredecessorIdToDurationsMapping().get(inPrecedessorDataType);
    }

    public long getDurationFor(int eventTypeId) {
        for (DataType dtp : getPredecessorIdToDurationsMapping().keySet()) {
            if (dtp.getEventType() == eventTypeId) {
                return getPredecessorIdToDurationsMapping().get(dtp);
            }
        }

        log.warning(format("Missing precedessor with ID: %d ", eventTypeId));
        return 0;
    }

    /**
     * Returns a list of {@link DataType} Objects which are the predecessors of
     * the DataType represented by this object. The order is given by the order
     * the predecessors were added to this TemporalDurationFeature object.
     *
     * @return a list of the predecessors
     */
    public ArrayList<DataType> getPredecessors() {
        ArrayList<DataType> out = new ArrayList<DataType>();
        for (DataType dtt : predecessorIdToDurationsMapping.keySet()) {
                out.add(dtt);
        }
        log.fine(format("Predecessors: %s", out));
        return out;
    }

    /**
     * Returns a list of data type ids of last predecessors of the DataType
     * represented by this object. The order is given by the order the
     * predecessors were added to this TemporalDurationFeature object.
     *
     * @return a list of dataype IDs
     */
    public ArrayList<Integer> getPrecedessorDataTypes() {
        ArrayList<Integer> out = new ArrayList<Integer>();
        for (DataType dtt : predecessorIdToDurationsMapping.keySet()) {
            out.add(dtt.getEventType());
        }

        return out;
    }

    public static TemporalDurationFeature createRandomTemporalDurationFeature() {
        DataType dtp = createRandomDataType();
        LinkedHashMap<DataType, Long> durations = new LinkedHashMap<DataType, Long>();

        for (int i = 0; i < 5; i++) {
            durations.put(createRandomDataType(), (long) (Math.random() * 1000));
        }

        return new TemporalDurationFeature(dtp, durations);
    }

    @Override
    public Object copy() {
        DataType dtp_copy = (DataType) super.copy();
        LinkedHashMap<DataType, Long> predecessorIdToDurationsMapping_copy = new LinkedHashMap<DataType, Long>();

        for (DataType d : predecessorIdToDurationsMapping.keySet()) {
            predecessorIdToDurationsMapping_copy.put((DataType) d.copy(), predecessorIdToDurationsMapping.get(d));
        }

        return new TemporalDurationFeature(dtp_copy, predecessorIdToDurationsMapping_copy);
    }

    protected void setPredecessorIdToDurationsMapping(LinkedHashMap<DataType, Long> predecessorIdToDurationsMapping) {
        this.predecessorIdToDurationsMapping = predecessorIdToDurationsMapping;
    }

    protected LinkedHashMap<DataType, Long> getPredecessorIdToDurationsMapping() {
        return predecessorIdToDurationsMapping;
    }
}

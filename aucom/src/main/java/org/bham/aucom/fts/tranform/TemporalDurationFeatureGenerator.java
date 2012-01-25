package org.bham.aucom.fts.tranform;

import nu.xom.Element;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.encoder.Encoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.logging.Logger;

public class TemporalDurationFeatureGenerator {
    private final ConcurrentHashMap<Integer, DataType> lastOccurrences = new ConcurrentHashMap<Integer, DataType>(new LinkedHashMap<Integer, DataType>());
    private final Collection<Integer> initialClasses;

    /*
     * null is permitted
     * 
     * @param inInitialClasses
     */

    public TemporalDurationFeatureGenerator(Collection<Integer> inInitialClasses) {
        initialClasses = new ArrayList<Integer>();
        if (inInitialClasses != null) {
            initialClasses.addAll(inInitialClasses);
        }
    }

    public void clearInitialClasses() {
        initialClasses.clear();
    }

    public void addInitalClasses(Collection<Integer> inInitialClassesToAdd) {
        if (inInitialClassesToAdd == null) {
            return;
        }
        initialClasses.addAll(inInitialClassesToAdd);
    }

    public void resetInitialClasses() {
        initialClasses.clear();
    }

    boolean isLastOccurancesInitialized() {
        boolean isLastOccurancesInitialized = true;
        for (Integer id : lastOccurrences.keySet()) {
            isLastOccurancesInitialized &= lastOccurrences.get(id).getTimestamp() == 0;
        }
        return isLastOccurancesInitialized;
    }

    public void initializeLastOccurances() {
        int initializationValue = 0;
        initializeLastOccurancesWithValue(initializationValue);
    }

    private void initializeLastOccurancesWithValue(long initializationValue) {
        this.lastOccurrences.clear();
        Logger.getLogger(this.getClass().getCanonicalName()).info("adding initial data points as last occurrences of " + initialClasses.size() + " classes");
        for (Integer classId : initialClasses) {
            Logger.getLogger(this.getClass().getCanonicalName()).info("adding initial occurrances for classId " + classId);
            List<DomainFeature> features = Encoder.getInstance().decode(classId);
            DataType dummy = new DataType(features, classId, new Observation(new Element("emptyContent"), initializationValue));
            dummy.setEventTypeId(classId);
            this.lastOccurrences.put(classId, dummy);
        }
    }

    public TemporalDurationFeature generateFeature(DataType in) {
        long timeSpan;
        LinkedHashMap<DataType, Long> durations = new LinkedHashMap<DataType, Long>();

        if (isLastOccurancesInitialized()) {
            initializeLastOccurancesWithValue(in.getTimestamp());
        }
        for (DataType lastOccurrence : lastOccurrences.values()) {
            timeSpan = calculateTimespanForElements(in, lastOccurrence);
            durations.put(lastOccurrence, timeSpan);
        }
        TemporalDurationFeature f = new TemporalDurationFeature(in, durations);
        updateLastOccurrences(in);
        return f;
    }

    /**
     * calculates the duration for the two elements.
     *
     * @param inTestElement  the currently occurred event as datatype object
     * @param lastOccurrence last occurrence of an event with a specific
     *                       datatype
     * @return timespan in milliseconds
     */
    private long calculateTimespanForElements(DataType inTestElement, DataType lastOccurrence) {
        if (lastOccurrence.getTimestamp() != -1) {
            return inTestElement.getTimestamp() - lastOccurrence.getTimestamp();
        }
        return 0l;
    }

    private void updateLastOccurrences(DataType inTestElement) {
        lastOccurrences.put(inTestElement.getEventType(), inTestElement);
    }
}

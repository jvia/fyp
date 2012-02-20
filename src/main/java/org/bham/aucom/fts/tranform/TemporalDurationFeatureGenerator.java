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
    ConcurrentHashMap<Integer, DataType> lastOccurrences = new ConcurrentHashMap<Integer, DataType>(new LinkedHashMap<Integer, DataType>());
    Collection<Integer> initialClasses;
    private final Logger log = Logger.getLogger(getClass().getName());

    /**
     * null is permitted
     *
     * @param inInitialClasses
     */
    public TemporalDurationFeatureGenerator(Collection<Integer> inInitialClasses) {
        initialClasses = new ArrayList<Integer>();
        if (inInitialClasses != null)
            initialClasses.addAll(inInitialClasses);
    }

    public void clearInitialClasses() {
        initialClasses.clear();
    }

    public void addInitalClasses(Collection<Integer> inInitialClassesToAdd) {
        if (inInitialClassesToAdd == null) return;
        initialClasses.addAll(inInitialClassesToAdd);
    }

    public void resetInitialClasses() {
        initialClasses.clear();
    }

    public boolean isLastOccurancesInitialized() {
        boolean isLastOccurancesInitialized = true;
        for (int id : lastOccurrences.keySet())
            isLastOccurancesInitialized &= lastOccurrences.get(id).getTimestamp() == 0;
        return isLastOccurancesInitialized;
    }

    public void initializeLastOccurances() {
        int initializationValue = 0;
        initializeLastOccurancesWithValue(initializationValue);
    }

    private void initializeLastOccurancesWithValue(long initializationValue) {
        lastOccurrences.clear();
        log.info(String.format("Adding initial data points as last occurrences of %d classes", initialClasses.size()));

        for (int classId : initialClasses) {
            log.info("adding initial occurrances for classId " + classId);
            List<DomainFeature> features = Encoder.getInstance().decode(classId);

            DataType dummy = new DataType(features, classId, new Observation(new Element("emptyContent"), initializationValue));
            dummy.setEventTypeId(classId);

            lastOccurrences.put(classId, dummy);
        }
    }

    public TemporalDurationFeature generateFeature(DataType in) {
        LinkedHashMap<DataType, Long> durations = new LinkedHashMap<DataType, Long>();

        if (isLastOccurancesInitialized()) {
            initializeLastOccurancesWithValue(in.getTimestamp());
        }

        for (DataType lastOccurrence : lastOccurrences.values()) {
            durations.put(lastOccurrence, calculateTimespanForElements(in, lastOccurrence));
        }

        TemporalDurationFeature f = new TemporalDurationFeature(in, durations);
        updateLastOccurrences(in);
        return f;
    }

    /**
     * Calculates the duration for the two elements. In case th
     *
     * @param inTestElement  the currently occurred event as datatype object
     * @param lastOccurrence last occurrence of an event with a specific
     *                       datatype
     * @return the duration between two elements
     */
    private long calculateTimespanForElements(DataType inTestElement, DataType lastOccurrence) {
        if (lastOccurrence.getTimestamp() != -1)
            return inTestElement.getTimestamp() - lastOccurrence.getTimestamp();
        return 0;
    }

    private void updateLastOccurrences(DataType inTestElement) {
        lastOccurrences.put(inTestElement.getEventType(), inTestElement);
    }
}

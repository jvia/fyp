package org.bham.aucom.data;

import org.bham.aucom.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * This is a generic DataType that contains a collection of DomainFeatures.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class DataType extends Observation {

    private final Logger log = Logger.getLogger(getClass().getName());

    private List<DomainFeature> selectedFeatures;
    private int eventType = -1;

    /**
     * Creates an empty object.
     */
    public DataType() {
        selectedFeatures = new ArrayList<DomainFeature>();
    }

    /**
     * Creates a DataType object.
     *
     * @param features    a collection of domain features
     * @param type        the type of event
     * @param observation the observation data
     */
    public DataType(final List<DomainFeature> features,
                    final int type,
                    final Observation observation) {
        super(observation);

        setTimestamp(observation.getTimestamp());
        setFirstElement(observation.isFirstElement());
        setLastElement(observation.isLastElement());

        selectedFeatures = new ArrayList<DomainFeature>();
        attributes.putAll(observation.attributes);
        selectedFeatures = features;
        eventType = type;

        if (selectedFeatures == null) {
            log.warning("Null feature set");
        }
    }

    /**
     * Creates a DataType object from an existing DataType object.
     *
     * @param d the data type
     */
    public DataType(final DataType d) {
        this(d.getFeatures(), d.getEventType(), d);
    }

    /**
     * Return the EventType.
     *
     * @return the event type
     */
    public int getEventType() {
        return eventType;
    }

    /**
     * Return the EventType a a String value.
     *
     * @return the event type
     */
    public String getEventTypeIdAsString() {
        return String.valueOf(eventType);
    }


    /**
     * Set the event type.
     *
     * @param eventTypeId the event type
     */
    public void setEventTypeId(int eventTypeId) {
        eventType = eventTypeId;
    }

    /**
     * Return a String representation of this object.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        if (selectedFeatures == null) {
            return "No features";
        }

        StringBuilder str = new StringBuilder("[");
        str.append(getTimestamp());
        str.append(", ");

        for (DomainFeature f : selectedFeatures) {
            str.append((f != null) ? f.getFeatureName() + ":" + f.getFeatureValue() + ";" : "--:--;");
        }

        str.append(" ").append(eventType).append(" ]");
        return str.toString();
    }

    /**
     * Return the name of the DataType.
     *
     * @return the name
     */
    public String getName() {
        return toString();
    }

    /**
     * Set the collection of DomainFeatures.
     *
     * @param features the domain features
     */
    public void setFeatures(final List<DomainFeature> features) {
        selectedFeatures = features;
        if (selectedFeatures == null) {
            log.warning("Got null feature set");
        }
    }


    /**
     * Return the collection of domain features.
     *
     * @return the domain features
     */
    public List<DomainFeature> getFeatures() {
        return Collections.unmodifiableList(selectedFeatures);
    }

    /**
     * Creates a random DataType object. Mostly used for testing.
     *
     * @return a random DataType object
     */
    public static DataType createRandomDataType() {
        Observation rndObs = createRandomObservation();
        List<DomainFeature> rndDomainFeatureList = createRandomDomainFeature();
        int rndDataType = createRandomDT();
        return new DataType(rndDomainFeatureList, rndDataType, rndObs);
    }

    private static int createRandomDT() {
        return (int) (1 + Math.random() * 10);
    }

    private static List<DomainFeature> createRandomDomainFeature() {
        List<DomainFeature> rndDomainFeature = new ArrayList<DomainFeature>();
        rndDomainFeature.add(new DomainFeature(Constants.SOURCE_TYPE, getRandomString(4)));
        rndDomainFeature.add(new DomainFeature(Constants.SCOPE_TYPE, getRandomString(4)));
        rndDomainFeature.add(new DomainFeature(Constants.EVENT_TYPE, getRandomString(4)));

        return rndDomainFeature;
    }

    private static String getRandomString(final int length) {
        StringBuilder value = new StringBuilder();
        Random rndNumber = new Random();

        int i = 0;
        while (i < length) {
            int intC = rndNumber.nextInt(125);
            if ((intC >= 48 && intC <= 57) || (intC >= 65 && intC <= 90) || (intC >= 97 && intC <= 122)) {
                value.append((char) intC);
                i++;
            }
        }

        return value.toString();
    }

    @Override
    public Object copy() {
        Observation obs_copy = (Observation) super.copy();
        List<DomainFeature> copied_DomainFeatures = new ArrayList<DomainFeature>();

        for (DomainFeature toCopy : getFeatures()) {
            copied_DomainFeatures.add((DomainFeature) toCopy.copy());
        }

        return new DataType(copied_DomainFeatures, getEventType(), obs_copy);
    }
}

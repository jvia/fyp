package org.bham.aucom.data;

import org.bham.aucom.util.Constants;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This is a generic DataType that contains a collection of DomainFeatures.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public class DataType extends Observation {

    private List<DomainFeature> selectedFeatures;
    private int eventType = -1;

    /**
     * Creates an empty object.
     */
    public DataType() {
        selectedFeatures = new ArrayList<DomainFeature>();
        // TODO remove empty/unused constructors
    }

    /**
     * Creates a DataType object.
     *
     * @param features    a collection of domain features
     * @param eventType   the type of event
     * @param observation the observation data
     */
    public DataType(List<DomainFeature> features, int eventType, Observation observation) {
        super(observation);
        selectedFeatures = new ArrayList<DomainFeature>();
        setTimestamp(observation.getTimestamp());

        attributes.putAll(observation.attributes);
        setFirstElement(observation.isFirstElement());
        setLastElement(observation.isLastElement());
        this.selectedFeatures = features;
        this.eventType = eventType;

        if (selectedFeatures == null) {
            Logger.getLogger(this.getClass().getCanonicalName()).log(Level.WARNING, "DataType warninig: got null feature set");
        }
    }

    /**
     * Creates a Datatype object from an existing Datatype object.
     *
     * @param d
     */
    public DataType(DataType d) {
        this(d.getFeatures(), d.getEventType(), d);
    }

    /**
     * Return the EventType.
     *
     * @return the event type
     */
    public int getEventType() {
        return this.eventType;
    }

    /**
     * Return the EventType a a String value.
     *
     * @return the event type
     */
    public String getEventTypeIdAsString() {
        return String.valueOf(this.eventType);
    }


    /**
     * Set the EventType.
     *
     * @param eventTypeId the event type
     */
    public void setEventTypeId(int eventTypeId) {
        this.eventType = eventTypeId;
    }

    /**
     * Return a String representation of this object.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        String str = "[";

        str += getTimestamp();
        str += ", ";
        if (null != selectedFeatures) {
            for (DomainFeature f : this.selectedFeatures) {
                str += (f != null) ? f.getFeatureName() + ":" + f.getFeatureValue() + ";" : "--:--;";
            }
        } else {
            str += "no features";
        }
        str += " " + this.eventType + " ]";
        return str;
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
    public void setFeatures(List<DomainFeature> features) {
        this.selectedFeatures = features;
        if (selectedFeatures == null) {
            System.err.println("-------------> DataType warninig: got null feature set");
        }
    }


    /**
     * Return the collection of domain features.
     *
     * @return the domain features
     */
    public List<DomainFeature> getFeatures() {
        return Collections.unmodifiableList(this.selectedFeatures);
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

    private static String getRandomString(int length) {
        String value = "";
        Random rndNumber = new Random();
        int i = 0;
        while (i < length) {
            int intC = rndNumber.nextInt(125);
            if ((intC >= 48 && intC <= 57) || (intC >= 65 && intC <= 90) || (intC >= 97 && intC <= 122)) {
                value += (char) intC;
                i++;
            }
        }
        return value;
    }

    @Override
    public Object copy() {
        Observation obs_copy = (Observation) super.copy();
        List<DomainFeature> copied_DomainFeatures = new ArrayList<DomainFeature>();
        for (DomainFeature toCopy : getFeatures()) {
            copied_DomainFeatures.add((DomainFeature) toCopy.copy());
        }
        DataType dtp_copy = new DataType(copied_DomainFeatures, getEventType(), obs_copy);
        return dtp_copy;
    }
}

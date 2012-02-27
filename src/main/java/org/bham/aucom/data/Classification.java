package org.bham.aucom.data;

import nu.xom.Element;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

/**
 * Class Classification represents the status of the system at any given point
 * in time. This class holds the current score and its associated status.
 *
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class Classification extends Score {
    private final Score score;
    private SystemFaultStatus status;

    /**
     * Constructor Classification creates a new Classification instance.
     *
     * @param data   the system score
     * @param status the system status
     */
    public Classification(Score data, SystemFaultStatus status) {
        super();
        this.score = data;
        this.status = status;
    }

    // --Commented out by Inspection START (2/25/12 10:41 AM):
    //    public Classification() {
    //        score = new RangeScore(new ArrayList<Score>());
    //        status = SystemFaultStatus.ABNORMAL;
    //    }
    // --Commented out by Inspection STOP (2/25/12 10:41 AM)

    // --Commented out by Inspection START (2/25/12 10:41 AM):
    //    public Classification(Classification cl) {
    //        this(cl.score, cl.status);
    //    }
    // --Commented out by Inspection STOP (2/25/12 10:41 AM)

    /**
     * Method getStatus returns the status of this Classification object.
     *
     * @return the status of this Classification object.
     */
    public SystemFaultStatus getStatus() {
        return status;
    }

    /**
     * Method getStatusAsDouble returns the status of this
     * Classification object as a double.
     *
     * @return the status of this Classification object.
     */
    public double getStatusAsDouble() {
        if (status.equals(SystemFaultStatus.ABNORMAL)) {
            return 1.0;
        }
        return 0.0;
    }

    // --Commented out by Inspection START (2/25/12 10:41 AM):
    //    public void setClassId(SystemFaultStatus status) {
    //        this.status = status;
    //    }
    // --Commented out by Inspection STOP (2/25/12 10:41 AM)

    /**
     * Set the classification to abnormal.
     */
    public void setAbnormal() {
        status = SystemFaultStatus.ABNORMAL;
    }

    /**
     * Set the classification to normal.
     */
    public void setNormal() {
        status = SystemFaultStatus.NORMAL;
    }

    /**
     * Set the classification to unknown.
     */
    public void setUnknown() {
        status = SystemFaultStatus.UNKNOWN;
    }

    /**
     * Create random classifications for use in testing.
     *
     * @return Classification a random classification
     */
    public static Classification createRandomClassification() {
        Score s = createRandomScore();
        SystemFaultStatus st = SystemFaultStatus.values()[new Random().nextInt(SystemFaultStatus.values().length)];
        return new Classification(s, st);
    }

    /**
     * Add an attribute to this classification.
     *
     * @param propertyName  attribute key
     * @param propertyValue attribute value
     */
    @Override
    public void addAttribute(String propertyName, String propertyValue) {
        score.addAttribute(propertyName, propertyValue);
    }

    /**
     * Checks if object has a given attribute
     *
     * @param propertyName key value
     * @return boolean true if object contains attribute
     */
    @Override
    public boolean containsAttribute(String propertyName) {
        return score.containsAttribute(propertyName);
    }

    /**
     * Method getAttributes returns the attributes of this Classification
     * object.
     * <p/>
     * The list of attributes about a piece of data.
     *
     * @return the attributes (type HashMap<String, String>) of this
     *         Classification object.
     */
    @Override
    public Map<String, String> getAttributes() {
        return score.getAttributes();
    }

    /**
     * Method getAttributeValue get value of attribute
     *
     * @param propertyName key
     * @return String value
     */
    @Override
    public String getAttributeValue(String propertyName) {
        return score.getAttributeValue(propertyName);
    }

    /**
     * Method getValue returns the value of this Classification object.
     *
     * @return the value (type double) of this Classification object.
     */
    @Override
    public double getValue() {
        return score.getValue();
    }

    /**
     * Method setValue sets the value of this Classification object.
     *
     * @param value the value of this Classification object.
     */
    @Override
    public void setValue(double value) {
        score.setValue(value);
    }

    /**
     * Method getVariance returns the variance of this Classification object.
     *
     * @return the variance (type double) of this Classification object.
     */
    @Override
    public double getVariance() {
        return score.getVariance();
    }

    /**
     * Method setVariance sets the variance of this Classification object.
     *
     * @param variance the variance of this Classification object.
     */
    @Override
    public void setVariance(double variance) {
        score.setVariance(variance);
    }

    /**
     * Method getTimestamp returns the timestamp of this Classification object.
     *
     * @return the timestamp (type long) of this Classification object.
     */
    @Override
    public long getTimestamp() {
        return score.getTimestamp();
    }

    /**
     * Method setTimestamp sets the timestamp of this Classification object.
     *
     * @param inTimestamp the timestamp of this Classification object.
     */
    @Override
    public void setTimestamp(long inTimestamp) {
        score.setTimestamp(inTimestamp);
    }

    /**
     * Method getContent returns the content of this Classification object.
     *
     * @return the content (type Element) of this Classification object.
     */
    @Override
    public Element getContent() {
        return score.getContent();
    }

    /**
     * Create a copy of this object.
     *
     * @return Object a copy
     */
    @Override
    public Object copy() {
        Score score_copy = (Score) score.copy();
        return new Classification(score_copy, status);
    }

    /**
     * Checks if score is the first element.
     *
     * @return true if first element
     */
    @Override
    public boolean isFirstElement() {
        return score.isFirstElement();
    }

    /**
     * Checks if score is last element.
     *
     * @return true if last element
     */
    @Override
    public boolean isLastElement() {
        return score.isLastElement();
    }

    /**
     * String representation of this object.
     *
     * @return String string representation
     */
    @Override
    public String toString() {
        return super.toString() + " cl: " + status;
    }

    /**
     * Method getDurationProbabilities returns the durationProbabilities of
     * this Classification object.
     *
     * @return the durationProbabilities (type ArrayList<Double>) of this
     *         Classification object.
     */
    @Override
    public ArrayList<Double> getDurationProbabilities() {
        return score.getDurationProbabilities();
    }

    /**
     * Get the probability of the score given the event type.
     *
     * @param eventType of type DataType
     * @return double
     */
    @Override
    public double getProbabilityFor(DataType eventType) {
        return score.getProbabilityFor(eventType);
    }

    /**
     * Return the mapping between {@link DataType} and probability.
     *
     * @return the dataTypeToProbabilityMapping (type HashMap<DataType,
     *         Double>) of this Classification object.
     */
    @Override
    protected HashMap<DataType, Double> getDataTypeToProbabilityMapping() {
        return score.getDataTypeToProbabilityMapping();
    }

    /**
     * Method getDurationFor gets the duration between this score and the last
     * time the given event was seen.
     *
     * @param predecessor a previous event
     * @return the time between the two events
     */
    @Override
    public long getDurationFor(DataType predecessor) {
        return score.getDurationFor(predecessor);
    }

    /**
     * Method getEventType returns the eventType of this Classification object.
     *
     * @return the eventType (type int) of this Classification object.
     */
    @Override
    public int getEventType() {
        return score.getEventType();
    }

    /**
     * Method getEventTypeIdAsString returns the event type of this
     * Classification object as a String.
     *
     * @return the eventTypeIdAsString (type String) of this Classification
     *         object.
     */
    @Override
    public String getEventTypeIdAsString() {
        return score.getEventTypeIdAsString();
    }

    /**
     * Method getFeatures returns the features of this Classification object.
     *
     * @return the features (type List<DomainFeature>) of this Classification
     *         object.
     */
    @Override
    public List<DomainFeature> getFeatures() {
        return score.getFeatures();
    }

    /**
     * Method getName returns the name of this Classification object.
     *
     * @return the name (type String) of this Classification object.
     */
    @Override
    public String getName() {
        return score.getName();
    }

    /**
     * Method getPredecessorIdToDurationsMapping returns the
     * predecessorIdToDurationsMapping of this Classification object.
     * <p/>
     * Holds durations between the occurrence of the data type represented by
     *
     * @return the predecessorIdToDurationsMapping (type LinkedHashMap<DataType,
     *         Long>) of this Classification object.
     */
    @Override
    protected LinkedHashMap<DataType, Long> getPredecessorIdToDurationsMapping() {
        return score.getPredecessorIdToDurationsMapping();
    }

    /**
     * Method getPredecessors returns the predecessors of this Classification
     * object.
     *
     * @return the predecessors (type ArrayList<DataType>) of this
     *         Classification object.
     */
    @Override
    public ArrayList<DataType> getPredecessors() {
        return score.getPredecessors();
    }

    /**
     * Checks if two classification objects are equal. These objects are
     * considered equal if they have the same timestamp.
     *
     * @param o other object
     * @return boolean true if equal
     */
    @Override
    public boolean equals(Object o) {
        if (o == null || !(o instanceof Classification)) {
            return false;
        }

        Classification c = (Classification) o;
        return getTimestamp() == c.getTimestamp();
    }

    /**
     * Dummy implementation.
     *
     * @return 42
     */
    @Override
    public int hashCode() {
        assert false : "hashCode not designed";
        return 42;
    }
}

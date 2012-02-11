package org.bham.aucom.data;

import nu.xom.Element;

import java.util.*;

public class Classification extends Score {
    private Score score;
    private SystemFaultStatus status;

    public Classification(Score data, SystemFaultStatus status) {
        super();
        this.score = data;
        this.status = status;
    }

    public Classification() {
        score = new RangeScore(new ArrayList<Score>());
        status = SystemFaultStatus.ABNORMAL;
    }

    public Classification(Classification cl) {
        this(cl.score, cl.status);
    }

    public SystemFaultStatus getStatus() {
        return status;
    }

    public double getStatusAsDouble() {
        if (status.equals(SystemFaultStatus.ABNORMAL))
            return 1.0;
        return 0.0;
    }

    public void setClassId(SystemFaultStatus status) {
        this.status = status;
    }

    public void setIsAbnormal() {
        status = SystemFaultStatus.ABNORMAL;
    }

    public void setIsNormal() {
        status = SystemFaultStatus.NORMAL;
    }

    public void setIsUnknown() {
        status = SystemFaultStatus.UNKNOWN;
    }

    public static Classification createRandomClassification() {
        Score s = createRandomScore();
        SystemFaultStatus st = SystemFaultStatus.values()[new Random().nextInt(SystemFaultStatus.values().length)];
        return new Classification(s, st);
    }

    /*
      * let's delegate everything
      */
    @Override
    public void addAttribute(String propertyName, String propertyValue) {
        score.addAttribute(propertyName, propertyValue);
    }

    @Override
    public boolean containsAttribute(String propertyName) {
        return score.containsAttribute(propertyName);
    }

    @Override
    public HashMap<String, String> getAttributes() {
        return score.getAttributes();
    }

    @Override
    public String getAttributeValue(String propertyName) {
        return score.getAttributeValue(propertyName);
    }

    @Override
    public double getValue() {
        return score.getValue();
    }

    @Override
    public void setValue(double value) {
        score.setValue(value);
    }

    @Override
    public double getVariance() {
        return score.getVariance();
    }

    @Override
    public void setVariance(double variance) {
        score.setVariance(variance);
    }

    @Override
    public long getTimestamp() {
        return score.getTimestamp();
    }

    @Override
    public void setTimestamp(long inTimestamp) {
        score.setTimestamp(inTimestamp);
    }

    @Override
    public Element getContent() {
        return score.getContent();
    }

    @Override
    public Object copy() {
        Score score_copy = (Score) score.copy();
        Classification cl_copy = new Classification(score_copy, status);
        return cl_copy;
    }

    @Override
    public void markAsFirstElement() {
        score.markAsFirstElement();
    }

    @Override
    public void unmarkAsFirstElement() {
        score.unmarkAsFirstElement();
    }

    @Override
    public void unmarkAsLastElement() {
        score.unmarkAsLastElement();
    }

    @Override
    public void markAsLastElement() {
        score.markAsLastElement();
    }

    @Override
    protected boolean isFirstElement() {
        return score.isFirstElement();
    }

    @Override
    protected boolean isLastElement() {
        return score.isLastElement();
    }

    @Override
    public String toString() {
        return super.toString() + " cl:" + status;
    }

    @Override
    public ArrayList<Double> getDurationProbabilities() {
        return score.getDurationProbabilities();
    }

    @Override
    public double getProbabilityFor(DataType eventType) {
        return score.getProbabilityFor(eventType);
    }

    @Override
    protected HashMap<DataType, Double> getDataTypeToProbabilityMapping() {
        return score.getDataTypeToProbabilityMapping();
    }

    @Override
    public long getDurationFor(DataType inPrecedessorDataType) {
        return score.getDurationFor(inPrecedessorDataType);
    }

    @Override
    public int getEventType() {
        return score.getEventType();
    }

    @Override
    public String getEventTypeIdAsString() {
        return score.getEventTypeIdAsString();
    }

    @Override
    public List<DomainFeature> getFeatures() {
        return score.getFeatures();
    }

    @Override
    public String getName() {
        return score.getName();
    }

    @Override
    protected LinkedHashMap<DataType, Long> getPredecessorIdToDurationsMapping() {
        return score.getPredecessorIdToDurationsMapping();
    }

    @Override
    public ArrayList<DataType> getPredecessors() {
        return score.getPredecessors();
    }

    @Override
    public boolean equals(Object o) {
        return o.getClass() == Classification.class && getTimestamp() == ((Classification) o).getTimestamp();
    }
}

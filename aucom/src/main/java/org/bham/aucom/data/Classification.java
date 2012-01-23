package org.bham.aucom.data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Random;

import nu.xom.Element;

public class Classification extends Score {
	private final Score score;
	private SystemFaultStatus status;

	public Classification(Score data, SystemFaultStatus status) {
		super();
		this.score = data;
		this.status = status;
	}

	public Classification(Classification cl) {
		this(cl.score, cl.status);
	}

	public SystemFaultStatus getStatus() {
		return this.status;
	}

	public double getStatusAsDouble() {
		if (this.status.equals(SystemFaultStatus.ABNORMAL))
			return 1.0d;
		return 0.0d;
	}

	public void setClassId(SystemFaultStatus status) {
		this.status = status;
	}

	public void setIsAbnormal() {
		this.status = SystemFaultStatus.ABNORMAL;
	}

	public void setIsNormal() {
		this.status = SystemFaultStatus.NORMAL;
	}

	public void setIsUnknown() {
		this.status = SystemFaultStatus.UNKNOWN;
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
		this.score.addAttribute(propertyName, propertyValue);
	}

	@Override
	public boolean containsAttribute(String propertyName) {
		return this.score.containsAttribute(propertyName);
	}
	@Override
	public HashMap<String, String> getAttributes() {
		return this.score.getAttributes();
	}

	@Override
	public String getAttributeValue(String propertyName) {
		return this.score.getAttributeValue(propertyName);
	}
	@Override
	public double getValue() {
		return this.score.getValue();
	}
	@Override
	public void setValue(double value) {
		this.score.setValue(value);
	}

	@Override
	public double getVariance() {
		return this.score.getVariance();
	}
	@Override
	public void setVariance(double variance) {
		this.score.setVariance(variance);
	}
	@Override
	public long getTimestamp() {
		return this.score.getTimestamp();
	}
	
	@Override
	public void setTimestamp(long inTimestamp) {
		this.score.setTimestamp(inTimestamp);
	}
	
	@Override
	public Element getContent() {
		return this.score.getContent();
	}
	@Override
	public Object copy() {
		Score score_copy = (Score)score.copy();
        return new Classification(score_copy, this.status);
	}
	@Override
	public void markAsFirstElement() {
		this.score.markAsFirstElement();
	}
	@Override
	public void unmarkAsFirstElement() {
		this.score.unmarkAsFirstElement();
	}
	@Override
	public void unmarkAsLastElement() {
		this.score.unmarkAsLastElement();
	}
	@Override
	public void markAsLastElement() {
		this.score.markAsLastElement();
	}
	@Override
	protected boolean isFirstElement() {
		return this.score.isFirstElement();
	}
	@Override
	protected boolean isLastElement() {
		return this.score.isLastElement();
	}
	@Override
	public String toString() {
		return super.toString() + " cl:" + status;
	}
	@Override
	public ArrayList<Double> getDurationProbabilities() {
		return this.score.getDurationProbabilities();
	}
	@Override
	public double getProbabilityFor(DataType eventType) {
		return this.score.getProbabilityFor(eventType);
	}
	@Override
	protected HashMap<DataType, Double> getDataTypeToProbabilityMapping() {
		return this.score.getDataTypeToProbabilityMapping();
	}
	@Override
	public long getDurationFor(DataType inPrecedessorDataType) {
		return this.score.getDurationFor(inPrecedessorDataType);
	}
	@Override
	public int getEventType() {
		return this.score.getEventType();
	}
	@Override
	public String getEventTypeIdAsString() {
		return this.score.getEventTypeIdAsString();
	}
	@Override
	public List<DomainFeature> getFeatures() {
		return this.score.getFeatures();
	}
	@Override
	public String getName() {
		return this.score.getName();
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
        return o.getClass() == Classification.class && this.getTimestamp() == ((Classification) o).getTimestamp();
    }
}

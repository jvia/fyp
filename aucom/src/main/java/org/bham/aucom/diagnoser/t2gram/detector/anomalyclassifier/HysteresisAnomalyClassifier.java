package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier;

import java.util.UUID;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.AnomalyClassifierStatusEvent;


public class HysteresisAnomalyClassifier extends AbstractAnomalyClassifier {
	private static final long serialVersionUID = 1L;
	private Double lower = 0.0;
	private Double upper = 0.0;
	private boolean isLastElementSatisfies = true;

	public HysteresisAnomalyClassifier(Double lower, Double upper) {
		super("hysteresis");
		this.setLower(lower);
		this.setUpper(upper);
		this.id = UUID.randomUUID();
	}

	@Override
	public boolean satisfies(Score s) {
		Double currentThreshold = getCurrentThreshold();
		boolean isSatisfies = s.getValue() >= currentThreshold;
		this.setLastElementSatisfies(isSatisfies);
		return isSatisfies;
	}

	private Double getCurrentThreshold() {
		if (this.isLastElementSatisfies())
			return this.getLower();
		return this.getUpper();
	}

	@Override
	public String toString() {
		return this.attributes.get("name") + " " + this.getLower() + " " + this.getUpper();
	}

	private void setLower(Double lower) {
		Double old = this.lower;
		this.lower = lower;
		if (!old.equals(this.lower)) {
			fireStatusChangedEvent(new AnomalyClassifierStatusEvent(this));
		}
	}

	public Double getLower() {
		return this.lower;
	}

	private void setUpper(Double upper) {
		double old = this.upper;
		this.upper = upper;
		if (old != this.upper) {
			fireStatusChangedEvent(new AnomalyClassifierStatusEvent(this));
		}
	}

	public Double getUpper() {
		return this.upper;
	}

	@Override
	public void setClassificator(AnomalyClassifier threshold) throws ClassCastException {
		HysteresisAnomalyClassifier ht = (HysteresisAnomalyClassifier) threshold;
		this.setLower(ht.getLower());
		this.setUpper(ht.getUpper());
		this.setLastElementSatisfies(ht.isLastElementSatisfies());
	}

	protected void setLastElementSatisfies(boolean isLastElementSatisfies) {
		boolean old = this.isLastElementSatisfies;
		this.isLastElementSatisfies = isLastElementSatisfies;
		if (old != this.isLastElementSatisfies) {
			fireStatusChangedEvent(new AnomalyClassifierStatusEvent(this));
		}
	}

	public boolean isLastElementSatisfies() {
		return this.isLastElementSatisfies;
	}

	@Override
	public boolean equals(Object obj) {
		HysteresisAnomalyClassifier inObj = (HysteresisAnomalyClassifier) obj;
        return this.lower.equals(inObj.getLower()) && this.upper.equals(inObj.getUpper());
	}

	@Override
	public void copy(AnomalyClassifier classifier) {
		HysteresisAnomalyClassifier cl = (HysteresisAnomalyClassifier) classifier;
		this.setLastElementSatisfies(cl.isLastElementSatisfies());
		this.setLower(cl.getLower());
		this.setUpper(cl.getUpper());
	}

	@Override
	public AnomalyClassifier duplicate() {
		HysteresisAnomalyClassifier duplicat = new HysteresisAnomalyClassifier(this.getLower(), this.getUpper());
		duplicat.setLastElementSatisfies(this.isLastElementSatisfies());
		return duplicat;
	}

	@Override
	public void reset() {
		isLastElementSatisfies = true;
	}
}

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import java.util.UUID;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.AnomalyClassificatorStatusEvent;


public class HysteresisAnomalyClassificator extends AbstractAnomalyClassificator {
	private static final long serialVersionUID = 1L;
	private Double lower = new Double(0.0);
	private Double upper = new Double(0.0);
	private boolean isLastElementSatisfies = true;

	public HysteresisAnomalyClassificator(Double lower, Double upper) {
		super("hysteresis");
		this.setLower(lower);
		this.setUpper(upper);
		this.id = UUID.randomUUID();
	}

	@Override
	public boolean satisfies(Score s) {
		Double currentThreshold = getCurrentThreshold();
		boolean isSatisfies = s.getValue() >= currentThreshold.doubleValue();
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
			fireStatusChangedEvent(new AnomalyClassificatorStatusEvent(this));
		}
	}

	public Double getLower() {
		return this.lower;
	}

	private void setUpper(Double upper) {
		double old = this.upper;
		this.upper = upper;
		if (old != this.upper) {
			fireStatusChangedEvent(new AnomalyClassificatorStatusEvent(this));
		}
	}

	public Double getUpper() {
		return this.upper;
	}

	@Override
	public void setClassificator(AnomalyClassificator threshold) throws ClassCastException {
		HysteresisAnomalyClassificator ht = (HysteresisAnomalyClassificator) threshold;
		this.setLower(ht.getLower());
		this.setUpper(ht.getUpper());
		this.setLastElementSatisfies(ht.isLastElementSatisfies());
	}

	protected void setLastElementSatisfies(boolean isLastElementSatisfies) {
		boolean old = this.isLastElementSatisfies;
		this.isLastElementSatisfies = isLastElementSatisfies;
		if (old != this.isLastElementSatisfies) {
			fireStatusChangedEvent(new AnomalyClassificatorStatusEvent(this));
		}
	}

	public boolean isLastElementSatisfies() {
		return this.isLastElementSatisfies;
	}

	@Override
	public boolean equals(Object obj) {
		HysteresisAnomalyClassificator inObj = (HysteresisAnomalyClassificator) obj;
		boolean equal = this.lower.equals(inObj.getLower()) && this.upper.equals(inObj.getUpper());
		return equal;
	}

	@Override
	public void copy(AnomalyClassificator classificator) {
		HysteresisAnomalyClassificator cl = (HysteresisAnomalyClassificator) classificator;
		this.setLastElementSatisfies(cl.isLastElementSatisfies());
		this.setLower(cl.getLower());
		this.setUpper(cl.getUpper());
	}

	@Override
	public AnomalyClassificator duplicate() {
		HysteresisAnomalyClassificator duplicat = new HysteresisAnomalyClassificator(this.getLower(), this.getUpper());
		duplicat.setLastElementSatisfies(this.isLastElementSatisfies());
		return duplicat;
	}

	@Override
	public void reset() {
		isLastElementSatisfies = true;
	}
}

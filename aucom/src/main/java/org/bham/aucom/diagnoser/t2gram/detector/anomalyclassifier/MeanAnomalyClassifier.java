package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier;

import java.util.UUID;

import org.bham.aucom.data.Score;


public class MeanAnomalyClassifier extends AbstractAnomalyClassifier {
	private static final long serialVersionUID = 1L;

	public MeanAnomalyClassifier(Double meanValue) {
		super("meanThreshold");
		this.parameters.put("mean", meanValue);
		this.id = UUID.randomUUID();
	}

	public void setMeanValue(double in) {
		this.parameters.put("mean", new Double(in));
	}

	public double getMeanValue() {
		return this.parameters.get("mean").doubleValue();
	}

	@Override
	public String toString() {
		String str = "" + this.parameters.get("mean");
		return str;
	}

	@Override
	public boolean satisfies(Score s) {
		return s.getValue() >= getMeanValue();
	}

	@Override
	public void setClassificator(AnomalyClassifier threshold) throws ClassCastException {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void copy(AnomalyClassifier classifier) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public AnomalyClassifier duplicate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		// TODO Auto-generated method stub
		
	}
}
package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier;

import org.bham.aucom.data.Score;

public class StatisticalHysterisisAnomalyClassifier extends AbstractAnomalyClassifier {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	StatisticalAnomalyClassifier statistic;
	HysteresisAnomalyClassifier hysteresis;
	double mean;
	double variance;

	public StatisticalHysterisisAnomalyClassifier(Double mean, Double variance) {
		super("StatisticalHysterisisThreshold");
		this.mean = mean.doubleValue();
		this.variance = variance.doubleValue();
	}

	@Override
	public boolean satisfies(Score scoreToCheck) {
		double mean = 0.0d;
		double scoreVariance = 0.0d;
		double a = 0.9;
		mean = scoreToCheck.getValue();
		scoreVariance = scoreToCheck.getVariance();
		double quotient = scoreVariance / this.variance;
		return (a * this.mean + (1.0 - a) * this.mean * quotient) <= mean;
	}

	@Override
	public void setClassificator(AnomalyClassifier threshold) throws ClassCastException {
		StatisticalHysterisisAnomalyClassifier st = (StatisticalHysterisisAnomalyClassifier) threshold;
		// TODO finish this
	}

	@Override
	public void copy(AnomalyClassifier classifier) {
		
	}

	@Override
	public AnomalyClassifier duplicate() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void reset() {
		statistic.reset();
		hysteresis.reset();
	}

}

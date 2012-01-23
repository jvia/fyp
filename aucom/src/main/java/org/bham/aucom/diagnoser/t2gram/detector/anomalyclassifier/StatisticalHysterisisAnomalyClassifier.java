package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier;

import org.bham.aucom.data.Score;

public class StatisticalHysterisisAnomalyClassifier extends AbstractAnomalyClassifier {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private StatisticalAnomalyClassifier statistic;
    private HysteresisAnomalyClassifier hysteresis;
    private final double mean;
    private final double variance;

    public StatisticalHysterisisAnomalyClassifier(Double mean, Double variance) {
        super("StatisticalHysterisisThreshold");
        this.mean = mean;
        this.variance = variance;
    }

    @Override
    public boolean satisfies(Score scoreToCheck) {
        double mean;
        double scoreVariance;
        double a = 0.9;
        mean = scoreToCheck.getValue();
        scoreVariance = scoreToCheck.getVariance();
        double quotient = scoreVariance / this.variance;
        return (a * this.mean + (1.0 - a) * this.mean * quotient) <= mean;
    }

    @Override
    public void setClassifier(AnomalyClassifier threshold) throws ClassCastException {
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

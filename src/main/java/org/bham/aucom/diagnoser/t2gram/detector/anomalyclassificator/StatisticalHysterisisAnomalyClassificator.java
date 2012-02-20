package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import org.bham.aucom.data.Score;

public class StatisticalHysterisisAnomalyClassificator extends AbstractAnomalyClassificator {

    private static final long serialVersionUID = 1L;
    StatisticalAnomalyClassificator statistic;
    HysteresisAnomalyClassificator hysteresis;
    double mean;
    double variance;

    public StatisticalHysterisisAnomalyClassificator(Double mean, Double variance) {
        super("StatisticalHysterisisThreshold");
        this.mean = mean;
        this.variance = variance;
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
    public void setClassificator(AnomalyClassificator threshold) throws ClassCastException {
        StatisticalHysterisisAnomalyClassificator st = (StatisticalHysterisisAnomalyClassificator) threshold;
        // TODO finish this
    }

    @Override
    public void copy(AnomalyClassificator classificator) {

    }

    @Override
    public AnomalyClassificator duplicate() {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public void reset() {
        statistic.reset();
        hysteresis.reset();
    }

}

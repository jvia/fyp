package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import org.bham.aucom.data.ConfigurationFailedException;

public class StatisticalAnomalyClassificatorConfigurator implements AnomalyConfigurator {
    private double mean = Double.NaN;
    private double variance = Double.NaN;

    @Override
    public void configure(AnomalyClassifier in) throws ConfigurationFailedException {
        try {
            StatisticalAnomalyClassifier sAcl = (StatisticalAnomalyClassifier) in;
            if (Double.isNaN(mean) || Double.isNaN(variance)) {
                throw new ConfigurationFailedException();
            }
            sAcl.setMean(getMean());
            sAcl.setVariance(getVariance());
        } catch (Exception exception) {
            throw new ConfigurationFailedException();
        }
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

}

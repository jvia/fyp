package org.bham.app.experiment;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class MeanVarianceResult extends Result {

    private  double mean;
    private double variance;

    public MeanVarianceResult(double mean, double variance) {
        this.mean = mean;
        this.variance = variance;
    }

    public double getMean() {
        return mean;
    }

    public void setMean(double mean) {
        this.mean = mean;
    }

    public double getVariance() {
        return variance;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    @Override
    public String getAsCsvString() {
        return String.format("Mean: %.5f, Variance: %.5f", mean, variance);
    }
}

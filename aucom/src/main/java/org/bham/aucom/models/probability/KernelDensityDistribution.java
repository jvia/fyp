package org.bham.aucom.models.probability;

import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import weka.estimators.KernelEstimator;

import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

import static org.bham.aucom.util.Constants.LOWEST_PROBABILITY;

public class KernelDensityDistribution implements ProbabilityDistribution {
    private static final long serialVersionUID = 1378894184156772597L;
    KernelEstimator estimator;
    double weight = 1.0;
    private double entropy = Double.MIN_VALUE;
    private int count = 0;
    double maxProbabilityValue = 0.0d;
    private double maxSeenValue = 0.0d;
    private HashMap<Double, Double> queriedValues = new HashMap<Double, Double>();

    public KernelDensityDistribution(double precision) {
        estimator = new KernelEstimator(precision);
    }

    public KernelDensityDistribution(double precision, double[] values) {
        estimator = new KernelEstimator(precision);
        update(values);
    }

    @Override
    public double getEntropy() {
        if (entropy == Double.MIN_VALUE) {
            double[] means = estimator.getMeans();
            for (int i = 0; i < estimator.getNumKernels(); i++) {
                if (Double.isNaN(getProbability(means[i]) * log(getProbability(means[i]), 2))) {
                    Logger.getLogger(getClass().getCanonicalName()).severe("not a number entropy value ");
                    Logger.getLogger(getClass().getCanonicalName()).severe("prob " + (getProbability(means[i])));
                    Logger.getLogger(getClass().getCanonicalName()).severe("logprob " + (log(getProbability(means[i]), 2)));
                    return LOWEST_PROBABILITY;
                }
                entropy += getProbability(means[i]) * log(getProbability(means[i]), 2);
                // Logger.getLogger(getClass().getCanonicalName()).info("prob for "
                // +means[i]+ " is " + estimator.getProbability(means[i]));
            }
            if (Double.isNaN(entropy))
                Logger.getLogger(getClass().getCanonicalName()).info("num kernels " + estimator.getNumKernels() + " entropy " + (-entropy));
            entropy = -entropy;
        }
        return entropy;
    }

    public double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    @Override
    public double getProbability(double val) {
        if (!getQueriedValues().containsKey(val))
            getQueriedValues().put(val, estimator.getProbability(val));
        return getQueriedValues().get(val);
    }

    @Override
    public String getType() {
        return "kernelDensity";
    }

    @Override
    public void update(double[] val) {
        queriedValues.clear();
        for (double aVal : val) {
            estimator.addValue(aVal, weight);
        }
        Arrays.sort(val);
        setMaxSeenValue(val[val.length - 1]);
        double[] samples = sampleProbability();
        Arrays.sort(samples);
        maxProbabilityValue = samples[samples.length - 1];
        // System.out.println("distribution trained with " +val.length+
        // "  max value is " +maxProbabilityValue);
        count += val.length;
    }

    @Override
    public int getCount() {
        return count;
    }

    @Override
    public double[] sampleProbability() {
        double sample[] = sample();
        double[] sampleProbabilities = new double[sample.length];

        for (int i = 0; i < sample.length; i++)
            sampleProbabilities[i] = getProbability(sample[i]);

        return sampleProbabilities;
    }

    @Override
    public double expectedValue() {
        return 0;
    }

    @Override
    public double variance() {
        return 0;
    }

    @Override
    public double[] sample() {
        estimator.getPrecision();
        estimator.getNumKernels();
        double[] means = estimator.getMeans();
        int numberSample = 800;
        estimator.getStdDev();
        Arrays.sort(means, 0, estimator.getNumKernels() - 1);
        double currentValue = 0.0d;
        double highestValue = getMaxSeenValue() * 10;
        double sampleRange = highestValue - currentValue;
        double stepSize = sampleRange / numberSample;
        double[] samples = new double[numberSample];

        for (int i = 0; i < numberSample; i++) {
            samples[i] = Math.round(currentValue);
            currentValue += stepSize;
        }
        return samples;
    }

    @Override
    public double getMaxProbability() {
        return maxProbabilityValue;
    }

    public HashMap<Double, Double> getQueriedValues() {
        return queriedValues;
    }

    void setMaxSeenValue(double maxSeenValue) {
        this.maxSeenValue = maxSeenValue;
    }

    public double getMaxSeenValue() {
        return maxSeenValue;
    }
}

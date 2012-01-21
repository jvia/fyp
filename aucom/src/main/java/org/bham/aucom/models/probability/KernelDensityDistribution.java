package org.bham.aucom.models.probability;

import static org.bham.aucom.util.Constants.LOWESTPROBABILITY;

import java.util.Arrays;
import java.util.HashMap;
import java.util.logging.Logger;

import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;

import weka.estimators.KernelEstimator;

public class KernelDensityDistribution implements ProbabilityDistribution {
	private static final long serialVersionUID = 1378894184156772597L;
	KernelEstimator estimator;
	double weight = 1.0;
	private double entropy = Double.MIN_VALUE;
	private int count = 0;
	double maxProbabilityValue = 0.0d;
	private double maxSeenValue = 0.0d;
	private HashMap<Double, Double> queriedValues = new HashMap<Double, Double>();
	Double tmpValue = new Double(0.0);
	private int lookUpTableUsed;
	private int lookUpTableNotUsed;

	public KernelDensityDistribution(double precision) {
		this.estimator = new KernelEstimator(precision);
	}

	public KernelDensityDistribution(double precision, double[] values) {
		this.estimator = new KernelEstimator(precision);
		this.lookUpTableUsed = 0;
		this.lookUpTableNotUsed = 0;
		update(values);
	}

	@Override
	public double getEntropy() {
		if (this.entropy == Double.MIN_VALUE) {
			double[] means = this.estimator.getMeans();
			for (int i = 0; i < this.estimator.getNumKernels(); i++) {
				if (Double.isNaN(getProbability(means[i]) * log(getProbability(means[i]), 2))) {
					Logger.getLogger(this.getClass().getCanonicalName()).severe("not a number entropy value ");
					Logger.getLogger(this.getClass().getCanonicalName()).severe("prob " + (getProbability(means[i])));
					Logger.getLogger(this.getClass().getCanonicalName()).severe("logprob " + (log(getProbability(means[i]), 2)));
					return LOWESTPROBABILITY;
				}
				this.entropy += getProbability(means[i]) * log(getProbability(means[i]), 2);
				// Logger.getLogger(this.getClass().getCanonicalName()).info("prob for "
				// +means[i]+ " is " + this.estimator.getProbability(means[i]));
			}
			if (Double.isNaN(this.entropy))
				Logger.getLogger(this.getClass().getCanonicalName()).info("num kernels " + this.estimator.getNumKernels() + " entropy " + (-this.entropy));
			this.entropy = -this.entropy;
		}
		return this.entropy;
	}

	public double log(double value, double base) {
		return Math.log(value) / Math.log(base);
	}

	@Override
	public double getProbability(double val) {
		boolean useBuffer = true;
		if (useBuffer) {
			Double dVal = Double.valueOf(val);
			if (!this.getQueriedValues().containsKey(dVal)) {
				this.getQueriedValues().put(dVal, Double.valueOf(this.estimator.getProbability(val)));
				this.lookUpTableUsed++;
			}else{
				this.lookUpTableNotUsed++;
			}
			double value = this.getQueriedValues().get(dVal).doubleValue();
			return value;
		} else {
			return this.estimator.getProbability(val);
		}
	}

	@Override
	public String getType() {
		return "kernelDensity";
	}

	@Override
	public void update(double[] val) {
		this.queriedValues.clear();
		for (int i = 0; i < val.length; i++) {
			this.estimator.addValue(val[i], this.weight);
		}
		Arrays.sort(val);
		this.setMaxSeenValue(val[val.length - 1]);
		double[] samples = sampleProbability();
		Arrays.sort(samples);
		this.maxProbabilityValue = samples[samples.length - 1];
		// System.out.println("distribution trained with " +val.length+
		// "  max value is " +this.maxProbabilityValue);
		this.count += val.length;
	}

	@Override
	public int getCount() {
		return this.count;
	}

	@Override
	public double[] sampleProbability() {
		double sample[] = sample();
		double[] sampleProbabilities = new double[sample.length];
		for (int i = 0; i < sample.length; i++) {
			sampleProbabilities[i] = this.getProbability(sample[i]);
		}
		return sampleProbabilities;
	}

	@Override
	public double expectedValue() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double variance() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double[] sample() {
		double precision = this.estimator.getPrecision();
		int numberCernels = this.estimator.getNumKernels();
		double[] means = this.estimator.getMeans();
		int numberSample = 800;
		double stdDev = this.estimator.getStdDev();
		Arrays.sort(means, 0, this.estimator.getNumKernels() - 1);
		double lowestValue = 0.0d;
		double currentValue = lowestValue;
		double highestValue = this.getMaxSeenValue() * 10;
		double sampleRange = highestValue - currentValue;
		// System.out.println("stdDev " + stdDev);
		// System.out.println("lowestValue " + currentValue);
		// System.out.println("highestValue " + highestValue);
		// System.out.println("sampleRange " + sampleRange);
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
		return this.maxProbabilityValue;
	}

	public HashMap<Double, Double> getQueriedValues() {
		return this.queriedValues;
	}

	void setMaxSeenValue(double maxSeenValue) {
		this.maxSeenValue = maxSeenValue;
	}

	public double getMaxSeenValue() {
		return this.maxSeenValue;
	}
}

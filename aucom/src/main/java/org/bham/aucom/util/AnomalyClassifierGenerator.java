package org.bham.aucom.util;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.AnomalyClassifier;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.StatisticalAnomalyClassifier;

import java.util.LinkedList;


public class AnomalyClassifierGenerator {
    private double initialThresholdValue;
    private double maxThresholdValue;
    private double thresholdValueStep;
    private double initialVarianceValue;
    private double maxVarianceValue;
    private double varianceValueStep;

    public AnomalyClassifierGenerator(double initialThresholdValue, double maxThresholdValue, double thresholdValueStep, double initialVarianceValue, double maxVarianceValue,
                                      double varianceValueStep) {
        this.initialThresholdValue = initialThresholdValue;
        this.maxThresholdValue = maxThresholdValue;
        this.thresholdValueStep = thresholdValueStep;
        this.initialVarianceValue = initialVarianceValue;
        this.maxVarianceValue = maxVarianceValue;
        this.varianceValueStep = varianceValueStep;
    }

    public double getInitialThresholdValue() {
        return this.initialThresholdValue;
    }

    public void setInitialThresholdValue(double initialThresholdValue) {
        this.initialThresholdValue = initialThresholdValue;
    }

    public double getMaxThresholdValue() {
        return this.maxThresholdValue;
    }

    public void setMaxThresholdValue(double maxThresholdValue) {
        this.maxThresholdValue = maxThresholdValue;
    }

    public double getThresholdValueStep() {
        return this.thresholdValueStep;
    }

    public void setThresholdValueStep(double thresholdValueStep) {
        this.thresholdValueStep = thresholdValueStep;
    }

    public double getInitialVarianceValue() {
        return this.initialVarianceValue;
    }

    public void setInitialVarianceValue(double initialVarianceValue) {
        this.initialVarianceValue = initialVarianceValue;
    }

    public double getMaxVarianceValue() {
        return this.maxVarianceValue;
    }

    public void setMaxVarianceValue(double maxVarianceValue) {
        this.maxVarianceValue = maxVarianceValue;
    }

    public double getVarianceValueStep() {
        return this.varianceValueStep;
    }

    public void setVarianceValueStep(double varianceValueStep) {
        this.varianceValueStep = varianceValueStep;
    }

    public LinkedList<AnomalyClassifier> generateClassifiersToTest() {
        LinkedList<AnomalyClassifier> thresholds = new LinkedList<AnomalyClassifier>();
        double thresholdInterval = this.getMaxThresholdValue() - this.getInitialThresholdValue();
        int thresholdIterations = (int) (thresholdInterval / this.getThresholdValueStep());
        double varianceInterval = this.getMaxVarianceValue() - this.getInitialVarianceValue();
        double varianceIterations = (int) (varianceInterval / this.getVarianceValueStep());
        for (int i = 0; i < thresholdIterations; i++) {
            for (int j = 0; j < varianceIterations; j++) {
                double mean = this.getInitialThresholdValue() + i * this.getThresholdValueStep();
                double variance = this.getInitialVarianceValue() + j * this.getVarianceValueStep();
                thresholds.add(new StatisticalAnomalyClassifier(mean, variance));
            }
        }
        System.out.println("generated " + thresholds.size() + " thresholds for testing");
        return thresholds;
    }
}
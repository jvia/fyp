package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.optimizer;

import org.bham.aucom.data.ClassificationTimeSeriesDescriptiveStatistics;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.AnomalyClassifier;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.StatisticalAnomalyClassifier;
import org.bham.aucom.util.AnomalyClassifierGenerator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;


public class ClassifierOptimizationMethod {
    private double allowedFalsePositiveRate;
    private StatisticalAnomalyClassifier bestClassifier;
    private StatisticalAnomalyClassifier currentClassifier;
    private ClassificationTimeSeriesDescriptiveStatistics statistic;
    private double bestFalsePositiveRate;
    private double bestPositiveQuadraticDistance;
    private LinkedList<AnomalyClassifier> classifiersToTest;
    private HashMap<AnomalyClassifier, Double> testedClassifiers;

    public ClassifierOptimizationMethod() {
        this.allowedFalsePositiveRate = 0.069d;
        setClassifiersToTest(new LinkedList<AnomalyClassifier>());
        setStatistic(new ClassificationTimeSeriesDescriptiveStatistics());
        setCurrentClassifier(null);
        setBestClassifier(null);
        setBestFalsePositiveRate(Double.MAX_VALUE);
        setBestPositiveQuadraticDistance(Double.MAX_VALUE);
        setTestedClassifiers(new LinkedHashMap<AnomalyClassifier, Double>());
    }


    public void initializeClassifiers() {
        getTestedClassifiers().clear();
        setBestClassifier(null);
        double initialThresholdValue = 0.55;
        double maxThresholdValue = 0.6;
        double thresholdValueStep = 0.02;
        double initialVarianceValue = 0.001;
        double maxVarianceValue = 0.015;
        double varianceValueStep = 0.001;
        AnomalyClassifierGenerator acg = new AnomalyClassifierGenerator(initialThresholdValue, maxThresholdValue, thresholdValueStep, initialVarianceValue, maxVarianceValue, varianceValueStep);
        getClassifiersToTest().addAll(acg.generateClassifiersToTest());
    }

    public double getAllowedFalsePositiveRate() {
        return this.allowedFalsePositiveRate;
    }

    public void setAllowedFalsePositiveRate(double allowedFalsePositiveRate) {
        this.allowedFalsePositiveRate = allowedFalsePositiveRate;
    }

    public StatisticalAnomalyClassifier getBestClassifier() {
        return this.bestClassifier;
    }

    public void setBestClassifier(StatisticalAnomalyClassifier bestClassifier) {
        this.bestClassifier = bestClassifier;
    }

    public StatisticalAnomalyClassifier getCurrentClassifier() {
        return this.currentClassifier;
    }

    public void setCurrentClassifier(StatisticalAnomalyClassifier currentClassifier) {
        this.currentClassifier = currentClassifier;
    }

    public ClassificationTimeSeriesDescriptiveStatistics getStatistic() {
        return this.statistic;
    }

    public void setStatistic(ClassificationTimeSeriesDescriptiveStatistics statistic) {
        this.statistic = statistic;
    }

    public double getBestFalsePositiveRate() {
        return this.bestFalsePositiveRate;
    }

    public void setBestFalsePositiveRate(double bestFalsePositiveRate) {
        this.bestFalsePositiveRate = bestFalsePositiveRate;
    }

    public double getBestPositiveQuadraticDistance() {
        return this.bestPositiveQuadraticDistance;
    }

    public void setBestPositiveQuadraticDistance(double bestPositiveQuadraticDistance) {
        this.bestPositiveQuadraticDistance = bestPositiveQuadraticDistance;
    }

    public LinkedList<AnomalyClassifier> getClassifiersToTest() {
        return this.classifiersToTest;
    }

    public void setClassifiersToTest(LinkedList<AnomalyClassifier> classifiersToTest) {
        this.classifiersToTest = classifiersToTest;
    }

    public HashMap<AnomalyClassifier, Double> getTestedClassifiers() {
        return this.testedClassifiers;
    }

    public void setTestedClassifiers(HashMap<AnomalyClassifier, Double> testedClassifiers) {
        this.testedClassifiers = testedClassifiers;
    }
}
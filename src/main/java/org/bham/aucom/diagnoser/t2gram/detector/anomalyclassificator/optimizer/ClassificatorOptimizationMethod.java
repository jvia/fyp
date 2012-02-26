package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer;

import org.bham.aucom.data.ClassificationTimeSeriesDescriptiveStatistics;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassifier;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.StatisticalAnomalyClassifier;
import org.bham.aucom.util.AnomalyClassificatorGenerator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ClassificatorOptimizationMethod {
    private double allowedFalsePositiveRate;
    private StatisticalAnomalyClassifier bestClassifier;
    private StatisticalAnomalyClassifier currentClassifier;
    private ClassificationTimeSeriesDescriptiveStatistics statistic;
    private double bestFalsePositiveRate;
    private double bestPositiveQuadraticDistance;
    private LinkedList<AnomalyClassifier> classificatorsToTest;
    private HashMap<AnomalyClassifier, Double> testedClassificators;

    public ClassificatorOptimizationMethod() {
        this.allowedFalsePositiveRate = 0.069d;
        setClassificatorsToTest(new LinkedList<AnomalyClassifier>());
        setStatistic(new ClassificationTimeSeriesDescriptiveStatistics());
        setCurrentClassifier(null);
        setBestClassifier(null);
        setBestFalsePositiveRate(Double.MAX_VALUE);
        setBestPositiveQuadraticDistance(Double.MAX_VALUE);
        setTestedClassificators(new LinkedHashMap<AnomalyClassifier, Double>());
    }

    /**
     * @param classificator
     */
    public void initializeClassificators() {
        getTestedClassificators().clear();
        setBestClassifier(null);
        double initialThresholdValue = 0.55;
        double maxThresholdValue = 0.6;
        double thresholdValueStep = 0.02;
        double initialVarianceValue = 0.001;
        double maxVarianceValue = 0.015;
        double varianceValueStep = 0.001;
        AnomalyClassificatorGenerator acg = new AnomalyClassificatorGenerator(initialThresholdValue, maxThresholdValue, thresholdValueStep, initialVarianceValue, maxVarianceValue, varianceValueStep);
        getClassificatorsToTest().addAll(acg.generateClassificatorsToTest());
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

    public LinkedList<AnomalyClassifier> getClassificatorsToTest() {
        return this.classificatorsToTest;
    }

    public void setClassificatorsToTest(LinkedList<AnomalyClassifier> classificatorsToTest) {
        this.classificatorsToTest = classificatorsToTest;
    }

    public HashMap<AnomalyClassifier, Double> getTestedClassificators() {
        return this.testedClassificators;
    }

    public void setTestedClassificators(HashMap<AnomalyClassifier, Double> testedClassificators) {
        this.testedClassificators = testedClassificators;
    }
}
package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer;

import org.bham.aucom.data.ClassificationTimeSeriesDescriptiveStatistics;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.StatisticalAnomalyClassificator;
import org.bham.aucom.util.AnomalyClassificatorGenerator;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.LinkedList;

public class ClassificatorOptimizationMethod {
    private double allowedFalsePositiveRate;
    private StatisticalAnomalyClassificator bestClassificator;
    private StatisticalAnomalyClassificator currentClassificator;
    private ClassificationTimeSeriesDescriptiveStatistics statistic;
    private double bestFalsePositiveRate;
    private double bestPositiveQuadraticDistance;
    private LinkedList<AnomalyClassificator> classificatorsToTest;
    private HashMap<AnomalyClassificator, Double> testedClassificators;

    public ClassificatorOptimizationMethod() {
        this.allowedFalsePositiveRate = 0.069d;
        setClassificatorsToTest(new LinkedList<AnomalyClassificator>());
        setStatistic(new ClassificationTimeSeriesDescriptiveStatistics());
        setCurrentClassificator(null);
        setBestClassificator(null);
        setBestFalsePositiveRate(Double.MAX_VALUE);
        setBestPositiveQuadraticDistance(Double.MAX_VALUE);
        setTestedClassificators(new LinkedHashMap<AnomalyClassificator, Double>());
    }

    /**
     * @param classificator
     */
    public void initializeClassificators() {
        getTestedClassificators().clear();
        setBestClassificator(null);
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

    public StatisticalAnomalyClassificator getBestClassificator() {
        return this.bestClassificator;
    }

    public void setBestClassificator(StatisticalAnomalyClassificator bestClassificator) {
        this.bestClassificator = bestClassificator;
    }

    public StatisticalAnomalyClassificator getCurrentClassificator() {
        return this.currentClassificator;
    }

    public void setCurrentClassificator(StatisticalAnomalyClassificator currentClassificator) {
        this.currentClassificator = currentClassificator;
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

    public LinkedList<AnomalyClassificator> getClassificatorsToTest() {
        return this.classificatorsToTest;
    }

    public void setClassificatorsToTest(LinkedList<AnomalyClassificator> classificatorsToTest) {
        this.classificatorsToTest = classificatorsToTest;
    }

    public HashMap<AnomalyClassificator, Double> getTestedClassificators() {
        return this.testedClassificators;
    }

    public void setTestedClassificators(HashMap<AnomalyClassificator, Double> testedClassificators) {
        this.testedClassificators = testedClassificators;
    }
}
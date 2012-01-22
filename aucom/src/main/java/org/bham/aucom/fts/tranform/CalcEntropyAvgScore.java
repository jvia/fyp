package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.SingleScore;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;

import java.util.logging.Logger;


public class CalcEntropyAvgScore extends AbstractAucomTranformNode<TemporalProbabilityFeature, Score> {
    protected T2GramModelI model;

    public CalcEntropyAvgScore(T2GramModelI inModel) {
        super("CalcEnropyAvgScore");
        setModel(inModel);
    }

    public CalcEntropyAvgScore() {
        super("CalcEnropyAvgScore");
    }

    @Override
    protected Score iTransform(TemporalProbabilityFeature arg0) throws Exception {
        Logger.getLogger(this.getClass().getCanonicalName()).info("CalcEntropyAvgScore iTransformCalled");
        return calculate(arg0);
    }

    protected Score calculate(TemporalProbabilityFeature inData) {
        warnIfModelIsNotTrained();
        return calculateScore(inData);

    }

    /**
     * @param inData
     * @return
     */
    protected Score calculateScore(TemporalProbabilityFeature inData) {
        double sum_entropy = calculateSumEntropy(inData);
        double denominator = calculateDenominator(sum_entropy);
        double scoreValue;

        scoreValue = calculateAbsoluteScoreValue(inData, denominator);
//		System.out.println("predecessors " + inData.getPredecessors().size());
//		System.out.println("predecessors " + inData.getPredecessors().get(0).getEventType());
//		System.out.println("sum_entropy " + sum_entropy);
//		System.out.println("denominator " + denominator);
//		System.out.println("absolute scoreValue " + scoreValue);
        scoreValue = normalize(inData, scoreValue);
//		System.out.println("normalized scoreValue " + scoreValue);
        return new SingleScore(inData, scoreValue);
    }

    /**
     * @param sum_entropy
     * @return
     */
    protected double calculateDenominator(double sum_entropy) {
        return Math.max(Math.pow(sum_entropy, 2), 0.00001);
    }

    /**
     * @param currentData
     * @param sum_entropy
     * @param scoreValue
     * @return
     */
    protected double calculateAbsoluteScoreValue(TemporalProbabilityFeature currentData, double denominator) {
        double scoreValue = 0.0d;
        for (DataType precedessorData : currentData.getPredecessors()) {
            warnIfDitstributionNotfound(currentData, precedessorData);
            scoreValue += calculateSingleScoreValue(precedessorData, currentData, denominator);
        }
        return scoreValue;
    }

    protected double calculateSingleScoreValue(DataType precedessorData, TemporalProbabilityFeature currentData, double denominator) {
        double probability = currentData.getProbabilityFor(precedessorData);
        double entropy = calculateSingleEntropy(currentData, precedessorData);

        warnIfProbabilityHasNaNValue(probability, currentData, precedessorData);
        warnIfEntropyHasNaNValue(entropy, currentData, precedessorData);

        return alg_calculateSingleScore(probability, entropy, denominator);
    }

    /**
     * @param probability
     * @param entropy
     * @param denominator
     * @return
     */
    protected double alg_calculateSingleScore(double probability, double entropy, double denominator) {
        return probability * (1 - Math.pow(entropy, 2) / denominator);
    }

    /**
     * @param inData
     * @param absoluteScore
     * @return
     */
    protected double normalize(TemporalProbabilityFeature inData, double absoluteScore) {
        double normalizedScore = absoluteScore;
        if (inData.getPredecessors().size() > 0) {
            normalizedScore = absoluteScore / inData.getPredecessors().size();
        }
        return normalizedScore;
    }

    /**
     * @param inData
     */
    protected double calculateSumEntropy(TemporalProbabilityFeature inData) {
        double[] singleEntropies = new double[inData.getPredecessors().size()];
        for (int i = 0; i < inData.getPredecessors().size(); i++) {
            DataType predecessor = inData.getPredecessors().get(i);
            singleEntropies[i] = calculateSingleEntropy(inData, predecessor);

        }

        return alg_calculateSumEntropy(singleEntropies);
    }

    protected double alg_calculateSumEntropy(double[] values) {
        double sum = 0.0d;
        for (double value : values) {
            sum += value;
        }
        return sum;
    }

    /**
     * @param inData
     * @param inPrecedessor
     * @return
     */
    private double calculateSingleEntropy(TemporalProbabilityFeature inData, DataType inPrecedessor) {
        return getModel().getEntropyOfDistribution(inPrecedessor.getEventType(), inData.getEventType());
    }

    /**
     *
     */
    private void warnIfModelIsNotTrained() {
        if (this.getModel().getTransitionMatrix().size() == 0) {
            Logger.getLogger(this.getClass().getCanonicalName()).severe("warning: model not trained");
        }
    }

    public void setModel(T2GramModelI model) {
        this.model = model;
    }

    public T2GramModelI getModel() {
        return this.model;
    }

    /**
     * @param inData
     * @param precedessorData
     */
    private void warnIfEntropyHasNaNValue(double entropy, TemporalProbabilityFeature inData, DataType precedessorData) {
        if (Double.isNaN(entropy))
            Logger.getLogger(getClass().getCanonicalName()).info("entropy " + precedessorData.getEventType() + "--->" + inData.getEventType() + " " + entropy);
    }

    /**
     * @param inData
     * @param precedessorData
     */
    private void warnIfProbabilityHasNaNValue(double probability, TemporalProbabilityFeature inData, DataType precedessorData) {
        if (Double.isNaN(probability))
            Logger.getLogger(this.getClass().getCanonicalName()).info("prob " + precedessorData.getEventType() + "--->" + inData.getEventType() + " " + probability);
    }

    /**
     * @param inData
     * @param precedessor
     */
    private void warnIfDitstributionNotfound(TemporalProbabilityFeature inData, DataType precedessor) {
        ProbabilityDistribution distribution = getModel().getDistributionFor(precedessor.getEventType(), inData.getEventType());
        if (distribution == null) {
            Logger.getLogger(this.getClass().getCanonicalName()).info("no distribution for " + precedessor.getEventType() + "--->" + inData.getEventType());
        }
    }
}

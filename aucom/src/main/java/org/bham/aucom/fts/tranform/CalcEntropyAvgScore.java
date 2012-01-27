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
        Score out = calculateScore(inData);
        return out;

    }

    /**
     * @param inData
     * @return
     */
    protected Score calculateScore(TemporalProbabilityFeature inData) {
        double sum_entropy = calculateSumEntropy(inData);
        double denominator = calculateDenominator(sum_entropy);
        double scoreValue = 0.0d;

        scoreValue = calculateAbsoluteScoreValue(inData, denominator);
        scoreValue = normalize(inData, scoreValue);
        Score out = new SingleScore(inData, scoreValue);
        return out;
    }

    protected double calculateDenominator(double sum_entropy) {
        return Math.max(Math.pow(sum_entropy, 2), 0.00001);
    }

    protected double calculateAbsoluteScoreValue(TemporalProbabilityFeature currentData, double denominator) {
        double scoreValue = 0.0d;
        for (DataType precedessorData : currentData.getPredecessors()) {
            warnIfDitstributionNotfound(currentData, precedessorData);
            scoreValue += calculateSingleScoreValue(precedessorData, currentData, denominator);
        }
        return scoreValue;
    }

    protected double calculateSingleScoreValue(DataType predecessor, TemporalProbabilityFeature current, double denominator) {
        double probability = current.getProbabilityFor(predecessor);
        double entropy = calculateSingleEntropy(current, predecessor);
        double output;

        /*
        * Experiment: Only allow 17 -> 31, 31 -> 32, 32 -> 33
        * ADD:updater:updater.sa   17
        * ADD:chain1:updater.sa    31
        * ADD:chain2:updater.sa    32
        * DELETE:chain3:updater.sa 33
        */
        int pre = predecessor.getEventType();
        int cur = current.getEventType();
        if ((pre == 17 && cur == 31) || (pre == 31 && cur == 32) || (pre == 31 && cur == 33)) {
            warnIfProbabilityHasNaNValue(probability, current, predecessor);
            warnIfEntropyHasNaNValue(entropy, current, predecessor);
            output = alg_calculateSingleScore(probability, entropy, denominator);
            System.out.printf("[%d ---> %d] => %.2f\n", predecessor.getEventType(), current.getEventType(), output);
        } else {
            output = 0;
        }

        return output;
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
        for (int i = 0; i < values.length; i++) {
            sum += values[i];
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

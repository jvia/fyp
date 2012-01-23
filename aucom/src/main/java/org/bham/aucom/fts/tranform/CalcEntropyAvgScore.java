package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.SingleScore;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;

import java.util.logging.Logger;


public class CalcEntropyAvgScore extends AbstractAucomTransformNode<TemporalProbabilityFeature, Score> {
    private Model model;

    public CalcEntropyAvgScore() {
        super("CalcEntropyAvgScore");
    }

    @Override
    protected Score iTransform(TemporalProbabilityFeature arg0) throws Exception {
        Logger.getLogger(this.getClass().getCanonicalName()).info("CalcEntropyAvgScore iTransformCalled");
        return calculate(arg0);
    }

    Score calculate(TemporalProbabilityFeature inData) {
        warnIfModelIsNotTrained();
        return calculateScore(inData);

    }

    Score calculateScore(TemporalProbabilityFeature inData) {
        double sum_entropy = calculateSumEntropy(inData);
        double denominator = calculateDenominator(sum_entropy);
        double scoreValue;

        scoreValue = calculateAbsoluteScoreValue(inData, denominator);
        scoreValue = normalize(inData, scoreValue);

        return new SingleScore(inData, scoreValue);
    }

    double calculateDenominator(double sum_entropy) {
        return Math.max(Math.pow(sum_entropy, 2), 0.00001);
    }

    double calculateAbsoluteScoreValue(TemporalProbabilityFeature currentData, double denominator) {
        double scoreValue = 0.0d;

        for (DataType predecessorData : currentData.getPredecessors()) {
            warnIfDistributionNotFound(currentData, predecessorData);
            scoreValue += calculateSingleScoreValue(predecessorData, currentData, denominator);
        }

        return scoreValue;
    }

    double calculateSingleScoreValue(DataType predecessorData, TemporalProbabilityFeature currentData, double denominator) {
        double probability = currentData.getProbabilityFor(predecessorData);
        double entropy = calculateSingleEntropy(currentData, predecessorData);

        warnIfProbabilityHasNaNValue(probability, currentData, predecessorData);
        warnIfEntropyHasNaNValue(entropy, currentData, predecessorData);

        return alg_calculateSingleScore(probability, entropy, denominator);
    }

    double alg_calculateSingleScore(double probability, double entropy, double denominator) {
        return probability * (1 - Math.pow(entropy, 2) / denominator);
    }

    double normalize(TemporalProbabilityFeature inData, double absoluteScore) {
        double normalizedScore = absoluteScore;
        if (inData.getPredecessors().size() > 0) {
            normalizedScore = absoluteScore / inData.getPredecessors().size();
        }
        return normalizedScore;
    }

    double calculateSumEntropy(TemporalProbabilityFeature inData) {
        double[] singleEntropy = new double[inData.getPredecessors().size()];
        for (int i = 0; i < inData.getPredecessors().size(); i++) {
            DataType predecessor = inData.getPredecessors().get(i);
            singleEntropy[i] = calculateSingleEntropy(inData, predecessor);

        }

        return alg_calculateSumEntropy(singleEntropy);
    }

    double alg_calculateSumEntropy(double[] values) {
        double sum = 0.0d;
        for (double value : values) {
            sum += value;
        }
        return sum;
    }

    private double calculateSingleEntropy(TemporalProbabilityFeature inData, DataType inPrecedessor) {
        return getModel().getEntropyOfDistribution(inPrecedessor.getEventType(), inData.getEventType());
    }

    private void warnIfModelIsNotTrained() {
        if (this.getModel().getTransitionMatrix().size() == 0) {
            Logger.getLogger(this.getClass().getCanonicalName()).severe("warning: model not trained");
        }
    }

    public void setModel(Model model) {
        this.model = model;
    }

    Model getModel() {
        return this.model;
    }

    private void warnIfEntropyHasNaNValue(double entropy, TemporalProbabilityFeature inData, DataType predecessorData) {
        if (Double.isNaN(entropy))
            Logger.getLogger(getClass().getCanonicalName()).info("entropy " + predecessorData.getEventType() + "--->" + inData.getEventType() + " " + entropy);
    }

    private void warnIfProbabilityHasNaNValue(double probability, TemporalProbabilityFeature inData, DataType predecessorData) {
        if (Double.isNaN(probability))
            Logger.getLogger(this.getClass().getCanonicalName()).info("prob " + predecessorData.getEventType() + "--->" + inData.getEventType() + " " + probability);
    }

    private void warnIfDistributionNotFound(TemporalProbabilityFeature inData, DataType predecessor) {
        ProbabilityDistribution distribution = getModel().getDistributionFor(predecessor.getEventType(), inData.getEventType());
        if (distribution == null) {
            Logger.getLogger(this.getClass().getCanonicalName()).info("no distribution for " + predecessor.getEventType() + "--->" + inData.getEventType());
        }
    }
}

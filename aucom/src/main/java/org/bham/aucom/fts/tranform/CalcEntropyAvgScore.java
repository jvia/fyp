package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.SingleScore;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.diagnoser.Model;

import java.util.logging.Logger;


public class CalcEntropyAvgScore extends AbstractAucomTransformNode<TemporalProbabilityFeature, Score> {
    private Model model;
    private final Logger log = Logger.getLogger(getClass().getName());

    public CalcEntropyAvgScore() {
        super("CalcEntropyAvgScore");
        log.info("Initialized");
    }

    public void setModel(Model model) {
        this.model = model;
    }

    @Override
    protected Score iTransform(TemporalProbabilityFeature inData) throws Exception {
        log.fine("CalcEntropyAvgScore iTransformCalled");

        if (model.getTransitionMatrix().isEmpty())
            log.warning("Model not trained");

        double sum_entropy = calculateSumEntropy(inData);
        // Calculate the denominator, choosing the max between entropy^2 and a minimum value
        double denominator = Math.max(Math.pow(sum_entropy, 2), 0.00001);
        double scoreValue;

        scoreValue = calculateAbsoluteScoreValue(inData, denominator);
        scoreValue = normalize(inData, scoreValue);

        return new SingleScore(inData, scoreValue);
    }

    double calculateAbsoluteScoreValue(TemporalProbabilityFeature currentData, double denominator) {
        double scoreValue = 0.0d;

        for (DataType predecessorData : currentData.getPredecessors()) {
            if (null == model.getDistributionFor(predecessorData.getEventType(), currentData.getEventType()))
                log.warning(String.format("No distribution for [%d ---> %d]", predecessorData.getEventType(), currentData.getEventType()));
            scoreValue += calculateSingleScoreValue(predecessorData, currentData, denominator);
        }

        return scoreValue;
    }

    double calculateSingleScoreValue(DataType predecessor, TemporalProbabilityFeature current, double denominator) {
        double probability = current.getProbabilityFor(predecessor);
        double entropy = model.getEntropyOfDistribution(predecessor.getEventType(), current.getEventType());
        log.fine(String.format("Input :: [%d ---> %d]", predecessor.getEventType(), current.getEventType()));

        // Warn if probability or entropy has NaN value
        if (Double.isNaN(probability) || Double.isNaN(entropy))
            log.warning(String.format("%s [%d ---> %d]: %.2f", Double.isNaN(probability) ? "Probability" : "Entropy",
                                      predecessor.getEventType(), current.getEventType(), entropy));

        // Calculate the single score value
        return probability * (1 - Math.pow(entropy, 2) / denominator);
    }

    double normalize(TemporalProbabilityFeature inData, double absoluteScore) {
        double normalizedScore = absoluteScore;
        if (inData.getPredecessors().size() > 0)
            normalizedScore = absoluteScore / inData.getPredecessors().size();
        return normalizedScore;
    }

    double calculateSumEntropy(TemporalProbabilityFeature inData) {
        double sum = 0.0;
        for (int i = 0; i < inData.getPredecessors().size(); i++)
            sum += model.getEntropyOfDistribution(inData.getPredecessors().get(i).getEventType(),
                                                  inData.getEventType());
        return sum;
    }
}

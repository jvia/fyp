package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.SingleScore;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;

import java.util.logging.Logger;

/**
 * Calculates the score for the system. The score is calculated from a {@link
 * TemporalProbabilityFeature} input.
 * <p/>
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class CalcEntropyAvgScore extends AbstractAucomTranformNode<TemporalProbabilityFeature, Score> {

    private final transient Logger log = Logger.getLogger(getClass().getName());
    private T2GramModelI model;

    /**
     * Create the score calculating node.
     *
     * @param model the model used for the score calculation
     */
    public CalcEntropyAvgScore(T2GramModelI model) {
        super("CalcEntropyAvgScore");
        this.model = model;
    }

    /**
     * Create the node with no model.
     */
    public CalcEntropyAvgScore() {
        super("CalcEntropyAvgScore");
    }

    /**
     * Transform the input into the output. In this case, that means
     * transforming the current {@link TemporalProbabilityFeature} value into a
     * {@link Score} value.
     *
     * @param current the current input feature
     * @return the current system score
     * @throws Exception an error happened
     */
    @Override
    protected Score iTransform(TemporalProbabilityFeature current) throws Exception {
        if (getModel().isEmpty()) {
            log.severe("Model not trained");
        }

        double sum_entropy = calculateSumEntropy(current);
        // If entropy is too small, ensure minimal denominator
        double denominator = Math.max(Math.pow(sum_entropy, 2), 0.00001);
        double scoreValue;

        scoreValue = calculateAbsoluteScoreValue(current, denominator);
        scoreValue = normalize(current, scoreValue);
        return new SingleScore(current, scoreValue);
    }

    protected double calculateAbsoluteScoreValue(TemporalProbabilityFeature current, double denominator) {
        double scoreValue = 0.0;

        for (DataType predecessor : current.getPredecessors()) {
            if (getModel().getDistributionFor(predecessor.getEventType(), current.getEventType()) == null) {
                log.warning(String.format("No probability distribution for [%d ---> %d]",
                                          predecessor.getEventType(), current.getEventType()));
            }
            scoreValue += calculateSingleScoreValue(predecessor, current, denominator);
        }

        return scoreValue;
    }

    /**
     * Calculates the score for the given current event type and a given
     * predecessor event type.
     *
     * @param predecessor a previous system event
     * @param current     the current system event
     * @param denominator the entropy of the distribution (normalized not to
     *                    divide by 0)
     * @return the score
     */
    protected double calculateSingleScoreValue(DataType predecessor, TemporalProbabilityFeature current, double denominator) {
        double probability = current.getProbabilityFor(predecessor);
        double entropy = calculateSingleEntropy(current, predecessor);
        double output;

        // Warn if values are illegal
        if (Double.isNaN(probability)) {
            log.warning(String.format("Probability is NaN: [%d --> %d]",
                                      predecessor.getEventType(),
                                      current.getEventType()));
        }
        if (Double.isNaN(entropy)) {
            log.warning(String.format("Entropy is NaN: [%d --> %d]",
                                      predecessor.getEventType(),
                                      current.getEventType()));
        }

        // Calculate output
        output = probability * (1 - Math.pow(entropy, 2) / denominator);
        log.fine(String.format("[%d ---> %d] => %.2f", predecessor.getEventType(), current.getEventType(), output));

        return output;
    }

    /**
     * Normalizes the score.
     *
     * @param current  the current feture
     * @param rawScore the raw score
     * @return the normalized score
     */
    protected double normalize(TemporalProbabilityFeature current, double rawScore) {
        double normalizedScore = rawScore;
        if (!current.getPredecessors().isEmpty()) {
            normalizedScore = rawScore / current.getPredecessors().size();
        }
        return normalizedScore;
    }

    /**
     * Sums the total entropy for all events in the sliding window.
     *
     * @param current current event
     * @return total entropy
     */
    protected double calculateSumEntropy(TemporalProbabilityFeature current) {
        double sum = 0.0;
        for (int i = 0; i < current.getPredecessors().size(); i++) {
            sum += calculateSingleEntropy(current, current.getPredecessors().get(i));
        }
        return sum;
    }

    /**
     * Gets the entropy of the distribution.
     *
     * @param current     the current event type
     * @param predecessor the previous event type
     * @return the entropy of the relevant distribution
     */
    private double calculateSingleEntropy(TemporalProbabilityFeature current, DataType predecessor) {
        return getModel().getEntropyOfDistribution(predecessor.getEventType(), current.getEventType());
    }


    /**
     * Set the model for score calculation.
     *
     * @param model the model
     */
    public void setModel(T2GramModelI model) {
        this.model = model;
    }

    /**
     * Get the model used for score calculation.
     *
     * @return the model
     */
    public T2GramModelI getModel() {
        return model;
    }
}

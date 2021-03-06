package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.SingleScore;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;

import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

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
    public CalcEntropyAvgScore(final T2GramModelI model) {
        super("CalcEntropyAvgScore");
        this.model = model;
    }

    /**
     * Create the node with no model.
     */
    public CalcEntropyAvgScore() {
        super("CalcEntropyAvgScore");
        log.setLevel(Level.ALL);
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
    protected Score iTransform(final TemporalProbabilityFeature current)
            throws Exception {
        if (getModel().isEmpty()) {
            log.severe("Model not trained");
            throw new RuntimeException("Model not trained");
        }

        double sum_entropy = calculateSumEntropy(current);
        // If entropy is too small, ensure minimal denominator
        double denominator = Math.max(Math.pow(sum_entropy, 2), 0.00001);
        double scoreValue;

        scoreValue = calculateAbsoluteScoreValue(current, denominator);
        scoreValue = normalize(current, scoreValue);
        return new SingleScore(current, scoreValue);
    }

    protected double calculateAbsoluteScoreValue(
            final TemporalProbabilityFeature current,
            final double denominator) {
        double val = 0.0;

        for (DataType predecessor : current.getPredecessors()) {
            int p = predecessor.getEventType();
            int c = current.getEventType();
            if (getModel().getDistributionFor(p, c) == null) {
                log.warning(
                        format("No probability distribution for [%d ---> %d]",
                               predecessor.getEventType(),
                               current.getEventType()));
            }

            val += singleScoreValue(predecessor, current, denominator);
        }

        log.fine(format("Raw score: %.5f", val));
        return val;
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
    protected double singleScoreValue(final DataType predecessor,
                                      final TemporalProbabilityFeature current,
                                      final double denominator) {
        double probability = current.getProbabilityFor(predecessor);
        double entropy = singleEntropy(current, predecessor);
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
        log.fine(format("[%d ---> %d] => %.2f",
                        predecessor.getEventType(),
                        current.getEventType(),
                        output));
        return output;
    }

    /**
     * Normalizes the score.
     *
     * @param current  the current feature
     * @param rawScore the raw score
     * @return the normalized score
     */
    protected double normalize(final TemporalProbabilityFeature current,
                               final double rawScore) {
        double normalizedScore = rawScore;
        if (!current.getPredecessors().isEmpty()) {
            normalizedScore = rawScore / current.getPredecessors().size();
        }
        log.fine(format("Normalized score (raw / predecessor): " +
                        "%.2f / %d = %.5f", rawScore,
                        current.getPredecessors().size(), normalizedScore));
        return normalizedScore;
    }

    /**
     * Sums the total entropy for all events in the sliding window.
     *
     * @param current current event
     * @return total entropy
     */
    protected double calculateSumEntropy(
            final TemporalProbabilityFeature current) {
        double sum = 0.0;
        for (int i = 0; i < current.getPredecessors().size(); i++) {
            sum += singleEntropy(current, current.getPredecessors().get(i));
        }
        return sum;
    }

    /**
     * Gets the entropy of the distribution.
     *
     * @param c the current event type
     * @param p the previous event type
     * @return the entropy of the relevant distribution
     */
    private double singleEntropy(final TemporalProbabilityFeature c,
                                 final DataType p) {
        return getModel().getEntropyOfDistribution(
                p.getEventType(),
                c.getEventType());
    }


    /**
     * Set the model for score calculation.
     *
     * @param model the model
     */
    public void setModel(final T2GramModelI model) {
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

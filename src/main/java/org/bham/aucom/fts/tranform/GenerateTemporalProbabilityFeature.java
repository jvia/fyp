package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.util.Constants;

import java.util.HashMap;
import java.util.logging.Logger;

import static java.lang.String.format;

/**
 * This node generates the {@link TemporalProbabilityFeature} from a given
 * {@link TemporalDurationFeature}.
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk
 */
public class GenerateTemporalProbabilityFeature extends AbstractAucomTranformNode<TemporalDurationFeature, TemporalProbabilityFeature> {
    private static final long serialVersionUID = 3658564740012943868L;
    private T2GramModelI model;
    private transient final Logger log = Logger.getLogger(getClass().getName());

    /**
     * Create the node with an empty model.
     */
    public GenerateTemporalProbabilityFeature() {
        super("TestModel");
    }

    /**
     * Transform a {@link TemporalDurationFeature} into a {@link
     * TemporalProbabilityFeature}.
     *
     * @param tdf the temporal duration feature to transform
     * @return the temporal probability feature
     * @throws Exception something went wrong
     */
    @Override
    protected TemporalProbabilityFeature iTransform(TemporalDurationFeature tdf) throws Exception {
        if (checkIfModelNotTrained()) return null;

        HashMap<DataType, Double> probabilities = new HashMap<DataType, Double>();
        int eventType = tdf.getEventType();

        for (DataType predecessor : tdf.getPredecessors()) {
            long timespan = tdf.getDurationFor(predecessor);

            // Calculate normalized probability from the raw & max probabilities
            double normalized = normalize(getModel().getProbability(predecessor.getEventType(), eventType, timespan),
                                          getModel().getMaxProbabilityFor(predecessor.getEventType(), eventType));

            probabilities.put(predecessor, normalized);
            log.fine(format("P(%d|%d --> %d) = %f", timespan, predecessor.getEventType(), tdf.getEventType(), normalized));
        }

        return new TemporalProbabilityFeature(tdf, probabilities);
    }

    /**
     * Set the model to generate the {@link TemporalProbabilityFeature}.
     *
     * @param model the model to use
     */
    public void setModel(T2GramModelI model) {
        this.model = model;
    }

    /**
     * Get the model.
     *
     * @return the model
     */
    public T2GramModelI getModel() {
        return this.model;
    }


    /**
     * Checks if the model has not yet been trained.
     *
     * @return true if model not yet trained
     */
    private boolean checkIfModelNotTrained() {
        return model == null || !model.isTrained();
    }

    /**
     * Normalize the probability.
     *
     * @param raw the raw probability
     * @param max the max probability
     * @return the normalized probability
     */
    private double normalize(double raw, double max) {
        if (max != Constants.LOWESTPROBABILITY)
            raw /= max;
        return Math.min(raw, 1.0);
    }
}
package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.TemporalDurationFeature;

import java.util.logging.Logger;

/**
 * This node transforms system events into its {@link TemporalDurationFeature}
 * representation.
 * <p/>
 * TODO :: Put a list which holds the history of elements
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk
 */
public class GenerateTemporalDurationFeature extends AbstractAucomTranformNode<DataType, TemporalDurationFeature> {
    private static final long serialVersionUID = 3563291160956922501L;
    private final transient Logger log = Logger.getLogger(getClass().getName());
    private transient TemporalDurationFeatureGenerator generator;

    /**
     * Create the transformation node.
     */
    public GenerateTemporalDurationFeature() {
        // TODO :: see if generator can be passed in as constructor parameter
        super("GenerateDurationFeature");
        generator = null;
    }

    /**
     * Transforms system input into a {@link TemporalDurationFeature}.
     *
     * @param input the system event
     * @return the temporal duration feature
     * @throws Exception something went wrong
     */
    @Override
    protected TemporalDurationFeature iTransform(DataType input) throws Exception {
        if (getGenerator() == null) {
            log.severe("Generator is missing. Cannot generate TemporalDurationFeature ");
            return null;
        }

        TemporalDurationFeature feature = getGenerator().generateFeature(input);
        if (feature == null) {
            log.severe("TemporalDurationFeature is null");
        }

        log.fine("Returning TemporalDurationFeature ");
        return feature;
    }

    /**
     * Resets the {@link TemporalDurationFeatureGenerator}.
     */
    public void reset() {
        if (getGenerator() != null) {
            getGenerator().initializeLastOccurances();
        }
    }

    /**
     * Set the generator.
     *
     * @param generator the generator to use.
     */
    public synchronized void setGenerator(TemporalDurationFeatureGenerator generator) {
        this.generator = generator;
    }

    /**
     * Get the generator.
     *
     * @return the generator.
     */
    public synchronized TemporalDurationFeatureGenerator getGenerator() {
        // TODO :: does this really need to be synchronized?
        return generator;
    }
}

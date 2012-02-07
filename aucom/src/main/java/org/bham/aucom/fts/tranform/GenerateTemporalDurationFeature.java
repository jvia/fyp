package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.TemporalDurationFeature;

import java.util.logging.Logger;

public class GenerateTemporalDurationFeature extends AbstractAucomTranformNode<DataType, TemporalDurationFeature> {

    private TemporalDurationFeatureGenerator generator;
    private final transient Logger log = Logger.getLogger(getClass().getName());

    public GenerateTemporalDurationFeature() {
        super("GenerateDurationFeature");
        generator = null;
    }

    @Override
    protected TemporalDurationFeature iTransform(DataType input) throws Exception {
        if (generator == null) {
            log.severe("Generator is missing, cannot generate TemporalDurationFeature ");
            return null;
        }

        TemporalDurationFeature feature = getGenerator().generateFeature(input);
        if (feature == null) log.severe("TemporalDurationFeature is null");

        log.fine("Returning TemporalDurationFeature ");
        return feature;
    }

    public void reset() {
        if (getGenerator() != null)
            getGenerator().initializeLastOccurances();
    }

    public synchronized void setGenerator(TemporalDurationFeatureGenerator generator) {
        this.generator = generator;
    }

    public synchronized TemporalDurationFeatureGenerator getGenerator() {
        return generator;
    }
}

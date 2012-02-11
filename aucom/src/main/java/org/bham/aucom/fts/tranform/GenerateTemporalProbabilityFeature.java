package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.util.Constants;

import java.util.HashMap;
import java.util.logging.Logger;

import static java.lang.String.format;

public class GenerateTemporalProbabilityFeature extends AbstractAucomTranformNode<TemporalDurationFeature, TemporalProbabilityFeature> {
    private T2GramModelI model = null;
    private transient final Logger log = Logger.getLogger(getClass().getName());

    public GenerateTemporalProbabilityFeature() {
        super("TestModel");
    }

    @Override
    protected TemporalProbabilityFeature iTransform(TemporalDurationFeature arg0) throws Exception {
        if (checkIfModelNotTrained()) return null;
        return generate(arg0);
    }

    protected TemporalProbabilityFeature generate(TemporalDurationFeature dataToTest) {
        int eventTypeToTest = dataToTest.getEventType();

        double maximalProbabilityForPredecessor;
        double absolutProbabilityForPredecessor;
        double normalizedProbabilityOfPredecessor;

        HashMap<DataType, Double> datatypeToProbabilities = new HashMap<DataType, Double>();
        long timespanToPredecessor;

        for (DataType predecessor : dataToTest.getPredecessors()) {
            try {
                timespanToPredecessor = dataToTest.getDurationFor(predecessor);

                absolutProbabilityForPredecessor = getModel().getProbability(predecessor.getEventType(), eventTypeToTest, timespanToPredecessor);
                maximalProbabilityForPredecessor = getModel().getMaxProbabilityFor(predecessor.getEventType(), eventTypeToTest);
                normalizedProbabilityOfPredecessor = normalizePrecedessorProbability(absolutProbabilityForPredecessor, maximalProbabilityForPredecessor);
                log.finer(format("Max probability: %f; Absolute probability: %f; Normalized Probability: %f; Difference: %f",
                                 maximalProbabilityForPredecessor, absolutProbabilityForPredecessor, normalizedProbabilityOfPredecessor,
                                 normalizedProbabilityOfPredecessor - absolutProbabilityForPredecessor));
                datatypeToProbabilities.put(predecessor, normalizedProbabilityOfPredecessor);
                log.fine(format("P(%d|%d --> %d) = %f", timespanToPredecessor, predecessor.getEventType(),
                                dataToTest.getEventType(), normalizedProbabilityOfPredecessor));
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        log.info("Tested: " + dataToTest);
        return new TemporalProbabilityFeature(dataToTest, datatypeToProbabilities);
    }

    private boolean checkIfModelNotTrained() {
        return model == null || !model.isTrained();
    }

    private double normalizePrecedessorProbability(double probabilityOfPrecedessor, double maxProbability) {
        double normalizedPredecessorProbability = probabilityOfPrecedessor;

        if (maxProbability == Constants.LOWESTPROBABILITY) {
            System.out.println("TestModel: maxProbability is " + maxProbability);
        } else {
            normalizedPredecessorProbability /= maxProbability;
        }

        normalizedPredecessorProbability = Math.min(normalizedPredecessorProbability, 1.0);
        return normalizedPredecessorProbability;
    }

    public void setModel(T2GramModelI model) {
        this.model = model;
    }

    public T2GramModelI getModel() {
        return this.model;
    }

}
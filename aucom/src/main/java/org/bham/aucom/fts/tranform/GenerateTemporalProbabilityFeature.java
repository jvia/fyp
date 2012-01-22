package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.util.Constants;

import java.util.HashMap;
import java.util.logging.Logger;


public class GenerateTemporalProbabilityFeature extends AbstractAucomTransformNode<TemporalDurationFeature, TemporalProbabilityFeature> {
    private T2GramModelI model = null;

    public GenerateTemporalProbabilityFeature() {
        super("TestModel");
    }

    @Override
    protected TemporalProbabilityFeature iTransform(TemporalDurationFeature arg0) throws Exception {
        TemporalProbabilityFeature f;
        if (checkIfModelNotTrained()) {
            return null;
        }
        f = generate(arg0);
        return f;
    }

    protected TemporalProbabilityFeature generate(TemporalDurationFeature dataToTest) {
        int eventTypeToTest = dataToTest.getEventType();

        double maximalProbabilityForPredecessor;
        double absoluteProbabilityForPredecessor;
        double normalizedProbabilityOfPredecessor;

        HashMap<DataType, Double> dataTypeToProbabilities = new HashMap<DataType, Double>();
        long timeSpanToPredecessor;
        for (DataType predecessor : dataToTest.getPredecessors()) {
            try {
                timeSpanToPredecessor = dataToTest.getDurationFor(predecessor);

                absoluteProbabilityForPredecessor = getModel().getProbability(predecessor.getEventType(), eventTypeToTest, timeSpanToPredecessor);
                maximalProbabilityForPredecessor = getModel().getMaxProbabilityFor(predecessor.getEventType(), eventTypeToTest);
                normalizedProbabilityOfPredecessor = normalizePredecessorProbability(absoluteProbabilityForPredecessor, maximalProbabilityForPredecessor);

                String message = "max:" + maximalProbabilityForPredecessor + " prob:" + absoluteProbabilityForPredecessor + " normalized prob:" + normalizedProbabilityOfPredecessor + " diff:"
                                 + (normalizedProbabilityOfPredecessor - absoluteProbabilityForPredecessor);
                Logger.getLogger(this.getClass().getCanonicalName()).info(message);

                dataTypeToProbabilities.put(predecessor, normalizedProbabilityOfPredecessor);

                try {
                    Logger.getLogger(this.getClass().getCanonicalName()).info(
                            "from: " + predecessor + " to " + predecessor + "timespan " + timeSpanToPredecessor + " probability " + normalizedProbabilityOfPredecessor);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }

        Logger.getLogger(this.getClass().getCanonicalName()).info("tested " + dataToTest);
        return new TemporalProbabilityFeature(dataToTest, dataTypeToProbabilities);
    }


    private boolean checkIfModelNotTrained() {
        return model == null || !model.isTrained();
    }

    private double normalizePredecessorProbability(double probabilityOfPredecessor, double maxProbability) {
        double normalizedPredecessorProbability;
        normalizedPredecessorProbability = probabilityOfPredecessor;
        if (maxProbability == Constants.LOWEST_PROBABILITY) {
            System.out.println("TestModel: maxProbability is " + maxProbability);
        } else {
            normalizedPredecessorProbability /= maxProbability;
        }
        normalizedPredecessorProbability = Math.min(normalizedPredecessorProbability, 1.0d);
        return normalizedPredecessorProbability;
    }

    public void setModel(T2GramModelI model) {
        this.model = model;
    }

    public T2GramModelI getModel() {
        return this.model;
    }

}
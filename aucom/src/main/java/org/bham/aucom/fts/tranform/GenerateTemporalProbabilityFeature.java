package org.bham.aucom.fts.tranform;

import java.util.HashMap;
import java.util.logging.Logger;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.util.Constants;


public class GenerateTemporalProbabilityFeature extends AbstractAucomTranformNode<TemporalDurationFeature, TemporalProbabilityFeature> {
    private T2GramModelI model = null;

    public GenerateTemporalProbabilityFeature() {
        super("TestModel");
    }

    @Override
    protected TemporalProbabilityFeature iTransform(TemporalDurationFeature arg0) throws Exception
    {
        TemporalProbabilityFeature f = null;
        if (checkIfModelNotTrained()) {
            return null;
        }
        f = generate(arg0);
        return f;
    }

    protected TemporalProbabilityFeature generate(TemporalDurationFeature dataToTest)
    {
        int eventTypeToTest = dataToTest.getEventType();

        double maximalProbabilityForPredecessor = 0.0f;
        double absolutProbabilityForPredecessor = 0.0f;
        double normalizedProbabilityOfPredecessor = 0.0f;

        HashMap<DataType, Double> datatypeToProbabilities = new HashMap<DataType, Double>();
        long timespanToPredecessor = 0l;
        for (DataType predecessor : dataToTest.getPredecessors()) {
            try {
                timespanToPredecessor = dataToTest.getDurationFor(predecessor);

                absolutProbabilityForPredecessor = getModel().getProbability(predecessor.getEventType(), eventTypeToTest, timespanToPredecessor);
                maximalProbabilityForPredecessor = getModel().getMaxProbabilityFor(predecessor.getEventType(), eventTypeToTest);
                normalizedProbabilityOfPredecessor = normalizePrecedessorProbability(absolutProbabilityForPredecessor, maximalProbabilityForPredecessor);

                String message = "max:" + maximalProbabilityForPredecessor + " prob:" + absolutProbabilityForPredecessor + " normalized prob:" + normalizedProbabilityOfPredecessor + " diff:"
                        + (normalizedProbabilityOfPredecessor - absolutProbabilityForPredecessor);
                Logger.getLogger(this.getClass().getCanonicalName()).info(message);
                
                datatypeToProbabilities.put(predecessor, Double.valueOf(normalizedProbabilityOfPredecessor));
                
                try {
                    Logger.getLogger(this.getClass().getCanonicalName()).info(
                            "from: " + predecessor + " to " + predecessor + "timespan " + timespanToPredecessor + " probability " + normalizedProbabilityOfPredecessor);
                } catch (NullPointerException e) {
                    e.printStackTrace();
                }
            } catch (Exception exception) {
                exception.printStackTrace();
            }
        }
        
        Logger.getLogger(this.getClass().getCanonicalName()).info("tested " + dataToTest);
        return new TemporalProbabilityFeature(dataToTest, datatypeToProbabilities);
    }

    /**
	 * 
	 */
    private boolean checkIfModelNotTrained()
    {
        return model == null || !model.isTrained();
    }

    /**
     * @param probabilityOfPrecedessor
     * @param maxProbability
     * @return
     */
    private double normalizePrecedessorProbability(double probabilityOfPrecedessor, double maxProbability)
    {
        double normalizedPredecessorProbability = 0.0d;
        normalizedPredecessorProbability = probabilityOfPrecedessor;
        if (maxProbability == Constants.LOWESTPROBABILITY) {
            System.out.println("TestModel: maxProbability is " + maxProbability);
        } else {
            normalizedPredecessorProbability /= maxProbability;
        }
        normalizedPredecessorProbability = Math.min(normalizedPredecessorProbability, 1.0d);
        return normalizedPredecessorProbability;
    }

    public void setModel(T2GramModelI model)
    {
        this.model = model;
    }

    public T2GramModelI getModel()
    {
        return this.model;
    }

}
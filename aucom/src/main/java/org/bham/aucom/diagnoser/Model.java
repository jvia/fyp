package org.bham.aucom.diagnoser;

import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.encoder.Encoder;
import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.diagnoser.t2gram.ProbabilityFactory;
import org.bham.aucom.fts.AbstractLinkableNode;
import org.bham.aucom.util.HashMatrix;
import org.bham.aucom.util.Tuple;

import java.io.Serializable;
import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Logger;

import static org.bham.aucom.util.Constants.LOWEST_PROBABILITY;


public class Model extends AbstractLinkableNode implements Serializable{
    private static final long serialVersionUID = 1L;
    private static final boolean WARN_ON_LOWEST_PROBABILITY = false;

    private String name;
    private HashMatrix<Integer, Integer, ProbabilityDistribution> transitionMatrix;
    private ProbabilityFactory factory;


    public Model(ProbabilityFactory inFactory) {
        super(UUID.randomUUID());
        transitionMatrix = new HashMatrix<Integer, Integer, ProbabilityDistribution>();
        setName("t2gramModel");
        factory = inFactory;
    }

    public Collection<Integer> getDimensions() {
        Collection<Integer> dimensions = new HashSet<Integer>();

        for (Tuple<Integer, Integer> key : transitionMatrix.keySet()) {
            Logger.getLogger(getClass().getCanonicalName()).info(key.toString());
            dimensions.add(key.getFirst());
            dimensions.add(key.getSecond());
        }

        return dimensions;
    }

    
    public int getNumberDistributions() {
        int numberAvailableDistributions = 0;

        for (Tuple<Integer, Integer> key : transitionMatrix.keySet())
            if (transitionMatrix.get(key.getFirst(), key.getSecond()) != null)
                numberAvailableDistributions++;

        return numberAvailableDistributions;
    }

    
    public boolean hasDistributionFor(int from, int to) {
        return getTransitionMatrix().containsKey(from, to);
    }

    
    public ProbabilityDistribution getDistributionFor(Integer from, Integer to) {
        return getTransitionMatrix().get(from, to);
    }

    
    public double getEntropyOfDistribution(int indexOne, int indexTwo) {
        ProbabilityDistribution distribution = getTransitionMatrix().get(indexOne, indexTwo);
        if (distribution != null)
            return distribution.getEntropy();

        String decodedIndexOne = "";
        for (DomainFeature f : Encoder.getInstance().decode(indexOne))
            decodedIndexOne += f.toString() + " ";

        String decodedIndexTwo = "";
        for (DomainFeature f : Encoder.getInstance().decode(indexTwo))
            decodedIndexTwo += f.toString() + " ";

        Logger.getLogger(getClass().getCanonicalName()).severe("CALC_ENTROPY: distribution for " + decodedIndexOne + "(" + indexOne + ")  to " + decodedIndexTwo + "(" + indexTwo + ") not found");
        return 0.0f;
    }

    
    public double getMaxProbabilityFor(int from, int to) {
        ProbabilityDistribution distribution = getDistributionFor(from, to);
        if (distribution != null) return distribution.getMaxProbability();
        Logger.getLogger(getClass().getCanonicalName()).warning(String.format("Max probability for %d to %d is missing", from, to));
        return LOWEST_PROBABILITY;
    }

    
    public double getProbability(int from, int to, long timeSpan) {
        double probability;
        Logger.getLogger(getClass().getCanonicalName()).info("calculating probability for " + "from " + from + " to " + to);
        if (hasDistributionFor(from, to)) {
            ProbabilityDistribution distribution = getDistributionFor(from, to);
            probability = distribution.getProbability(timeSpan);
            Logger.getLogger(getClass().getCanonicalName()).info("from " + from + " to " + to + "present, prob is " + probability);
        } else {
            probability = LOWEST_PROBABILITY;
            if (WARN_ON_LOWEST_PROBABILITY) {
                System.out.println("DurationProbabilityModel: from " + from + " to " + to + "NOT present, returning " + LOWEST_PROBABILITY);
            }
        }
        return probability;
    }

    
    public void addDistribution(Integer from, Integer to, ProbabilityDistribution dist) {
        getTransitionMatrix().put(from, to, dist);
    }

    
    public int size() {
        return getTransitionMatrix().size();
    }

    
    public boolean isTrained() {
        return getTransitionMatrix().size() > 0;
    }

    
    public HashMatrix<Integer, Integer, ProbabilityDistribution> getTransitionMatrix() {
        return transitionMatrix;
    }

    
    public String toString() {
        return "name: " + getName() + ", trained: " + isTrained();
    }

    protected void setName(String name) {
        this.name = name;
    }

    
    public String getName() {
        return name;
    }

    
    public ProbabilityFactory getDistributionFactory() {
        return factory;
    }}
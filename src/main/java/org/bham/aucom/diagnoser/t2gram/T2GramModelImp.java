package org.bham.aucom.diagnoser.t2gram;

import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.encoder.Encoder;
import org.bham.aucom.fts.AbstractLinkableNode;
import org.bham.aucom.util.HashMatrix;
import org.bham.aucom.util.Tuple;

import java.util.Collection;
import java.util.HashSet;
import java.util.UUID;
import java.util.logging.Logger;

import static org.bham.aucom.util.Constants.LOWESTPROBABILITY;

public class T2GramModelImp extends AbstractLinkableNode implements T2GramModelI {
    private static final long serialVersionUID = 1L;
    private String name;
    private final HashMatrix<Integer, Integer, ProbabilityDistribution> transitionMatrix;
    private final ProbabilityFactory factory;
    private final transient Logger log = Logger.getLogger(getClass().getName());

    public T2GramModelImp(ProbabilityFactory inFactory) {
        super(UUID.randomUUID());
        transitionMatrix = new HashMatrix<Integer, Integer, ProbabilityDistribution>();
        setName("t2gramModel");
        factory = inFactory;
    }

    @Override
    public Collection<Integer> getDimensions() {
        Collection<Integer> dimensions = new HashSet<Integer>();
        for (Tuple<Integer, Integer> key : this.transitionMatrix.keySet()) {
            //log.info(key + "");
            dimensions.add(key.getFirstElement());
            dimensions.add(key.getSecondElement());
        }
        return dimensions;
    }

    @Override
    public int getNumberDistirbutions() {
        int numberavailableDistributions = 0;
        for (Tuple<Integer, Integer> key : this.transitionMatrix.keySet()) {
            if (this.transitionMatrix.get(key.getFirstElement(), key.getSecondElement()) != null) {
                numberavailableDistributions++;
            }
        }
        return numberavailableDistributions;
    }

    @Override
    public boolean hasDistributionFor(Integer from, Integer to) {
        return transitionMatrix.containsKey(from, to);
    }

    @Override
    public ProbabilityDistribution getDistributionFor(Integer from, Integer to) {
        return transitionMatrix.get(from, to);
    }

    @Override
    public double getEntropyOfDistribution(int indexOne, int indexTwo) {
        ProbabilityDistribution distribution = transitionMatrix.get(indexOne, indexTwo);
        if (distribution != null) {
            return distribution.getEntropy();
        }
        StringBuilder decodedIndexOne = new StringBuilder();
        for (DomainFeature f : Encoder.getInstance().decode(indexOne)) {
            decodedIndexOne.append(f).append(" ");
        }
        StringBuilder decodedIndexTwo = new StringBuilder();
        for (DomainFeature f : Encoder.getInstance().decode(indexTwo)) {
            decodedIndexTwo.append(f).append(" ");
        }
        log.severe(String.format("CALC_ENTROPY: H(%d,%d) = H(%s,%s) = Ø",
                                 indexOne, indexTwo, decodedIndexOne, decodedIndexTwo));
        return 0.0f;
    }

    @Override
    public double getMaxProbabilityFor(int from, int to) {
        ProbabilityDistribution distribution = getDistributionFor(from, to);
        if (distribution != null) {
            return distribution.getMaxProbability();
        }
        log.warning(String.format("Probability Distribution for [%d --> %d] is missing.", from, to));
        return LOWESTPROBABILITY;
    }

    @Override
    public double getProbability(int from, int to, long timespan) {
        double probability;

        if (hasDistributionFor(from, to)) {
            ProbabilityDistribution distribution = getDistributionFor(from, to);
            probability = distribution.getProbability(timespan);
        } else {
            probability = LOWESTPROBABILITY;
            log.warning(String.format("P(%d|%d,%d) = Ø. Using %f instead", timespan, to, from, probability));
        }
        log.fine(String.format("P(timespan|to,from) => P(%d ms|%d,%d) = %.5f",
                               timespan, to, from, probability));
        return probability;
    }

    @Override
    public void addDistribution(Integer from, Integer to, ProbabilityDistribution dist) {
        transitionMatrix.put(from, to, dist);
    }

    @Override
    public boolean isEmpty() {
        return size() == 0;
    }

    @Override
    public int size() {
        return transitionMatrix.size();
    }

    @Override
    public boolean isTrained() {
        return transitionMatrix.size() > 0;
    }

    @Override
    public HashMatrix<Integer, Integer, ProbabilityDistribution> getTransitionMatrix() {
        return this.transitionMatrix;
    }

    @Override
    public String toString() {
        return String.format("Name: %s; Trained: %b, Dimensions: %s", this.getName(), isTrained(), getDimensions());
    }

    protected void setName(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    /**
     * Get the probability factory.
     *
     * @return the probability factory
     */
    @Override
    public ProbabilityFactory getDistributionFactory() {
        return factory;
    }

    /**
     * Unused.
     */
    @Override
    public void addModelListener() { }

    /**
     * Unused.
     */
    @Override
    public void removeModelListener() { }
}

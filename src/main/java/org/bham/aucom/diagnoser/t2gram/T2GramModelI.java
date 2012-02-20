package org.bham.aucom.diagnoser.t2gram;

import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.util.HashMatrix;

import java.util.Collection;

public interface T2GramModelI extends Model {
    public Collection<Integer> getDimensions();

    public ProbabilityFactory getDistributionFactory();

    public int getNumberDistirbutions();

    public boolean hasDistributionFor(Integer from, Integer to);

    public ProbabilityDistribution getDistributionFor(Integer from, Integer to);

    public double getEntropyOfDistribution(int indexOne, int indexTwo);

    public double getMaxProbabilityFor(int from, int to);

    public double getProbability(int from, int to, long timespan);

    public void addDistribution(Integer from, Integer to, ProbabilityDistribution dist);

    public boolean isEmpty();

    public int size();

    public HashMatrix<Integer, Integer, ProbabilityDistribution> getTransitionMatrix();
}

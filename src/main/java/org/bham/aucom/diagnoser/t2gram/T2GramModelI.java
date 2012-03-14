package org.bham.aucom.diagnoser.t2gram;

import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.util.HashMatrix;

import java.util.Collection;

public interface T2GramModelI extends Model {
    public Collection<Integer> getDimensions();

    public ProbabilityFactory getDistributionFactory();

    public int getNumberDistributions();

    public boolean hasDistributionFor(int from, int to);

    public ProbabilityDistribution getDistributionFor(int from, int to);

    public double getEntropyOfDistribution(int indexOne, int indexTwo);

    public double getMaxProbabilityFor(int from, int to);

    public double getProbability(int from, int to, long timespan);

    public void addDistribution(int from, int to, ProbabilityDistribution dist);

    public boolean isEmpty();

    public int size();

    public HashMatrix<Integer, Integer, ProbabilityDistribution> getTransitionMatrix();
}

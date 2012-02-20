package org.bham.aucom.models.probability;

import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.diagnoser.t2gram.ProbabilityFactory;

import java.util.UUID;

public class KDELogProbabilityDistributionFactory extends ProbabilityFactory {
    private static final long serialVersionUID = 0L;
    private final String name = "logkde";
    UUID id;

    public KDELogProbabilityDistributionFactory() {
        this.id = UUID.randomUUID();
    }

    @Override
    public ProbabilityDistribution create(double[] trainingValues) {
        return new KDELogProbabilityDistribution(trainingValues);
    }

    @Override
    public String toString() {
        return this.getName();
    }

    public String getName() {
        return this.name;
    }

    @Override
    public ProbabilityDistribution create() {
        double[] trainingValues = {};
        return new KDELogProbabilityDistribution(trainingValues);
    }

}

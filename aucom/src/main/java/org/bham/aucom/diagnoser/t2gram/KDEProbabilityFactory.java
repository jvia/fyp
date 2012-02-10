package org.bham.aucom.diagnoser.t2gram;

import org.bham.aucom.models.probability.KernelDensityDistribution;

import java.util.UUID;
import java.util.logging.Logger;

/**
 * Factory class to create a Kernel density estimator.
 */
public class KDEProbabilityFactory extends ProbabilityFactory {
    private static final long serialVersionUID = 0L;
    private final transient Logger log = Logger.getLogger(getClass().getName());

    private final String name = "kde";
    private final UUID id;

    public KDEProbabilityFactory() {
        this(1.0);
    }

    public KDEProbabilityFactory(Double precision) {
        this.setPrecision(precision);
        this.id = UUID.randomUUID();
    }

    @Override
    public ProbabilityDistribution create(double[] trainingValues) {
        return new KernelDensityDistribution(getPrecision(), trainingValues);
    }

    private void setPrecision(double precision) {
        parameters.put("precision", precision);
    }

    private double getPrecision() {
        return parameters.get("precision").doubleValue();
    }

    @Override
    public String toString() {
        return String.format("Type: %s; Precision: %s", name, parameters.get("precision"));
    }

    public String getName() {
        return name;
    }

    @Override
    public ProbabilityDistribution create() {
        return new KernelDensityDistribution(getPrecision());
    }
}

package org.bham.aucom.diagnoser.t2gram;

import java.util.UUID;

import org.bham.aucom.models.probability.KernelDensityDistribution;

public class KDEProbabilityFactory extends ProbabilityFactory {
	private static final long serialVersionUID = 0L;

	String name = "kde";
	UUID id;

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
		this.parameters.put("precision", precision);
	}

	private double getPrecision() {
		return this.parameters.get("precision").doubleValue();
	}

	@Override
	public String toString() {
		return "kde;" + this.parameters.get("precision");
	}

	public String getName() {
		return this.name;
	}

	@Override
	public ProbabilityDistribution create() {
		 return new KernelDensityDistribution(getPrecision());
	}
}

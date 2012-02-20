package org.bham.aucom.gui;

import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.diagnoser.t2gram.ProbabilityFactory;
import org.bham.aucom.models.probability.HistogramDistribution;

public class HistogramProbabilityFactory extends ProbabilityFactory {
    private static final long serialVersionUID = 0L;

    private double binSize;

    public HistogramProbabilityFactory(double inBinsize) {
        this.binSize = inBinsize;
    }

    @Override
    public ProbabilityDistribution create(double[] trainingValues) {
        HistogramDistribution distribution = new HistogramDistribution("", this.binSize);
        distribution.update(trainingValues);
        return distribution;
    }

    @Override
    public ProbabilityDistribution create() {
        return new HistogramDistribution("", this.binSize);
    }
}

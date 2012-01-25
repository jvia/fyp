package org.bham.aucom.models.probability;

import java.util.ArrayList;


public class EMOptimizedKernelDensityDistribution extends KernelDensityDistribution {
    private static final long serialVersionUID = 1L;
    private final ArrayList<Double> trainingValues;

    public EMOptimizedKernelDensityDistribution() {
        super(1);
        this.trainingValues = new ArrayList<Double>();
    }

    public EMOptimizedKernelDensityDistribution(double[] trainingValues) {
        super(1, trainingValues);
        this.trainingValues = new ArrayList<Double>();
    }
}

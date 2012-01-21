package org.bham.aucom.models.probability;

import java.util.ArrayList;


public class EMOptimizedKernelDensityDistribution  extends KernelDensityDistribution{
	private static final long serialVersionUID = 1L;
	ArrayList<Double> trainingValues;
	public EMOptimizedKernelDensityDistribution() {
		super(1);
		this.trainingValues = new ArrayList<Double>();
	}
	public EMOptimizedKernelDensityDistribution(double[] trainingValues) {
		super(1,trainingValues);
		this.trainingValues = new ArrayList<Double>();
		optimize();
	}
	@Override
	public void update(double[] val) {
		super.update(val);
		
	}
	/*
	 * Uses an EM algorithm to optimize the precision value of the 
	 * Kernelestimator for the given training data
	 */
	public void optimize(){
		
	}

}

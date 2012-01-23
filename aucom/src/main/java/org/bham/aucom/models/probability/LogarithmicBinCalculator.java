package org.bham.aucom.models.probability;

import org.bham.aucom.util.BinCalculator;

public class LogarithmicBinCalculator implements BinCalculator {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1231341324234L;
	private final double basis;
	public LogarithmicBinCalculator(double basis) {
		this.basis = basis;
	}
	@Override
	public int calculateBin(double value) {
		if(value < 1.0d)
			return 0;
		return (int)Math.floor(log(value)) + 1;
	} 

	@Override
	public double getHighBoundary(double value) {
		if(value < 1.0d)
			return Math.pow(basis, 0);
		return Math.pow(this.basis, Math.ceil(log(value)));
	}

	@Override
	public double getLowBoundary(double value) {
		if(value < 1.0d)
			return 0;
		return Math.pow(this.basis, Math.floor(log(value)));
	}
	double log(double value){
		return Math.log(value)/Math.log(this.basis);
	}

}

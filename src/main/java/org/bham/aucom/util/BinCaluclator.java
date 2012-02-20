package org.bham.aucom.util;

import java.io.Serializable;

public interface BinCaluclator extends Serializable{
	public int calculateBin(double value);
	public double getLowBoundary(double value);
	public double getHighBoundary(double value);
}

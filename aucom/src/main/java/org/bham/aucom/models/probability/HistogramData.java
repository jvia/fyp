package org.bham.aucom.models.probability;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Logger;

import org.bham.aucom.util.BinCaluclator;

public class HistogramData implements Serializable {
	private static final long serialVersionUID = 0L;
	private double binSize;
	private LinkedHashMap<Integer, HistogramBin> content;
	BinCaluclator calculator;
	boolean isDirty;
	int sumValues;

	public HistogramData(double inBinSize) {
		this(new LinkedHashMap<Integer, HistogramBin>(), inBinSize);
	}

	public HistogramData(LinkedHashMap<Integer, HistogramBin> in, double inBinSize) {
		setBins(in);
		this.calculator = new LinearBinCalculator(inBinSize);
		//calculator = new LogarithmicBinCalculator(inBinSize);
		this.isDirty = true;
		setBinSize(inBinSize);
	}

	public int getBinNumberForValue(double value) {
		return this.calculator.calculateBin(value);
	}
	public void severe(String  msg){
		Logger.getLogger(this.getClass().getName()).severe(msg);
	}

	public void info(String info) {
		Logger.getLogger(this.getClass().getCanonicalName()).info(info);
	}
	public int getNearestBinNumberForValue(double value){
		int binNumber = this.calculator.calculateBin(value);
		if(containsBinWithNumber(binNumber)){
			info("bin with binNumber "+binNumber+" is present");
			return binNumber;
		}
		info("bin "+binNumber+" from " +getNumBins()+ " bins is wrong, searching for nearest bin");
		int lowerBin = binNumber;
		int higherBin = Math.min(getNumBins()-1, binNumber);
		boolean lowerBinFound = false;
		boolean higherBinFound = false;
		while(!lowerBinFound && lowerBin>0){
			lowerBin--;
			lowerBinFound = containsBinWithNumber(binNumber);
		}
		if(lowerBinFound)
			info("lowerBin is " + lowerBin);
		else
			info("no lower bin found");
		while(!higherBinFound && higherBin<getNumBins()-1){
			higherBin++;
			higherBinFound = containsBinWithNumber(binNumber);
		}
		if(higherBinFound)
			info("lowerBin is " + higherBin);
		else
			info("no lower bin found");
		if(Math.abs(binNumber-lowerBin) == Math.abs(binNumber-higherBin)){
			double splitValue = this.binSize/2.0d;
			double modulo = value%this.binSize;
			info("distance to lower bin and higher bin is equal, decinding based on splitValue " +splitValue + "which to choose");
			if(modulo< splitValue && lowerBinFound){
				binNumber = lowerBin;
				info("lower bin "+lowerBin+" is valid");
			}else if(higherBinFound){
				binNumber = higherBin;
				info("higher bin "+higherBin+" is valid");
			}else{
				binNumber = 0;// there must be a least one bin left !
				info("one bin left, take this one with value " + getValueOfBinWithNumber(0));
			}
		}else{
			if(Math.abs(binNumber-lowerBin) < Math.abs(binNumber-higherBin)){
				binNumber = lowerBin;
			}else{
				binNumber = higherBin;
			}			
		}
		//System.out.println("nearest bin is " +binNumber);
		return binNumber;
	}
	public boolean containsBinWithNumber(int binNumber) {
		return getBins().containsKey(binNumber);
	}

	public boolean isBinMissingWithNumber(int binNumber) {
		return !containsBinWithNumber(binNumber);
	}

	public void put(double value) {
		int binNumber = this.calculator.calculateBin(value);
		boolean binisMissing = isBinMissingWithNumber(binNumber);
		if (binisMissing) {
			double lowBoundary = this.calculator.getLowBoundary(value);
			double highBoundary = this.calculator.getHighBoundary(value);
			String label = String.valueOf(lowBoundary) + "_"
					+ String.valueOf(highBoundary);
			getBins().put(
					binNumber,
					new HistogramBin(label, binNumber, 0, lowBoundary,
							highBoundary));
		}
		increase(binNumber);
		this.isDirty = true;
	}

	public double[] getValues() {
		double out[] = new double[getBins().size()];
		int i = 0;
		for (HistogramBin bin : getBins().values()) {
			out[i] = bin.getValue();
			i++;
		}
		return out;
	}

	public double getValueOfBinWithNumber(int binNumber) {
		if (!getBins().containsKey(binNumber))
			return -1.0f;
		return getBins().get(binNumber).getValue();
	}

	public String getLabelOfBinWithNumber(int binNumber) {
		return getBins().get(binNumber).getBinLabel();
	}

	public HistogramBin getBinWithBinNumber(int binNumber) {
		return getBins().get(binNumber);
	}

	/*
	 * Increases the number of occurrences for the bin with the identification
	 * number defined by id
	 * 
	 * @input the identification number of the bin which value has to be
	 * increased
	 */
	private void increase(int id) {
		getBinWithBinNumber(id).increase();
	}

	/*
	 * Sets the value of number of occurrences of the bin with the
	 * identification number defined by id
	 * 
	 * @input the identification number of the bin which value has to be set to
	 * 0
	 */
	public void reset(int id) {
		getBinWithBinNumber(id).reset();
		this.isDirty = true;
	}

	public void resetHist() {
		for (HistogramBin bin : getBins().values()) {
			bin.reset();
		}
		this.isDirty = true;
	}

	public int getNumBins() {
		return getBins().size();
	}

	public String[] getBinLabel() {
		ArrayList<String> label = new ArrayList<String>();
		String[] label_arr = new String[1];
		for (HistogramBin bin : getBins().values()) {
			label.add(bin.getBinLabel());
		}
		label.toArray(label_arr);
		return label_arr;
	}

	public double getMaxValue() {
		double max = Integer.MIN_VALUE;
		for (HistogramBin bin : getBins().values()) {
			if (max < bin.getValue())
				max = bin.getValue();

		}
		return max;
	}

	public int getBinNumberOfMaxValue() {
		double max = Integer.MIN_VALUE;
		int binNumber = -1;
		for (HistogramBin bin : getBins().values()) {
			if (max < bin.getValue())
				max = bin.getValue();
			binNumber = bin.getBinNumber();

		}
		return binNumber;
	}

	public int getSumValues() {
		if (!this.isDirty)
			return this.sumValues;
		int sum = 0;
		for (HistogramBin bin : getBins().values()) {
			sum += bin.getValue();
		}
		this.sumValues = sum;
		this.isDirty = false;
		return sum;
	}

	@Override
	public String toString() {
		if (getBins().size() == 0)
			return "[nobins]";
		String out = "";
		for (HistogramBin bin : getBins().values()) {
			out += bin + "\n";
		}
		return out;
	}

	/**
	 * @param content
	 *            the content to set
	 */
	private void setBins(LinkedHashMap<Integer, HistogramBin> content) {
		this.content = content;
		this.isDirty = true;
	}

	/**
	 * @return the content
	 */
	public LinkedHashMap<Integer, HistogramBin> getBins() {
		return this.content;
	}

	public void setBinSize(double binSize) {
		this.binSize = binSize;
	}

	public double  getBinSize() {
		return this.binSize;
	}
}
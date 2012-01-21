package org.bham.aucom.models.probability;

import static org.bham.aucom.util.Constants.LOWESTPROBABILITY;

import java.util.Iterator;
import java.util.logging.Logger;

import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.util.Gaussian;

public class HistogramDistributionWithGauss implements ProbabilityDistribution{
	private static final long serialVersionUID = 1L;
	private String name;
	public HistogramData content;

	class HistogramDistributionIterator implements Iterator<Double> {
		HistogramDistributionWithGauss dist;
		int index;
		int maxIndex;
		Iterator<HistogramBin> it;

		public HistogramDistributionIterator(HistogramDistributionWithGauss inDist) {
			this.dist = inDist;
			this.maxIndex = inDist.content.getNumBins() - 1;
			this.index = 0;
			this.it = this.dist.content.getBins().values().iterator();
		}

		@Override
		public boolean hasNext() {
			return this.it.hasNext();
		}

		@Override
		public Double next() {
			double d = this.dist.getProbByBinNumber(this.it.next().getBinNumber());
			return d;
		}

		@Override
		public void remove() {
			//TODO: remove einbauen
		}

	}
	public boolean validate(){
		Iterator<Double> values = getProbabilities();
		int numValues = 0;
		double sum = 0.0f;
		while(values.hasNext()){
			double val =values.next(); 
			sum+= val;
			numValues++;
		}
		boolean valid = sum >= 1.0d - LOWESTPROBABILITY &  sum <= 1.0d + LOWESTPROBABILITY; 
		if(!valid)
			System.out.println("Warning histogram dist not valid. Is: "+ sum + " with " + numValues + "bins but should be 1.0d ");
		return valid;
		
	}
	public HistogramDistributionWithGauss(String inName, double inBinSize) {
		setName(inName);
		this.content = new HistogramData(inBinSize);
		info("new distribution " + getName() + " binsize " + getBinSize() + " number values " + 0);
	}
	public HistogramDistributionWithGauss(String inName, double inBinSize, double[] vals) {
		setName(inName);
		this.content = new HistogramData(inBinSize);
		for(int i=0;i<vals.length;i++){
			this.content.put(vals[i]);
		}
		info("new distribution " + getName() + " binsize " + getBinSize() + " number values " + vals.length);
	}

	private void put(double value) {
		this.content.put(value);
//		validate();
	}

	public Iterator<Double> getProbabilities(){
		return new HistogramDistributionIterator(this);
	}
	public double getProbByBinNumber(int binNumber) {
		double probability = LOWESTPROBABILITY;
		if (this.content.containsBinWithNumber(binNumber)) {
			double numElements = this.content.getSumValues();
			double binValue = this.content.getValueOfBinWithNumber(binNumber);
			probability = binValue / numElements;
		}
		return probability;
	}
	public double getProbByBinLabel(String binLabel) {
		double probability = LOWESTPROBABILITY;
		if (this.content.containsBinWithNumber(1)) {
			double numElements = this.content.getSumValues();
			double binValue = this.content.getValueOfBinWithNumber(1);
			probability = binValue / numElements;
		}
		return probability;
	}

	@Override
	public String toString() {
		String out = "";
		out = this.content.toString();
		return out;
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return this.name;
	}

	//TODO: check the following for functionality
	private double getDistributionEntropy() {
		double entropy = 0.0f;
		 Iterator<Double> it = getProbabilities();
		 while(it.hasNext()){
			 entropy += calcEntropy(it.next());
		 }
		return entropy;
	}
	private double getBinEntropy(int binNumber){
		return calcEntropy(this.content.getValueOfBinWithNumber(binNumber));
	}
	private double calcEntropy(double inValue){
		return (double)(inValue * Math.log(1/inValue)/Math.log(2));
	}
	public void setBinSize(double binSize) {
		this.content.setBinSize(binSize);
	}

	public double getBinSize() {
		return this.content.getBinSize();
	}
	@Override
	public double getEntropy() {
		double entropy = 0.0f;
		 Iterator<Double> it = getProbabilities();
		 while(it.hasNext()){
			 entropy += calcEntropy(it.next());
		 }
		return entropy;
	}
	@Override
	public double getProbability(double val) {
		int binNumber = this.content.getBinNumberForValue(val);
		int nearestBinNumber = this.content.getNearestBinNumberForValue(val); 
		double prob = getProbByBinNumber(binNumber);
		if(binNumber != nearestBinNumber){
			if(prob == LOWESTPROBABILITY)
				info("Warning: prob shoudn't be equals to smallest prob possible!");
			double mu = this.content.getBinSize()*0.5f + nearestBinNumber*this.content.getBinSize();
			double sigma = this.content.getBinSize()*2;
			double factor = (double)Gaussian.phi((val-mu)/sigma); 
			info("no bin present for "+val+ " applying factor " + factor +" gauss on nearest probability value " +prob);
			prob*=factor;
		}
		return prob;
	}
	@Override
	public String getType() {
		return "Histogram";
	}
	@Override
	public void update(double[] val) {
		for(int i=0;i<val.length;i++){
			put(val[i]);
		}
	}
	public void severe(String  msg){
		Logger.getLogger(this.getClass().getName()).severe(msg);
	}

	public void info(String info) {
		Logger.getLogger(this.getClass().getCanonicalName()).info(info);
	}
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double expectedValue() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double[] sample() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double[] sampleProbability() {
		// TODO Auto-generated method stub
		return null;
	}
	@Override
	public double variance() {
		// TODO Auto-generated method stub
		return 0;
	}
	@Override
	public double getMaxProbability() {
		// TODO Auto-generated method stub
		return 0;
	}
}
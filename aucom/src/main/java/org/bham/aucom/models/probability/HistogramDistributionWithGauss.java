package org.bham.aucom.models.probability;

import static org.bham.aucom.util.Constants.LOWEST_PROBABILITY;

import java.util.Iterator;
import java.util.logging.Logger;

import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;
import org.bham.aucom.util.Gaussian;

public class HistogramDistributionWithGauss implements ProbabilityDistribution{
	private static final long serialVersionUID = 1L;
	private String name;
	private final HistogramData content;

	class HistogramDistributionIterator implements Iterator<Double> {
		final HistogramDistributionWithGauss dist;
		final int index;
		final int maxIndex;
		final Iterator<HistogramBin> it;

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
            return this.dist.getProbByBinNumber(this.it.next().getBinNumber());
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
		boolean valid = sum >= 1.0d - LOWEST_PROBABILITY &  sum <= 1.0d + LOWEST_PROBABILITY;
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
        for (double val : vals) {
            this.content.put(val);
        }
		info("new distribution " + getName() + " binsize " + getBinSize() + " number values " + vals.length);
	}

	private void put(double value) {
		this.content.put(value);
//		validate();
	}

	Iterator<Double> getProbabilities(){
		return new HistogramDistributionIterator(this);
	}
	double getProbByBinNumber(int binNumber) {
		double probability = LOWEST_PROBABILITY;
		if (this.content.containsBinWithNumber(binNumber)) {
			double numElements = this.content.getSumValues();
			double binValue = this.content.getValueOfBinWithNumber(binNumber);
			probability = binValue / numElements;
		}
		return probability;
	}
	public double getProbByBinLabel(String binLabel) {
		double probability = LOWEST_PROBABILITY;
		if (this.content.containsBinWithNumber(1)) {
			double numElements = this.content.getSumValues();
			double binValue = this.content.getValueOfBinWithNumber(1);
			probability = binValue / numElements;
		}
		return probability;
	}

	@Override
	public String toString() {
		String out;
		out = this.content.toString();
		return out;
	}

	/**
	 * @param name
	 *            the name to set
	 */
    void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the name
	 */
    String getName() {
		return this.name;
	}

	private double calcEntropy(double inValue){
		return inValue * Math.log(1/inValue)/Math.log(2);
	}
	public void setBinSize(double binSize) {
		this.content.setBinSize(binSize);
	}

	double getBinSize() {
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
			if(prob == LOWEST_PROBABILITY)
				info("Warning: prob shoudn't be equals to smallest prob possible!");
			double mu = this.content.getBinSize()*0.5f + nearestBinNumber*this.content.getBinSize();
			double sigma = this.content.getBinSize()*2;
			double factor = Gaussian.phi((val-mu)/sigma);
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
        for (double aVal : val) {
            put(aVal);
        }
	}
	public void severe(String  msg){
		Logger.getLogger(this.getClass().getName()).severe(msg);
	}

	void info(String info) {
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
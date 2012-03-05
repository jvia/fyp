package org.bham.aucom.models.probability;

import org.bham.aucom.diagnoser.t2gram.ProbabilityDistribution;

import java.util.Iterator;
import java.util.logging.Logger;

import static org.bham.aucom.util.Constants.LOWESTPROBABILITY;

public class HistogramDistribution implements ProbabilityDistribution {
    private static final long serialVersionUID = 1L;

    private String name;
    public HistogramData content;

    public HistogramDistribution(String inName, double inBinSize, double[] vals) {
        setName(inName);
        this.content = new HistogramData(inBinSize);
        for (double val : vals) {
            this.content.put(val);
        }
    }

    public boolean validate() {
        Iterator<Double> values = getProbabilities();
        int numValues = 0;
        double sum = 0.0d;
        while (values.hasNext()) {
            double val = values.next();
            sum += val;
            numValues++;
        }
        boolean valid = sum >= 1.0d - LOWESTPROBABILITY & sum <= 1.0d + LOWESTPROBABILITY;
        if (!valid) {
            System.out.println("Warning histogram dist not valid. Is: " + sum + " with " + numValues + "bins but should be 1.0d ");
        }
        return valid;

    }

    public HistogramDistribution(String inName, double inBinSize) {
        setName(inName);
        this.content = new HistogramData(inBinSize);
    }

    private void put(double value) {
        this.content.put(value);
        //  validate();
    }

    private double getProb(double value) {
        int binNumber = this.content.getBinNumberForValue(value);
        return getProbByBinNumber(binNumber);
    }

    public Iterator<Double> getProbabilities() {
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
        return content.toString();
    }

    /**
     * @param name the name to set
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
    public double getDistributionEntropy() {
        double entropy = 0.0f;
        Iterator<Double> it = getProbabilities();
        while (it.hasNext()) {
            entropy += calcEntropy(it.next());
        }
        return -entropy;
    }

    private double getBinEntropy(int binNumber) {
        return calcEntropy(this.content.getValueOfBinWithNumber(binNumber));
    }

    public double log(double value, double base) {
        return Math.log(value) / Math.log(base);
    }

    private double calcEntropy(double inValue) {
        return inValue * log(inValue, 2);
        /*
           * why that????
           */
        //return inValue * Math.log(1/inValue)/Math.log(2);
    }

    public void setBinSize(int binSize) {
        this.content.setBinSize(binSize);
    }

    public double getBinSize() {
        return this.content.getBinSize();
    }

    public HistogramData getContent() {
        return content;
    }

    @Override
    public double getEntropy() {
        double entropy = 0.0f;
        Iterator<Double> it = getProbabilities();
        while (it.hasNext()) {
            entropy += calcEntropy(it.next());
        }
        return -entropy;
    }

    @Override
    public double getProbability(double val) {
        int binNumber = this.content.getBinNumberForValue(val);
        return getProbByBinNumber(binNumber);
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

    public void severe(String msg) {
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

    static class HistogramDistributionIterator implements Iterator<Double> {
        HistogramDistribution dist;
        int index;
        int maxIndex;

        Iterator<HistogramBin> it;

        public HistogramDistributionIterator(HistogramDistribution inDist) {
            this.dist = inDist;
            this.maxIndex = inDist.content.getNumBins() - 1;
            this.index = 0;
            this.it = this.dist.content.getBins().values().iterator();
        }

        @Override
        public boolean hasNext() {
            return it.hasNext();
        }

        @Override
        public Double next() {
            return dist.getProbByBinNumber(it.next().getBinNumber());
        }

        @Override
        public void remove() {
            //TODO: remove einbauen
        }

    }
}
package org.bham.aucom.diagnoser.t2gram;

import java.io.Serializable;

public interface ProbabilityDistribution extends Serializable {

    /*
      * @param val The value for which the probability has to be calculated
      */
    public double getProbability(double val);//

    public double getMaxProbability();

    public int getCount();

    public String getType();

    /*
      * The entropy value of the distribution
      * based on the entropy concept of Shannon
      */
    public double getEntropy();

    public double[] sampleProbability();

    public double[] sample();

    public double variance();

    public double expectedValue();

    public void update(double[] val);

}

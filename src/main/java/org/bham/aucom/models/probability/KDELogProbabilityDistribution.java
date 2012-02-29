package org.bham.aucom.models.probability;


public class KDELogProbabilityDistribution extends KernelDensityDistribution {
    /**
     *
     */
    private static final long serialVersionUID = -1112967046746146751L;

    public KDELogProbabilityDistribution(double[] values) {
        super(1.0);
        update(values);
    }

    @Override
    public void update(double[] val) {
        for (int i = 0; i < val.length; i++) {
            val[i] = getLogValue(val[i]);
        }
        super.update(val);
    }

    public double getLogValue(double value) {
        if (value == 0.0)
            return 0.0;
        return super.getProbability(Math.log(value));
    }

    @Override
    public double getProbability(double value) {
        return super.getProbability(getLogValue(value));
    }

}

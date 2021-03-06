package org.bham.aucom.models.probability;

import org.bham.aucom.util.BinCalculator;

public class LinearBinCalculator implements BinCalculator {
    /**
     *
     */
    private static final long serialVersionUID = 1L;
    private double intervalSize;

    public LinearBinCalculator(double intervalSize) {
        this.setIntervalSize(intervalSize);
    }

    @Override
    public int calculateBin(double value) {
        int num = (int) Math.floor(value / intervalSize);
        return num;
    }

    public void setIntervalSize(double intervalSize) {
        this.intervalSize = intervalSize;
    }

    public double getIntervalSize() {
        return intervalSize;
    }

    @Override
    public double getHighBoundary(double value) {
        double highBoundary = getLowBoundary(value) + intervalSize;
        return highBoundary;
    }

    @Override
    public double getLowBoundary(double value) {
        double num = (int) Math.floor(value / intervalSize);
        return num * intervalSize;
    }

}

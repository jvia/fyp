package org.bham.aucom.models.probability;

import java.io.Serializable;

public class HistogramBin implements Serializable {
    private static final long serialVersionUID = 0L;
    private String binLabel;
    double value;
    private double lowBorder;
    private double highBorder;
    private int binNumber;

    public HistogramBin(String inBinLabel, int inBinNumber, int startValue, double inLowBorder, double inHighBorder) {
        value = startValue;
        setBinNumber(inBinNumber);
        // Assert.assertTrue(inBinNumber >=0);
        setLowBorder(inLowBorder);
        // Assert.assertTrue(inLowBorder >=0);
        setHighBorder(inHighBorder);
        // Assert.assertTrue(inHighBorder >=0);
        setBinLabel(inBinLabel);
    }

    public HistogramBin(String inBinLabel, int inBinNumber, double inLowBorder, double inHighBorder) {
        this(inBinLabel, inBinNumber, 0, inLowBorder, inHighBorder);
    }

    public double getValue() {
        return value;
    }

    public void increase() {
        value++;
    }

    public void reset() {
        value = 0;
    }

    public void setBorders(double inLowBorder, double inHighBorder) {

    }

    /**
     * @param binLabel
     *            the binLabel to set
     */
    public void setBinLabel(String binLabel) {
        this.binLabel = binLabel;
    }

    /**
     * @return the binLabel
     */
    public String getBinLabel() {
        return binLabel;
    }

    /**
     * @param binNumber
     *            the binNumber to set
     */
    public void setBinNumber(int binNumber) {
        this.binNumber = binNumber;
    }

    /**
     * @return the binNumber
     */
    public int getBinNumber() {
        return binNumber;
    }

    @Override
    public String toString() {
        String out = "";
        out += " " + (getLowBorder() + getHighBorder()) / 2.0;
        out += " " + getValue();
        return out;

    }

    /**
     * @param lowBorder
     *            the lowBorder to set
     */
    public void setLowBorder(double lowBorder) {
        this.lowBorder = lowBorder;
    }

    /**
     * @return the lowBorder
     */
    public double getLowBorder() {
        return lowBorder;
    }

    /**
     * @param highBorder
     *            the highBorder to set
     */
    public void setHighBorder(double highBorder) {
        this.highBorder = highBorder;
    }

    /**
     * @return the highBorder
     */
    public double getHighBorder() {
        return highBorder;
    }
}

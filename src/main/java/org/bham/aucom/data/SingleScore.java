package org.bham.aucom.data;

/**
 * TODO: remove this class
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 */
public class SingleScore extends Score {

    /**
     * @param tpf
     * @param value
     */
    public SingleScore(TemporalProbabilityFeature tpf, double value) {
        super(tpf, value);
    }

    /**
     * @param s
     */
    public SingleScore(SingleScore s) {
        this(s, s.getValue());
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        String str = "[ts:" + getTimestamp() + ":scr:" + getValue() + "]";
        return str;
    }

    /**
     * @return
     */
    @Override
    public Object copy() {
        TemporalProbabilityFeature tpf_copy = (TemporalProbabilityFeature) super.copy();
        return new SingleScore(tpf_copy, this.getValue());
    }
}

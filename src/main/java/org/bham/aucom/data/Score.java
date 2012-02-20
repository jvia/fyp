package org.bham.aucom.data;

import java.util.ArrayList;

public abstract class Score extends TemporalProbabilityFeature {

    private double value;
    private double variance = 0.0d;

    public Score(TemporalProbabilityFeature f, double value) {
        super(f);
        setValue(value);
        setVariance(0.0d);
    }

    public Score(Score s) {
        this(s, s.getValue());
    }

    public Score() {
        super();
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getValue() {
        return this.value;
    }

    public void setVariance(double variance) {
        this.variance = variance;
    }

    public double getVariance() {
        return this.variance;
    }

    public static Score createRandomScore() {
        if (Math.random() <= 0.5)
            return createRandomSingleScore();
        return createRandomRangeScore();
    }

    public static Score createRandomRangeScore() {
        TemporalProbabilityFeature tpf = Score.createRandomTemporalProbabilityFeature();
        int numberSubScores = 1 + (int) (Math.random() * 10);
        ArrayList<Score> subScores = new ArrayList<Score>();
        for (int i = 0; i < numberSubScores; i++) {
            subScores.add(createRandomSingleScore());
        }
        return new RangeScore(subScores);
    }

    public static Score createRandomSingleScore() {
        return new SingleScore(Score.createRandomTemporalProbabilityFeature(), Math.random());
    }

    @Override
    public String toString() {
        return super.toString() + " sval:" + getValue() + ";svar:" + getVariance();
    }
}

package org.bham.aucom.data;

import java.util.ArrayList;

public abstract class Score extends TemporalProbabilityFeature {

    private double value;
    private double variance = 0.0d;

    Score(TemporalProbabilityFeature f, double value)
    {
        super(f);
        setValue(value);
        setVariance(0.0d);
    }

    Score(Score s)
    {
        this(s, s.getValue());
    }

    public Score() {
		super();
	}

	void setValue(double value)
    {
        this.value = value;
    }

    public double getValue()
    {
        return this.value;
    }

    void setVariance(double variance)
    {
        this.variance = variance;
    }

    public double getVariance()
    {
        return this.variance;
    }

    static Score createRandomScore()
    {
        if (Math.random() <= 0.5)
            return createRandomSingleScore();
        return createRandomRangeScore();
    }

    public static Score createRandomRangeScore()
    {
        Score.createRandomTemporalProbabilityFeature();
        int numberSubScores = 1 + (int) (Math.random() * 10);
        ArrayList<Score> subScores = new ArrayList<Score>();
        for (int i = 0; i < numberSubScores; i++) {
            subScores.add(createRandomSingleScore());
        }
        return new RangeScore(subScores);
    }

    private static Score createRandomSingleScore()
    {
        return new SingleScore(Score.createRandomTemporalProbabilityFeature(), Math.random());
    }

    @Override
    public String toString()
    {
        return super.toString() + " sval:" + getValue() + ";svar:" + getVariance();
    }
}

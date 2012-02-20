package org.bham.aucom.data;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

public class RangeScoreTest {

    @Before
    public void setUp() throws Exception {
    }

    @SuppressWarnings("boxing")
    @Test
    public void testGetVariance() {
        ArrayList<Score> scores = new ArrayList<Score>();
        scores.add(new SingleScore(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature(), 2.3));
        scores.add(new SingleScore(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature(), 2.3));
        scores.add(new SingleScore(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature(), 2.3));
        scores.add(new SingleScore(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature(), 2.3));
        scores.add(new SingleScore(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature(), 2.3));
        Assert.assertEquals(0.0, new RangeScore(scores).getVariance(), 0.00000001);
    }

    @Test
    public void testInvariance() {
        Score ss = new SingleScore(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature(), 2.3);
        ArrayList<Score> ars = new ArrayList<Score>();
        ars.add(ss);
        RangeScore rs = new RangeScore(ars);
        Assert.assertEquals(ss.getValue(), rs.getValue());
        Assert.assertEquals(ss.getVariance(), rs.getVariance());

    }

}

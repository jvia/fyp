package org.bham.aucom.util;

import junit.framework.Assert;
import org.bham.aucom.data.Score;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.HysteresisAnomalyClassifier;
import org.junit.Before;
import org.junit.Test;

public class HysteresisThresholdTest {
    HysteresisAnomalyClassifier t;

    @Before
    public void setUp() throws Exception {
        this.t = new HysteresisAnomalyClassifier(1.0, 2.0);
    }

    public Score generateExampleScore(double value) {
        Score s = Score.createRandomSingleScore();
        s.setValue(value);
        return s;
    }

    @Test
    public void testSatisfies() {
        Assert.assertTrue(this.t.satisfies(generateExampleScore(1.0)));
        Assert.assertTrue(this.t.satisfies(generateExampleScore(1.5)));
        Assert.assertTrue(this.t.satisfies(generateExampleScore(2.0)));
        Assert.assertTrue(this.t.satisfies(generateExampleScore(2.5)));

        Assert.assertFalse(this.t.satisfies(generateExampleScore(0.9)));
        Assert.assertFalse(this.t.satisfies(generateExampleScore(1.5)));
        Assert.assertFalse(this.t.satisfies(generateExampleScore(1.99)));
        Assert.assertTrue(this.t.satisfies(generateExampleScore(2.0)));
    }

    @Test
    public void testGetAttributes() {
        Assert.assertNotNull(this.t.getAttributes());
    }

    @Test
    public void testGetName() {
        Assert.assertNotNull(this.t.getName());
        Assert.assertEquals("hysteresis", this.t.getName());
    }
}

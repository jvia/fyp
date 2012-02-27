package org.bham.aucom.data.timeseries;

import junit.framework.Assert;
import org.bham.aucom.data.RangeScore;
import org.junit.Before;
import org.junit.Test;

public class RangeScoreTest {

    @Before
    public void setUp() throws Exception {
    }


    @Test
    public void testMarking() {
        RangeScore rs = (RangeScore) RangeScore.createRandomRangeScore();
        Assert.assertEquals(false, rs.isFirstElement());
        Assert.assertEquals(false, rs.isLastElement());
        rs.setFirstElement(true);
        rs.setLastElement(true);
        Assert.assertEquals(true, rs.isFirstElement());
        Assert.assertEquals(true, rs.isLastElement());
    }

    @Test
    public void testCopy() {
        RangeScore rs = (RangeScore) RangeScore.createRandomRangeScore();
        rs.setFirstElement(true);
        rs.setLastElement(true);
        Assert.assertEquals(true, rs.isFirstElement());
        Assert.assertEquals(true, rs.isLastElement());
        RangeScore rs_copy = (RangeScore) rs.copy();
        Assert.assertEquals(true, rs_copy.isFirstElement());
        Assert.assertEquals(true, rs_copy.isLastElement());

    }

}

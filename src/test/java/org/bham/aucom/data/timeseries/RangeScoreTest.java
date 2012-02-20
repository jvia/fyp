package org.bham.aucom.data.timeseries;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.RangeScore;

public class RangeScoreTest {

	@Before
	public void setUp() throws Exception {
	}

	
	@Test
	public void testMarking(){
		RangeScore rs = (RangeScore)RangeScore.createRandomRangeScore();
		Assert.assertEquals(false, rs.isMarkedAsFirstElement());
		Assert.assertEquals(false, rs.isMarkedAsLastElement());
		rs.markAsFirstElement();
		rs.markAsLastElement();
		Assert.assertEquals(true, rs.isMarkedAsFirstElement());
		Assert.assertEquals(true, rs.isMarkedAsLastElement());
	}
	@Test
	public void testCopy() {
		RangeScore rs = (RangeScore)RangeScore.createRandomRangeScore();
		rs.markAsFirstElement();
		rs.markAsLastElement();
		Assert.assertEquals(true, rs.isMarkedAsFirstElement());
		Assert.assertEquals(true, rs.isMarkedAsLastElement());
		RangeScore rs_copy = (RangeScore)rs.copy();
		Assert.assertEquals(true, rs_copy.isMarkedAsFirstElement());
		Assert.assertEquals(true, rs_copy.isMarkedAsLastElement());
		
	}

}

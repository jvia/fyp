package org.bham.aucom.util;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;

public class RingBufferTest {
	RingBuffer<Double> buf;
	@Before
	public void setUp() throws Exception {
		buf = new RingBuffer<Double>(5);
		
	}

	@Test
	public void testAdd() {
		Assert.assertEquals(0, buf.getNextInsertPosition());
		buf.add(new Double(1.0));
		Assert.assertEquals(1.0, buf.get(0).doubleValue(),0.0);
		Assert.assertEquals(1, buf.getNextInsertPosition());
		buf.add(new Double(2.0));
		Assert.assertEquals(2, buf.getNextInsertPosition());
		buf.add(new Double(3.0));
		Assert.assertEquals(3, buf.getNextInsertPosition());
		buf.add(new Double(4.0));
		Assert.assertEquals(4, buf.getNextInsertPosition());
		buf.add(new Double(5.0));
		Assert.assertEquals(5, buf.getNextInsertPosition());
		buf.add(new Double(6.0));
		Assert.assertEquals(1, buf.getNextInsertPosition());
		Assert.assertEquals(2.0, buf.get(0).doubleValue(),0.0);
	}

	@Test
	public void testGet() {
		buf.add(new Double(1.0));
		Assert.assertEquals(1.0, buf.get(0).doubleValue(),0.0);
		buf.add(new Double(2.0));
		Assert.assertEquals(2.0, buf.get(1).doubleValue(),0.0);
		buf.add(new Double(3.0));
		Assert.assertEquals(3.0, buf.get(2).doubleValue(),0.0);
	}
	@Test
	public void testAddAll(){
		Double[] d = new Double[4];
		d[0] = 0.0;
		d[1] = 1.0;
		d[2] = 2.0;
		d[3] = 3.0;
		System.out.println("<--");
		buf.addAll(d);
		System.out.println("-->");
		Assert.assertEquals(0.0, buf.get(0).doubleValue(),0.0);
		Assert.assertEquals(1.0, buf.get(1).doubleValue(),0.0);
		Assert.assertEquals(2.0, buf.get(2).doubleValue(),0.0);
		Assert.assertEquals(3.0, buf.get(3).doubleValue(),0.0);
		Assert.assertNull(buf.get(4));
		d[0] = 4.0;
		d[1] = 5.0;
		d[2] = 6.0;
		d[3] = 7.0;
		System.out.println("<--");
		buf.addAll(d);
		System.out.println("-->");
		Assert.assertEquals(3.0, buf.get(0).doubleValue(),0.0);
		Assert.assertEquals(4.0, buf.get(1).doubleValue(),0.0);
		Assert.assertEquals(5.0, buf.get(2).doubleValue(),0.0);
		Assert.assertEquals(6.0, buf.get(3).doubleValue(),0.0);
		Assert.assertEquals(7.0, buf.get(4).doubleValue(),0.0);
	}
	@Test
	public void initialize(){
		Double[] d = new Double[4];
		d[0] = 0.0;
		d[1] = 1.0;
		d[2] = 2.0;
		d[3] = 3.0;
		buf.addAll(d);
		buf.clear();
		Assert.assertEquals(buf.getNextInsertPosition(), 0);
		Assert.assertNull(buf.get(0));
	}
	@Test
	public void testSize(){
		buf.add(Double.valueOf(10));
		Assert.assertEquals(1, buf.size());
		buf.add(Double.valueOf(10));
		buf.add(Double.valueOf(10));
		buf.add(Double.valueOf(10));
		Assert.assertEquals(4, buf.size());
	}
	@Test
	public void testToArrayIfFull() {
		Double[] d = new Double[10];
		Double[] dd = new Double[5];
		d[0] = 0.0;
		d[1] = 1.0;
		d[2] = 2.0;
		d[3] = 3.0;
		d[4] = 4.0;
		d[5] = 5.0;
		d[6] = 6.0;
		d[7] = 7.0;
		d[8] = 8.0;
		d[9] = 9.0;
		buf.addAll(d);
		Object[] tmp = buf.toArray();
		dd = Arrays.copyOf(tmp, tmp.length, Double[].class);
		Assert.assertEquals(5, tmp.length);
		Assert.assertEquals(d[5], dd[0], 0.0);
		Assert.assertEquals(d[6], dd[1], 0.0);
		Assert.assertEquals(d[7], dd[2], 0.0);
		Assert.assertEquals(d[8], dd[3], 0.0);
		Assert.assertEquals(d[9], dd[4], 0.0);
	}
	
	@Test
	public void testToArrayIfNOTFull() {
	    Double[] d = new Double[4];
	    Double[] dd = new Double[5];
	    d[0] = 0.0;
	    d[1] = 1.0;
	    d[2] = 2.0;
	    d[3] = 3.0;
	    buf.addAll(d);
	    Object[] tmp = buf.toArray();
	    Assert.assertEquals(4, tmp.length);
	    dd = Arrays.copyOf(tmp, tmp.length, Double[].class);
	    Assert.assertEquals(d[0], dd[0], 0.0);
	    Assert.assertEquals(d[1], dd[1], 0.0);
	    Assert.assertEquals(d[2], dd[2], 0.0);
	    Assert.assertEquals(d[3], dd[3], 0.0);
	}

	@Test
	public void testIsFilled() {
	}

}

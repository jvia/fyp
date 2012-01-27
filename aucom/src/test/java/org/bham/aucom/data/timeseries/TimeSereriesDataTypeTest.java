package org.bham.aucom.data.timeseries;

import java.util.ArrayList;

import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.DataType;

public class TimeSereriesDataTypeTest implements TimeSeriesStatusListener {
	TimeSeries<DataType> s;
	private TimeseriesStatusEvent status = null; 
	@Before
	public void setUp() throws Exception {
		this.status= null;
		this.s = new DataTypeTimeSeries();
		this.s.addTimeSeriesStatusListener(this);
		for(int i= 0; i < 100;i++){
			this.s.add(DataType.createRandomDataType());
			Thread.sleep(10);
		}
	}


	@Test
	public void testGet() {
		Assert.assertNotNull(this.s.get(0));
	}

	@Test
	public void testAdd() {
		this.s.add(DataType.createRandomDataType());
		Assert.assertNotNull(this.status);
		Assert.assertEquals(TimeseriesStatus.ELEMENTSADDED, this.status.getStatus());
	}
	@Test
	public void testRemove() {
		this.s.remove(this.s.size()-1);
		Assert.assertNotNull(this.status);
		Assert.assertEquals(TimeseriesStatus.ELEMENTSREMOVED, this.status.getStatus());
	}
	@Test
	public void testClear() {
		this.s.clear();
		Assert.assertNotNull(this.status);
		Assert.assertEquals(TimeseriesStatus.ELEMENTSREMOVED, this.status.getStatus());
	}

	@Test
	public void testAddAll() {
		this.s.clear();
		this.status = null;
		ArrayList<DataType> l = new ArrayList<DataType>();
		this.s.addTimeSeriesStatusListener(this);
		for(int i= 0; i < 100;i++){
			l.add(DataType.createRandomDataType());
		}
		this.s.addAll(l);
		Assert.assertNotNull(this.status);
		Assert.assertEquals(TimeseriesStatus.ELEMENTSADDED, this.status.getStatus());
	}
//
//	@Test
//	public void testSize() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetall() {
//		fail("Not yet implemented");
//	}
//
//	@Test
//	public void testGetName() {
//		fail("Not yet implemented");
//	}


	@Override
	public void timeseriesStatusChanged(TimeseriesStatusEvent status) {
		this.status= status;
	}


	public boolean isReady() {
		return false;
	}

}

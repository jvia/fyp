package org.bham.aucom.util;


import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.junit.Before;
import org.junit.Test;

public class HashMatrixTest {
	HashMatrix<Integer, Integer, Integer> matrix;
	List<ScheduledExecutorService> services;
	@Before
	public void setUp() throws Exception {
		matrix= new HashMatrix<Integer, Integer, Integer>();
	}
	
	@Test 
	public void concurrencyTest(){
		matrix = createRanomFilledMatrix();
		try {
			for(int i = 0; i < 100; i++){
				ScheduledExecutorService s = Executors.newSingleThreadScheduledExecutor();
				s.scheduleAtFixedRate(new Runnable() {
					public void run() {
						
					}
				}, 10, 10, TimeUnit.MILLISECONDS);
				services.add(s);
			}
			
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	
	/*
	 *  Helper functions
	 */
	private HashMatrix<Integer, Integer, Integer> createRanomFilledMatrix() {
		HashMatrix<Integer, Integer, Integer> m = new  HashMatrix<Integer, Integer, Integer>();
		for(int i = 0; i< 100; i++){
			int firstIndex = (int)Math.random()*100;
			int secondIndex = (int)Math.random()*100;
			int value = (int)Math.random()*100;
			m.put(firstIndex, secondIndex, value);
		}
		return m;
	}

}

package org.bham.aucom.util;


import java.util.ArrayList;
import java.util.HashMap;
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

    /**
     * Test of values method, of class HashMatrix.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        HashMatrix instance = new HashMatrix();
        List expResult = null;
        List result = instance.values();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of put method, of class HashMatrix.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Object indexOne = null;
        Object indexTwo = null;
        Object value = null;
        HashMatrix instance = new HashMatrix();
        instance.put(indexOne, indexTwo, value);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class HashMatrix.
     */
    @Test
    public void testGet_GenericType() {
        System.out.println("get");
        Object indexOne = null;
        HashMatrix instance = new HashMatrix();
        HashMap expResult = null;
        HashMap result = instance.get(indexOne);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class HashMatrix.
     */
    @Test
    public void testGet_GenericType_GenericType() {
        System.out.println("get");
        Object indexOne = null;
        Object indexTwo = null;
        HashMatrix instance = new HashMatrix();
        Object expResult = null;
        Object result = instance.get(indexOne, indexTwo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsFirstKey method, of class HashMatrix.
     */
    @Test
    public void testContainsFirstKey() {
        System.out.println("containsFirstKey");
        Object indexOne = null;
        HashMatrix instance = new HashMatrix();
        boolean expResult = false;
        boolean result = instance.containsFirstKey(indexOne);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsKey method, of class HashMatrix.
     */
    @Test
    public void testContainsKey() {
        System.out.println("containsKey");
        Object indexOne = null;
        Object indexTwo = null;
        HashMatrix instance = new HashMatrix();
        boolean expResult = false;
        boolean result = instance.containsKey(indexOne, indexTwo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of keySet method, of class HashMatrix.
     */
    @Test
    public void testKeySet() {
        System.out.println("keySet");
        HashMatrix instance = new HashMatrix();
        ArrayList expResult = null;
        ArrayList result = instance.keySet();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class HashMatrix.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        HashMatrix instance = new HashMatrix();
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dimension method, of class HashMatrix.
     */
    @Test
    public void testDimension() {
        System.out.println("dimension");
        HashMatrix instance = new HashMatrix();
        int[] expResult = null;
        int[] result = instance.dimension();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class HashMatrix.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        HashMatrix instance = new HashMatrix();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

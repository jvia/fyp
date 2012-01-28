/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

import java.util.LinkedHashMap;
import org.bham.aucom.data.timeseries.DataTypeTimeSeries;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jxv911
 */
public class DatasetListTest {

    public DatasetListTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of put method, of class DatasetList.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        String name = "";
        DataTypeTimeSeries model = null;
        DatasetList instance = new DatasetList();
        instance.put(name, model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class DatasetList.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        String name = "";
        DatasetList instance = new DatasetList();
        instance.delete(name);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class DatasetList.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        String name = "";
        DatasetList instance = new DatasetList();
        DataTypeTimeSeries expResult = null;
        DataTypeTimeSeries result = instance.get(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of indexOf method, of class DatasetList.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        String name = "";
        DatasetList instance = new DatasetList();
        int expResult = 0;
        int result = instance.indexOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElementAt method, of class DatasetList.
     */
    @Test
    public void testGetElementAt() {
        System.out.println("getElementAt");
        int arg0 = 0;
        DatasetList instance = new DatasetList();
        Object expResult = null;
        Object result = instance.getElementAt(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSize method, of class DatasetList.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        DatasetList instance = new DatasetList();
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsKey method, of class DatasetList.
     */
    @Test
    public void testContainsKey() {
        System.out.println("containsKey");
        String name = "";
        DatasetList instance = new DatasetList();
        boolean expResult = false;
        boolean result = instance.containsKey(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setDatasets method, of class DatasetList.
     */
    @Test
    public void testSetDatasets() {
        System.out.println("setDatasets");
        LinkedHashMap<String, DataTypeTimeSeries> datasets = null;
        DatasetList instance = new DatasetList();
        instance.setDatasets(datasets);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDatasets method, of class DatasetList.
     */
    @Test
    public void testGetDatasets() {
        System.out.println("getDatasets");
        DatasetList instance = new DatasetList();
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.getDatasets();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
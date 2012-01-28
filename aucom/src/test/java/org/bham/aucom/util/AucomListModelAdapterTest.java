/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

import org.bham.aucom.data.util.DataModelStatusEvent;
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
public class AucomListModelAdapterTest {

    public AucomListModelAdapterTest() {
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
     * Test of getElementAt method, of class AucomListModelAdapter.
     */
    @Test
    public void testGetElementAt() {
        System.out.println("getElementAt");
        int index = 0;
        AucomListModelAdapter instance = new AucomListModelAdapter();
        Object expResult = null;
        Object result = instance.getElementAt(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSize method, of class AucomListModelAdapter.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        AucomListModelAdapter instance = new AucomListModelAdapter();
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of accepts method, of class AucomListModelAdapter.
     */
    @Test
    public void testAccepts() {
        System.out.println("accepts");
        DataModelStatusEvent status = null;
        AucomListModelAdapter instance = new AucomListModelAdapter();
        boolean expResult = false;
        boolean result = instance.accepts(status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dataModelStatusChanged method, of class AucomListModelAdapter.
     */
    @Test
    public void testDataModelStatusChanged() {
        System.out.println("dataModelStatusChanged");
        DataModelStatusEvent status = null;
        AucomListModelAdapter instance = new AucomListModelAdapter();
        instance.dataModelStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
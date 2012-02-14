/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.tranform;

import java.util.HashMap;
import org.bham.aucom.data.DataType;
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
public class CountDataTypesTest {

    public CountDataTypesTest() {
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
     * Test of iTransform method, of class CountDataTypes.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        DataType input = null;
        CountDataTypes instance = new CountDataTypes();
        DataType expResult = null;
        DataType result = instance.iTransform(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setCounting method, of class CountDataTypes.
     */
    @Test
    public void testSetCounting() {
        System.out.println("setCounting");
        HashMap<Integer, Integer> counting = null;
        CountDataTypes instance = new CountDataTypes();
        instance.setCounting(counting);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCounting method, of class CountDataTypes.
     */
    @Test
    public void testGetCounting() {
        System.out.println("getCounting");
        CountDataTypes instance = new CountDataTypes();
        HashMap expResult = null;
        HashMap result = instance.getCounting();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data;

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
public class NodeGraphXYDataItemTest {

    public NodeGraphXYDataItemTest() {
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
     * Test of getVal method, of class NodeGraphXYDataItem.
     */
    @Test
    public void testGetVal() {
        System.out.println("getVal");
        NodeGraphXYDataItem instance = null;
        double expResult = 0.0;
        double result = instance.getVal();
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassId method, of class NodeGraphXYDataItem.
     */
    @Test
    public void testGetClassId() {
        System.out.println("getClassId");
        NodeGraphXYDataItem instance = null;
        int expResult = 0;
        int result = instance.getClassId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.sink;

import org.bham.aucom.data.AbstractData;
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
public class SingleDataSinkTest {

    public SingleDataSinkTest() {
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
     * Test of pushItem method, of class SingleDataSink.
     */
    @Test
    public void testPushItem() throws Exception {
        System.out.println("pushItem");
        AbstractData e = null;
        SingleDataSink instance = new SingleDataSink();
        instance.pushItem(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setData method, of class SingleDataSink.
     */
    @Test
    public void testSetData() {
        System.out.println("setData");
        AbstractData data = null;
        SingleDataSink instance = new SingleDataSink();
        instance.setData(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getData method, of class SingleDataSink.
     */
    @Test
    public void testGetData() {
        System.out.println("getData");
        SingleDataSink instance = new SingleDataSink();
        AbstractData expResult = null;
        AbstractData result = instance.getData();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
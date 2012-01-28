/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.util;

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
public class DataModelStatusListenerTest {

    public DataModelStatusListenerTest() {
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
     * Test of accepts method, of class DataModelStatusListener.
     */
    @Test
    public void testAccepts() {
        System.out.println("accepts");
        DataModelStatusEvent status = null;
        DataModelStatusListener instance = new DataModelStatusListenerImpl();
        boolean expResult = false;
        boolean result = instance.accepts(status);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of dataModelStatusChanged method, of class DataModelStatusListener.
     */
    @Test
    public void testDataModelStatusChanged() {
        System.out.println("dataModelStatusChanged");
        DataModelStatusEvent status = null;
        DataModelStatusListener instance = new DataModelStatusListenerImpl();
        instance.dataModelStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DataModelStatusListenerImpl implements DataModelStatusListener {

        public boolean accepts(DataModelStatusEvent status) {
            return false;
        }

        public void dataModelStatusChanged(DataModelStatusEvent status) {
        }
    }

}
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
public class DummySinkTest {

    public DummySinkTest() {
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
     * Test of pushItem method, of class DummySink.
     */
    @Test
    public void testPushItem() throws Exception {
        System.out.println("pushItem");
        AbstractData e = null;
        DummySink instance = new DummySink();
        instance.pushItem(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
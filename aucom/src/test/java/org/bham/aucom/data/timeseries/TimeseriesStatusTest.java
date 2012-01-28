/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.timeseries;

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
public class TimeseriesStatusTest {

    public TimeseriesStatusTest() {
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
     * Test of values method, of class TimeseriesStatus.
     */
    @Test
    public void testValues() {
        System.out.println("values");
        TimeseriesStatus[] expResult = null;
        TimeseriesStatus[] result = TimeseriesStatus.values();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of valueOf method, of class TimeseriesStatus.
     */
    @Test
    public void testValueOf() {
        System.out.println("valueOf");
        String name = "";
        TimeseriesStatus expResult = null;
        TimeseriesStatus result = TimeseriesStatus.valueOf(name);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
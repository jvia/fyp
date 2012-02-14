/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.timeseries;

import java.util.UUID;
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
public class ObservationTimeSeriesTest {

    public ObservationTimeSeriesTest() {
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
     * Test of getGeneratorId method, of class ObservationTimeSeries.
     */
    @Test
    public void testGetGeneratorId() {
        System.out.println("getGeneratorId");
        ObservationTimeSeries instance = new ObservationTimeSeries();
        UUID expResult = null;
        UUID result = instance.getGeneratorId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of equals method, of class ObservationTimeSeries.
     */
    @Test
    public void testEquals() {
        System.out.println("equals");
        Object arg0 = null;
        ObservationTimeSeries instance = new ObservationTimeSeries();
        boolean expResult = false;
        boolean result = instance.equals(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
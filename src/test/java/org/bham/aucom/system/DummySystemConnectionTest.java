/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.system;

import org.bham.aucom.data.timeseries.TimeSeries;
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
public class DummySystemConnectionTest {

    public DummySystemConnectionTest() {
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
     * Test of iConnect method, of class DummySystemConnection.
     */
    @Test
    public void testIConnect() throws Exception {
        System.out.println("iConnect");
        DummySystemConnection instance = new DummySystemConnection();
        instance.iConnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iDisconnect method, of class DummySystemConnection.
     */
    @Test
    public void testIDisconnect() {
        System.out.println("iDisconnect");
        DummySystemConnection instance = new DummySystemConnection();
        instance.iDisconnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getObservationTimeSeries method, of class DummySystemConnection.
     */
    @Test
    public void testGetObservationTimeSeries() {
        System.out.println("getObservationTimeSeries");
        DummySystemConnection instance = new DummySystemConnection();
        TimeSeries expResult = null;
        TimeSeries result = instance.getObservationTimeSeries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
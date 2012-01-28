/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.main.prepare;

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
public class ConvertFromDatToObservationTimeseriesTest {

    public ConvertFromDatToObservationTimeseriesTest() {
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
     * Test of main method, of class ConvertFromDatToObservationTimeseries.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ConvertFromDatToObservationTimeseries.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of convertDatToObservation method, of class ConvertFromDatToObservationTimeseries.
     */
    @Test
    public void testConvertDatToObservation() {
        System.out.println("convertDatToObservation");
        String str = "";
        ConvertFromDatToObservationTimeseries instance = new ConvertFromDatToObservationTimeseries();
        instance.convertDatToObservation(str);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
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
public class ClassificationTimeSeriesTest {

    public ClassificationTimeSeriesTest() {
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
     * Test of createRandomTimeSeries method, of class ClassificationTimeSeries.
     */
    @Test
    public void testCreateRandomTimeSeries() {
        System.out.println("createRandomTimeSeries");
        ClassificationTimeSeries expResult = null;
        ClassificationTimeSeries result = ClassificationTimeSeries.createRandomTimeSeries();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
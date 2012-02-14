/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io;

import java.io.File;
import java.io.IOException;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
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
public class TimeSeriesInputTest {

    public TimeSeriesInputTest() {
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
     * Test of read method, of class TimeSeriesInput.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        File file = null;
        TimeSeriesInput instance = new TimeSeriesInputImpl();
        TimeSeries expResult = null;
        TimeSeries result = instance.read(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TimeSeriesInputImpl implements TimeSeriesInput {

        public TimeSeries<?> read(File file) throws ValidityException, ParsingException, IOException {
            return null;
        }
    }

}
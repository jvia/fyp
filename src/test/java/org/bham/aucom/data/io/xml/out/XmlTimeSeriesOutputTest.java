/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io.xml.out;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.junit.*;

import java.io.File;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author jxv911
 */
public class XmlTimeSeriesOutputTest {

    public XmlTimeSeriesOutputTest() {
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
     * Test of write method, of class XmlTimeSeriesOutput.
     */
    @Test
    public void testWrite() {
        System.out.println("write");
        TimeSeries<Classification> timeSeriesTowrite = null;
        File file = null;
        XmlTimeSeriesOutput instance = new XmlTimeSeriesOutput();
        boolean expResult = false;
        boolean result = instance.write(timeSeriesTowrite, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
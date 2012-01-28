/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.main.prepare;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.junit.runners.Suite;

/**
 *
 * @author jxv911
 */
@RunWith(Suite.class)
@Suite.SuiteClasses({org.bham.aucom.main.prepare.TransformToRelativeTimestampsTest.class,org.bham.aucom.main.prepare.OrderTest.class,org.bham.aucom.main.prepare.GetDataTypesFromObsTimeSeriesTest.class,org.bham.aucom.main.prepare.SaveTDFToCSVTest.class,org.bham.aucom.main.prepare.FixTimestampsTest.class,org.bham.aucom.main.prepare.ConvertFromDatToObservationTimeseriesTest.class,org.bham.aucom.main.prepare.TransformClTimeSeriesToCsvTest.class})
public class PrepareSuite {

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

}
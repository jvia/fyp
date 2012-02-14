/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data;

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
@Suite.SuiteClasses({org.bham.aucom.data.ConfigurationFailedExceptionTest.class,org.bham.aucom.data.DataTypeTest.class,org.bham.aucom.data.timeseries.TimeseriesSuite.class,org.bham.aucom.data.encoder.EncoderSuite.class,org.bham.aucom.data.DataAttributesTest.class,org.bham.aucom.data.ClassificationTimeSeriesDescriptiveStatisticsTest.class,org.bham.aucom.data.ClassificationTest.class,org.bham.aucom.data.SingleScoreTest.class,org.bham.aucom.data.SystemFaultStatusTest.class,org.bham.aucom.data.FileTypeTest.class,org.bham.aucom.data.TemporalDurationFeatureTest.class,org.bham.aucom.data.ScoreTest.class,org.bham.aucom.data.RangeScoreTest.class,org.bham.aucom.data.AttributableObjectTest.class,org.bham.aucom.data.TemporalProbabilityFeatureTest.class,org.bham.aucom.data.NodeGraphXYDataItemTest.class,org.bham.aucom.data.io.IoSuite.class,org.bham.aucom.data.management.ManagementSuite.class,org.bham.aucom.data.util.UtilSuite.class,org.bham.aucom.data.ObservationTest.class,org.bham.aucom.data.DomainFeatureTest.class,org.bham.aucom.data.LinkEnumTest.class,org.bham.aucom.data.ConsistentObservationCheckerTest.class,org.bham.aucom.data.AbstractDataTest.class})
public class DataSuite {

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
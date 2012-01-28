/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.tranform;

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
@Suite.SuiteClasses({org.bham.aucom.fts.tranform.RawDataToCsvRowTest.class,org.bham.aucom.fts.tranform.TransformNodeEventListenerTest.class,org.bham.aucom.fts.tranform.DocumentToObservationTest.class,org.bham.aucom.fts.tranform.GenerateTemporalProbabilityFeatureTest.class,org.bham.aucom.fts.tranform.TemporalDurationFeatureGeneratorTest.class,org.bham.aucom.fts.tranform.CalculateDurationTest.class,org.bham.aucom.fts.tranform.AssertConsistentObservationTest.class,org.bham.aucom.fts.tranform.TransformNodeEventTest.class,org.bham.aucom.fts.tranform.CropTimestampFromDataTest.class,org.bham.aucom.fts.tranform.StringToBufferOutputEventTest.class,org.bham.aucom.fts.tranform.EncodeDataTest.class,org.bham.aucom.fts.tranform.PostofixToStringTest.class,org.bham.aucom.fts.tranform.T2GramDataToStringTest.class,org.bham.aucom.fts.tranform.CalcEntropyAvgScoreTest.class,org.bham.aucom.fts.tranform.CountFrequencyForClassesTest.class,org.bham.aucom.fts.tranform.GenerateTemporalDurationFeatureTest.class,org.bham.aucom.fts.tranform.CalcDummyScoreTest.class,org.bham.aucom.fts.tranform.CountDifferentClassesTest.class,org.bham.aucom.fts.tranform.ClassificateTest.class,org.bham.aucom.fts.tranform.CountEventsTest.class,org.bham.aucom.fts.tranform.CalculateFrequencyTest.class,org.bham.aucom.fts.tranform.PauseNodeTest.class,org.bham.aucom.fts.tranform.CalcMeanvalueTest.class,org.bham.aucom.fts.tranform.MarkNextDataPointAsLastTest.class,org.bham.aucom.fts.tranform.CountDataTypesTest.class,org.bham.aucom.fts.tranform.AbstractAucomTranformNodeTest.class,org.bham.aucom.fts.tranform.EntropyAvgCalculatorTest.class,org.bham.aucom.fts.tranform.AddAttributeToElementTest.class})
public class TranformSuite {

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
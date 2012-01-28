/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector;

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
@Suite.SuiteClasses({org.bham.aucom.diagnoser.t2gram.detector.T2GramDetectorTest.class,org.bham.aucom.diagnoser.t2gram.detector.T2GramDetectorPanelTest.class,org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyclassificatorSuite.class,org.bham.aucom.diagnoser.t2gram.detector.ClassificatorSelectionPanelTest.class})
public class DetectorSuite {

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
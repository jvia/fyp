/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram;

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
@Suite.SuiteClasses({org.bham.aucom.diagnoser.t2gram.model.ModelSuite.class,org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactoryTest.class,org.bham.aucom.diagnoser.t2gram.visualizer.VisualizerSuite.class,org.bham.aucom.diagnoser.t2gram.analyser.AnalyserSuite.class,org.bham.aucom.diagnoser.t2gram.T2GramModelTrainerTest.class,org.bham.aucom.diagnoser.t2gram.ModelExceptionTest.class,org.bham.aucom.diagnoser.t2gram.AbstractModelTrainerTest.class,org.bham.aucom.diagnoser.t2gram.T2GramModelImpTest.class,org.bham.aucom.diagnoser.t2gram.detector.DetectorSuite.class,org.bham.aucom.diagnoser.t2gram.T2GramModelITest.class,org.bham.aucom.diagnoser.t2gram.ProbabilityFactoryTest.class,org.bham.aucom.diagnoser.t2gram.ProbabilityDistributionTest.class})
public class T2gramSuite {

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
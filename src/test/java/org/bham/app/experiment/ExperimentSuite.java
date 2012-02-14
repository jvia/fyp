/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.app.experiment;

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
@Suite.SuiteClasses({org.bham.app.experiment.ExperimentTest.class,org.bham.app.experiment.ResultTest.class,org.bham.app.experiment.CrossValidateExperimentFactoryTest.class,org.bham.app.experiment.TrainModelExperimentFactoryTest.class,org.bham.app.experiment.CrossValidateExperimentTest.class,org.bham.app.experiment.CastObservationCollectionTest.class,org.bham.app.experiment.SaveDurationAsCSVFactoryTest.class,org.bham.app.experiment.TrainAndSaveExperimentTest.class,org.bham.app.experiment.TrainOptimizeClassificateExperimentFactoryTest.class,org.bham.app.experiment.TrainModelOnSingleFileExperimentTest.class,org.bham.app.experiment.ExperimenterTest.class,org.bham.app.experiment.CastExperimentFactoryTest.class,org.bham.app.experiment.CastObservationCollectionFactoryTest.class,org.bham.app.experiment.ExperimentFactoryTest.class,org.bham.app.experiment.OptimizeClassificatorExperimentFactoryTest.class,org.bham.app.experiment.ClassificationToCSVTest.class,org.bham.app.experiment.LoadAndTestExperimentFactoryTest.class,org.bham.app.experiment.LoadAndTestExperimentTest.class,org.bham.app.experiment.SaveDurationsAsCSVTest.class,org.bham.app.experiment.ClassificationToCSVFactoryTest.class,org.bham.app.experiment.CastExperimentTest.class,org.bham.app.experiment.OptimizeClassificatorExperimentTest.class,org.bham.app.experiment.TrainOptimizeClassificateExperimentTest.class})
public class ExperimentSuite {

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
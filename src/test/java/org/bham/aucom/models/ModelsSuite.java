/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.models;

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
@Suite.SuiteClasses({org.bham.aucom.models.classificator.ClassificatorSuite.class,org.bham.aucom.models.probability.ProbabilitySuite.class,org.bham.aucom.models.markov.MarkovSuite.class,org.bham.aucom.models.hmm.HmmSuite.class,org.bham.aucom.models.frequency.FrequencySuite.class})
public class ModelsSuite {

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
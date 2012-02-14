/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui.threshold;

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
@Suite.SuiteClasses({org.bham.aucom.gui.threshold.HysteresisThresholdPanelTest.class,org.bham.aucom.gui.threshold.AbstractCreateAnomalyClassificatorPanelTest.class,org.bham.aucom.gui.threshold.StatisticalAnomalyClassificatorCreatePanelTest.class,org.bham.aucom.gui.threshold.MeanThresholdPanelTest.class})
public class ThresholdSuite {

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
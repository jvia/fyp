/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.visualizer;

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
@Suite.SuiteClasses({org.bham.aucom.diagnoser.t2gram.visualizer.ExtractTimestampScoreClassificationTest.class,org.bham.aucom.diagnoser.t2gram.visualizer.CropTimestampFromtripleTest.class,org.bham.aucom.diagnoser.t2gram.visualizer.T2GramVisualizerPanelTest.class,org.bham.aucom.diagnoser.t2gram.visualizer.T2GramVisualizerTest.class,org.bham.aucom.diagnoser.t2gram.visualizer.T2GramVisualizerGraphTest.class})
public class VisualizerSuite {

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
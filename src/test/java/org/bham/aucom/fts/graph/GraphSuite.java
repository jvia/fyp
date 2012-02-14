/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.graph;

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
@Suite.SuiteClasses({org.bham.aucom.fts.graph.OptimizerGraphTest.class,org.bham.aucom.fts.graph.DetectorGraphTest.class,org.bham.aucom.fts.graph.DataTypeExtractionGraphTest.class,org.bham.aucom.fts.graph.TemporalDurationFeatureGenerationGraphTest.class,org.bham.aucom.fts.graph.AbstractAucomGraphTest.class,org.bham.aucom.fts.graph.DataSequenceStatisticsGraphTest.class,org.bham.aucom.fts.graph.DataSequenceStatisticsTest.class,org.bham.aucom.fts.graph.T2GramTrainerGraphTest.class,org.bham.aucom.fts.graph.ObservationToScoreGraphTest.class})
public class GraphSuite {

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
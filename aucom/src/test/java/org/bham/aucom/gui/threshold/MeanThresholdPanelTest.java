/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui.threshold;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jxv911
 */
public class MeanThresholdPanelTest {

    public MeanThresholdPanelTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of getAnomalyClassificator method, of class MeanThresholdPanel.
     */
    @Test
    public void testGetAnomalyClassificator() throws Exception {
        System.out.println("getAnomalyClassificator");
        MeanThresholdPanel instance = new MeanThresholdPanel();
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.getAnomalyClassificator();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
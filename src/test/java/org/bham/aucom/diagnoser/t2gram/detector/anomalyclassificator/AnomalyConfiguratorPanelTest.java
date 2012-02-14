/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

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
public class AnomalyConfiguratorPanelTest {

    public AnomalyConfiguratorPanelTest() {
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
     * Test of getaCfg method, of class AnomalyConfiguratorPanel.
     */
    @Test
    public void testGetaCfg() {
        System.out.println("getaCfg");
        AnomalyConfiguratorPanel instance = new AnomalyConfiguratorPanel();
        AnomalyConfigurator expResult = null;
        AnomalyConfigurator result = instance.getaCfg();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setAnomalyConfigurator method, of class AnomalyConfiguratorPanel.
     */
    @Test
    public void testSetAnomalyConfigurator() {
        System.out.println("setAnomalyConfigurator");
        AnomalyConfigurator aCfg = null;
        AnomalyConfiguratorPanel instance = new AnomalyConfiguratorPanel();
        instance.setAnomalyConfigurator(aCfg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
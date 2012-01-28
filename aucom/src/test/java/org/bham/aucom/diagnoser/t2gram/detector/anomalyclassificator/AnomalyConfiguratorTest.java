/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import org.bham.aucom.data.ConfigurationFailedException;
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
public class AnomalyConfiguratorTest {

    public AnomalyConfiguratorTest() {
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
     * Test of configure method, of class AnomalyConfigurator.
     */
    @Test
    public void testConfigure() throws Exception {
        System.out.println("configure");
        AnomalyClassificator in = null;
        AnomalyConfigurator instance = new AnomalyConfiguratorImpl();
        instance.configure(in);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AnomalyConfiguratorImpl implements AnomalyConfigurator {

        public void configure(AnomalyClassificator in) throws ConfigurationFailedException {
        }
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.timeseries;

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
public class AnomalyClassificatorListenerTest {

    public AnomalyClassificatorListenerTest() {
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
     * Test of anomalyClassificatorStatusChanged method, of class AnomalyClassificatorListener.
     */
    @Test
    public void testAnomalyClassificatorStatusChanged() {
        System.out.println("anomalyClassificatorStatusChanged");
        AnomalyClassificatorStatusEvent status = null;
        AnomalyClassificatorListener instance = new AnomalyClassificatorListenerImpl();
        instance.anomalyClassificatorStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AnomalyClassificatorListenerImpl implements AnomalyClassificatorListener {

        public void anomalyClassificatorStatusChanged(AnomalyClassificatorStatusEvent status) {
        }
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser;

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
public class DetectionListenerTest {

    public DetectionListenerTest() {
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
     * Test of handleDetectorEvent method, of class DetectionListener.
     */
    @Test
    public void testHandleDetectorEvent() {
        System.out.println("handleDetectorEvent");
        DetectorEvent event = null;
        DetectionListener instance = new DetectionListenerImpl();
        instance.handleDetectorEvent(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DetectionListenerImpl implements DetectionListener {

        public void handleDetectorEvent(DetectorEvent event) {
        }
    }

}
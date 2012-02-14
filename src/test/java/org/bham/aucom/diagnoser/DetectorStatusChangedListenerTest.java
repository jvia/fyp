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
public class DetectorStatusChangedListenerTest {

    public DetectorStatusChangedListenerTest() {
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
     * Test of handleDetectorStatusChangedEvent method, of class DetectorStatusChangedListener.
     */
    @Test
    public void testHandleDetectorStatusChangedEvent() {
        System.out.println("handleDetectorStatusChangedEvent");
        DetectorStatusChangedEvent evt = null;
        DetectorStatusChangedListener instance = new DetectorStatusChangedListenerImpl();
        instance.handleDetectorStatusChangedEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class DetectorStatusChangedListenerImpl implements DetectorStatusChangedListener {

        public void handleDetectorStatusChangedEvent(DetectorStatusChangedEvent evt) {
        }
    }

}
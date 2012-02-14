/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.xcfrecorder;

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
public class RecorderStatusListenerTest {

    public RecorderStatusListenerTest() {
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
     * Test of handleRecorderStatusEvent method, of class RecorderStatusListener.
     */
    @Test
    public void testHandleRecorderStatusEvent() {
        System.out.println("handleRecorderStatusEvent");
        RecorderStatusChangedEvent evt = null;
        RecorderStatusListener instance = new RecorderStatusListenerImpl();
        instance.handleRecorderStatusEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class RecorderStatusListenerImpl implements RecorderStatusListener {

        public void handleRecorderStatusEvent(RecorderStatusChangedEvent evt) {
        }
    }

}
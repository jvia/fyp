/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.sink;

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
public class SinkStatusListenerTest {

    public SinkStatusListenerTest() {
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
     * Test of sinkStatusChanged method, of class SinkStatusListener.
     */
    @Test
    public void testSinkStatusChanged() {
        System.out.println("sinkStatusChanged");
        AucomSinkStatusEvent event = null;
        SinkStatusListener instance = new SinkStatusListenerImpl();
        instance.sinkStatusChanged(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class SinkStatusListenerImpl implements SinkStatusListener {

        public void sinkStatusChanged(AucomSinkStatusEvent event) {
        }
    }

}
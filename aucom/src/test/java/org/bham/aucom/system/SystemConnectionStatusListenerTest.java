/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.system;

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
public class SystemConnectionStatusListenerTest {

    public SystemConnectionStatusListenerTest() {
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
     * Test of handleSystemConnectionEvent method, of class SystemConnectionStatusListener.
     */
    @Test
    public void testHandleSystemConnectionEvent() {
        System.out.println("handleSystemConnectionEvent");
        SystemConnectionStatusChangedEvent event = null;
        SystemConnectionStatusListener instance = new SystemConnectionStatusListenerImpl();
        instance.handleSystemConnectionEvent(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class SystemConnectionStatusListenerImpl implements SystemConnectionStatusListener {

        public void handleSystemConnectionEvent(SystemConnectionStatusChangedEvent event) {
        }
    }

}
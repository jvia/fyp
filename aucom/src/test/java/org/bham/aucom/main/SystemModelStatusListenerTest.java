/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.main;

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
public class SystemModelStatusListenerTest {

    public SystemModelStatusListenerTest() {
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
     * Test of systemModelStatusChanged method, of class SystemModelStatusListener.
     */
    @Test
    public void testSystemModelStatusChanged() {
        System.out.println("systemModelStatusChanged");
        SystemModelStatusEvent evt = null;
        SystemModelStatusListener instance = new SystemModelStatusListenerImpl();
        instance.systemModelStatusChanged(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class SystemModelStatusListenerImpl implements SystemModelStatusListener {

        public void systemModelStatusChanged(SystemModelStatusEvent evt) {
        }
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.source;

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
public class SourceEventListenerTest {

    public SourceEventListenerTest() {
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
     * Test of sourceEventFired method, of class SourceEventListener.
     */
    @Test
    public void testSourceEventFired() {
        System.out.println("sourceEventFired");
        SourceEvent event = null;
        SourceEventListener instance = new SourceEventListenerImpl();
        instance.sourceEventFired(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class SourceEventListenerImpl implements SourceEventListener {

        public void sourceEventFired(SourceEvent event) {
        }
    }

}
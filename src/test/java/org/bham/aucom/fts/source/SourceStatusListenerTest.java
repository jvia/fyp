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
public class SourceStatusListenerTest {

    public SourceStatusListenerTest() {
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
     * Test of sourceStatusChanged method, of class SourceStatusListener.
     */
    @Test
    public void testSourceStatusChanged() {
        System.out.println("sourceStatusChanged");
        SourceStatusEvent event = null;
        SourceStatusListener instance = new SourceStatusListenerImpl();
        instance.sourceStatusChanged(event);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class SourceStatusListenerImpl implements SourceStatusListener {

        public void sourceStatusChanged(SourceStatusEvent event) {
        }
    }

}
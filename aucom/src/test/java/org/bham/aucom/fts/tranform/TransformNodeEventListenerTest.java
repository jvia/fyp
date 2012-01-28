/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.tranform;

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
public class TransformNodeEventListenerTest {

    public TransformNodeEventListenerTest() {
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
     * Test of handleTransformNodeEvent method, of class TransformNodeEventListener.
     */
    @Test
    public void testHandleTransformNodeEvent() {
        System.out.println("handleTransformNodeEvent");
        TransformNodeEvent evt = null;
        TransformNodeEventListener instance = new TransformNodeEventListenerImpl();
        instance.handleTransformNodeEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class TransformNodeEventListenerImpl implements TransformNodeEventListener {

        public void handleTransformNodeEvent(TransformNodeEvent evt) {
        }
    }

}
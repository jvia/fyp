/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.sink;

import net.sf.xcf.fts.Event;
import net.sf.xcf.fts.nodes.sink.BufferOutputEvent;
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
public class OutputStreamSinkOverridingTest {

    public OutputStreamSinkOverridingTest() {
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
     * Test of handleEvent method, of class OutputStreamSinkOverriding.
     */
    @Test
    public void testHandleEvent() throws Exception {
        System.out.println("handleEvent");
        Event<? extends BufferOutputEvent> input = null;
        OutputStreamSinkOverriding instance = null;
        Event expResult = null;
        Event result = instance.handleEvent(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of pushItem method, of class OutputStreamSinkOverriding.
     */
    @Test
    public void testPushItem() throws Exception {
        System.out.println("pushItem");
        BufferOutputEvent evt = null;
        OutputStreamSinkOverriding instance = null;
        instance.pushItem(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
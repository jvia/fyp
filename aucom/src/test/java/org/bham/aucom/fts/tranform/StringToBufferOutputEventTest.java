/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.tranform;

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
public class StringToBufferOutputEventTest {

    public StringToBufferOutputEventTest() {
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
     * Test of transform method, of class StringToBufferOutputEvent.
     */
    @Test
    public void testTransform() throws Exception {
        System.out.println("transform");
        String arg0 = "";
        StringToBufferOutputEvent instance = new StringToBufferOutputEvent();
        BufferOutputEvent expResult = null;
        BufferOutputEvent result = instance.transform(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
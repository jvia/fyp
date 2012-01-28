/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.AbstractData;
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
public class MarkNextDataPointAsLastTest {

    public MarkNextDataPointAsLastTest() {
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
     * Test of iTransform method, of class MarkNextDataPointAsLast.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        AbstractData input = null;
        MarkNextDataPointAsLast instance = new MarkNextDataPointAsLast();
        AbstractData expResult = null;
        AbstractData result = instance.iTransform(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setMarkNextElementAsLast method, of class MarkNextDataPointAsLast.
     */
    @Test
    public void testSetMarkNextElementAsLast() {
        System.out.println("setMarkNextElementAsLast");
        MarkNextDataPointAsLast instance = new MarkNextDataPointAsLast();
        instance.setMarkNextElementAsLast();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
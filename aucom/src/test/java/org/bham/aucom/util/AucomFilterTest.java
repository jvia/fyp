/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

import java.util.logging.LogRecord;
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
public class AucomFilterTest {

    public AucomFilterTest() {
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
     * Test of isLoggable method, of class AucomFilter.
     */
    @Test
    public void testIsLoggable() {
        System.out.println("isLoggable");
        LogRecord lr = null;
        AucomFilter instance = new AucomFilter();
        boolean expResult = false;
        boolean result = instance.isLoggable(lr);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
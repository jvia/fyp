/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

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
public class AucomLoggerTest {

    public AucomLoggerTest() {
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
     * Test of info method, of class AucomLogger.
     */
    @Test
    public void testInfo() {
        System.out.println("info");
        Object o = null;
        String msg = "";
        AucomLogger.info(o, msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of severe method, of class AucomLogger.
     */
    @Test
    public void testSevere() {
        System.out.println("severe");
        Object o = null;
        String msg = "";
        AucomLogger.severe(o, msg);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
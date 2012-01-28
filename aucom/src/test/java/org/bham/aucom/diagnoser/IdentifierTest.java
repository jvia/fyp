/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser;

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
public class IdentifierTest {

    public IdentifierTest() {
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
     * Test of monitor method, of class Identifier.
     */
    @Test
    public void testMonitor() {
        System.out.println("monitor");
        Detector detector = null;
        Identifier instance = new IdentifierImpl();
        instance.monitor(detector);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getResults method, of class Identifier.
     */
    @Test
    public void testGetResults() {
        System.out.println("getResults");
        Identifier instance = new IdentifierImpl();
        Object expResult = null;
        Object result = instance.getResults();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDetector method, of class Identifier.
     */
    @Test
    public void testGetDetector() {
        System.out.println("getDetector");
        Identifier instance = new IdentifierImpl();
        Detector expResult = null;
        Detector result = instance.getDetector();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IdentifierImpl implements Identifier {

        public void monitor(Detector detector) {
        }

        public Object getResults() {
            return null;
        }

        public Detector getDetector() {
            return null;
        }
    }

}
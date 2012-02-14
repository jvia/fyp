/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data;

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
public class ConsistentObservationCheckerTest {

    public ConsistentObservationCheckerTest() {
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
     * Test of check method, of class ConsistentObservationChecker.
     */
    @Test
    public void testCheck() {
        System.out.println("check");
        Observation observationToCheck = null;
        ConsistentObservationChecker instance = new ConsistentObservationChecker();
        boolean expResult = false;
        boolean result = instance.check(observationToCheck);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
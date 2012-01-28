/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.Observation;
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
public class AssertConsistentObservationTest {

    public AssertConsistentObservationTest() {
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
     * Test of iTransform method, of class AssertConsistentObservation.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        Observation input = null;
        AssertConsistentObservation instance = new AssertConsistentObservation();
        Observation expResult = null;
        Observation result = instance.iTransform(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data;

import nu.xom.Element;
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
public class ObservationTest {

    public ObservationTest() {
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
     * Test of createRandomObservation method, of class Observation.
     */
    @Test
    public void testCreateRandomObservation() {
        System.out.println("createRandomObservation");
        Observation expResult = null;
        Observation result = Observation.createRandomObservation();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getContent method, of class Observation.
     */
    @Test
    public void testGetContent() {
        System.out.println("getContent");
        Observation instance = new Observation();
        Element expResult = null;
        Element result = instance.getContent();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContent method, of class Observation.
     */
    @Test
    public void testSetContent() {
        System.out.println("setContent");
        Element content = null;
        Observation instance = new Observation();
        instance.setContent(content);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class Observation.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        Observation instance = new Observation();
        Object expResult = null;
        Object result = instance.copy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class Observation.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        Observation instance = new Observation();
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
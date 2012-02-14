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
public class DomainFeatureTest {

    public DomainFeatureTest() {
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
     * Test of getFeatureName method, of class DomainFeature.
     */
    @Test
    public void testGetFeatureName() {
        System.out.println("getFeatureName");
        DomainFeature instance = null;
        String expResult = "";
        String result = instance.getFeatureName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFeatureValue method, of class DomainFeature.
     */
    @Test
    public void testGetFeatureValue() {
        System.out.println("getFeatureValue");
        DomainFeature instance = null;
        String expResult = "";
        String result = instance.getFeatureValue();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class DomainFeature.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        DomainFeature instance = null;
        Object expResult = null;
        Object result = instance.copy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
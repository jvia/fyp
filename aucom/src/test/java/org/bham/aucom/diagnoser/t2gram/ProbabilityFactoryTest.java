/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram;

import java.util.HashMap;
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
public class ProbabilityFactoryTest {

    public ProbabilityFactoryTest() {
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
     * Test of create method, of class ProbabilityFactory.
     */
    @Test
    public void testCreate_doubleArr() {
        System.out.println("create");
        double[] trainingValues = null;
        ProbabilityFactory instance = new ProbabilityFactoryImpl();
        ProbabilityDistribution expResult = null;
        ProbabilityDistribution result = instance.create(trainingValues);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class ProbabilityFactory.
     */
    @Test
    public void testCreate_0args() {
        System.out.println("create");
        ProbabilityFactory instance = new ProbabilityFactoryImpl();
        ProbabilityDistribution expResult = null;
        ProbabilityDistribution result = instance.create();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttributes method, of class ProbabilityFactory.
     */
    @Test
    public void testGetAttributes() {
        System.out.println("getAttributes");
        ProbabilityFactory instance = new ProbabilityFactoryImpl();
        HashMap expResult = null;
        HashMap result = instance.getAttributes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAttribute method, of class ProbabilityFactory.
     */
    @Test
    public void testAddAttribute() {
        System.out.println("addAttribute");
        String propertyName = "";
        String propertyValue = "";
        ProbabilityFactory instance = new ProbabilityFactoryImpl();
        instance.addAttribute(propertyName, propertyValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAttribute method, of class ProbabilityFactory.
     */
    @Test
    public void testDeleteAttribute() {
        System.out.println("deleteAttribute");
        String propertyName = "";
        ProbabilityFactory instance = new ProbabilityFactoryImpl();
        instance.deleteAttribute(propertyName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttributeValue method, of class ProbabilityFactory.
     */
    @Test
    public void testGetAttributeValue() {
        System.out.println("getAttributeValue");
        String propertyName = "";
        ProbabilityFactory instance = new ProbabilityFactoryImpl();
        String expResult = "";
        String result = instance.getAttributeValue(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsAttribute method, of class ProbabilityFactory.
     */
    @Test
    public void testContainsAttribute() {
        System.out.println("containsAttribute");
        String propertyName = "";
        ProbabilityFactory instance = new ProbabilityFactoryImpl();
        boolean expResult = false;
        boolean result = instance.containsAttribute(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ProbabilityFactoryImpl extends ProbabilityFactory {

        public ProbabilityDistribution create(double[] trainingValues) {
            return null;
        }

        public ProbabilityDistribution create() {
            return null;
        }
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data;

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
public class AttributableObjectTest {

    public AttributableObjectTest() {
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
     * Test of getAttributes method, of class AttributableObject.
     */
    @Test
    public void testGetAttributes() {
        System.out.println("getAttributes");
        AttributableObject instance = new AttributableObjectImpl();
        HashMap expResult = null;
        HashMap result = instance.getAttributes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAttribute method, of class AttributableObject.
     */
    @Test
    public void testAddAttribute() {
        System.out.println("addAttribute");
        String propertyName = "";
        String propertyValue = "";
        AttributableObject instance = new AttributableObjectImpl();
        instance.addAttribute(propertyName, propertyValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAttribute method, of class AttributableObject.
     */
    @Test
    public void testDeleteAttribute() {
        System.out.println("deleteAttribute");
        String propertyName = "";
        AttributableObject instance = new AttributableObjectImpl();
        instance.deleteAttribute(propertyName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttributeValue method, of class AttributableObject.
     */
    @Test
    public void testGetAttributeValue() {
        System.out.println("getAttributeValue");
        String propertyName = "";
        AttributableObject instance = new AttributableObjectImpl();
        String expResult = "";
        String result = instance.getAttributeValue(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsAttribute method, of class AttributableObject.
     */
    @Test
    public void testContainsAttribute() {
        System.out.println("containsAttribute");
        String propertyName = "";
        AttributableObject instance = new AttributableObjectImpl();
        boolean expResult = false;
        boolean result = instance.containsAttribute(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AttributableObjectImpl implements AttributableObject {

        public HashMap<String, String> getAttributes() {
            return null;
        }

        public void addAttribute(String propertyName, String propertyValue) {
        }

        public void deleteAttribute(String propertyName) {
        }

        public String getAttributeValue(String propertyName) {
            return "";
        }

        public boolean containsAttribute(String propertyName) {
            return false;
        }
    }

}
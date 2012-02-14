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
public class AbstractDataTest {

    public AbstractDataTest() {
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
     * Test of getAttributes method, of class AbstractData.
     */
    @Test
    public void testGetAttributes() {
        System.out.println("getAttributes");
        AbstractData instance = new AbstractDataImpl();
        HashMap expResult = null;
        HashMap result = instance.getAttributes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAttribute method, of class AbstractData.
     */
    @Test
    public void testAddAttribute() {
        System.out.println("addAttribute");
        String propertyName = "";
        String propertyValue = "";
        AbstractData instance = new AbstractDataImpl();
        instance.addAttribute(propertyName, propertyValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAttribute method, of class AbstractData.
     */
    @Test
    public void testDeleteAttribute() {
        System.out.println("deleteAttribute");
        String propertyName = "";
        AbstractData instance = new AbstractDataImpl();
        instance.deleteAttribute(propertyName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttributeValue method, of class AbstractData.
     */
    @Test
    public void testGetAttributeValue() {
        System.out.println("getAttributeValue");
        String propertyName = "";
        AbstractData instance = new AbstractDataImpl();
        String expResult = "";
        String result = instance.getAttributeValue(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsAttribute method, of class AbstractData.
     */
    @Test
    public void testContainsAttribute() {
        System.out.println("containsAttribute");
        String propertyName = "";
        AbstractData instance = new AbstractDataImpl();
        boolean expResult = false;
        boolean result = instance.containsAttribute(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of markAsFirstElement method, of class AbstractData.
     */
    @Test
    public void testMarkAsFirstElement() {
        System.out.println("markAsFirstElement");
        AbstractData instance = new AbstractDataImpl();
        instance.markAsFirstElement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unmarkAsFirstElement method, of class AbstractData.
     */
    @Test
    public void testUnmarkAsFirstElement() {
        System.out.println("unmarkAsFirstElement");
        AbstractData instance = new AbstractDataImpl();
        instance.unmarkAsFirstElement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMarkedAsFirstElement method, of class AbstractData.
     */
    @Test
    public void testIsMarkedAsFirstElement() {
        System.out.println("isMarkedAsFirstElement");
        AbstractData instance = new AbstractDataImpl();
        boolean expResult = false;
        boolean result = instance.isMarkedAsFirstElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of markAsLastElement method, of class AbstractData.
     */
    @Test
    public void testMarkAsLastElement() {
        System.out.println("markAsLastElement");
        AbstractData instance = new AbstractDataImpl();
        instance.markAsLastElement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of unmarkAsLastElement method, of class AbstractData.
     */
    @Test
    public void testUnmarkAsLastElement() {
        System.out.println("unmarkAsLastElement");
        AbstractData instance = new AbstractDataImpl();
        instance.unmarkAsLastElement();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isMarkedAsLastElement method, of class AbstractData.
     */
    @Test
    public void testIsMarkedAsLastElement() {
        System.out.println("isMarkedAsLastElement");
        AbstractData instance = new AbstractDataImpl();
        boolean expResult = false;
        boolean result = instance.isMarkedAsLastElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTimestamp method, of class AbstractData.
     */
    @Test
    public void testGetTimestamp() {
        System.out.println("getTimestamp");
        AbstractData instance = new AbstractDataImpl();
        long expResult = 0L;
        long result = instance.getTimestamp();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setTimestamp method, of class AbstractData.
     */
    @Test
    public void testSetTimestamp() {
        System.out.println("setTimestamp");
        long inTimestamp = 0L;
        AbstractData instance = new AbstractDataImpl();
        instance.setTimestamp(inTimestamp);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class AbstractData.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        AbstractData instance = new AbstractDataImpl();
        Object expResult = null;
        Object result = instance.copy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setFirstElement method, of class AbstractData.
     */
    @Test
    public void testSetFirstElement() {
        System.out.println("setFirstElement");
        boolean isFirstElement = false;
        AbstractData instance = new AbstractDataImpl();
        instance.setFirstElement(isFirstElement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isFirstElement method, of class AbstractData.
     */
    @Test
    public void testIsFirstElement() {
        System.out.println("isFirstElement");
        AbstractData instance = new AbstractDataImpl();
        boolean expResult = false;
        boolean result = instance.isFirstElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setLastElement method, of class AbstractData.
     */
    @Test
    public void testSetLastElement() {
        System.out.println("setLastElement");
        boolean isLastElement = false;
        AbstractData instance = new AbstractDataImpl();
        instance.setLastElement(isLastElement);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of isLastElement method, of class AbstractData.
     */
    @Test
    public void testIsLastElement() {
        System.out.println("isLastElement");
        AbstractData instance = new AbstractDataImpl();
        boolean expResult = false;
        boolean result = instance.isLastElement();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AbstractDataImpl extends AbstractData {

        public Object copy() {
            return null;
        }
    }

}
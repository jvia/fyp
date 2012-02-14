/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.UUID;
import org.bham.aucom.data.LinkEnum;
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
public class AbstractLinkableNodeTest {

    public AbstractLinkableNodeTest() {
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
     * Test of getAttributes method, of class AbstractLinkableNode.
     */
    @Test
    public void testGetAttributes() {
        System.out.println("getAttributes");
        AbstractLinkableNode instance = new AbstractLinkableNodeImpl();
        HashMap expResult = null;
        HashMap result = instance.getAttributes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAttribute method, of class AbstractLinkableNode.
     */
    @Test
    public void testAddAttribute() {
        System.out.println("addAttribute");
        String propertyName = "";
        String propertyValue = "";
        AbstractLinkableNode instance = new AbstractLinkableNodeImpl();
        instance.addAttribute(propertyName, propertyValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAttribute method, of class AbstractLinkableNode.
     */
    @Test
    public void testDeleteAttribute() {
        System.out.println("deleteAttribute");
        String propertyName = "";
        AbstractLinkableNode instance = new AbstractLinkableNodeImpl();
        instance.deleteAttribute(propertyName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttributeValue method, of class AbstractLinkableNode.
     */
    @Test
    public void testGetAttributeValue() {
        System.out.println("getAttributeValue");
        String propertyName = "";
        AbstractLinkableNode instance = new AbstractLinkableNodeImpl();
        String expResult = "";
        String result = instance.getAttributeValue(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsAttribute method, of class AbstractLinkableNode.
     */
    @Test
    public void testContainsAttribute() {
        System.out.println("containsAttribute");
        String propertyName = "";
        AbstractLinkableNode instance = new AbstractLinkableNodeImpl();
        boolean expResult = false;
        boolean result = instance.containsAttribute(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLinks method, of class AbstractLinkableNode.
     */
    @Test
    public void testGetLinks_0args() {
        System.out.println("getLinks");
        AbstractLinkableNode instance = new AbstractLinkableNodeImpl();
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.getLinks();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsLink method, of class AbstractLinkableNode.
     */
    @Test
    public void testContainsLink() {
        System.out.println("containsLink");
        LinkEnum link = null;
        AbstractLinkableNode instance = new AbstractLinkableNodeImpl();
        boolean expResult = false;
        boolean result = instance.containsLink(link);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addLink method, of class AbstractLinkableNode.
     */
    @Test
    public void testAddLink_LinkEnum_UUID() {
        System.out.println("addLink");
        LinkEnum link = null;
        UUID uuid = null;
        AbstractLinkableNode instance = new AbstractLinkableNodeImpl();
        instance.addLink(link, uuid);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addLink method, of class AbstractLinkableNode.
     */
    @Test
    public void testAddLink_LinkEnum_List() {
        System.out.println("addLink");
        LinkEnum link = null;
        List<UUID> uuids = null;
        AbstractLinkableNode instance = new AbstractLinkableNodeImpl();
        instance.addLink(link, uuids);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getLinks method, of class AbstractLinkableNode.
     */
    @Test
    public void testGetLinks_LinkEnum() {
        System.out.println("getLinks");
        LinkEnum link = null;
        AbstractLinkableNode instance = new AbstractLinkableNodeImpl();
        List expResult = null;
        List result = instance.getLinks(link);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setId method, of class AbstractLinkableNode.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        UUID id = null;
        AbstractLinkableNode instance = new AbstractLinkableNodeImpl();
        instance.setId(id);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class AbstractLinkableNode.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        AbstractLinkableNode instance = new AbstractLinkableNodeImpl();
        UUID expResult = null;
        UUID result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AbstractLinkableNodeImpl extends AbstractLinkableNode {
    }

}
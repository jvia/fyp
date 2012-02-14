/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.main.prepare;

import java.io.File;
import java.util.ArrayList;
import java.util.SortedMap;
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
public class OrderTest {

    public OrderTest() {
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
     * Test of order method, of class Order.
     */
    @Test
    public void testOrder() {
        System.out.println("order");
        File f = null;
        Order instance = new Order();
        instance.order(f);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of sort method, of class Order.
     */
    @Test
    public void testSort() {
        System.out.println("sort");
        ArrayList<Element> list = null;
        Order instance = new Order();
        SortedMap expResult = null;
        SortedMap result = instance.sort(list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extractXml method, of class Order.
     */
    @Test
    public void testExtractXml() {
        System.out.println("extractXml");
        File inFile = null;
        Order instance = new Order();
        ArrayList expResult = null;
        ArrayList result = instance.extractXml(inFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Order.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Order.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class Order.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        File file = null;
        SortedMap<Long, Element> list = null;
        Order instance = new Order();
        instance.save(file, list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
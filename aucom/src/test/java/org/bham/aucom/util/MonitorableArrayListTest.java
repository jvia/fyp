/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

import org.junit.*;

import javax.swing.event.ListDataListener;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author jxv911
 */
public class MonitorableArrayListTest {

    public MonitorableArrayListTest() {
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
     * Test of get method, of class MonitorableArrayList.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        int index = 0;
        MonitorableArrayList instance = null;
        Object expResult = null;
        Object result = instance.get(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeListDataListener method, of class MonitorableArrayList.
     */
    @Test
    public void testRemoveListDataListener() {
        System.out.println("removeListDataListener");
        ListDataListener l = null;
        MonitorableArrayList instance = null;
        instance.removeListDataListener(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addListDataListener method, of class MonitorableArrayList.
     */
    @Test
    public void testAddListDataListener() {
        System.out.println("addListDataListener");
        ListDataListener l = null;
        MonitorableArrayList instance = null;
        instance.addListDataListener(l);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class MonitorableArrayList.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        MonitorableArrayList instance = null;
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyListeners method, of class MonitorableArrayList.
     */
    @Test
    public void testNotifyListeners() {
        System.out.println("notifyListeners");
        int from = 0;
        int to = 0;
        MonitorableArrayList instance = null;
        instance.notifyListeners(from, to);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyListenersRemove method, of class MonitorableArrayList.
     */
    @Test
    public void testNotifyListenersRemove() {
        System.out.println("notifyListenersRemove");
        int from = 0;
        int to = 0;
        MonitorableArrayList instance = null;
        instance.notifyListenersRemove(from, to);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyListenersAdd method, of class MonitorableArrayList.
     */
    @Test
    public void testNotifyListenersAdd() {
        System.out.println("notifyListenersAdd");
        int from = 0;
        int to = 0;
        MonitorableArrayList instance = null;
        instance.notifyListenersAdd(from, to);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class MonitorableArrayList.
     */
    @Test
    public void testAdd_GenericType() {
        System.out.println("add");
        Object o = null;
        MonitorableArrayList instance = null;
        boolean expResult = false;
        boolean result = instance.add(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class MonitorableArrayList.
     */
    @Test
    public void testAdd_int_GenericType() {
        System.out.println("add");
        int index = 0;
        Object element = null;
        MonitorableArrayList instance = null;
        instance.add(index, element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAll method, of class MonitorableArrayList.
     */
    @Test
    public void testAddAll() {
        System.out.println("addAll");
//        Collection<? extends E> o = null;
//        MonitorableArrayList instance = null;
//        boolean expResult = false;
//        boolean result = instance.addAll(o);
//        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of clear method, of class MonitorableArrayList.
     */
    @Test
    public void testClear() {
        System.out.println("clear");
        MonitorableArrayList instance = null;
        instance.clear();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class MonitorableArrayList.
     */
    @Test
    public void testRemove_int() {
        System.out.println("remove");
        int i = 0;
        MonitorableArrayList instance = null;
        Object expResult = null;
        Object result = instance.remove(i);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of remove method, of class MonitorableArrayList.
     */
    @Test
    public void testRemove_Object() {
        System.out.println("remove");
        Object o = null;
        MonitorableArrayList instance = null;
        boolean expResult = false;
        boolean result = instance.remove(o);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of set method, of class MonitorableArrayList.
     */
    @Test
    public void testSet() {
        System.out.println("set");
        int index = 0;
        Object element = null;
        MonitorableArrayList instance = null;
        Object expResult = null;
        Object result = instance.set(index, element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

import java.util.Observable;
import org.bham.aucom.diagnoser.Model;
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
public class ModelListTest {

    public ModelListTest() {
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
     * Test of put method, of class ModelList.
     */
    @Test
    public void testPut() {
        System.out.println("put");
        Model model = null;
        ModelList instance = null;
        instance.put(model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ModelList.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        Model model = null;
        ModelList instance = null;
        instance.delete(model);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of indexOf method, of class ModelList.
     */
    @Test
    public void testIndexOf() {
        System.out.println("indexOf");
        Model model = null;
        ModelList instance = null;
        int expResult = 0;
        int result = instance.indexOf(model);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElementAt method, of class ModelList.
     */
    @Test
    public void testGetElementAt() {
        System.out.println("getElementAt");
        int index = 0;
        ModelList instance = null;
        Object expResult = null;
        Object result = instance.getElementAt(index);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSize method, of class ModelList.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        ModelList instance = null;
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of update method, of class ModelList.
     */
    @Test
    public void testUpdate() {
        System.out.println("update");
        Observable arg0 = null;
        Object arg1 = null;
        ModelList instance = null;
        instance.update(arg0, arg1);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser;

import java.util.UUID;
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
public class ModelTest {

    public ModelTest() {
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
     * Test of isTrained method, of class Model.
     */
    @Test
    public void testIsTrained() {
        System.out.println("isTrained");
        Model instance = new ModelImpl();
        boolean expResult = false;
        boolean result = instance.isTrained();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class Model.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        Model instance = new ModelImpl();
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getId method, of class Model.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        Model instance = new ModelImpl();
        UUID expResult = null;
        UUID result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addModelListener method, of class Model.
     */
    @Test
    public void testAddModelListener() {
        System.out.println("addModelListener");
        Model instance = new ModelImpl();
        instance.addModelListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeModelListener method, of class Model.
     */
    @Test
    public void testRemoveModelListener() {
        System.out.println("removeModelListener");
        Model instance = new ModelImpl();
        instance.removeModelListener();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ModelImpl implements Model {

        public boolean isTrained() {
            return false;
        }

        public String getName() {
            return "";
        }

        public UUID getId() {
            return null;
        }

        public void addModelListener() {
        }

        public void removeModelListener() {
        }
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

import java.io.File;
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
public class FileOperatorTest {

    public FileOperatorTest() {
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
     * Test of getExtension method, of class FileOperator.
     */
    @Test
    public void testGetExtension() {
        System.out.println("getExtension");
        File file = null;
        String expResult = "";
        String result = FileOperator.getExtension(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class FileOperator.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        File file = null;
        String expResult = "";
        String result = FileOperator.getName(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getPath method, of class FileOperator.
     */
    @Test
    public void testGetPath() {
        System.out.println("getPath");
        File file = null;
        String expResult = "";
        String result = FileOperator.getPath(file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
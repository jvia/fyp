/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.app.experiment;

import java.util.LinkedList;
import org.bham.aucom.data.AttributableObject;
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
public class ResultTest {

    public ResultTest() {
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
     * Test of getAsCsvString method, of class Result.
     */
    @Test
    public void testGetAsCsvString() {
        System.out.println("getAsCsvString");
        Result instance = new ResultImpl();
        String expResult = "";
        String result = instance.getAsCsvString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setConfigurationElements method, of class Result.
     */
    @Test
    public void testSetConfigurationElements() {
        System.out.println("setConfigurationElements");
        LinkedList<AttributableObject> configurationElements = null;
        Result instance = new ResultImpl();
        instance.setConfigurationElements(configurationElements);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConfigurationElements method, of class Result.
     */
    @Test
    public void testGetConfigurationElements() {
        System.out.println("getConfigurationElements");
        Result instance = new ResultImpl();
        LinkedList expResult = null;
        LinkedList result = instance.getConfigurationElements();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class ResultImpl extends Result {

        public String getAsCsvString() {
            return "";
        }
    }

}
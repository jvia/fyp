/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.app.experiment;

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
public class SaveDurationsAsCSVTest {

    public SaveDurationsAsCSVTest() {
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
     * Test of preprocess method, of class SaveDurationsAsCSV.
     */
    @Test
    public void testPreprocess() throws Exception {
        System.out.println("preprocess");
        SaveDurationsAsCSV instance = null;
        instance.preprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of process method, of class SaveDurationsAsCSV.
     */
    @Test
    public void testProcess() throws Exception {
        System.out.println("process");
        SaveDurationsAsCSV instance = null;
        instance.process();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of call method, of class SaveDurationsAsCSV.
     */
    @Test
    public void testCall() throws Exception {
        System.out.println("call");
        SaveDurationsAsCSV instance = null;
        Result expResult = null;
        Result result = instance.call();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postprocess method, of class SaveDurationsAsCSV.
     */
    @Test
    public void testPostprocess() throws Exception {
        System.out.println("postprocess");
        SaveDurationsAsCSV instance = null;
        instance.postprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of toString method, of class SaveDurationsAsCSV.
     */
    @Test
    public void testToString() {
        System.out.println("toString");
        SaveDurationsAsCSV instance = null;
        String expResult = "";
        String result = instance.toString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
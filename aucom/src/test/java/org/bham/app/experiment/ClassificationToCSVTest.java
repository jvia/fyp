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
public class ClassificationToCSVTest {

    public ClassificationToCSVTest() {
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
     * Test of preprocess method, of class ClassificationToCSV.
     */
    @Test
    public void testPreprocess() throws Exception {
        System.out.println("preprocess");
        ClassificationToCSV instance = null;
        instance.preprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of process method, of class ClassificationToCSV.
     */
    @Test
    public void testProcess() {
        System.out.println("process");
        ClassificationToCSV instance = null;
        instance.process();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of postprocess method, of class ClassificationToCSV.
     */
    @Test
    public void testPostprocess() throws Exception {
        System.out.println("postprocess");
        ClassificationToCSV instance = null;
        instance.postprocess();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of call method, of class ClassificationToCSV.
     */
    @Test
    public void testCall() throws Exception {
        System.out.println("call");
        ClassificationToCSV instance = null;
        Result expResult = null;
        Result result = instance.call();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.main;

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
public class AnomalyRecognitionTest {

    public AnomalyRecognitionTest() {
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
     * Test of trainModels method, of class AnomalyRecognition.
     */
    @Test
    public void testTrainModels() {
        System.out.println("trainModels");
        AnomalyRecognition instance = new AnomalyRecognition();
        instance.trainModels();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of test method, of class AnomalyRecognition.
     */
    @Test
    public void testTest() {
        System.out.println("test");
        File f = null;
        AnomalyRecognition instance = new AnomalyRecognition();
        instance.test(f);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class AnomalyRecognition.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        AnomalyRecognition.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
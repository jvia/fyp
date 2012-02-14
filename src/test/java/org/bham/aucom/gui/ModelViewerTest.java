/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui;

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
public class ModelViewerTest {

    public ModelViewerTest() {
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
     * Test of load method, of class ModelViewer.
     */
    @Test
    public void testLoad() {
        System.out.println("load");
        File inFileToLoad = null;
        ModelViewer instance = new ModelViewer();
        instance.load(inFileToLoad);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of displayDistribution method, of class ModelViewer.
     */
    @Test
    public void testDisplayDistribution() {
        System.out.println("displayDistribution");
        String label = "";
        ModelViewer instance = new ModelViewer();
        instance.displayDistribution(label);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of updateView method, of class ModelViewer.
     */
    @Test
    public void testUpdateView() {
        System.out.println("updateView");
        ModelViewer instance = new ModelViewer();
        instance.updateView();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class ModelViewer.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        ModelViewer.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
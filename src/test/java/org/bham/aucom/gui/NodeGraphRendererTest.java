/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui;

import java.awt.Image;
import org.jfree.chart.plot.Plot;
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
public class NodeGraphRendererTest {

    public NodeGraphRendererTest() {
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
     * Test of getImage method, of class NodeGraphRenderer.
     */
    @Test
    public void testGetImage() {
        System.out.println("getImage");
        Plot plot = null;
        int series = 0;
        int item = 0;
        double x = 0.0;
        double y = 0.0;
        NodeGraphRenderer instance = new NodeGraphRenderer();
        Image expResult = null;
        Image result = instance.getImage(plot, series, item, x, y);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
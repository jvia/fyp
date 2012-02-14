/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

import java.awt.Color;
import java.awt.Image;
import java.util.ArrayList;
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
public class RedGreenGradientImageGeneratorTest {

    public RedGreenGradientImageGeneratorTest() {
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
     * Test of getColor method, of class RedGreenGradientImageGenerator.
     */
    @Test
    public void testGetColor() {
        System.out.println("getColor");
        double val = 0.0;
        Color expResult = null;
        Color result = RedGreenGradientImageGenerator.getColor(val);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImage method, of class RedGreenGradientImageGenerator.
     */
    @Test
    public void testGetImage_double_int() {
        System.out.println("getImage");
        double val = 0.0;
        int num = 0;
        Image expResult = null;
        Image result = RedGreenGradientImageGenerator.getImage(val, num);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getImage method, of class RedGreenGradientImageGenerator.
     */
    @Test
    public void testGetImage_Color() {
        System.out.println("getImage");
        Color c = null;
        Image expResult = null;
        Image result = RedGreenGradientImageGenerator.getImage(c);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAbnormalcolors method, of class RedGreenGradientImageGenerator.
     */
    @Test
    public void testGetAbnormalcolors() {
        System.out.println("getAbnormalcolors");
        ArrayList expResult = null;
        ArrayList result = RedGreenGradientImageGenerator.getAbnormalcolors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNormalcolors method, of class RedGreenGradientImageGenerator.
     */
    @Test
    public void testGetNormalcolors() {
        System.out.println("getNormalcolors");
        ArrayList expResult = null;
        ArrayList result = RedGreenGradientImageGenerator.getNormalcolors();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
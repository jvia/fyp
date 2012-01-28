/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui.charts;

import java.util.ArrayList;
import org.bham.aucom.data.Score;
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
public class EventViewTest {

    public EventViewTest() {
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
     * Test of info method, of class EventView.
     */
    @Test
    public void testInfo() {
        System.out.println("info");
        String info = "";
        EventView instance = null;
        instance.info(info);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of add method, of class EventView.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        ArrayList<Score> data = null;
        EventView instance = null;
        instance.add(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initChart method, of class EventView.
     */
    @Test
    public void testInitChart() {
        System.out.println("initChart");
        EventView instance = null;
        instance.initChart();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class EventView.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        EventView.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
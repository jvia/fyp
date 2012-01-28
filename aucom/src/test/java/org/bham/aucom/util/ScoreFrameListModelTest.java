/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

import java.util.ArrayList;
import org.bham.aucom.data.timeseries.DataTypeTimeSeries;
import org.bham.aucom.gui.charts.ProbabilityChartFrame;
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
public class ScoreFrameListModelTest {

    public ScoreFrameListModelTest() {
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
     * Test of add method, of class ScoreFrameListModel.
     */
    @Test
    public void testAdd() {
        System.out.println("add");
        ProbabilityChartFrame element = null;
        ScoreFrameListModel instance = new ScoreFrameListModel();
        instance.add(element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of delete method, of class ScoreFrameListModel.
     */
    @Test
    public void testDelete() {
        System.out.println("delete");
        ProbabilityChartFrame element = null;
        ScoreFrameListModel instance = new ScoreFrameListModel();
        instance.delete(element);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getElementAt method, of class ScoreFrameListModel.
     */
    @Test
    public void testGetElementAt() {
        System.out.println("getElementAt");
        int arg0 = 0;
        ScoreFrameListModel instance = new ScoreFrameListModel();
        Object expResult = null;
        Object result = instance.getElementAt(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of get method, of class ScoreFrameListModel.
     */
    @Test
    public void testGet() {
        System.out.println("get");
        String title = "";
        ScoreFrameListModel instance = new ScoreFrameListModel();
        ProbabilityChartFrame expResult = null;
        ProbabilityChartFrame result = instance.get(title);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAll method, of class ScoreFrameListModel.
     */
    @Test
    public void testGetAll() {
        System.out.println("getAll");
        ScoreFrameListModel instance = new ScoreFrameListModel();
        ArrayList expResult = null;
        ArrayList result = instance.getAll();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSize method, of class ScoreFrameListModel.
     */
    @Test
    public void testGetSize() {
        System.out.println("getSize");
        ScoreFrameListModel instance = new ScoreFrameListModel();
        int expResult = 0;
        int result = instance.getSize();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsKey method, of class ScoreFrameListModel.
     */
    @Test
    public void testContainsKey() {
        System.out.println("containsKey");
        ProbabilityChartFrame element = null;
        ScoreFrameListModel instance = new ScoreFrameListModel();
        boolean expResult = false;
        boolean result = instance.containsKey(element);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFramesForScoreSequence method, of class ScoreFrameListModel.
     */
    @Test
    public void testGetFramesForScoreSequence() {
        System.out.println("getFramesForScoreSequence");
        DataTypeTimeSeries s = null;
        ScoreFrameListModel instance = new ScoreFrameListModel();
        ArrayList expResult = null;
        ArrayList result = instance.getFramesForScoreSequence(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
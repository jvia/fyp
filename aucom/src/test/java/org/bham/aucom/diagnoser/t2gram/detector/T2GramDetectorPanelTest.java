/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector;

import java.io.File;
import org.bham.aucom.data.timeseries.TimeseriesStatusEvent;
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
public class T2GramDetectorPanelTest {

    public T2GramDetectorPanelTest() {
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
     * Test of loadModelAndRegisterForEvents method, of class T2GramDetectorPanel.
     */
    @Test
    public void testLoadModelAndRegisterForEvents() throws Exception {
        System.out.println("loadModelAndRegisterForEvents");
        File f = null;
        T2GramDetectorPanel instance = null;
        instance.loadModelAndRegisterForEvents(f);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of appendRowToInfoTextArea method, of class T2GramDetectorPanel.
     */
    @Test
    public void testAppendRowToInfoTextArea() {
        System.out.println("appendRowToInfoTextArea");
        String row = "";
        T2GramDetectorPanel instance = null;
        instance.appendRowToInfoTextArea(row);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of timeseriesStatusChanged method, of class T2GramDetectorPanel.
     */
    @Test
    public void testTimeseriesStatusChanged() {
        System.out.println("timeseriesStatusChanged");
        TimeseriesStatusEvent status = null;
        T2GramDetectorPanel instance = null;
        instance.timeseriesStatusChanged(status);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
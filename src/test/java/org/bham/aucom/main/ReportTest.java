/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.main;

import java.io.File;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import nu.xom.Element;
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
public class ReportTest {

    public ReportTest() {
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
     * Test of printComponents method, of class Report.
     */
    @Test
    public void testPrintComponents() {
        System.out.println("printComponents");
        LinkedHashMap<String, Integer> components = null;
        ArrayList<Element> elements = null;
        Report instance = new Report();
        instance.printComponents(components, elements);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of analyseComponents method, of class Report.
     */
    @Test
    public void testAnalyseComponents() {
        System.out.println("analyseComponents");
        ArrayList<Element> list = null;
        Report instance = new Report();
        LinkedHashMap expResult = null;
        LinkedHashMap result = instance.analyseComponents(list);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reportTimestamps method, of class Report.
     */
    @Test
    public void testReportTimestamps() {
        System.out.println("reportTimestamps");
        Report instance = new Report();
        instance.reportTimestamps();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class Report.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        Report.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extractXml method, of class Report.
     */
    @Test
    public void testExtractXml() {
        System.out.println("extractXml");
        File inFile = null;
        Report instance = new Report();
        ArrayList expResult = null;
        ArrayList result = instance.extractXml(inFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.main;

import java.io.File;
import java.util.ArrayList;
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
public class XcfFacesOutBoostDetectorTest {

    public XcfFacesOutBoostDetectorTest() {
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
     * Test of main method, of class XcfFacesOutBoostDetector.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        XcfFacesOutBoostDetector.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateBoostDetectoor method, of class XcfFacesOutBoostDetector.
     */
    @Test
    public void testGenerateBoostDetectoor() {
        System.out.println("generateBoostDetectoor");
        long duration = 0L;
        long offset = 0L;
        ArrayList expResult = null;
        ArrayList result = XcfFacesOutBoostDetector.generateBoostDetectoor(duration, offset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of generateXcfFacesOuts method, of class XcfFacesOutBoostDetector.
     */
    @Test
    public void testGenerateXcfFacesOuts() {
        System.out.println("generateXcfFacesOuts");
        long duration = 0L;
        long offset = 0L;
        ArrayList expResult = null;
        ArrayList result = XcfFacesOutBoostDetector.generateXcfFacesOuts(duration, offset);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class XcfFacesOutBoostDetector.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        ArrayList<Element> elements = null;
        File file = null;
        XcfFacesOutBoostDetector.save(elements, file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.main.prepare;

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
public class FixTimestampsTest {

    public FixTimestampsTest() {
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
     * Test of main method, of class FixTimestamps.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        FixTimestamps.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of extractXml method, of class FixTimestamps.
     */
    @Test
    public void testExtractXml() {
        System.out.println("extractXml");
        File inFile = null;
        FixTimestamps instance = new FixTimestamps();
        ArrayList expResult = null;
        ArrayList result = instance.extractXml(inFile);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of save method, of class FixTimestamps.
     */
    @Test
    public void testSave() {
        System.out.println("save");
        File file = null;
        ArrayList<Element> list = null;
        FixTimestamps instance = new FixTimestamps();
        instance.save(file, list);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
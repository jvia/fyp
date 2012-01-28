/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.graph;

import java.util.ArrayList;
import java.util.Set;
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
public class DataSequenceStatisticsTest {

    public DataSequenceStatisticsTest() {
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
     * Test of getDurationInSeconds method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetDurationInSeconds() {
        System.out.println("getDurationInSeconds");
        DataSequenceStatistics instance = null;
        long expResult = 0L;
        long result = instance.getDurationInSeconds();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getDurationAsString method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetDurationAsString() {
        System.out.println("getDurationAsString");
        DataSequenceStatistics instance = null;
        String expResult = "";
        String result = instance.getDurationAsString();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberClasses method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetNumberClasses() {
        System.out.println("getNumberClasses");
        DataSequenceStatistics instance = null;
        int expResult = 0;
        int result = instance.getNumberClasses();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getClassIds method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetClassIds() {
        System.out.println("getClassIds");
        DataSequenceStatistics instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getClassIds();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getSources method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetSources() {
        System.out.println("getSources");
        DataSequenceStatistics instance = null;
        Set expResult = null;
        Set result = instance.getSources();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberSources method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetNumberSources() {
        System.out.println("getNumberSources");
        DataSequenceStatistics instance = null;
        int expResult = 0;
        int result = instance.getNumberSources();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountForSources method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetCountForSources() {
        System.out.println("getCountForSources");
        String source = "";
        DataSequenceStatistics instance = null;
        int expResult = 0;
        int result = instance.getCountForSources(source);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getCountFrequencyForSource method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetCountFrequencyForSource() {
        System.out.println("getCountFrequencyForSource");
        String source = "";
        DataSequenceStatistics instance = null;
        double expResult = 0.0;
        double result = instance.getCountFrequencyForSource(source);
        assertEquals(expResult, result, 0.0);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScopes method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetScopes() {
        System.out.println("getScopes");
        DataSequenceStatistics instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getScopes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberScopes method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetNumberScopes() {
        System.out.println("getNumberScopes");
        DataSequenceStatistics instance = null;
        int expResult = 0;
        int result = instance.getNumberScopes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getTypes method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetTypes() {
        System.out.println("getTypes");
        DataSequenceStatistics instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberTypes method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetNumberTypes() {
        System.out.println("getNumberTypes");
        DataSequenceStatistics instance = null;
        int expResult = 0;
        int result = instance.getNumberTypes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getNumberElements method, of class DataSequenceStatistics.
     */
    @Test
    public void testGetNumberElements() {
        System.out.println("getNumberElements");
        DataSequenceStatistics instance = null;
        int expResult = 0;
        int result = instance.getNumberElements();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
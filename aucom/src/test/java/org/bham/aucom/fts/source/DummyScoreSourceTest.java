/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.source;

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
public class DummyScoreSourceTest {

    public DummyScoreSourceTest() {
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
     * Test of iNextItem method, of class DummyScoreSource.
     */
    @Test
    public void testINextItem() throws Exception {
        System.out.println("iNextItem");
        DummyScoreSource instance = new DummyScoreSource();
        Score expResult = null;
        Score result = instance.iNextItem();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iDisconnect method, of class DummyScoreSource.
     */
    @Test
    public void testIDisconnect() throws Exception {
        System.out.println("iDisconnect");
        DummyScoreSource instance = new DummyScoreSource();
        instance.iDisconnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iConnect method, of class DummyScoreSource.
     */
    @Test
    public void testIConnect() throws Exception {
        System.out.println("iConnect");
        DummyScoreSource instance = new DummyScoreSource();
        instance.iConnect();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
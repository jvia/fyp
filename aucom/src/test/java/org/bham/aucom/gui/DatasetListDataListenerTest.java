/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui;

import javax.swing.event.ListDataEvent;
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
public class DatasetListDataListenerTest {

    public DatasetListDataListenerTest() {
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
     * Test of contentsChanged method, of class DatasetListDataListener.
     */
    @Test
    public void testContentsChanged() {
        System.out.println("contentsChanged");
        ListDataEvent e = null;
        DatasetListDataListener instance = new DatasetListDataListener();
        instance.contentsChanged(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intervalAdded method, of class DatasetListDataListener.
     */
    @Test
    public void testIntervalAdded() {
        System.out.println("intervalAdded");
        ListDataEvent e = null;
        DatasetListDataListener instance = new DatasetListDataListener();
        instance.intervalAdded(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of intervalRemoved method, of class DatasetListDataListener.
     */
    @Test
    public void testIntervalRemoved() {
        System.out.println("intervalRemoved");
        ListDataEvent e = null;
        DatasetListDataListener instance = new DatasetListDataListener();
        instance.intervalRemoved(e);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
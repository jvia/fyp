/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.gui;

import java.awt.Component;
import javax.swing.JList;
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
public class ScoreListCellRendererTest {

    public ScoreListCellRendererTest() {
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
     * Test of getListCellRendererComponent method, of class ScoreListCellRenderer.
     */
    @Test
    public void testGetListCellRendererComponent() {
        System.out.println("getListCellRendererComponent");
        JList list = null;
        Object value = null;
        int index = 0;
        boolean isSelected = false;
        boolean hasFocus = false;
        ScoreListCellRenderer instance = new ScoreListCellRenderer();
        Component expResult = null;
        Component result = instance.getListCellRendererComponent(list, value, index, isSelected, hasFocus);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.sink;

import org.bham.aucom.data.DataType;
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
public class SaveT2GramDataTest {

    public SaveT2GramDataTest() {
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
     * Test of pushItem method, of class SaveT2GramData.
     */
    @Test
    public void testPushItem() throws Exception {
        System.out.println("pushItem");
        DataType data = null;
        SaveT2GramData instance = new SaveT2GramData();
        instance.pushItem(data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
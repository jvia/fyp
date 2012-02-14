/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.tranform;

import java.util.ArrayList;
import nu.xom.Attribute;
import org.bham.aucom.data.Observation;
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
public class AddAttributeToElementTest {

    public AddAttributeToElementTest() {
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
     * Test of setAttr method, of class AddAttributeToElement.
     */
    @Test
    public void testSetAttr() {
        System.out.println("setAttr");
        ArrayList<Attribute> attr = null;
        AddAttributeToElement instance = new AddAttributeToElement();
        instance.setAttr(attr);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttr method, of class AddAttributeToElement.
     */
    @Test
    public void testGetAttr() {
        System.out.println("getAttr");
        AddAttributeToElement instance = new AddAttributeToElement();
        ArrayList expResult = null;
        ArrayList result = instance.getAttr();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of iTransform method, of class AddAttributeToElement.
     */
    @Test
    public void testITransform() throws Exception {
        System.out.println("iTransform");
        Observation input = null;
        AddAttributeToElement instance = new AddAttributeToElement();
        Observation expResult = null;
        Observation result = instance.iTransform(input);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
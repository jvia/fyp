/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser;

import nu.xom.Document;
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
public class IdentifierFactoryTest {

    public IdentifierFactoryTest() {
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
     * Test of configure method, of class IdentifierFactory.
     */
    @Test
    public void testConfigure() {
        System.out.println("configure");
        Document doc = null;
        IdentifierFactory instance = new IdentifierFactoryImpl();
        instance.configure(doc);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of create method, of class IdentifierFactory.
     */
    @Test
    public void testCreate() {
        System.out.println("create");
        IdentifierFactory instance = new IdentifierFactoryImpl();
        Detector expResult = null;
        Detector result = instance.create();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IdentifierFactoryImpl implements IdentifierFactory {

        public void configure(Document doc) {
        }

        public Detector create() {
            return null;
        }
    }

}
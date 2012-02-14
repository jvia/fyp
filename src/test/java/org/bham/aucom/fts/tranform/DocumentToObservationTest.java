/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.fts.tranform;

import nu.xom.Document;
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
public class DocumentToObservationTest {

    public DocumentToObservationTest() {
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
     * Test of transform method, of class DocumentToObservation.
     */
    @Test
    public void testTransform() throws Exception {
        System.out.println("transform");
        Document arg0 = null;
        DocumentToObservation instance = new DocumentToObservation();
        Observation expResult = null;
        Observation result = instance.transform(arg0);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
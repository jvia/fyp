/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import java.util.HashMap;
import java.util.UUID;
import org.bham.aucom.data.timeseries.AnomalyClassificatorListener;
import org.bham.aucom.data.timeseries.AnomalyClassificatorStatusEvent;
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
public class AbstractAnomalyClassificatorTest {

    public AbstractAnomalyClassificatorTest() {
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
     * Test of getId method, of class AbstractAnomalyClassificator.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        AbstractAnomalyClassificator instance = null;
        UUID expResult = null;
        UUID result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttributes method, of class AbstractAnomalyClassificator.
     */
    @Test
    public void testGetAttributes() {
        System.out.println("getAttributes");
        AbstractAnomalyClassificator instance = null;
        HashMap expResult = null;
        HashMap result = instance.getAttributes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getName method, of class AbstractAnomalyClassificator.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        AbstractAnomalyClassificator instance = null;
        String expResult = "";
        String result = instance.getName();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addAttribute method, of class AbstractAnomalyClassificator.
     */
    @Test
    public void testAddAttribute() {
        System.out.println("addAttribute");
        String propertyName = "";
        String propertyValue = "";
        AbstractAnomalyClassificator instance = null;
        instance.addAttribute(propertyName, propertyValue);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of deleteAttribute method, of class AbstractAnomalyClassificator.
     */
    @Test
    public void testDeleteAttribute() {
        System.out.println("deleteAttribute");
        String propertyName = "";
        AbstractAnomalyClassificator instance = null;
        instance.deleteAttribute(propertyName);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttributeValue method, of class AbstractAnomalyClassificator.
     */
    @Test
    public void testGetAttributeValue() {
        System.out.println("getAttributeValue");
        String propertyName = "";
        AbstractAnomalyClassificator instance = null;
        String expResult = "";
        String result = instance.getAttributeValue(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsAttribute method, of class AbstractAnomalyClassificator.
     */
    @Test
    public void testContainsAttribute() {
        System.out.println("containsAttribute");
        String propertyName = "";
        AbstractAnomalyClassificator instance = null;
        boolean expResult = false;
        boolean result = instance.containsAttribute(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSequenceStatusListener method, of class AbstractAnomalyClassificator.
     */
    @Test
    public void testAddSequenceStatusListener() {
        System.out.println("addSequenceStatusListener");
        AnomalyClassificatorListener listener = null;
        AbstractAnomalyClassificator instance = null;
        instance.addSequenceStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeStatusListener method, of class AbstractAnomalyClassificator.
     */
    @Test
    public void testRemoveStatusListener() {
        System.out.println("removeStatusListener");
        AnomalyClassificatorListener listener = null;
        AbstractAnomalyClassificator instance = null;
        instance.removeStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fireStatusChangedEvent method, of class AbstractAnomalyClassificator.
     */
    @Test
    public void testFireStatusChangedEvent() {
        System.out.println("fireStatusChangedEvent");
        AnomalyClassificatorStatusEvent evt = null;
        AbstractAnomalyClassificator instance = null;
        instance.fireStatusChangedEvent(evt);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AbstractAnomalyClassificatorImpl extends AbstractAnomalyClassificator {

        public AbstractAnomalyClassificatorImpl() {
            super("");
        }
    }

}
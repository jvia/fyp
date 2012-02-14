/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.util;

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
public class AucomObservableTest {

    public AucomObservableTest() {
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
     * Test of notifyObservers method, of class AucomObservable.
     */
    @Test
    public void testNotifyObservers() {
        System.out.println("notifyObservers");
        AucomObservable instance = new AucomObservable();
        instance.notifyObservers();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of notifyObservers method, of class AucomObservable.
     */
    @Test
    public void testNotifyObservers_Object() {
        System.out.println("notifyObservers");
        Object o = null;
        AucomObservable instance = new AucomObservable();
        instance.notifyObservers(o);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
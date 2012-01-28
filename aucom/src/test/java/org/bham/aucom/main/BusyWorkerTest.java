/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.main;

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
public class BusyWorkerTest {

    public BusyWorkerTest() {
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
     * Test of intensiveCalc method, of class BusyWorker.
     */
    @Test
    public void testIntensiveCalc() {
        System.out.println("intensiveCalc");
        int numThreads = 0;
        BusyWorker.intensiveCalc(numThreads);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of fib method, of class BusyWorker.
     */
    @Test
    public void testFib() {
        System.out.println("fib");
        Long fib = null;
        Long expResult = null;
        Long result = BusyWorker.fib(fib);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of main method, of class BusyWorker.
     */
    @Test
    public void testMain() {
        System.out.println("main");
        String[] args = null;
        BusyWorker.main(args);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
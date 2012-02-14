/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io;

import java.io.File;
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
public class BinaryIOTest {

    public BinaryIOTest() {
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
     * Test of read method, of class BinaryIO.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        File fileToLoadFrom = null;
        BinaryIO instance = null;
        Object expResult = null;
        Object result = instance.read(fileToLoadFrom);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class BinaryIO.
     */
    @Test
    public void testWrite() {
        System.out.println("write");
        Object faultDetectionModelTowrite = null;
        File file = null;
        BinaryIO instance = null;
        boolean expResult = false;
        boolean result = instance.write(faultDetectionModelTowrite, file);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class BinaryIOImpl extends BinaryIO {

        public BinaryIOImpl() {
            super(null);
        }
    }

}
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
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
public class IOInterfaceTest {

    public IOInterfaceTest() {
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
     * Test of read method, of class IOInterface.
     */
    @Test
    public void testRead() throws Exception {
        System.out.println("read");
        File fileToLoad = null;
        IOInterface instance = new IOInterfaceImpl();
        Object expResult = null;
        Object result = instance.read(fileToLoad);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of write method, of class IOInterface.
     */
    @Test
    public void testWrite() {
        System.out.println("write");
        Object timeSeriesTowrite = null;
        File fileToWriteTo = null;
        IOInterface instance = new IOInterfaceImpl();
        boolean expResult = false;
        boolean result = instance.write(timeSeriesTowrite, fileToWriteTo);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class IOInterfaceImpl implements IOInterface {

        public T read(File fileToLoad) throws FileNotFoundException, ValidityException, ParsingException, IOException {
            return null;
        }

        public boolean write(T timeSeriesTowrite, File fileToWriteTo) {
            return false;
        }
    }

}
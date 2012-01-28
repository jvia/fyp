/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.encoder;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import org.bham.aucom.data.DomainFeature;
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
public class CastEncoderTest {

    public CastEncoderTest() {
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
     * Test of getFeatures method, of class CastEncoder.
     */
    @Test
    public void testGetFeatures() {
        System.out.println("getFeatures");
        Observation in = null;
        CastEncoder instance = new CastEncoder();
        List expResult = null;
        List result = instance.getFeatures(in);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encode method, of class CastEncoder.
     */
    @Test
    public void testEncode_Observation() {
        System.out.println("encode");
        Observation in = null;
        CastEncoder instance = new CastEncoder();
        int expResult = 0;
        int result = instance.encode(in);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encode method, of class CastEncoder.
     */
    @Test
    public void testEncode_List() {
        System.out.println("encode");
        List<DomainFeature> in = null;
        CastEncoder instance = new CastEncoder();
        int expResult = 0;
        int result = instance.encode(in);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decode method, of class CastEncoder.
     */
    @Test
    public void testDecode() {
        System.out.println("decode");
        int id = 0;
        CastEncoder instance = new CastEncoder();
        List expResult = null;
        List result = instance.decode(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEncoding method, of class CastEncoder.
     */
    @Test
    public void testGetEncoding() {
        System.out.println("getEncoding");
        CastEncoder instance = new CastEncoder();
        ConcurrentHashMap expResult = null;
        ConcurrentHashMap result = instance.getEncoding();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
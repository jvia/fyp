/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.encoder;

import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.junit.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author jxv911
 */
public class EncoderTest {

    public EncoderTest() {
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
     * Test of getFeatures method, of class Encoder.
     */
    @Test
    public void testGetFeatures() {
        System.out.println("getFeatures");
        Observation in = null;
        Encoder instance = new EncoderImpl();
        List expResult = null;
        List result = instance.getFeatures(in);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encode method, of class Encoder.
     */
    @Test
    public void testEncode_Observation() {
        System.out.println("encode");
        Observation in = null;
        Encoder instance = new EncoderImpl();
        int expResult = 0;
        int result = instance.encode(in);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of encode method, of class Encoder.
     */
    @Test
    public void testEncode_List() {
        System.out.println("encode");
        List<DomainFeature> in = null;
        Encoder instance = new EncoderImpl();
        int expResult = 0;
        int result = instance.encode(in);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of decode method, of class Encoder.
     */
    @Test
    public void testDecode() {
        System.out.println("decode");
        int id = 0;
        Encoder instance = new EncoderImpl();
        List expResult = null;
        List result = instance.decode(id);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getEncoding method, of class Encoder.
     */
    @Test
    public void testGetEncoding() {
        System.out.println("getEncoding");
        Encoder instance = new EncoderImpl();
        ConcurrentHashMap expResult = null;
        ConcurrentHashMap result = instance.getEncoding();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getInstance method, of class Encoder.
     */
    @Test
    public void testGetInstance() {
        System.out.println("getInstance");
        //Encoder expResult = null;
        //Encoder result = Encoder.getInstance();
        //assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class EncoderImpl extends Encoder {

        public List<DomainFeature> getFeatures(Observation in) {
            return null;
        }

        public int encode(Observation in) {
            return 0;
        }

        public int encode(List<DomainFeature> in) {
            return 0;
        }

        public List<DomainFeature> decode(int id) {
            return null;
        }

        public ConcurrentHashMap<String, Integer> getEncoding() {
            return null;
        }
    }

}
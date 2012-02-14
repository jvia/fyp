/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.AnomalyClassificatorListener;
import org.junit.*;

import java.util.HashMap;
import java.util.UUID;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

/**
 *
 * @author jxv911
 */
public class AnomalyClassificatorTest {

    public AnomalyClassificatorTest() {
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
     * Test of getId method, of class AnomalyClassificator.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        AnomalyClassificator instance = new AnomalyClassificatorImpl();
        UUID expResult = null;
        UUID result = instance.getId();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of satisfies method, of class AnomalyClassificator.
     */
    @Test
    public void testSatisfies() {
        System.out.println("satisfies");
        Score s = null;
        AnomalyClassificator instance = new AnomalyClassificatorImpl();
        boolean expResult = false;
        boolean result = instance.satisfies(s);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setClassificator method, of class AnomalyClassificator.
     */
    @Test
    public void testSetClassificator() {
        System.out.println("setClassificator");
        AnomalyClassificator classificator = null;
        AnomalyClassificator instance = new AnomalyClassificatorImpl();
        instance.setClassificator(classificator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class AnomalyClassificator.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        AnomalyClassificator classificator = null;
        AnomalyClassificator instance = new AnomalyClassificatorImpl();
        instance.copy(classificator);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of duplicate method, of class AnomalyClassificator.
     */
    @Test
    public void testDuplicate() {
        System.out.println("duplicate");
        AnomalyClassificator instance = new AnomalyClassificatorImpl();
        AnomalyClassificator expResult = null;
        AnomalyClassificator result = instance.duplicate();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of addSequenceStatusListener method, of class AnomalyClassificator.
     */
    @Test
    public void testAddSequenceStatusListener() {
        System.out.println("addSequenceStatusListener");
        AnomalyClassificatorListener listener = null;
        AnomalyClassificator instance = new AnomalyClassificatorImpl();
        instance.addSequenceStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of removeStatusListener method, of class AnomalyClassificator.
     */
    @Test
    public void testRemoveStatusListener() {
        System.out.println("removeStatusListener");
        AnomalyClassificatorListener listener = null;
        AnomalyClassificator instance = new AnomalyClassificatorImpl();
        instance.removeStatusListener(listener);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of reset method, of class AnomalyClassificator.
     */
    @Test
    public void testReset() {
        System.out.println("reset");
        AnomalyClassificator instance = new AnomalyClassificatorImpl();
        instance.reset();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    public class AnomalyClassificatorImpl implements AnomalyClassificator {

        public UUID getId() {
            return null;
        }

        public boolean satisfies(Score s) {
            return false;
        }

        public void setClassificator(AnomalyClassificator classificator) throws ClassCastException {
        }

        public void copy(AnomalyClassificator classificator) {
        }

        public AnomalyClassificator duplicate() {
            return null;
        }

        public void addSequenceStatusListener(AnomalyClassificatorListener listener) {
        }

        public void removeStatusListener(AnomalyClassificatorListener listener) {
        }

        public void reset() {
        }

        @Override
        public HashMap<String, String> getAttributes() {
            throw new UnsupportedOperationException("getAttributes() not implemented yet");
        }

        @Override
        public void addAttribute(String propertyName, String propertyValue) {
            throw new UnsupportedOperationException("addAttribute() not implemented yet");
        }

        @Override
        public void deleteAttribute(String propertyName) {
            throw new UnsupportedOperationException("deleteAttribute() not implemented yet");
        }

        @Override
        public String getAttributeValue(String propertyName) {
            throw new UnsupportedOperationException("getAttributeValue() not implemented yet");
        }

        @Override
        public boolean containsAttribute(String propertyName) {
            throw new UnsupportedOperationException("containsAttribute() not implemented yet");
        }
    }

}
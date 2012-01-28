package org.bham.aucom.data;

import junit.framework.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class RangeScoreTest {

    @Before
    public void setUp() throws Exception {
    }

    @SuppressWarnings("boxing")
    @Test
    public void testGetVariance() {
        ArrayList<Score> scores = new ArrayList<Score>();
        scores.add(new SingleScore(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature(), 2.3));
        scores.add(new SingleScore(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature(), 2.3));
        scores.add(new SingleScore(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature(), 2.3));
        scores.add(new SingleScore(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature(), 2.3));
        scores.add(new SingleScore(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature(), 2.3));
        RangeScore rangeScore = new RangeScore(scores);
        Assert.assertEquals(0.0280, rangeScore.getVariance(), 0.00000001);
    }

    @Test
    public void testInvariance() {
        Score ss = new SingleScore(TemporalProbabilityFeature.createRandomTemporalProbabilityFeature(), 2.3);
        ArrayList<Score> ars = new ArrayList<Score>();
        ars.add(ss);
        RangeScore rs = new RangeScore(ars);
        Assert.assertEquals(ss.getValue(), rs.getValue());
        Assert.assertEquals(ss.getVariance(), rs.getVariance());

    }

    /**
     * Test of size method, of class RangeScore.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        RangeScore instance = null;
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setScores method, of class RangeScore.
     */
    @Test
    public void testSetScores() {
        System.out.println("setScores");
        ArrayList<Score> scores = null;
        RangeScore instance = null;
        instance.setScores(scores);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getScores method, of class RangeScore.
     */
    @Test
    public void testGetScores() {
        System.out.println("getScores");
        RangeScore instance = null;
        ArrayList expResult = null;
        ArrayList result = instance.getScores();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttributes method, of class RangeScore.
     */
    @Test
    public void testGetAttributes() {
        System.out.println("getAttributes");
        RangeScore instance = null;
        HashMap expResult = null;
        HashMap result = instance.getAttributes();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getAttributeValue method, of class RangeScore.
     */
    @Test
    public void testGetAttributeValue() {
        System.out.println("getAttributeValue");
        String propertyName = "";
        RangeScore instance = null;
        String expResult = "";
        String result = instance.getAttributeValue(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsAttribute method, of class RangeScore.
     */
    @Test
    public void testContainsAttribute() {
        System.out.println("containsAttribute");
        String propertyName = "";
        RangeScore instance = null;
        boolean expResult = false;
        boolean result = instance.containsAttribute(propertyName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of copy method, of class RangeScore.
     */
    @Test
    public void testCopy() {
        System.out.println("copy");
        RangeScore instance = null;
        Object expResult = null;
        Object result = instance.copy();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

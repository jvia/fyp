package org.bham.aucom.data;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.fail;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class ClassificationTest {

    Classification abnormal = new Classification(new SingleScore(new TemporalProbabilityFeature(), 1.0), SystemFaultStatus.ABNORMAL);
    Classification unknown = new Classification(new SingleScore(new TemporalProbabilityFeature(), 0.5), SystemFaultStatus.UNKNOWN);
    Classification normal = new Classification(new SingleScore(new TemporalProbabilityFeature(), 0.0), SystemFaultStatus.NORMAL);

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {

    }

    @Test
    public void testGetStatus() throws Exception {
        assertThat(abnormal.getStatus(), is(SystemFaultStatus.ABNORMAL));
        assertThat(unknown.getStatus(), is(SystemFaultStatus.UNKNOWN));
        assertThat(normal.getStatus(), is(SystemFaultStatus.NORMAL));
    }

    @Test
    public void testGetStatusAsDouble() throws Exception {
        assertThat(abnormal.getStatusAsDouble(), is(1.0));
        assertThat(unknown.getStatusAsDouble(), is(0.0));
        assertThat(normal.getStatusAsDouble(), is(0.0));
    }

    @Test
    public void testSetClassId() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testSetIsAbnormal() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testSetIsNormal() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testSetIsUnknown() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testCreateRandomClassification() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testAddAttribute() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testContainsAttribute() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetAttributes() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetAttributeValue() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetValue() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testSetValue() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetVariance() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testSetVariance() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetTimestamp() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testSetTimestamp() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetContent() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testCopy() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testMarkAsFirstElement() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testUnmarkAsFirstElement() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testUnmarkAsLastElement() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testMarkAsLastElement() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testToString() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetDurationProbabilities() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetProbabilityFor() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetDurationFor() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetEventType() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetEventTypeIdAsString() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetFeatures() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetName() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testGetPredecessors() throws Exception {
        fail("Not implemented yet.");
    }

    @Test
    public void testEquals() throws Exception {
        fail("Not implemented yet.");
    }
}

package org.bham.aucom.data;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class ClassificationTest {

    Classification abnormal = new Classification(new SingleScore(new TemporalProbabilityFeature(), 1.0), SystemFaultStatus.ABNORMAL);
    Classification unknown = new Classification(new SingleScore(new TemporalProbabilityFeature(), 0.5), SystemFaultStatus.UNKNOWN);
    Classification normal = new Classification(new SingleScore(new TemporalProbabilityFeature(), 0.0), SystemFaultStatus.NORMAL);

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
}
package org.bham.aucom.fts.tranform;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeNotNull;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class MatrixReducerTest {
    @Test
    public void testGetReducer() throws Exception {
        System.out.println("Testing constructor.");
        assumeNotNull(getClass().getResource("/connections.txt"));
        MatrixReducer reducer = new MatrixReducer();
        assertThat(reducer, notNullValue(null));
    }

    @Test
    public void testAreConnected() throws Exception {
        assumeNotNull(getClass().getResource("/connections.txt"));
        MatrixReducer reducer = new MatrixReducer();
        assertThat(reducer.areConnected(0, 2), is(true));
        assertThat(reducer.areConnected(1, 2), is(false));
    }
}

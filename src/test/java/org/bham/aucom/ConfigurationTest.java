package org.bham.aucom;

import org.junit.Test;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class ConfigurationTest {


    @Test
    public void testGetInstance() throws Exception {
        assertThat(Configuration.getInstance(), is(notNullValue()));
    }

    @Test
    public void testSize() throws Exception {
        Configuration config = Configuration.getInstance();
        assertThat(config.size(), is(2));
    }

    @Test
    public void testGetValue() throws Exception {
        Configuration config = Configuration.getInstance();
        assertThat(config.getValue("SystemConnection"), is("org.bham.system.cast.CastSystemConnection"));
        assertThat(config.getValue("encoder"), is("org.bham.aucom.data.encoder.SourceScopeTypeEncoder"));
    }
}

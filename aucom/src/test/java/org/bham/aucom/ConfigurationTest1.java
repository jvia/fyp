package org.bham.aucom;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Unit tests for the Configuration class.
 * 
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class ConfigurationTest1 {

    /**
     * Test of getInstance method, of class Configuration.
     */
    @Test
    public void testGetInstance()
    {
        System.out.print("getInstance");
        assertNotNull(Configuration.getInstance());
    }

    /**
     * Test of getValue method, of class Configuration.
     */
    @Test
    public void testGetValue()
    {
        System.out.println("getValue");
        assertEquals(Configuration.getInstance().getValue("SystemConnection"), "org.bham.org.bham.system.playfile.PlayFileSystemConnection");
        assertEquals(Configuration.getInstance().getValue("encoder"), "org.bham.aucom.data.encoder.SourceScopeTypeEncoder");
    }

    /**
     * Test of size method, of class Configuration.
     */
    @Test
    public void testSize()
    {
        System.out.println("size");
        assertTrue(Configuration.getInstance().size() > 0);
    }
}

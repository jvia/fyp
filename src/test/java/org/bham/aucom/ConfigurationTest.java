package org.bham.aucom;

import junit.framework.Assert;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.fail;

public class ConfigurationTest {

    @Test
    public void testGetInstance() {
        // TODO :: load configuration file correctly
        Configuration c = Configuration.getInstance();
        Assert.assertNotNull(c);
        Assert.assertEquals(2, c.size());
    }

    /**
     * Test of getValue method, of class Configuration.
     */
    @Test
    public void testGetValue() {
        System.out.println("getValue");
        String inKey = "";
        Configuration instance = null;
        String expResult = "";
        String result = instance.getValue(inKey);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of size method, of class Configuration.
     */
    @Test
    public void testSize() {
        System.out.println("size");
        Configuration instance = null;
        int expResult = 0;
        int result = instance.size();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of resetConfiguration method, of class Configuration.
     */
    @Test
    public void testResetConfiguration() {
        System.out.println("resetConfiguration");
        Configuration instance = null;
        instance.resetConfiguration();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

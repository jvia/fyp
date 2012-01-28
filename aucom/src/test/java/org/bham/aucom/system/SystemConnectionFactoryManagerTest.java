package org.bham.aucom.system;


import java.io.BufferedReader;
import java.io.File;
import java.io.InputStream;
import junit.framework.Assert;

import org.junit.Before;
import org.junit.Test;


public class SystemConnectionFactoryManagerTest {
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void loadFromFile(){
		try {
			SystemConnectionFactoryManager.loadExternClassesFile();
		} catch (Exception exception) {
			exception.printStackTrace();
			Assert.fail();
		}
		Assert.assertEquals(true, SystemConnectionFactoryManager.map.containsKey("dummySystemFactory"));
		
	}
	@Test
	public void getConnection(){
		try {
			SystemConnection factory =SystemConnectionFactoryManager.getInstance().getConnection("dummySystemConnection"); 
		} catch (Exception exception) {
			exception.printStackTrace();
			Assert.fail();
		}
	}

    /**
     * Test of getInstance method, of class SystemConnectionFactoryManager.
     */
    @Test
    public void testGetInstance() throws Exception {
        System.out.println("getInstance");
        SystemConnectionFactoryManager expResult = null;
        SystemConnectionFactoryManager result = SystemConnectionFactoryManager.getInstance();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of initialize method, of class SystemConnectionFactoryManager.
     */
    @Test
    public void testInitialize() throws Exception {
        System.out.println("initialize");
        SystemConnectionFactoryManager.initialize();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadExternClassesFile method, of class SystemConnectionFactoryManager.
     */
    @Test
    public void testLoadExternClassesFile() throws Exception {
        System.out.println("loadExternClassesFile");
        SystemConnectionFactoryManager.loadExternClassesFile();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFactories method, of class SystemConnectionFactoryManager.
     */
    @Test
    public void testLoadFactories_File() throws Exception {
        System.out.println("loadFactories");
        File file = null;
        SystemConnectionFactoryManager.loadFactories(file);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFactories method, of class SystemConnectionFactoryManager.
     */
    @Test
    public void testLoadFactories_InputStream() throws Exception {
        System.out.println("loadFactories");
        InputStream inStream = null;
        SystemConnectionFactoryManager.loadFactories(inStream);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadFactories method, of class SystemConnectionFactoryManager.
     */
    @Test
    public void testLoadFactories_BufferedReader() throws Exception {
        System.out.println("loadFactories");
        BufferedReader br = null;
        SystemConnectionFactoryManager.loadFactories(br);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of loadInternClassesFile method, of class SystemConnectionFactoryManager.
     */
    @Test
    public void testLoadInternClassesFile() throws Exception {
        System.out.println("loadInternClassesFile");
        SystemConnectionFactoryManager.loadInternClassesFile();
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of containsKey method, of class SystemConnectionFactoryManager.
     */
    @Test
    public void testContainsKey() {
        System.out.println("containsKey");
        String key = "";
        SystemConnectionFactoryManager instance = new SystemConnectionFactoryManager();
        boolean expResult = false;
        boolean result = instance.containsKey(key);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getConnection method, of class SystemConnectionFactoryManager.
     */
    @Test
    public void testGetConnection() throws Exception {
        System.out.println("getConnection");
        String factoryName = "";
        SystemConnectionFactoryManager instance = new SystemConnectionFactoryManager();
        SystemConnection expResult = null;
        SystemConnection result = instance.getConnection(factoryName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of getFactoryClass method, of class SystemConnectionFactoryManager.
     */
    @Test
    public void testGetFactoryClass() throws Exception {
        System.out.println("getFactoryClass");
        String factoryName = "";
        SystemConnectionFactoryManager instance = new SystemConnectionFactoryManager();
        Class expResult = null;
        Class result = instance.getFactoryClass(factoryName);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

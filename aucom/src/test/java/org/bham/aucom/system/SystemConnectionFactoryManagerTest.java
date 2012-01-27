package org.bham.aucom.system;


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
}

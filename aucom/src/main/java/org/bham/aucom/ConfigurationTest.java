package org.bham.aucom;

import java.util.logging.Level;
import java.util.logging.Logger;

import junit.framework.Assert;

import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void testGetInstance() {
		Logger.getLogger("").setLevel(Level.ALL);
		Configuration c = Configuration.getInstance();
		Assert.assertNotNull(c);
		Assert.assertEquals(2, c.size());
	}
}

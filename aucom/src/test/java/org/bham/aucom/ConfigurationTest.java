package org.bham.aucom;

import junit.framework.Assert;
import org.junit.Test;

public class ConfigurationTest {

	@Test
	public void testGetInstance() {
        // TODO :: load configuration file correctly
		Configuration c = Configuration.getInstance();
		Assert.assertNotNull(c);
		Assert.assertEquals(2, c.size());
	}
}

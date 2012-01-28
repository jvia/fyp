package org.bham.aucom.data.util;

import nu.xom.XPathContext;
import org.junit.Before;
import org.junit.Test;

import static junit.framework.Assert.assertEquals;
import static org.junit.Assert.fail;

public class XmlToJavaObjectConverterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testGetParameterClassesFrom() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetComplexObjectFrom() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetPrimitiveObjectFromElement() {
		fail("Not yet implemented");
	}

	@Test
	public void testGetClassFrom() {
		fail("Not yet implemented");
	}

	@Test
	public void testIsPrimitiveParameter() {
		fail("Not yet implemented");
	}
	@Test
	public void testGetObjectFrom() {
		fail("Not yet implemented");
	}

    /**
     * Test of getContext method, of class XmlToJavaObjectConverter.
     */
    @Test
    public void testGetContext() {
        System.out.println("getContext");
        XmlToJavaObjectConverter instance = new XmlToJavaObjectConverter();
        XPathContext expResult = null;
        XPathContext result = instance.getContext();
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of setContext method, of class XmlToJavaObjectConverter.
     */
    @Test
    public void testSetContext() {
        System.out.println("setContext");
        XPathContext context = null;
        XmlToJavaObjectConverter instance = new XmlToJavaObjectConverter();
        instance.setContext(context);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}

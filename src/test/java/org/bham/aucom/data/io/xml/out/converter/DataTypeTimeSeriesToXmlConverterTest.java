/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

package org.bham.aucom.data.io.xml.out.converter;

import nu.xom.Element;
import org.bham.aucom.data.DataType;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author jxv911
 */
public class DataTypeTimeSeriesToXmlConverterTest {

    public DataTypeTimeSeriesToXmlConverterTest() {
    }

    @BeforeClass
    public static void setUpClass() throws Exception {
    }

    @AfterClass
    public static void tearDownClass() throws Exception {
    }

    @Before
    public void setUp() {
    }

    @After
    public void tearDown() {
    }

    /**
     * Test of appendElementContentAsXml method, of class DataTypeTimeSeriesToXmlConverter.
     */
    @Test
    public void testAppendElementContentAsXml() {
        System.out.println("appendElementContentAsXml");
        Element containerElement = null;
        DataType data = null;
        DataTypeTimeSeriesToXmlConverter instance = new DataTypeTimeSeriesToXmlConverter();
        instance.appendElementContentAsXml(containerElement, data);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

}
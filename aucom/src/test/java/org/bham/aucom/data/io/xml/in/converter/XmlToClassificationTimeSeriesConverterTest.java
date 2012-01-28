package org.bham.aucom.data.io.xml.in.converter;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.UUID;

import junit.framework.Assert;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.SystemFaultStatus;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.io.TimeSeriesIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.util.Constants;

public class XmlToClassificationTimeSeriesConverterTest {
	File classifiationTimeSeriesFile = new File("data"+File.separatorChar+"unittest" + File.separatorChar+"UTClassificationtimeSeries.cl");
	ClassificationTimeSeries clTs = null;
	@Before
	public void setUp() throws Exception {
		Document doc = TimeSeriesIO.getXmlDocumentFromFile(this.classifiationTimeSeriesFile);
		Element element = getFirstEelement(doc.getRootElement());
		Assert.assertNotNull(element);
		this.clTs = (ClassificationTimeSeries) new XmlToClassificationTimeSeriesConverter().convert(doc);
	}

	@Test
	public void testCreateDataFromElementElement() {
		Assert.assertEquals(SystemFaultStatus.NORMAL, this.clTs.get(0).getStatus());
		Assert.assertEquals(SystemFaultStatus.ABNORMAL, this.clTs.get(1).getStatus());
	}

	@Test
	public void testCreateTimeSeriesUUIDUUIDUUIDArrayListOfClassification() {
		Assert.assertNotNull(this.clTs);
		Assert.assertEquals(2, this.clTs.size());
	}
	

	@Test
	public void testWriteAndReadTimeSeriesAttributes() throws IOException, ValidityException, DataAlreadyExistsException, ParsingException {
		clTs.addAttribute(Constants.FAULT_INDUCED,"123");
		File temp = File.createTempFile(UUID.randomUUID().toString() + "_tmp", ".cl");
		boolean success = AucomIO.getInstance().writeTimeSeries(clTs, temp);
		Assert.assertEquals(true, success);
		TimeSeries<Classification> clsTs_reloaded = (TimeSeries<Classification>) AucomIO.getInstance().readTimeSeries(temp);
		Assert.assertEquals(true, clsTs_reloaded.containsAttribute(Constants.FAULT_INDUCED));
		Assert.assertEquals("123", clsTs_reloaded.getAttributeValue(Constants.FAULT_INDUCED));
	}
	private Element getFirstEelement(Element e) {
		return (Element)e.query("./ts:elements/ts:element", XmlToTimeSeriesConverter.getContext()).get(0);
	}

    /**
     * Test of createDataFromElement method, of class XmlToClassificationTimeSeriesConverter.
     */
    @Test
    public void testCreateDataFromElement() {
        System.out.println("createDataFromElement");
        Element e = null;
        XmlToClassificationTimeSeriesConverter instance = new XmlToClassificationTimeSeriesConverter();
        Classification expResult = null;
        Classification result = instance.createDataFromElement(e);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }

    /**
     * Test of createTimeSeries method, of class XmlToClassificationTimeSeriesConverter.
     */
    @Test
    public void testCreateTimeSeries() {
        System.out.println("createTimeSeries");
        UUID id = null;
        UUID generatorID = null;
        UUID generatedFromID = null;
        ArrayList<Classification> items = null;
        XmlToClassificationTimeSeriesConverter instance = new XmlToClassificationTimeSeriesConverter();
        TimeSeries expResult = null;
        TimeSeries result = instance.createTimeSeries(id, generatorID, generatedFromID, items);
        assertEquals(expResult, result);
        // TODO review the generated test code and remove the default call to fail.
        fail("The test case is a prototype.");
    }
}

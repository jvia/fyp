package org.bham.aucom.data.management;


import java.io.File;

import junit.framework.Assert;
import nu.xom.Document;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.io.TimeSeriesIO;
import org.bham.aucom.data.io.xml.in.XmlTimeSeriesInput;
import org.bham.aucom.data.io.xml.out.XmlTimeSeriesOutput;

public class TimeSeriesLoaderTest {
	
	private static final String GENERATOR_ID_TEST = "3e3b4c61-a1c3-484b-b11b-2af716892c5d";
	private static final String GENERATEDFROM_ID_TEST = "d6a7e981-2772-4388-8aca-f61dc0144c3f";
	private static final String OWN_ID_TEST = "02011f0a-8b10-4e27-a601-9ebd8f63f1fb";
	private static final String ROOTELEMENTQNAME = "ts:timeseries";
	private static final String NAMESPACEURI = "http://www.cor-lab.de/formats/ts/1.0";
	private static final String PREFIX = "ts";
	TimeSeriesIO reader = new TimeSeriesIO(new XmlTimeSeriesInput(), new XmlTimeSeriesOutput());
	File dir = new File("data"+File.separatorChar+"unittest"+File.separatorChar);
	File scoreFile = new File(this.dir.getAbsolutePath() + File.separatorChar+"UTScoreTimeSeries.score");
	
	@Before
	public void setUp() throws Exception {
		
	}

	@Test
	public void getXmlDocumentTest(){
		try {
			Document doc = this.reader.getXmlDocumentFromFile(this.scoreFile);
			Assert.assertEquals(PREFIX,  doc.getRootElement().getNamespacePrefix());
			Assert.assertEquals(NAMESPACEURI,  doc.getRootElement().getNamespaceURI());
			Assert.assertEquals(ROOTELEMENTQNAME,  doc.getRootElement().getQualifiedName());
		} catch (Exception e) {
			e.printStackTrace();
			Assert.fail(e.getLocalizedMessage());
		}
	}

//	@Test
//	public void getIdTest(){
//		Document doc;
//		try {
//			doc = this.reader.getXmlDocumentFromFile(this.scoreFile);
//			UUID id = this.reader.getOwnId(this.reader.getRootElement(doc));
//			Assert.assertNotNull(id);
//			Assert.assertEquals(UUID.fromString(OWN_ID_TEST), id);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
//	@Test
//	public void getGeneratorIdTest(){
//		Document doc;
//		try {
//			doc = this.tsl.getXmlDocumentFromFile(this.scoreFile);
//			UUID id = this.tsl.getGeneratorID(this.tsl.getRootElement(doc));
//			Assert.assertNotNull(id);
//			Assert.assertEquals(UUID.fromString(GENERATOR_ID_TEST), id);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
//	@Test
//	public void getGeneratedFromIdTest(){
//		Document doc;
//		try {
//			doc = this.tsl.getXmlDocumentFromFile(this.scoreFile);
//			UUID id = this.tsl.getGeneratedFromID(this.tsl.getRootElement(doc));
//			Assert.assertNotNull(id);
//			Assert.assertEquals(UUID.fromString(GENERATEDFROM_ID_TEST), id);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
//	@Test
//	public void getElementsTest(){
//		Document doc;
//		int expectedNumberElements = 2;
//		try {
//			doc = this.tsl.getXmlDocumentFromFile(this.scoreFile);
//			int actualNumberElements = this.tsl.getElements(this.tsl.getRootElement(doc)).size();
//			Assert.assertEquals("ts:element" , this.tsl.getElements(this.tsl.getRootElement(doc)).get(0).getQualifiedName());
//			Assert.assertEquals(expectedNumberElements, actualNumberElements);
//		} catch (Exception e) {
//			e.printStackTrace();
//			Assert.fail();
//		}
//	}
}

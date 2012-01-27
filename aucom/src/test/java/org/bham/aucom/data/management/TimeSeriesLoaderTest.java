package org.bham.aucom.data.management;


import junit.framework.Assert;
import nu.xom.Document;
import org.bham.aucom.data.io.TimeSeriesIO;
import org.bham.aucom.data.io.xml.in.XmlTimeSeriesInput;
import org.bham.aucom.data.io.xml.out.XmlTimeSeriesOutput;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.util.UUID;

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

}

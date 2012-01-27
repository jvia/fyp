package org.bham.aucom.data.io.xml.in.converter;

import java.io.File;
import java.io.IOException;

import junit.framework.Assert;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.timeseries.DataTypeTimeSeries;

public class XmlToDatatypeTimeSeriesConverterTest {
	File observationFile = new File("data"+File.separatorChar+"unittestdata" + File.separatorChar+"UTDataTypeTimeSeries.dtp");
	private DataTypeTimeSeries dtpTs;
	@Before
	public void setUp() throws Exception {
		Document doc = getDocument();
		Element element = getElement(doc);
		Assert.assertNotNull(element);
		this.dtpTs = (DataTypeTimeSeries) new XmlToDatatypeTimeSeriesConverter().convert(doc);
	}
	@Test
	public void testCreateTimeSeries(){
		Assert.assertNotNull(this.dtpTs);
		Assert.assertEquals(3, this.dtpTs.size());
	}
	@Test
	public void testCreateDataFromElement() {
		DataType dtp = this.dtpTs.get(0);
		Assert.assertEquals("1295266966695", String.valueOf(dtp.getTimestamp()));
		Assert.assertNotNull(dtp.getContent());
		Assert.assertTrue(dtp.getContent().toXML().length() > 0);
		Assert.assertEquals(3, dtp.getFeatures().size());
		Assert.assertEquals("source", dtp.getFeatures().get(0).getFeatureName());
		Assert.assertEquals("abc", dtp.getFeatures().get(0).getFeatureValue());
		Assert.assertEquals("type", dtp.getFeatures().get(1).getFeatureName());
		Assert.assertEquals("insert", dtp.getFeatures().get(1).getFeatureValue());
		Assert.assertEquals("scope", dtp.getFeatures().get(2).getFeatureName());
		Assert.assertEquals("shorttermmemory", dtp.getFeatures().get(2).getFeatureValue());
		Assert.assertEquals(1, dtp.getEventType());
		
	}
	private Element getElement(Document document) {
		Nodes nodes = document.query("./ts:timeseries/ts:elements/ts:element", XmlToTimeSeriesConverter.getContext());
		Element e = (Element)nodes.get(0);
		return e;
	}

	private Document getDocument() {
		Builder b = new Builder();
		Document doc = getXmlDocumentFrom(b);

		return doc;
	}
	
	private Document getXmlDocumentFrom(Builder b){
		try {
			return b.build(this.observationFile);
		} catch (ValidityException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		} catch (ParsingException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		} catch (IOException exception) {
			// TODO Auto-generated catch block
			exception.printStackTrace();
		}
		return null;
	}

}

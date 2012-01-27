package org.bham.aucom.data.io.xml.out.converter;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.not;
import nu.xom.Document;
import nu.xom.Element;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.io.xml.in.converter.XmlToTimeSeriesConverter;
import org.bham.aucom.data.timeseries.DataTypeTimeSeries;

public class DataTypeTimSeriesToXmlConverterTest {
	DataTypeTimeSeries dtpTs= null;
	private Document convertedDocument;
	@Before
	public void setUp() throws Exception {
		dtpTs = new DataTypeTimeSeries();
		dtpTs.add(DataType.createRandomDataType());
		dtpTs.add(DataType.createRandomDataType());
		dtpTs.add(DataType.createRandomDataType());
		dtpTs.add(DataType.createRandomDataType());
		convertedDocument = new DataTypeTimeSeriesToXmlConverter().convertTimeSeries(dtpTs); // appendElementContentAsXml(element, dtpTs.get(0));
	}
	@Test 
	public void testDomainFeature(){
		Assert.assertThat(Integer.valueOf(convertedDocument.getRootElement().query("//ts:datatype/ts:parameter", XmlToTimeSeriesConverter.getContext()).size()), is(not(0)));
		Assert.assertEquals("domainfeature", ((Element)convertedDocument.query("//ts:datatype/ts:parameter", XmlToTimeSeriesConverter.getContext()).get(0)).getAttributeValue("type"));
		Assert.assertEquals(dtpTs.get(0).getFeatures().get(0).getFeatureName(), ((Element)convertedDocument.query("//ts:datatype/ts:parameter", XmlToTimeSeriesConverter.getContext()).get(0)).getAttributeValue("name"));
		Assert.assertEquals(dtpTs.get(0).getFeatures().get(0).getFeatureValue(), ((Element)convertedDocument.query("//ts:datatype/ts:parameter", XmlToTimeSeriesConverter.getContext()).get(0)).getAttributeValue("value"));
	}
	@Test
	public void testConvertElementElementDataType() {
		String expectedContent = this.dtpTs.get(0).getContent().getValue();
		String actualContent = convertedDocument.getRootElement().query("//ts:observation/*", XmlToTimeSeriesConverter.getContext() ).get(0).getValue();
		Assert.assertThat(expectedContent, is(actualContent));
	}
}

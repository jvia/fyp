package org.bham.aucom.data.io.xml.in.converter;

import java.io.IOException;
import java.io.StringReader;

import junit.framework.Assert;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import org.junit.Before;
import org.junit.Test;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.util.Constants;

public class XmlToTimeSeriesConverterTest {

	@Before
	public void setUp() throws Exception {
	}

	@Test
	public void testHasFaultInducedAttribute() {
		TimeSeries<Observation> tsObs =  new XmlToObservationTimeSeriesConverter().convert(getDoc());
		Assert.assertEquals(1, tsObs.getAttributes().size());
	}
	@Test
	public void testHasFaultInducedAttributeWithValue() {
		TimeSeries<Observation> tsObs =  new XmlToObservationTimeSeriesConverter().convert(getDoc());
		Assert.assertEquals("123456789", tsObs.getAttributeValue(Constants.FAULT_INDUCED));
	}

	@Test
	public void testElementsHaveAttributes(){
		TimeSeries<Observation> tsObs =  new XmlToObservationTimeSeriesConverter().convert(getDoc());
		Assert.assertEquals(2, tsObs.get(0).getAttributes().size());
	}
	@Test
	public void testGetElements() {
		TimeSeries<Observation> tsObs =  new XmlToObservationTimeSeriesConverter().convert(getDoc());
		Assert.assertEquals(7, tsObs.size());
	}

	public Document getDoc(){
		String str = ""; 
		str += "<?xml version=\"1.0\"?>";
		str += "<ts:timeseries id=\"6576bc02-0dd0-4a61-9c94-07363f914ed1\" type=\"obs\" xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\">";
		str += "<ts:attributes xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\">" +
				"<ts:attribute xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\" "+Constants.FAULT_INDUCED+"=\"123456789\"></ts:attribute >" +
				"</ts:attributes>";
		str += "<ts:elements>";
		str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.1\"/><ts:attribute testattribue=\"testvalue\"/></ts:attributes><ts:observation timestamp=\"1826\"><cast eventType=\"OVERWRITE\" generatorType=\"odd\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
		str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.2\"/></ts:attributes><ts:observation timestamp=\"1863\"><cast eventType=\"OVERWRITE\" generatorType=\"even\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
		str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.3\"/></ts:attributes><ts:observation timestamp=\"1883\"><cast eventType=\"OVERWRITE\" generatorType=\"odd\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
		str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.4\"/></ts:attributes><ts:observation timestamp=\"1939\"><cast eventType=\"OVERWRITE\" generatorType=\"even\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
		str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.5\"/></ts:attributes><ts:observation timestamp=\"1973\"><cast eventType=\"OVERWRITE\" generatorType=\"even\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
		str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.6\"/></ts:attributes><ts:observation timestamp=\"1991\"><cast eventType=\"OVERWRITE\" generatorType=\"odd\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
		str += "<ts:element xmlns:ts=\"http://www.cor-lab.de/formats/ts/1.0\"><ts:attributes><ts:attribute thresholdUsed=\"0.7\"/></ts:attributes><ts:observation timestamp=\"2039\"><cast eventType=\"OVERWRITE\" generatorType=\"even\" memoryType=\"collatz.sa\" /></ts:observation></ts:element>";
		str += "</ts:elements>";
		str += "</ts:timeseries>";
		System.out.println(str);
		try {
			return new Builder().build(new StringReader(str));
		} catch (ValidityException e) {
			e.printStackTrace();
		} catch (ParsingException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;

	}
}

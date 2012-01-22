package org.bham.aucom.data.io.xml.out.converter;

import nu.xom.Attribute;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.XPathContext;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.util.Constants;

/*
 * Abstract class, interface for writing timeseries to an xml Document
 */
public abstract class TimeSeriesToXmlDocumentConverter<T extends AbstractData> {
	XPathContext context = new XPathContext("ts", "http://www.cor-lab.de/formats/ts/1.0");

	public Document convertTimeSeries(TimeSeries<T> timeSeriesToWrite) throws IllegalArgumentException {
		if(timeSeriesToWrite.size()==0){
			throw new IllegalArgumentException("timeseries to save must not be of length zero");
		}
		Document xmlDocumentToWrite = createEmptyTimeSeriesDocument();
		addTimeSeriesProperties(xmlDocumentToWrite, timeSeriesToWrite);
		addTimeSeriesElements(xmlDocumentToWrite, timeSeriesToWrite);
		addTimeSeriesAttributes(xmlDocumentToWrite, timeSeriesToWrite);
		return xmlDocumentToWrite;
	}
	
	/*
	 * add all attributes of the {@linkplain timeSeriesToWrite} to {@linkplain xmlDocumentToWrite} 
	 * @param doc
	 * @param timeSeries
	 */
	private void addTimeSeriesAttributes(Document doc,
			TimeSeries<T> timeSeries) {
		Element attributesElement = new Element("ts:attributes", Constants.URI);
		for(String attributeName : timeSeries.getAttributes().keySet()){
			Element  attr = new Element("ts:attribute", Constants.URI);
			attr.addAttribute(new Attribute(attributeName, timeSeries.getAttributeValue(attributeName)));
			attributesElement.appendChild(attr);
		}
		doc.getRootElement().appendChild(attributesElement);
	}
	@SuppressWarnings("hiding")
    public <T extends AbstractData> Document addTimeSeriesProperties(Document xmlDocumentToWrite,TimeSeries<T> timeSeriesToWrite){
		xmlDocumentToWrite.getRootElement().addAttribute(new Attribute("id", timeSeriesToWrite.getId().toString()));
		xmlDocumentToWrite.getRootElement().addAttribute(new Attribute("type", timeSeriesToWrite.getType().toString()));
		return xmlDocumentToWrite;
	}
	public abstract void appendElementContentAsXml(Element e, T data);
	
	private Document addTimeSeriesElements(Document xmlDocumentToWrite,TimeSeries<T> timeSeriesToWrite) {
		Element n = (Element)xmlDocumentToWrite.query(".//ts:elements", this.context).get(0);
		for(int i = 0; i < timeSeriesToWrite.size(); i++){
			Element element = new Element("ts:element", Constants.URI);
			addElementAttributesAsXml(element, timeSeriesToWrite.get(i));
			appendElementContentAsXml(element, timeSeriesToWrite.get(i));
			n.appendChild(element);
		}
		return xmlDocumentToWrite;
	}

	protected void addElementAttributesAsXml(Element element, T t) {
			Element attributes = new Element("ts:attributes", Constants.URI);
			for(String attributeName : t.getAttributes().keySet()){
				Element  attr = new Element("ts:attribute", Constants.URI);
				attr.addAttribute(new Attribute(attributeName, t.getAttributeValue(attributeName)));
				attributes.appendChild(attr);
			}
			element.appendChild(attributes);
		}

	private Document createEmptyTimeSeriesDocument() {
		Element rootElement = new Element("ts:timeseries", Constants.URI);
		Document doc = new Document(rootElement);
		doc.getRootElement().appendChild(new Element("ts:elements", Constants.URI));
		return doc;
	}
}

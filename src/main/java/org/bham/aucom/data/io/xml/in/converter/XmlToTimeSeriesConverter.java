package org.bham.aucom.data.io.xml.in.converter;

import nu.xom.*;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.LinkEnum;
import org.bham.aucom.data.timeseries.TimeSeries;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class XmlToTimeSeriesConverter<T extends AbstractData> {
    private final static XPathContext context = new XPathContext("ts", "http://www.cor-lab.de/formats/ts/1.0");

    public static Element getRootElement(Document timeSeriesDocument) {
        return (Element) timeSeriesDocument.query("//ts:timeseries", getContext()).get(0);
    }


    public TimeSeries<T> convert(Document timeSeriesDocument) {
        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "getting root element");
        Element timeSeriesElement = getRootElement(timeSeriesDocument);
        UUID id = getOwnId(timeSeriesElement);
        Logger.getLogger(this.getClass().getCanonicalName()).info("id : " + id);
        UUID generatorID = getGeneratorID(timeSeriesElement);
        Logger.getLogger(this.getClass().getCanonicalName()).info("generatorID : " + generatorID);
        UUID generatedFromID = getGeneratedFromID(timeSeriesElement);
        Logger.getLogger(this.getClass().getCanonicalName()).info("generatedFromID : " + generatedFromID);

        List<Element> elements = getElements(timeSeriesElement);
        Logger.getLogger(this.getClass().getCanonicalName()).info("number elements in timeseries : " + elements.size());
        ArrayList<T> items = new ArrayList<T>();
        for (Element e : elements) {
            T data = createDataFromElement(e);
            HashMap<String, String> elementAttributes = getAttributes(e);
            if (data != null) {
                data.getAttributes().putAll(elementAttributes);
                items.add(data);
            }
        }
        HashMap<String, String> attributes = getTimeSeriesAttributes(timeSeriesDocument);
        TimeSeries<T> ts = createTimeSeries(id, generatorID, generatedFromID, items);
        ts.getAttributes().putAll(attributes);
        return ts;
    }

    /**
     * Retrieves attributes from an xml element and returns them as a HashMap. Keys
     * are attribute names, values are attribute values
     *
     * @param e the element from which the attributes are read
     */
    private HashMap<String, String> getAttributes(Element e) {
        HashMap<String, String> attributes = new HashMap<String, String>();
        Nodes attributeNodes = e.query("./ts:attributes/ts:attribute", getContext());
        for (int i = 0; i < attributeNodes.size(); i++) {
            Element attributeElement = (Element) attributeNodes.get(i);
            for (int j = 0; j < attributeElement.getAttributeCount(); j++) {
                attributes.put(attributeElement.getAttribute(j).getLocalName(), attributeElement.getAttribute(j).getValue());
            }
        }
        return attributes;
    }


    protected HashMap<String, String> getTimeSeriesAttributes(Document doc) {
        HashMap<String, String> attributes = new HashMap<String, String>();
        Nodes attributeNodes = doc.getRootElement().query("./ts:attributes/ts:attribute", getContext());
        Logger.getLogger(this.getClass().getCanonicalName()).info(attributeNodes.size() + "  attributes found");
        for (int i = 0; i < attributeNodes.size(); i++) {
            Element attributeElement = (Element) attributeNodes.get(i);
            for (int j = 0; j < attributeElement.getAttributeCount(); j++) {
                attributes.put(attributeElement.getAttribute(j).getLocalName(), attributeElement.getAttribute(j).getValue());
            }
        }
        return attributes;
    }

    public abstract T createDataFromElement(Element e);

    public abstract TimeSeries<T> createTimeSeries(UUID id, UUID generatorID, UUID generatedFromID, ArrayList<T> items);

    protected List<Element> getElements(Element oElement) {
        Nodes el = oElement.query("./ts:elements/ts:element", getContext());
        List<Element> list = new ArrayList<Element>();
        for (int i = 0; i < el.size(); i++) {
            Node n = el.get(i);
            list.add((Element) n);
        }
        return list;
    }

    protected UUID getGeneratedFromID(Element oElement) {
        return getId(oElement, LinkEnum.generatedFromId);
    }

    protected UUID getGeneratorID(Element oElement) {
        return getId(oElement, LinkEnum.generatorId);
    }

    protected UUID getOwnId(Element ctsElement) {
        return getId(ctsElement, LinkEnum.id);
    }

    protected UUID getId(Element ctsElement, LinkEnum id) {
        String linkName = id.toString();
        if (ctsElement.getAttribute(linkName) == null)
            return null;
        String value = ctsElement.getAttribute(linkName).getValue();
        return UUID.fromString(value);
    }


    protected long getTimestamp(Element e) {
        long timestamp = -1;
        if (e.getAttribute("timestamp") != null) {
            timestamp = Long.valueOf(e.getAttributeValue("timestamp"));
        }
        return timestamp;
    }


    public static XPathContext getContext() {
        return context;
    }


}

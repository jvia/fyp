package org.bham.aucom.data.io.xml.out.converter;

import nu.xom.Attribute;
import nu.xom.Element;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.util.Constants;

/**
 * <ts:datatype>
 * <ts:parameter type="domainfeature" name="source" value="ac"/>
 * <ts:parameter type="domainfeature" name="type" value="insert"/>
 * <ts:parameter type="domainfeature" name="scope" value="shorttermemory"/>
 * <ts:parameter type="eventTypeId">2</ts:parameter>
 * </ts:datatype>
 */
public class DataTypeTimeSeriesToXmlConverter extends TimeSeriesToXmlDocumentConverter<DataType> {


    @Override
    public void appendElementContentAsXml(Element containerElement, DataType data) {
        new ObservationTimeSeriesToXmlConverter().appendElementContentAsXml(containerElement, data);
        Element dataTypeElement = new Element("ts:datatype", Constants.URI);
        containerElement.appendChild(dataTypeElement);

        for (DomainFeature feature : data.getFeatures()) {
            Element featureElement = new Element("ts:parameter", Constants.URI);
            dataTypeElement.appendChild(featureElement);

            featureElement.addAttribute(new Attribute("type", "domainfeature"));
            featureElement.addAttribute(new Attribute("name", feature.getFeatureName()));
            featureElement.addAttribute(new Attribute("value", feature.getFeatureValue()));
        }
        Element idElement = new Element("ts:parameter", Constants.URI);
        idElement.addAttribute(new Attribute("type", "eventTypeId"));
        idElement.appendChild(data.getEventTypeIdAsString());
        dataTypeElement.appendChild(idElement);
    }

}

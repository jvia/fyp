package org.bham.aucom.data.io.xml.out.converter;

import nu.xom.Attribute;
import nu.xom.Element;
import org.bham.aucom.data.Classification;
import org.bham.aucom.util.Constants;

public class ClassificationTimeSeriesToXmlConverter extends TimeSeriesToXmlDocumentConverter<Classification> {
    //
    @Override
    public void appendElementContentAsXml(Element containerElement, Classification data) {
        new ScoreTimeSeriesToXmlConverter().appendElementContentAsXml(containerElement, data);
        Element classificationElement = new Element("ts:classification", Constants.URI);
        containerElement.appendChild(classificationElement);
        classificationElement.addAttribute(new Attribute("systemstatus", data.getStatus().toString()));
    }

}

package org.bham.aucom.data.io.xml.out.converter;

import nu.xom.Attribute;
import nu.xom.Element;
import org.bham.aucom.data.Observation;
import org.bham.aucom.util.Constants;

public class ObservationTimeSeriesToXmlConverter extends TimeSeriesToXmlDocumentConverter<Observation> {

    private static final String OBSERVATION_ELEMENT = "ts:observation";

    @Override
    public void appendElementContentAsXml(Element containerElement, Observation data) {
        Element observation = new Element(OBSERVATION_ELEMENT, Constants.URI);
        observation.addAttribute(new Attribute("timestamp", String.valueOf(data.getTimestamp())));
        if (data.getContent() != null) {
            observation.appendChild(data.getContent().copy());
        } else {
            System.err.println("ObservationTimeSeriesToXmlConverter: warning observation at " + data.getTimestamp() + " has no content");
        }
        containerElement.appendChild(observation);
    }

}

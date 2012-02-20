package org.bham.aucom.data.io.xml.out.converter;

import nu.xom.Attribute;
import nu.xom.Element;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.util.Constants;

public class TemporalProbabilityFeatureTimeSeriesToXmlConverter extends TimeSeriesToXmlDocumentConverter<TemporalProbabilityFeature> {

    //	<ts:temporalprobabilityfeature>
//    <ts:predecessors>
//    	<ts:element type="probability" eventType="1" value="0.1"></ts:element>
//    	<ts:element type="probability" eventType="2" value="0.2"></ts:element>
//    	<ts:element type="probability" eventType="3" value="0.3"></ts:element>
//    	<ts:element type="probability" eventType="4" value="0.4"></ts:element>
//  	</ts:predecessors>
//  </ts:temporalprobabilityfeature>

    @Override
    public void appendElementContentAsXml(Element containerElement, TemporalProbabilityFeature data) {
        new TemporalDurationTimeSeriesFeatureToXmlConverter().appendElementContentAsXml(containerElement, data);
        Element tpElement = appendTpfElement(containerElement);
        Element predecessors = appendPredecessorElement(tpElement);
        appendPropabilities(data, predecessors);
    }

    /**
     * @param data
     * @param predecessors
     */
    private void appendPropabilities(TemporalProbabilityFeature data, Element predecessors) {
        for (DataType dtp : data.getPredecessors()) {
            Element probability = new Element("ts:element", Constants.URI);
            probability.addAttribute(new Attribute("type", "probability"));
            probability.addAttribute(new Attribute("eventType", String.valueOf(dtp.getEventType())));
            probability.addAttribute(new Attribute("value", String.valueOf(data.getProbabilityFor(dtp))));
            predecessors.appendChild(probability);
        }
    }

    /**
     * @param tpElement
     * @return
     */
    private Element appendPredecessorElement(Element tpElement) {
        Element predecessors = new Element("ts:predecessors", Constants.URI);
        tpElement.appendChild(predecessors);
        return predecessors;
    }

    /**
     * @param containerElement
     * @return
     */
    private Element appendTpfElement(Element containerElement) {
        Element tpElement = new Element("ts:temporalprobabilityfeature", Constants.URI);
        containerElement.appendChild(tpElement);
        return tpElement;
    }

}

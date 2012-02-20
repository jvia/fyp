package org.bham.aucom.data.io.xml.out.converter;

import nu.xom.Attribute;
import nu.xom.Element;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.util.Constants;

public class TemporalDurationTimeSeriesFeatureToXmlConverter extends TimeSeriesToXmlDocumentConverter<TemporalDurationFeature> {

	@Override
	public void appendElementContentAsXml(Element containerElement, TemporalDurationFeature data) {
		new DataTypeTimeSeriesToXmlConverter().appendElementContentAsXml(containerElement, data);
		Element tpdElement = new Element("ts:temporaldurationfeature", Constants.URI);
		containerElement.appendChild(tpdElement);
		Element predecessors = new Element("ts:predecessors", Constants.URI);
		tpdElement.appendChild(predecessors);
		for(DataType predecessor: data.getPredecessors()){
			Element predecessorsElement = new Element("ts:element", Constants.URI);
			predecessorsElement.addAttribute(new Attribute("duration", String.valueOf(data.getDurationFor(predecessor))));
			predecessors.appendChild(predecessorsElement);
			new DataTypeTimeSeriesToXmlConverter().appendElementContentAsXml(predecessorsElement, predecessor);
		}
	}
}

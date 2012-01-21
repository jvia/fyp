package org.bham.aucom.data.io.xml.in.converter;

import java.util.ArrayList;
import java.util.UUID;

import nu.xom.Element;
import nu.xom.Nodes;

import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.ObservationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;

public class XmlToObservationTimeSeriesConverter extends XmlToTimeSeriesConverter<Observation>{
	/*
	 * (non-Javadoc)
	 * Interface implementation 
	 */
	
	@Override
	public	Observation createDataFromElement(Element e) {
		Nodes nodes = e.query("./ts:observation", getContext());
        // if( 0 == nodes.size()){
        // Assert.fail(e.toXML());
        // }
		Element observationElement = (Element)nodes.get(0);
		long timestamp = getTimestamp(observationElement);
		Element content = getcontentFrom(observationElement); 
		return new Observation(content, timestamp);
	}


	@Override
	public TimeSeries<Observation> createTimeSeries(UUID id, UUID generatorID, UUID generatedFromID, ArrayList<Observation> items) {
		ObservationTimeSeries obsts = new ObservationTimeSeries();
		obsts.setId(id);
		obsts.setGenerator(generatorID);
		obsts.setGeneratedFrom(generatedFromID);
		obsts.addAll(items);
		return obsts;
	}
	
	
	/*
	 * Helper functions
	 */
	
	protected Element getcontentFrom(Element e) {
		Nodes nodes = e.query("./*", XmlToTimeSeriesConverter.getContext());
		if(nodes.size() == 0){
			return new Element("emptyConcent");
		}
		Element contentValueElement  = (Element)nodes .get(0);
		return contentValueElement;
	}
}

package org.bham.aucom.data.io.xml.in.converter;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import nu.xom.Element;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.data.timeseries.TemporalProbabilityFeatureTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;

public class XmlToTemporalProbabilityFeatureTimeSeriesConverter extends XmlToTimeSeriesConverter<TemporalProbabilityFeature> {
	@Override
	public	TemporalProbabilityFeature createDataFromElement(Element e) {
		TemporalDurationFeature tdf = new XmlToTemporalDurationFeatureTimeSeriesConverter().createDataFromElement(e);
		LinkedHashMap<DataType, Double> probabilities = getProbabilities(tdf, e);
		return new TemporalProbabilityFeature(tdf, probabilities);
	}

	private LinkedHashMap<DataType, Double> getProbabilities(TemporalDurationFeature tdf, Element e) {
		Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO,"extracting "+ tdf.getPredecessors().size() +"  probabilities for " + tdf.getName());
		LinkedHashMap<DataType, Double> probabilities = new LinkedHashMap<DataType, Double>();
		for(DataType predecessor :tdf.getPredecessors()){
			String queryString = "./ts:temporalprobabilityfeature/ts:predecessors/ts:element[@eventType='"+predecessor.getEventType()+"']";
			Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO,"predecessor "+ predecessor + " string " + queryString);
			Element element = (Element)e.query(queryString, XmlToTimeSeriesConverter.getContext()).get(0);
			Double probability = Double.valueOf(element.getAttributeValue("value"));
			probabilities.put(predecessor, probability);
		}
		return probabilities;
	}

	@Override
	public
	TimeSeries<TemporalProbabilityFeature> createTimeSeries(UUID id, UUID generatorID, UUID generatedFromID, ArrayList<TemporalProbabilityFeature> items) {
		TemporalProbabilityFeatureTimeSeries tpfts = new TemporalProbabilityFeatureTimeSeries(generatorID, generatedFromID, id, items);
		return tpfts;
	}

}

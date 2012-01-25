package org.bham.aucom.data.io.xml.in.converter;

import nu.xom.Element;
import nu.xom.Nodes;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.DataTypeTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class XmlToDataTypeTimeSeriesConverter extends XmlToTimeSeriesConverter<DataType> {
    @Override
    public DataType createDataFromElement(Element e) {
        Observation obs = new XmlToObservationTimeSeriesConverter().createDataFromElement(e);
        int eventType = getEventTypeIdFrom(e);
        List<DomainFeature> domainFeatures = getDomainFeaturesFrom(e);
        return new DataType(domainFeatures, eventType, obs);
    }

    private List<DomainFeature> getDomainFeaturesFrom(Element e) {
        List<DomainFeature> domainFeatures = new ArrayList<DomainFeature>();
        Nodes nodes = e.query("./ts:datatype/ts:parameter[@type='domainfeature']", getContext());
        for (int i = 0; i < nodes.size(); i++) {
            String featureName = ((Element) nodes.get(i)).getAttributeValue("name");
            String featureValue = ((Element) nodes.get(i)).getAttributeValue("value");
            domainFeatures.add(new DomainFeature(featureName, featureValue));
        }
        return domainFeatures;
    }

    private int getEventTypeIdFrom(Element e) {
        Nodes nodes = e.query("./ts:datatype/ts:parameter[@type='eventTypeId']", getContext());
        int eventTypeId = -1;
        if (nodes.size() != 0) {
            eventTypeId = Integer.valueOf(nodes.get(0).getValue());
        }
        return eventTypeId;
    }

    @Override
    public TimeSeries<DataType> createTimeSeries(UUID id, UUID generatorID, UUID generatedFromID, ArrayList<DataType> items) {
        DataTypeTimeSeries dtpts = new DataTypeTimeSeries();
        dtpts.setId(id);
        dtpts.setGenerator(generatorID);
        dtpts.setGeneratedFrom(generatedFromID);
        dtpts.addAll(items);
        return dtpts;
    }
}

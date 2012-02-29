package org.bham.aucom.data.io.xml.in.converter;

import nu.xom.Element;
import nu.xom.Nodes;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.timeseries.TemporalDurationFeatureTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.UUID;

public class XmlToTemporalDurationFeatureTimeSeriesConverter extends XmlToTimeSeriesConverter<TemporalDurationFeature> {
    @Override
    public TemporalDurationFeature createDataFromElement(Element e) {
        DataType dtt = new XmlToDatatypeTimeSeriesConverter().createDataFromElement(e);
        LinkedHashMap<DataType, Long> durations = getDurationsFrom(e);
        TemporalDurationFeature tdf = new TemporalDurationFeature(dtt, durations);
        return tdf;
    }

    private LinkedHashMap<DataType, Long> getDurationsFrom(Element e) {
        Nodes nodes = getPredecessorsNodes(e);
        LinkedHashMap<DataType, Long> durations = new LinkedHashMap<DataType, Long>();
        for (int i = 0; i < nodes.size(); i++) {
            Element predeCessorElement = (Element) nodes.get(i);
            Long duration = getDurationFroPredecessor(predeCessorElement);
            DataType dtt = new XmlToDatatypeTimeSeriesConverter().createDataFromElement((Element) nodes.get(i));
            durations.put(dtt, duration);
        }
        return durations;
    }

    private Long getDurationFroPredecessor(Element predeCessorElement) {
        return Long.valueOf(predeCessorElement.getAttributeValue("duration"));
    }

    /**
     * @param e
     * @return
     */
    private Nodes getPredecessorsNodes(Element e) {
        return e.query("./ts:temporaldurationfeature/ts:predecessors/ts:element", getContext());
    }

    @Override
    public TimeSeries<TemporalDurationFeature> createTimeSeries(UUID id, UUID generatorID, UUID generatedFromID, ArrayList<TemporalDurationFeature> items) {
        TemporalDurationFeatureTimeSeries tdfts = new TemporalDurationFeatureTimeSeries();
        tdfts.setId(id);
        tdfts.setGenerator(generatorID);
        tdfts.setGeneratedFrom(generatedFromID);
        return tdfts;
    }

}
 
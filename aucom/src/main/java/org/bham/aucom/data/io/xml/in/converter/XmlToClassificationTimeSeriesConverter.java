package org.bham.aucom.data.io.xml.in.converter;

import nu.xom.Element;
import org.bham.aucom.data.*;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

public class XmlToClassificationTimeSeriesConverter extends XmlToTimeSeriesConverter<Classification> {
    @Override
    public Classification createDataFromElement(Element e) {
        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, e.toXML());
        SystemFaultStatus status = getSystemStatus(e);
        Score s = new XmlToScoreTimeSeriesConverter().createDataFromElement(e);
        if (s != null) {
            Classification a = new Classification(new SingleScore(new TemporalProbabilityFeature(), 0.0), status);
            Classification c;
            c = new Classification(s, status);
            return c;
        }
        return null;
    }

    private SystemFaultStatus getSystemStatus(Element e) {
        String systemStatusString = ((Element) e.query("./ts:classification", this.getContext()).get(0)).getAttributeValue("systemstatus");
        return Enum.valueOf(SystemFaultStatus.class, systemStatusString);
    }

    @Override
    public TimeSeries<Classification> createTimeSeries(UUID id, UUID generatorID, UUID generatedFromID, ArrayList<Classification> items) {
        ClassificationTimeSeries sts = new ClassificationTimeSeries();
        sts.setId(id);
        sts.setGeneratedFrom(generatedFromID);
        sts.setGenerator(generatorID);
        sts.addAll(items);
        return sts;
    }


}

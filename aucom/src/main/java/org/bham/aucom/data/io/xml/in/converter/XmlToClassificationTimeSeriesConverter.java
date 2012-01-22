package org.bham.aucom.data.io.xml.in.converter;

import java.util.ArrayList;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import nu.xom.Element;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.SystemFaultStatus;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;

public class XmlToClassificationTimeSeriesConverter extends XmlToTimeSeriesConverter<Classification>{
	@Override
	public Classification createDataFromElement(Element e) {
		Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE,e.toXML());
		SystemFaultStatus status = getSystemStatus(e);
		Score s = new XmlToScoreTimeSeriesConverter().createDataFromElement(e);
		if(s != null){
			return new Classification(s,status);
		}
		return null;
	}

	private SystemFaultStatus getSystemStatus(Element e) {
		String systemStatusString = ((Element)e.query("./ts:classification", getContext()).get(0)).getAttributeValue("systemstatus");
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

package org.bham.aucom.fts.tranform;

import java.util.logging.Logger;

import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;
import nu.xom.Document;
import nu.xom.Element;

import org.bham.aucom.data.Observation;

public class DocumentToObservation extends AbstractTransformNode<Document, Observation> {

	@Override
	protected Observation transform(Document arg0) throws Exception {
			Logger.getLogger(this.getClass().getCanonicalName()).info("transformation input : " + arg0.toXML());
			Element out = (Element)arg0.getRootElement().copy();
			Logger.getLogger(this.getClass().getCanonicalName()).info("transformation output : " + out.toXML());
			long timestamp = Long.valueOf(arg0.getRootElement().getAttributeValue("timestamp")).longValue();
			Observation observation = new Observation(out, timestamp); 
			return observation;
	}

}

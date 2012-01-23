package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;
import org.bham.aucom.data.Classification;

public class RawDataToCsvRow extends AbstractTransformNode<Classification, String> {

    @Override
    protected String transform(Classification arg0) throws Exception {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append(arg0.getEventType()).append(";");
        strBuffer.append(arg0.getTimestamp()).append(";");
        strBuffer.append(arg0.getValue()).append(";");
        strBuffer.append(arg0.getVariance()).append(";");
        strBuffer.append(arg0.getStatusAsDouble());
        return strBuffer.toString();
    }
}

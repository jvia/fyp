package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;
import org.bham.aucom.data.Classification;

public class RawDataToCsvRow extends AbstractTransformNode<Classification, String> {
    boolean isFirstElement = true;
    long ts = -1l;

    @Override
    protected String transform(Classification arg0) throws Exception {
        StringBuilder strBuffer = new StringBuilder();
        strBuffer.append(arg0.getEventType());
        strBuffer.append(";");
        strBuffer.append(arg0.getTimestamp());
        strBuffer.append(";");
        strBuffer.append(arg0.getValue());
        strBuffer.append(";");
        strBuffer.append(arg0.getVariance());
        strBuffer.append(";");
        strBuffer.append(arg0.getStatusAsDouble());
        return strBuffer.toString();
    }
}

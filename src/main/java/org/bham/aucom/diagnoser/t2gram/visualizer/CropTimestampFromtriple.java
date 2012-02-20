package org.bham.aucom.diagnoser.t2gram.visualizer;

import net.sf.xcf.fts.Triple;
import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;
import org.bham.aucom.data.SystemFaultStatus;


public class CropTimestampFromtriple extends AbstractTransformNode<Triple<Long, Double, SystemFaultStatus>, Triple<Long, Double, SystemFaultStatus>> {
    Long ts;

    public CropTimestampFromtriple() {
        ts = (long) -1;
    }

    @Override
    protected Triple<Long, Double, SystemFaultStatus> transform(Triple<Long, Double, SystemFaultStatus> input) throws Exception {
        if (ts.equals(Long.valueOf(-1))) {
            ts = input.first;
        }
        return new Triple<Long, Double, SystemFaultStatus>(input.first - ts, input.second, input.third);
    }

}

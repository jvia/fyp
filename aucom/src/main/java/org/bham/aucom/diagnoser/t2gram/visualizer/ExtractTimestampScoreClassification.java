package org.bham.aucom.diagnoser.t2gram.visualizer;

import net.sf.xcf.fts.Triple;
import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.SystemFaultStatus;

public class ExtractTimestampScoreClassification extends AbstractTransformNode<Classification, Triple<Long, Double, SystemFaultStatus>> {
	long firstTs;

	public ExtractTimestampScoreClassification() {
		firstTs = -1L;
	}

	@Override
	protected Triple<Long, Double, SystemFaultStatus> transform(Classification input) throws Exception {
		if(firstTs == -1L){
			firstTs = input.getTimestamp();
		}
		return new Triple<Long, Double, SystemFaultStatus>(input.getTimestamp() - firstTs, input.getValue(), input.getStatus());
	}
}

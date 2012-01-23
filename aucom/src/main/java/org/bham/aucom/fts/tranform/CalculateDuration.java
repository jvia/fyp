package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;

import org.bham.aucom.data.DataAttributes;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.timeseries.TimeSeries;

class CalculateDuration extends AbstractTransformNode<DataType, DataType> {
	private final TimeSeries<DataType> sequence;
	private final long beginTimestamp;
	public CalculateDuration(TimeSeries<DataType> sequence) {
		this.sequence = sequence;
		this.beginTimestamp = sequence.get(0).getTimestamp();
	}
	@Override
	protected DataType transform(DataType input) throws Exception {
		this.sequence.addAttribute(DataAttributes.DURATION, String.valueOf(input.getTimestamp() - this.beginTimestamp));
		return null;
	}

}

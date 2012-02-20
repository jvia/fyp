package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;
import org.bham.aucom.data.DataAttributes;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.timeseries.TimeSeries;

public class CountEvents extends AbstractTransformNode<DataType,DataType>{
	TimeSeries<DataType> sequence;
	public CountEvents(TimeSeries<DataType> sequence) {
		this.sequence = sequence;
	}
	@Override
	protected DataType transform(DataType input) throws Exception {
		int numElements = 0;
		if(this.sequence.containsAttribute(DataAttributes.NUMBER_OF_ELEMENTS)){
			numElements = Integer.valueOf(this.sequence.getAttributeValue(DataAttributes.NUMBER_OF_ELEMENTS));
		}
		numElements++;
		this.sequence.addAttribute(DataAttributes.NUMBER_OF_ELEMENTS, String.valueOf(numElements));
		return null;
	}

}

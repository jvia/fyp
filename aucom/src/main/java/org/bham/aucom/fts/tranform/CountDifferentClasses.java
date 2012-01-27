package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;
import org.bham.aucom.data.DataAttributes;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.timeseries.TimeSeries;

public class CountDifferentClasses extends AbstractTransformNode<DataType, DataType> {
	TimeSeries<DataType> sequence;

	public CountDifferentClasses(TimeSeries<DataType> sequence) {
		this.sequence = sequence;
	}

	@Override
	protected DataType transform(DataType input) throws Exception {
		String classId = String.valueOf(Integer.valueOf(input.getAttributeValue(DataAttributes.CLASSID)));
		String attributeName = DataAttributes.NUMBER_OF_ELEMENTS + classId;
		long numOccurances = 1;
		if (this.sequence.containsAttribute(attributeName)) {
			numOccurances = Long.valueOf(this.sequence.getAttributeValue(attributeName)) + 1;
		}
		this.sequence.addAttribute(attributeName, String.valueOf(numOccurances));
		return null;
	}

}

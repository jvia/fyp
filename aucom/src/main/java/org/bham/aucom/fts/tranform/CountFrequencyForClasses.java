package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;

import org.bham.aucom.data.DataAttributes;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.timeseries.TimeSeries;

public class CountFrequencyForClasses extends AbstractTransformNode<DataType, DataType> {
	TimeSeries<DataType> sequence;
	public CountFrequencyForClasses(TimeSeries<DataType> sequence) {
		this.sequence = sequence;
	}
	@Override
	protected DataType transform(DataType input) throws Exception {
		String classFrequencyName = DataAttributes.FREQUENCY + input.getEventType();
		String numOfElementsForClassName = DataAttributes.NUMBER_OF_ELEMENTS + input.getEventType();
		double numOfElementsForClassValue = Double.valueOf(this.sequence.getAttributeValue(numOfElementsForClassName)).doubleValue();
		String durationName = DataAttributes.DURATION;
		double durationValue = Double.valueOf(this.sequence.getAttributeValue(durationName)).doubleValue();
		double frequency = numOfElementsForClassValue / durationValue *1000.0;
		this.sequence.addAttribute(classFrequencyName, String.valueOf(frequency));
		return null;
	}

}

package org.bham.aucom.fts.tranform;

import java.util.HashMap;

import org.bham.aucom.data.DataType;


public class CountDataTypes extends AbstractAucomTranformNode<DataType, DataType> {
	private HashMap<Integer, Integer> counting;
	
	public CountDataTypes() {
		super("CountDataTypes");
		setCounting(new HashMap<Integer, Integer>());
	}
	

	@Override
	protected DataType iTransform(DataType input) throws Exception {
		int eventType = input.getEventType();
		if(counting.containsKey(eventType)){
			counting.put(eventType, counting.get(eventType) +1);
		}else{
			counting.put(eventType, 1);
		}
		return input;
	}


	protected void setCounting(HashMap<Integer, Integer> counting) {
		this.counting = counting;
	}


	public HashMap<Integer, Integer> getCounting() {
		return counting;
	}

}

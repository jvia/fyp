package org.bham.aucom.xcfrecorder;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.fts.tranform.AbstractAucomTranformNode;

public class Counter<TIn extends AbstractData> extends AbstractAucomTranformNode<TIn, TIn> {
	int counter = 0;
	Counter() {
		super("counter");
	}

	@Override
	protected TIn iTransform(TIn input) throws Exception {
		increase();
		return input;
	}
	public synchronized int getCounter(){
		return this.counter;
	}
	private synchronized void increase(){
		this.counter++;
	}
	public synchronized void reset(){
		this.counter =0;
	}
}

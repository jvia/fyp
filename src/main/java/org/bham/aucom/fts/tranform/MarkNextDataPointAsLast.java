package org.bham.aucom.fts.tranform;

import org.bham.aucom.data.AbstractData;

public class MarkNextDataPointAsLast<TIn extends AbstractData> extends AbstractAucomTranformNode<TIn, TIn> {

	private boolean markerActive;

	public MarkNextDataPointAsLast() {
		super("MarkNextDataPointAsLast");
		markerActive = false;
	}

	@Override
	protected TIn iTransform(TIn input) throws Exception {
		if(isMarkerActive()){
			input.markAsLastElement();
			deactivate();
		}
		return input;
	}
	private boolean isMarkerActive() {
		return markerActive;
	}

	private synchronized void deactivate() {
		markerActive = false;
	}

	public void setMarkNextElementAsLast(){
		markerActive = true;
	}

}

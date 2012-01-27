package org.bham.aucom.fts.tranform;

import java.io.Serializable;

import org.bham.aucom.data.AbstractData;

public class CropTimestampFromData<T extends AbstractData> extends AbstractAucomTranformNode<T, T> implements Serializable {
	private static final long serialVersionUID = 1L;

	public CropTimestampFromData() {
		super("CropTimestampFromData");
	}

	private long firstTimestamp = -1;

	public void reset() {
		setFirstTimestamp(-1);
	}

	public void setFirstTimestamp(long firstTimestamp) {
		this.firstTimestamp = firstTimestamp;
	}

	public long getFirstTimestamp() {
		return firstTimestamp;
	}

	@Override
	protected T iTransform(T input) throws Exception {
		if (getFirstTimestamp() == -1) {
			setFirstTimestamp(input.getTimestamp());
		}
		input.setTimestamp(input.getTimestamp() - getFirstTimestamp());
		return input;
	}
}

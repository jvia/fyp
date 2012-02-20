package org.bham.aucom.data.timeseries;

import java.util.EventObject;

public class TimeseriesStatusEvent extends EventObject {

	private static final long serialVersionUID = 1L;
	private TimeseriesStatus status;
	private int startIndex;
	private int endIndex;
	public TimeseriesStatusEvent(Object source, TimeseriesStatus status, int startIndex, int endIndex) {
		super(source);
		this.setStatus(status);
		this.setStartIndex(startIndex);
		this.setEndIndex(endIndex);
		
	}
	private void setStartIndex(int startIndex) {
		this.startIndex = startIndex;
	}
	public  int getStartIndex() {
		return this.startIndex;
	}
	private void setEndIndex(int endIndex) {
		this.endIndex = endIndex;
	}
	public int getEndIndex() {
		return this.endIndex;
	}
	private void setStatus(TimeseriesStatus status) {
		this.status = status;
	}
	public TimeseriesStatus getStatus() {
		return this.status;
	}
	@Override
	public String toString() {
		return this.status + " " + this.startIndex + " " + this.endIndex;
	}
}

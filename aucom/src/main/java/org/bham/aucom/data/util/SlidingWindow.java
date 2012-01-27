package org.bham.aucom.data.util;

import java.io.Serializable;
import java.util.UUID;

public class SlidingWindow implements Serializable{
	private UUID id;
	private static final long serialVersionUID = -1274196816789289259L;
	private long intervalSize;
	private long intervalOverlapSize;

	public SlidingWindow(long intervalSize, long intervalOverlapSize) {
		this.intervalSize=intervalSize;
		this.intervalOverlapSize = intervalOverlapSize;
		this.id = UUID.randomUUID();
	}
	public SlidingWindow(long intervalSize, long intervalOverlapSize, UUID id) {
		this(intervalSize, intervalOverlapSize);
		this.id = id;
	}

	public synchronized long getIntervalSize() {
		return this.intervalSize;
	}

	public synchronized void setIntervalSize(long intervalSize) {
		this.intervalSize = intervalSize;
	}

	public long getIntervalOverlapSize() {
		return this.intervalOverlapSize;
	}

	public void setIntervalOverlapSize(long intervalOverlapSize) {
		this.intervalOverlapSize = intervalOverlapSize;
	}
	public void copy(SlidingWindow slidingWindow){
		setIntervalOverlapSize(slidingWindow.getIntervalOverlapSize());
		setIntervalSize(slidingWindow.getIntervalSize());
	}

	protected void setId(UUID id) {
		this.id = id;
	}

	public UUID getId() {
		return this.id;
	}
	@Override
	public String toString() {
		return "["+this.getIntervalSize()+"," +this.getIntervalOverlapSize() +"]";
	}
	
}
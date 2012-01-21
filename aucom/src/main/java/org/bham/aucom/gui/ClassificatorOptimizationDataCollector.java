package org.bham.aucom.gui;

import java.util.EventListener;
import java.util.EventObject;

import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.ScoreTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesStatusListener;
import org.bham.aucom.data.timeseries.TimeseriesStatus;
import org.bham.aucom.data.timeseries.TimeseriesStatusEvent;


public class ClassificatorOptimizationDataCollector implements TimeSeriesStatusListener {
	int numRequiredElements = 1000;
	private final TimeSeries<Score> collectedData;
	boolean isOptimizing;
	TimeSeries<? extends Score> timseriesToCollectFrom;
	private boolean isStopAutomatically;
	public ClassificatorOptimizationDataCollector() {
		this.collectedData = new ScoreTimeSeries();
		this.isOptimizing = false;
		this.isStopAutomatically = false;
	}

	public ClassificatorOptimizationDataCollector(int inNumRequiredElements) {
		this();
		this.numRequiredElements = inNumRequiredElements;

	}
	public ClassificatorOptimizationDataCollector(int inNumRequiredElements,  boolean inIsStopAutomatically) {
		this(inNumRequiredElements);
		this.isStopAutomatically = inIsStopAutomatically;
	}

	public void start(TimeSeries<? extends Score> inTimseriesToCollectFrom) {
		if (this.isOptimizing) {
			// ignore
		} else {
			timseriesToCollectFrom = inTimseriesToCollectFrom;
			timseriesToCollectFrom.addTimeSeriesStatusListener(this);
			fireStarting();
			this.isOptimizing = true;
		}
	}
	public void stop(){
		if(this.isOptimizing){
			this.timseriesToCollectFrom.removeTimeseriesStatusListener(this);
			this.isOptimizing = false;
			this.getCollectedData().get(getCollectedData().size()-1).markAsLastElement();
		fireStopping();
		}else{
			// ignore
		}
	}




	/*
	 * event handling ---->
	 */
	protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();
	

	public void addStatusListener(ClOpDataCollectorStatusListener listener) {
		this.listenerList.add(ClOpDataCollectorStatusListener.class, listener);
	}

	public void removeTimeseriesStatusListener(ClOpDataCollectorStatusListener listener) {
		this.listenerList.remove(ClOpDataCollectorStatusListener.class, listener);
	}

	// This method is used to fire TrainingStatusChangedEvents
	void fireTimeseriesStatusChangedEvent(ClOpDataCollectorStatus evt) {
		Object[] listeners = this.listenerList.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == ClOpDataCollectorStatusListener.class) {
				((ClOpDataCollectorStatusListener) listeners[i + 1]).clOptStatusChanged(evt);
			}
		}
	}

	public interface ClOpDataCollectorStatusListener extends EventListener {
		public void clOptStatusChanged(ClOpDataCollectorStatus status);
	}

	public enum ClOpEvent {
		STARTING, STOPPING, REQUIREDREACHED, ELEMENTADDED
	}

	public class ClOpDataCollectorStatus extends EventObject {
		private static final long serialVersionUID = 1L;
		private ClOpEvent event;

		public ClOpDataCollectorStatus(Object source, ClOpEvent event) {
			super(source);
			this.setEvent(event);
		}

		private void setEvent(ClOpEvent event) {
			this.event = event;
		}

		public ClOpEvent getEvent() {
			return event;
		}

	}

	/*
	 * <---- event handling
	 */

	@SuppressWarnings("unchecked")
	@Override
	public void timeseriesStatusChanged(TimeseriesStatusEvent status) {
		if (status.getStatus().equals(TimeseriesStatus.ELEMENTSADDED)) {
			for (int i = status.getStartIndex(); i <= status.getEndIndex(); i++) {
				TimeSeries<? extends Score> ts = (TimeSeries<? extends Score>) status.getSource();
				this.getCollectedData().add((Score)ts.get(i));
			}
		}
		fireElementAdded();
		if(this.numRequiredElements <= this.getCollectedData().size()){
			fireRequiredReached();
			if(this.isStopAutomatically){
				Score last = this.getCollectedData().get(this.getCollectedData().size()-1);
				this.getCollectedData().remove(this.getCollectedData().size()-1);
				this.getCollectedData().add((Score)last.copy());
				stop();
			}
		}
	}

	/**
	 * 
	 */
	private void fireRequiredReached() {
		fireTimeseriesStatusChangedEvent(new ClOpDataCollectorStatus(this, ClOpEvent.REQUIREDREACHED));
	}

	private void fireElementAdded() {
		fireTimeseriesStatusChangedEvent(new ClOpDataCollectorStatus(this, ClOpEvent.ELEMENTADDED));
	}
	private void fireStarting() {
		fireTimeseriesStatusChangedEvent(new ClOpDataCollectorStatus(this, ClOpEvent.STARTING));
	}
	private void fireStopping() {
		fireTimeseriesStatusChangedEvent(new ClOpDataCollectorStatus(this, ClOpEvent.STOPPING));
	}

	public TimeSeries<Score> getCollectedData() {
		return collectedData;
	}

	public boolean isReady() {
		return false;
	}

}

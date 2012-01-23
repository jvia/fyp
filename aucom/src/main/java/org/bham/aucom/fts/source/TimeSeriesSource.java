package org.bham.aucom.fts.source;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesStatusListener;
import org.bham.aucom.data.timeseries.TimeSeriesStatus;
import org.bham.aucom.data.timeseries.TimeSeriesStatusEvent;


public class TimeSeriesSource<T extends AbstractData> extends AucomSourceAdapter<T> implements TimeSeriesStatusListener {
	private static final long serialVersionUID = 0L;
	final BlockingQueue<T> queue;
	private TimeSeries<T> input;



	public TimeSeriesSource(TimeSeries<T> input, String noficifactionName) {
		super("SequenceQueueSource");
		this.queue = new LinkedBlockingQueue<T>();
		this.setInput(input);
		this.setState(SourceStatus.CONNECTED);
		copyDataFromTimeSeriesToSourceQueue(0, this.getInput().size() - 1);
		Logger.getLogger(this.getClass().getCanonicalName()).info("init:" + noficifactionName + " elements: " + this.queue.size());
	}

	public TimeSeriesSource(String noficifactionName) {
		super("SequenceQueueSource");
		this.queue = new LinkedBlockingQueue<T>();
		this.setInput(null);
		this.setState(SourceStatus.CONNECTED);
		Logger.getLogger(this.getClass().getCanonicalName()).info("initializing queue with notification name:" + noficifactionName + "and " + this.queue.size() + " elements");
	}

	@Override
	protected T iNextItem() throws Exception {
		Logger.getLogger(this.getClass().getCanonicalName()).info(" next element called, queue size " + getQueue().size());
		T element;
		element = this.getQueue().take();
		if(element != null && element.isMarkedAsLastElement()){
			setsendLastElement();
		}
		return element;
	}

	

	

	protected BlockingQueue<T> getQueue() {
		return this.queue;
	}

	public void reset(){
		getQueue().clear();
		setInput(null);
	}
	public int size() {
		return this.queue.size();
	}

	protected void copyDataFromTimeSeriesToSourceQueue(int startIndex, int endIndex) {
		if (getInput() == null)
			return;
		for (int i = startIndex; i <= endIndex; i++) {
			TimeSeriesSource.this.queue.add(this.getInput().get(i));
		}
	}

	@Override
	public void timeSeriesStatusChanged(TimeSeriesStatusEvent status) {
		if (status.getStatus().equals(TimeSeriesStatus.ELEMENTS_ADDED)) {
			copyDataFromTimeSeriesToSourceQueue(status.getStartIndex(), status.getEndIndex());
		}
	}

	

	public void setInput(TimeSeries<T> newInput) {
		deregisterFromCurrentInputIfNotNull();
		if (newInput == null) {
			Logger.getLogger(this.getClass().getCanonicalName()).info("new input to set is null");
		} else {
			Logger.getLogger(this.getClass().getCanonicalName()).info("setting new input for inputqueue, size " + newInput.size());
		}

		this.input = newInput;
		registerOnNewInputIfNotNull();
		copyInitialElementIfInputNotNull();
	}

	/**
	 * This is a helper function which is called when a new timeseries has been
	 * set as input . It copies all elements from the @input timeseries to the
	 * source blocking queue if the timeseries object is not null.
	 */
	private void copyInitialElementIfInputNotNull() {
		if (this.input != null) {
			copyDataFromTimeSeriesToSourceQueue(0, this.input.size() - 1);
		}
	}

	/**
	 * 
	 */
	private void registerOnNewInputIfNotNull() {
		if (this.input != null) {
			Logger.getLogger(this.getClass().getCanonicalName()).info("starting listenting on new input");
			this.getInput().addTimeSeriesStatusListener(this);
		}
	}

	/**
	 * 
	 */
	private void deregisterFromCurrentInputIfNotNull() {
		Logger.getLogger(this.getClass().getCanonicalName()).info("entered deregisterFromCurrentInputIfNotNull " + (getInput() != null));
		if (getInput() != null) {
			getInput().removeTimeseriesStatusListener(this);
			Logger.getLogger(this.getClass().getCanonicalName()).info("stopping listening on current input");
		}
	}

	public TimeSeries<T> getInput() {
		return this.input;
	}

	@Override
	protected void iDisconnect() throws ActionFailedException {
		// is ignored by this implementation
	}

	@Override
	protected void iConnect() throws ActionFailedException {
		// is ignored by this implementation
	}

	public boolean isReady() {
		return false;
	}

}

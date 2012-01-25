package org.bham.aucom.fts.source;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesStatus;
import org.bham.aucom.data.timeseries.TimeSeriesStatusEvent;
import org.bham.aucom.data.timeseries.TimeSeriesStatusListener;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;


public class TimeSeriesSource<T extends AbstractData> extends AucomSourceAdapter<T> implements TimeSeriesStatusListener {
    private static final long serialVersionUID = 0L;
    private final BlockingQueue<T> queue;
    private TimeSeries<T> input;
    private final Logger log = Logger.getLogger(getClass().getName());


    public TimeSeriesSource(TimeSeries<T> input, String noficifactionName) {
        super("SequenceQueueSource");
        queue = new LinkedBlockingQueue<T>();
        setInput(input);
        setState(SourceStatus.CONNECTED);
        copyDataFromTimeSeriesToSourceQueue(0, getInput().size() - 1);

        log.info("Initializing with" + noficifactionName + " elements: " + queue.size());
    }

    public TimeSeriesSource(String noficifactionName) {
        super("SequenceQueueSource");
        queue = new LinkedBlockingQueue<T>();
        setInput(null);
        setState(SourceStatus.CONNECTED);

        log.info(String.format("Initializing queue with notification name \"%s\" and %d elements", noficifactionName, queue.size()));
    }

    @Override
    protected T iNextItem() throws Exception {
        log.fine("Next element called, queue size " + getQueue().size());
        T element = getQueue().take();
        if (element != null && element.isMarkedAsLastElement())
            setsendLastElement();
        return element;
    }

    BlockingQueue<T> getQueue() {
        return queue;
    }

    public void reset() {
        getQueue().clear();
        setInput(null);
    }

    public int size() {
        return queue.size();
    }

    void copyDataFromTimeSeriesToSourceQueue(int startIndex, int endIndex) {
        if (getInput() == null) return;
        for (int i = startIndex; i <= endIndex; i++)
            queue.add(getInput().get(i));
    }

    @Override
    public void timeSeriesStatusChanged(TimeSeriesStatusEvent status) {
        if (status.getStatus().equals(TimeSeriesStatus.ELEMENTS_ADDED))
            copyDataFromTimeSeriesToSourceQueue(status.getStartIndex(), status.getEndIndex());
    }


    public void setInput(TimeSeries<T> newInput) {
        deregisterFromCurrentInputIfNotNull();

        if (newInput == null)
            log.fine("New input to set is null");
        else
            log.fine("Setting new input for input queue, size " + newInput.size());

        input = newInput;
        registerOnNewInputIfNotNull();
        copyInitialElementIfInputNotNull();
    }

    /**
     * This is a helper function which is called when a new timeseries has been
     * set as input . It copies all elements from the @input timeseries to the
     * source blocking queue if the timeseries object is not null.
     */
    private void copyInitialElementIfInputNotNull() {
        if (input != null) {
            copyDataFromTimeSeriesToSourceQueue(0, input.size() - 1);
        }
    }

    private void registerOnNewInputIfNotNull() {
        if (input != null) {
            log.fine("Starting listenting on new input");
            getInput().addTimeSeriesStatusListener(this);
        }
    }

    private void deregisterFromCurrentInputIfNotNull() {
        log.finer("Entered deregisterFromCurrentInputIfNotNull " + (getInput() != null));
        if (getInput() != null) {
            getInput().removeTimeseriesStatusListener(this);
            log.fine("Stopping listening on current input");
        }
    }

    public TimeSeries<T> getInput() {
        return input;
    }

    @Override
    protected void iDisconnect() throws ActionFailedException {}

    @Override
    protected void iConnect() throws ActionFailedException {}

    public boolean isReady() {
        return false;
    }
}

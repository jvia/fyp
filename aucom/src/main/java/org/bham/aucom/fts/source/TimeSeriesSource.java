package org.bham.aucom.fts.source;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesStatusListener;
import org.bham.aucom.data.timeseries.TimeseriesStatus;
import org.bham.aucom.data.timeseries.TimeseriesStatusEvent;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.logging.Logger;

public class TimeSeriesSource<T extends AbstractData> extends AucomSourceAdapter<T> implements TimeSeriesStatusListener {
    private static final long serialVersionUID = 0L;
    final BlockingQueue<T> queue;
    private TimeSeries<T> input;
    private transient Logger log = Logger.getLogger(getClass().getName());


    public TimeSeriesSource(TimeSeries<T> input, String noficifactionName) {
        super("SequenceQueueSource");
        queue = new LinkedBlockingQueue<T>();
        setInput(input);
        setState(SourceStatus.CONNECTED);
        copyDataFromTimeSeriesToSourceQueue(0, getInput().size() - 1);
        log.config(String.format("Init: %s elements %d", noficifactionName, queue.size()));
    }

    public TimeSeriesSource(String noficifactionName) {
        super("SequenceQueueSource");
        queue = new LinkedBlockingQueue<T>();
        setInput(null);
        setState(SourceStatus.CONNECTED);
        log.info(String.format("Initializing queue with notification name: %s and %d elements", noficifactionName, queue.size()));
    }

    @Override
    protected T iNextItem() throws Exception {
        log.info(String.format("Next element called, queue size %d", getQueue().size()));
        T element;
        element = getQueue().take();

        if (element != null && element.isMarkedAsLastElement()) {
            setsendLastElement();
        }

        return element;
    }


    protected BlockingQueue<T> getQueue() {
        return queue;
    }

    public void reset() {
        getQueue().clear();
        setInput(null);
    }

    public int size() {
        return queue.size();
    }

    protected void copyDataFromTimeSeriesToSourceQueue(int startIndex, int endIndex) {
        if (getInput() == null)
            return;

        for (int i = startIndex; i <= endIndex; i++)
            queue.add(getInput().get(i));
    }

    @Override
    public void timeseriesStatusChanged(TimeseriesStatusEvent status) {
        if (status.getStatus().equals(TimeseriesStatus.ELEMENTSADDED))
            copyDataFromTimeSeriesToSourceQueue(status.getStartIndex(), status.getEndIndex());
    }


    public void setInput(TimeSeries<T> newInput) {
        deregisterFromCurrentInputIfNotNull();
        if (newInput == null) {
            log.info("new input to set is null");
        } else {
            log.info("setting new input for inputqueue, size " + newInput.size());
        }

        input = newInput;
        registerOnNewInputIfNotNull();
        copyInitialElementIfInputNotNull();
    }

    /**
     * This is a helper function which is called when a new timeseries has been
     * set as input. It copies all elements from the @input timeseries to the
     * source blocking queue if the timeseries object is not null.
     */
    private void copyInitialElementIfInputNotNull() {
        if (input != null) {
            copyDataFromTimeSeriesToSourceQueue(0, input.size() - 1);
        }
    }

    private void registerOnNewInputIfNotNull() {
        if (input != null) {
            log.info("starting listenting on new input");
            getInput().addTimeSeriesStatusListener(this);
        }
    }

    private void deregisterFromCurrentInputIfNotNull() {
        log.info(String.format("Entered deregisterFromCurrentInputIfNotNull %s", (getInput() != null)));
        if (getInput() != null) {
            getInput().removeTimeseriesStatusListener(this);
            log.info("Stopping listening on current input");
        }
    }

    public TimeSeries<T> getInput() {
        return input;
    }

    /**
     * Unused.
     *
     * @throws ActionFailedException
     */
    @Override
    protected void iDisconnect() throws ActionFailedException {}

    /**
     * Unused.
     *
     * @throws ActionFailedException
     */
    @Override
    protected void iConnect() throws ActionFailedException {}

    public boolean isReady() {
        return false;
    }

}

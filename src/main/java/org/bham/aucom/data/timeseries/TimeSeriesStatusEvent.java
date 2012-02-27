package org.bham.aucom.data.timeseries;

import java.util.EventObject;

/**
 * Class TimeSeriesStatusEvent represents an event of a period of time.
 *
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class TimeSeriesStatusEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    private TimeSeriesStatus status;
    private int startIndex;
    private int endIndex;

    /**
     * Constructor TimeSeriesStatusEvent creates a new TimeSeriesStatusEvent
     * instance.
     *
     * @param source     of type Object
     * @param status     of type TimeSeriesStatus
     * @param startIndex of type int
     * @param endIndex   of type int
     */
    public TimeSeriesStatusEvent(final Object source,
                                 final TimeSeriesStatus status,
                                 final int startIndex,
                                 final int endIndex) {
        super(source);
        setStatus(status);
        setStartIndex(startIndex);
        setEndIndex(endIndex);
    }

    /**
     * Method setStartIndex sets the startIndex of this TimeSeriesStatusEvent
     * object.
     *
     * @param startIndex the startIndex of this TimeSeriesStatusEvent object.
     */
    private void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    /**
     * Method getStartIndex returns the startIndex of this
     * TimeSeriesStatusEvent
     * object.
     *
     * @return the startIndex (type int) of this TimeSeriesStatusEvent object.
     */
    public int getStartIndex() {
        return startIndex;
    }

    /**
     * Method setEndIndex sets the endIndex of this TimeSeriesStatusEvent
     * object.
     *
     * @param endIndex the endIndex of this TimeSeriesStatusEvent object.
     */
    private void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    /**
     * Method getEndIndex returns the endIndex of this TimeSeriesStatusEvent
     * object.
     *
     * @return the endIndex (type int) of this TimeSeriesStatusEvent object.
     */
    public int getEndIndex() {
        return endIndex;
    }

    /**
     * Method setStatus sets the status of this TimeSeriesStatusEvent object.
     *
     * @param status the status of this TimeSeriesStatusEvent object.
     */
    private void setStatus(TimeSeriesStatus status) {
        this.status = status;
    }

    /**
     * Method getStatus returns the status of this TimeSeriesStatusEvent
     * object.
     *
     * @return the status (type TimeSeriesStatus) of this TimeSeriesStatusEvent
     *         object.
     */
    public TimeSeriesStatus getStatus() {
        return status;
    }

    /**
     * String representation.
     *
     * @return string representation
     */
    @Override
    public String toString() {
        return String.format("%s %d--%d", status, startIndex, endIndex);
    }
}

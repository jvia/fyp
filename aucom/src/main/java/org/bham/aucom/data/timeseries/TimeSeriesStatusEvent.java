package org.bham.aucom.data.timeseries;

import java.util.EventObject;

public class TimeSeriesStatusEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    private TimeSeriesStatus status;
    private int startIndex;
    private int endIndex;

    public TimeSeriesStatusEvent(Object source, TimeSeriesStatus status, int startIndex, int endIndex) {
        super(source);
        this.setStatus(status);
        this.setStartIndex(startIndex);
        this.setEndIndex(endIndex);

    }

    private void setStartIndex(int startIndex) {
        this.startIndex = startIndex;
    }

    public int getStartIndex() {
        return this.startIndex;
    }

    private void setEndIndex(int endIndex) {
        this.endIndex = endIndex;
    }

    public int getEndIndex() {
        return this.endIndex;
    }

    private void setStatus(TimeSeriesStatus status) {
        this.status = status;
    }

    public TimeSeriesStatus getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return this.status + " " + this.startIndex + " " + this.endIndex;
    }
}

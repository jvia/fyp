package org.bham.aucom.diagnoser;


import java.util.EventObject;


public class DetectorStatusChangedEvent extends EventObject {
    private static final long serialVersionUID = 1L;
    private final Detector.DetectorStatus previousStatus;
    private final Detector.DetectorStatus currentStatus;

    public DetectorStatusChangedEvent(Object source, Detector.DetectorStatus previousStatus2, Detector.DetectorStatus currentStatus2) {
        super(source);
        previousStatus = previousStatus2;
        currentStatus = currentStatus2;
    }

    public Detector.DetectorStatus getPreviousStatus() {
        return previousStatus;
    }

    public Detector.DetectorStatus getCurrentStatus() {
        return currentStatus;
    }

    @Override
    public String toString() {
        return "[" + previousStatus + "->" + currentStatus + "]";
    }

}

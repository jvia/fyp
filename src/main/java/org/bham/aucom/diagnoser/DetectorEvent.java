package org.bham.aucom.diagnoser;

import org.bham.aucom.data.SystemFaultStatus;

import java.util.EventObject;

public class DetectorEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    private final SystemFaultStatus status;

    public DetectorEvent(Object source, SystemFaultStatus inStatus) {
        super(source);
        status = inStatus;
    }

    public SystemFaultStatus getStatus() {
        return status;
    }

}

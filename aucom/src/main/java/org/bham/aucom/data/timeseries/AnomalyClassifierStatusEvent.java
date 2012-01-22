package org.bham.aucom.data.timeseries;

import java.util.EventObject;

public class AnomalyClassifierStatusEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    public AnomalyClassifierStatusEvent(Object source) {
        super(source);
    }
}

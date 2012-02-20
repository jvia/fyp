package org.bham.aucom.data.timeseries;

import java.util.EventObject;

public class AnomalyClassificatorStatusEvent extends EventObject {

    private static final long serialVersionUID = 1L;

    public AnomalyClassificatorStatusEvent(Object source) {
        super(source);
    }
}

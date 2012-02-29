package org.bham.aucom.data.timeseries;

import java.util.EventListener;

public interface AnomalyClassificatorListener extends EventListener {
    public void anomalyClassificatorStatusChanged(AnomalyClassificatorStatusEvent status);
}

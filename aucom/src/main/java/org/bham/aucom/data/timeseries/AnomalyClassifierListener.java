package org.bham.aucom.data.timeseries;

import java.util.EventListener;

public interface AnomalyClassifierListener extends EventListener {
	public void anomalyClassifierStatusChanged(AnomalyClassifierStatusEvent status);
}

package org.bham.aucom.data.timeseries;

import java.util.EventListener;

public interface TimeSeriesStatusListener extends EventListener {
	public void timeSeriesStatusChanged(TimeSeriesStatusEvent status);
}
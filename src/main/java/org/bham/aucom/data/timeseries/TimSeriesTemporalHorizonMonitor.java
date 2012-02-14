package org.bham.aucom.data.timeseries;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import org.bham.aucom.data.AbstractData;

public class TimSeriesTemporalHorizonMonitor {
	TimeSeries<? extends AbstractData> timeSeriesToMonitor = null;
	private ScheduledExecutorService service = null;
	private long horizonInMilliseconds = 0l;

	public TimSeriesTemporalHorizonMonitor(TimeSeries<? extends AbstractData> backendTimeseries) {
		this.timeSeriesToMonitor = backendTimeseries;
		setHorizonInMilliseconds(10000l);
		startMonitorService();
	}
	public TimSeriesTemporalHorizonMonitor(TimeSeries<? extends AbstractData> backendTimeseries, long horizonMilliseconds) {
		this.timeSeriesToMonitor = backendTimeseries;
		setHorizonInMilliseconds(horizonMilliseconds);
		startMonitorService();
	}

	private void startMonitorService() {
		this.service = Executors.newScheduledThreadPool(1);
		this.service.scheduleAtFixedRate(new Runnable() {

			@Override
			public void run() {
				TimSeriesTemporalHorizonMonitor.this.trim();
			}
		}, 100, 100, TimeUnit.MILLISECONDS);
	}

	protected void trim() {
		long newestTimestampInSeries = this.timeSeriesToMonitor.get(this.timeSeriesToMonitor.size() - 1).getTimestamp();
		long oldestAllowedTimstamp = newestTimestampInSeries - getHorizonInMilliseconds();
		for (int i = 0; i < this.timeSeriesToMonitor.size(); i++) {
			if (this.timeSeriesToMonitor.get(i).getTimestamp() < oldestAllowedTimstamp) {
				this.timeSeriesToMonitor.remove(i);
			}else{
				return; 
			}
		}
	}

	@Override
	protected void finalize() throws Throwable {
		this.service.shutdownNow();
		super.finalize();
	}

	public synchronized void setHorizonInMilliseconds(long horizonInMilliseconds) {
		this.horizonInMilliseconds = horizonInMilliseconds;
	}

	public synchronized long getHorizonInMilliseconds() {
		return this.horizonInMilliseconds;
	}
}

package org.bham.system.playfile;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.source.AucomSourceAdapter;
import org.bham.aucom.fts.source.SourceStatus;

public class PlayTimeSeriesSource<T extends AbstractData> extends AucomSourceAdapter<T> {
	private static final long serialVersionUID = 1L;
	final BlockingQueue<T> queue;
	TimeSeries<T> input;
	ScheduledExecutorService service;
	long firstTimestamp;
	Object waiterObj;

	public PlayTimeSeriesSource() {
		super("PlayTimeSeriesSource");
		queue = new LinkedBlockingQueue<T>();
		waiterObj = new Object();
	}

	/*
	 * set input timeseries. a null argument is ignored
	 */

	public void setInput(TimeSeries<T> newInput) {
		if (newInput == null) {
			return;
		}
		this.input = newInput;
	}

	@Override
	protected void iDisconnect() throws ActionFailedException {
		if (getStatus().equals(SourceStatus.DISCONNECTED)) {
			return;
		}
		input = null;
		service.shutdownNow();
	}

	/*
	 * creates executorservice which pushes events from input to queue based on
	 * timestamps of elements in queue calls to this method while the source has
	 * the "connected" statuts will be ignored
	 */

	public boolean isReady(){
		return input != null && input.size()!= 0;
	}
	@Override
	protected void iConnect() throws ActionFailedException {
		if (getStatus().equals(SourceStatus.CONNECTED)) {
			return;
		}
		if (input == null) {
			throw new ActionFailedException("input timeseries is missing");
		}
		if (input.size() == 0) {
			throw new ActionFailedException("timeseries emtpy");
		}
		firstTimestamp = Long.MIN_VALUE;
		scheduleTimeSeries();
	}

	void scheduleTimeSeries() {
		service = Executors.newScheduledThreadPool(1, new ThreadFactory() {
			
			@Override
			public Thread newThread(Runnable r) {
				Thread t = new Thread(r);
				t.setName("PlayTimeSeriesSource-tsSecheduler");
				return t;
			}
		});
		service.schedule(new Runnable() {
			int index = 0;

			@Override
			public void run() {
				try {
					queue.add(input.get(index));
					if (index == input.size() - 1) {
//						System.out.println("last index " + index);
						return;
					}
					long diff = input.get(index + 1).getTimestamp() - input.get(index).getTimestamp();
//					System.out.println(input.get(index + 1).getTimestamp() + " " + input.get(index).getTimestamp() + " " + index + " " + diff);
					index++;
					service.schedule(this, diff, TimeUnit.MILLISECONDS);
				} catch (Exception exception) {
					exception.printStackTrace();
				}
			}
		}, 0, TimeUnit.MILLISECONDS);
	}

	@Override
	protected T iNextItem() throws Exception {
		T element = null;
		element = this.queue.take();
		if (element != null && element.isMarkedAsLastElement()) {
			setsendLastElement();
		}
		return element;
	}
}

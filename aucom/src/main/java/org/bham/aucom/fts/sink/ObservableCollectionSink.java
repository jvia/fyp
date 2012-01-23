package org.bham.aucom.fts.sink;

import static org.bham.aucom.util.Constants.STOPPED;

import java.util.Collection;
import java.util.Observable;
import java.util.logging.Logger;

import net.sf.xcf.fts.nodes.sink.SinkAdapter;

import org.bham.aucom.data.AttributableObject;
import org.bham.aucom.util.AucomObservable;

class ObservableCollectionSink<T extends AttributableObject> extends SinkAdapter<T> {
	private Collection<T> data;
	private final Observable o;
	private final String notificationName;

	public ObservableCollectionSink(Collection<T> data, String noficifactionName) {
		super("ObservableCollectionSink");
		this.setData(data);
		this.o = new AucomObservable();
		this.notificationName = noficifactionName;
	}

	@Override
	protected void pushItem(T arg) throws Exception {
		if (arg.getAttributes().containsKey("lastElement")) {
			Logger.getLogger(this.getClass().getCanonicalName()).info("got last element");
			if (arg.getAttributes().get("lastElement").equals("yes")) {
				this.o.notifyObservers(this.notificationName + STOPPED);
				Logger.getLogger(this.getClass().getCanonicalName()).info("pushing last item " + arg);
			}
		}
		getData().add(arg);
	}

	public Observable getObservable() {
		return this.o;
	}

	void setData(Collection<T> data) {
		this.data = data;
	}

	Collection<T> getData() {
		return this.data;
	}

}

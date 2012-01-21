package org.bham.aucom.fts.sink;

import java.util.logging.Level;
import java.util.logging.Logger;

import net.sf.xcf.fts.nodes.sink.SinkAdapter;

public abstract class AucomSinkAdapter<TIn> extends SinkAdapter<TIn> {

	public AucomSinkAdapter(String name) {
		super(name);

	}

	/*
	 * event handling
	 */
	protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

	public void addSinkStatusListener(SinkStatusListener listener) {
		if (isSinkStatusListenerRegistered(listener)) {
			Logger.getLogger(this.getClass().getCanonicalName()).log(Level.WARNING, "listener " + listener + " already registered");
			return;
		}
		Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, "adding new listener " + listener);
		this.listenerList.add(SinkStatusListener.class, listener);
	}

	public boolean isSinkStatusListenerRegistered(SinkStatusListener listener) {
		boolean isRegistered = false;
		Object[] listeners = this.listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i + 1].equals(listener)) {
				isRegistered = true;
			}
		}
		return isRegistered;
	}

	public void removeAucomSinkStatusListener(SinkStatusListener listener) {
		Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, "removing new listener " + listener);
		this.listenerList.remove(SinkStatusListener.class, listener);
	}

	// This method is used to fire TrainingStatusChangedEvents
	void fireAucomSinkStatusChangedEvent(AucomSinkStatusEvent evt) {
		Object[] listeners = this.listenerList.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == SinkStatusListener.class) {
				Logger.getLogger(this.getClass().getCanonicalName()).info("notifying (" + i + ")" + listeners[i + 1] + " about " + evt);
				((SinkStatusListener) listeners[i + 1]).sinkStatusChanged(evt);
			}
		}
	}

	public int getNumberListeners() {
		return listenerList.getListenerCount();
	}

}

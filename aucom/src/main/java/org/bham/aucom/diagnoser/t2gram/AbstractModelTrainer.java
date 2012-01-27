package org.bham.aucom.diagnoser.t2gram;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.bham.aucom.diagnoser.ModelTrainer;
import org.bham.aucom.diagnoser.ModelTrainerListener;
import org.bham.aucom.diagnoser.StatusChangedEvent;
import org.bham.aucom.diagnoser.TrainerStatus;

public abstract class AbstractModelTrainer implements ModelTrainer {
	TrainerStatus currentStatus = TrainerStatus.READY;
	TrainerStatus previousStatus = TrainerStatus.READY;

	public void setStatus(TrainerStatus newStatus){
		Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "setting new state to " + newStatus + " previously " +previousStatus);
		previousStatus = currentStatus;
		currentStatus = newStatus;
		fireStatusChangedEvent(new StatusChangedEvent(this, previousStatus, currentStatus));
	}
	
	/*
	 * event handling ---->
	 */

	protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

	// This method is used to fire TrainingStatusChangedEvents
	void fireStatusChangedEvent(StatusChangedEvent evt) {
		Object[] listeners = this.listenerList.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == ModelTrainerListener.class) {
				((ModelTrainerListener) listeners[i + 1]).modelTrainerStatusChanged(evt);
			}
		}
	}
	public boolean isListenerRegistered(ModelTrainerListener listener) {
		boolean isRegistered = false;
		Object[] listeners = this.listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i+1].equals(listener)) {
				isRegistered = true;
			}
		}
		return isRegistered;
	}

	@Override
	public void addModelTrainerListener(ModelTrainerListener listener) {
		if(isListenerRegistered(listener)){
			return;
		}
		this.listenerList.add(ModelTrainerListener.class, listener);

	}

	@Override
	public void removeModelTrainerListener(ModelTrainerListener listener) {
		listenerList.remove(ModelTrainerListener.class, listener);
	}

	@Override
	public void removeAllListeners() {
		Object[] listeners = this.listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == ModelTrainerListener.class) {
				listenerList.remove(ModelTrainerListener.class, (ModelTrainerListener)listeners[i + 1]);
			}
		}
	}
}

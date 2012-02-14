package org.bham.aucom.diagnoser;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JLabel;
import javax.swing.JPanel;

import org.bham.aucom.Presentable;
import org.bham.aucom.util.Constants;

abstract public class AbstractDetector implements Detector, Presentable{
	public enum DetectorStatus{
		NOTREADY, 
		READY,
		RUNNING,
		STOPPED
	}
	DetectorStatus previousStatus = DetectorStatus.NOTREADY;
	private DetectorStatus currentStatus = DetectorStatus.NOTREADY;
	
	protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

	public void setStatus(DetectorStatus newStatus){
		if(newStatus.equals(getCurrentStatus())){
			Logger.getLogger(this.getClass().getCanonicalName()).info("trying to set previous status "  + newStatus);
			return;
		}
		previousStatus = getCurrentStatus();
		setCurrentStatus(newStatus);
		DetectorStatusChangedEvent event = new DetectorStatusChangedEvent(this, previousStatus, getCurrentStatus());
		Logger.getLogger(this.getClass().getCanonicalName()).info("setting status from " + previousStatus + " to "  + getCurrentStatus());
		fireDetectorStatusChangedEvent(event);
	}
	
	/*
	 * event handling ---->
	 */
	

	/**
	 * add a status listener to the detector. listener will be notified when the status of the detector changes
	 * see: @DetectorStatus for more information
	 * @param the listener to add
	 */
	@Override
	public void addStatusListener(DetectorStatusChangedListener listener) {
		if(isSinkStatusListenerRegistered(listener)){
			Logger.getLogger(this.getClass().getCanonicalName()).log(Level.WARNING, "listener " + listener + " already registered");
			return;
		}
		this.listenerList.add(DetectorStatusChangedListener.class, listener);
	}
	
	public boolean isSinkStatusListenerRegistered(DetectorStatusChangedListener listener) {
		boolean isRegistered = false;
		Object[] listeners = this.listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i+1].equals(listener)) {
				isRegistered = true;
			}
		}
		return isRegistered;
	}

	/**
	 * removes a specific listener from this object. no more notification will be sent to the removed listener  
	 */
	@Override
	public void removeStatusListener(DetectorStatusChangedListener listener) {
		this.listenerList.remove(DetectorStatusChangedListener.class, listener);
	}

	@Override
	public void removeAllStatusListeners() {
		Object[] listeners = this.listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == DetectorStatusChangedListener.class) {
				this.listenerList.remove(DetectorStatusChangedListener.class, (DetectorStatusChangedListener) listeners[i + 1]);
			}
		}
	}

	protected void fireDetectorEvent(DetectorEvent evt) {
		Object[] listeners = this.listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == DetectionListener.class) {
				((DetectionListener) listeners[i + 1]).handleDetectorEvent(evt);
			}
		}
	}
	
	protected void fireDetectorStatusChangedEvent(DetectorStatusChangedEvent evt) {
		Object[] listeners = this.listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == DetectorStatusChangedListener.class) {
				Logger.getLogger(this.getClass().getCanonicalName()).info("notifying "  + listeners[i + 1]);
				((DetectorStatusChangedListener) listeners[i + 1]).handleDetectorStatusChangedEvent(evt);
			}
		}
	}
	
	@Override
	public void addDetectionListener(DetectionListener listener) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void removeDetectionListener(DetectionListener listener) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void removeAllDetectionListeners() {
		// TODO Auto-generated method stub
		
	}
	
	/*
	 * event handling ---->
	 */
	
	/**
	 *	Returns a default detector panel. It allows basic interaction with the detector. 
	 */
	
	@Override
	public JPanel getPanel() {
		JPanel p = new JPanel();
		p.setName("DefaultDetectorPanel");
		p.add(new JLabel("default"));
		p .setPreferredSize(new Dimension(Constants.DEFAULTPRESENTABEWIDTH, 40));
		return p;
	}

	protected void setCurrentStatus(DetectorStatus currentStatus) {
		this.currentStatus = currentStatus;
	}

	public DetectorStatus getCurrentStatus() {
		return currentStatus;
	}
}

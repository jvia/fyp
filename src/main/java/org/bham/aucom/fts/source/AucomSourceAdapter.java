package org.bham.aucom.fts.source;

import net.sf.xcf.fts.nodes.source.SourceAdapter;

import java.io.Serializable;
import java.util.logging.Logger;

public abstract class AucomSourceAdapter<T> extends SourceAdapter<T> implements Serializable {
    private SourceStatus state = SourceStatus.DISCONNECTED;
    Object waitObject = new Object();
    protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();
    private SourceStatus previousState = SourceStatus.DISCONNECTED;
    private boolean sendLastElement = false;

    public AucomSourceAdapter(String name) {
        super(name);
    }

    /*
      * event handling
      */
    public void addSourceStatusListener(SourceStatusListener listener) {
        if (isListenerRegistered(listener)) {
            return;
        }
        this.listenerList.add(SourceStatusListener.class, listener);
    }

    public void removeSourceStatusListener(SourceStatusListener listener) {
        this.listenerList.remove(SourceStatusListener.class, listener);
    }

    public int getNumberListeners() {
        return listenerList.getListenerCount();
    }

    public boolean isListenerRegistered(SourceStatusListener listener) {
        boolean isRegistered = false;
        Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i + 1].equals(listener)) {
                isRegistered = true;
            }
        }
        return isRegistered;
    }

    // This method is used to fire TrainingStatusChangedEvents
    void fireSourceStatusChangedEvent(SourceStatusEvent evt) {
        Logger.getLogger(this.getClass().getCanonicalName()).info("notifying " + this.listenerList.getListenerList().length);
        Object[] listeners = this.listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == SourceStatusListener.class) {
                ((SourceStatusListener) listeners[i + 1]).sourceStatusChanged(evt);
            }
        }
    }

    protected void setState(SourceStatus state) {
        this.state = state;
    }

    public SourceStatus getStatus() {
        return state;
    }

    public void pause() {
        if (!this.state.equals(SourceStatus.PAUSED)) {// prevent override of
            // previous state
            this.previousState = this.state;
            this.state = SourceStatus.PAUSED;
        }
    }

    public void resume() throws IllegalStateChange {
        if (this.state.equals(SourceStatus.PAUSED)) {
            // only do something when paused
            System.out.println(this.getClass().getName() + " restoring from PAUSED to " + previousState);
            this.state = this.previousState;
            synchronized (waitObject) {
                this.waitObject.notifyAll();
            }
        } else {
            throw new IllegalStateChange("resume is not allowed in state: " + this.state);
        }
    }

    protected void setsendLastElement() {
        this.sendLastElement = true;
    }

    protected void resetSendLastElement() {
        this.sendLastElement = false;
    }

    protected boolean isSentLastElement() {
        return this.sendLastElement;
    }

    abstract protected void iDisconnect() throws ActionFailedException;

    abstract protected void iConnect() throws ActionFailedException;

    public void connect() throws ActionFailedException {
        iConnect();
        setState(SourceStatus.CONNECTED);
    }


    /*
      * stops the source
      */
    public void disconnect() throws ActionFailedException {
        try {
            iDisconnect();
            this.state = SourceStatus.DISCONNECTED;
            fireSourceDisconnected();
        } catch (ActionFailedException exception) {
            exception.printStackTrace();
        }
    }

    protected abstract T iNextItem() throws Exception;

    @Override
    protected T nextItem() throws Exception {
        T nextItem = iNextItem();
        if (this.getStatus().equals(SourceStatus.PAUSED)) {
            System.out.println(this.getClass().getName() + "nextitem called but status is pause, waiting");
//try {
            synchronized (this.waitObject) {
                this.waitObject.wait();
            }
//} catch (Exception exception) {
//exception.printStackTrace();
//}
        }
        if (this.getStatus().equals(SourceStatus.CONNECTED)) {
            this.setState(SourceStatus.RUNNING);
            fireFirstElementTaken();
        }
        if (isSentLastElement()) {
            this.setState(SourceStatus.CONNECTED);
            fireLastElementTaken();
            resetSendLastElement();
        }
        return nextItem;
    }

    /**
     * Should be called to indicate that the element in a time-series marked as
     * last is taken. Fires an @SequenceQueueSourceStatusEvent event with a
     * value which indicates that the last element of the time-series is sent to
     * subsequent nodes.
     */
    private void fireLastElementTaken() {
        Logger.getLogger(this.getClass().getCanonicalName()).info(" taking last element, firing SequenceQueueSourceStatusEvent");
        fireSourceStatusChangedEvent(new SourceStatusEvent(this, SourceStatus.LAST_ELEMENT_SENT));
    }

    private void fireSourceDisconnected() {
        Logger.getLogger(this.getClass().getCanonicalName()).info(" source " + name + " stopped");
        fireSourceStatusChangedEvent(new SourceStatusEvent(this, SourceStatus.DISCONNECTED));
    }

    /**
     * Should be called to indicate that the element in a time-series marked as
     * first is taken. Fires an @SequenceQueueSourceStatusEvent event with a
     * value which indicates that the first element of the time-series is sent
     * to subsequent nodes .
     */
    private void fireFirstElementTaken() {
        Logger.getLogger(this.getClass().getCanonicalName()).info(" taking first element, firing SequenceQueueSourceStatusEvent");
        fireSourceStatusChangedEvent(new SourceStatusEvent(this, SourceStatus.FIRST_ELEMENT_SENT));
    }
}

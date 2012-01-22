package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.sink.NodeStatus;

import java.util.logging.Logger;

public abstract class AbstractAucomTransformNode<TIn extends AbstractData, TOut extends AbstractData> extends AbstractTransformNode<TIn, TOut> {
    protected TimeSeries<TOut> ts = null;
    long lastProcessingTime = 0l;
    long startTimestamp = 0l;
    long stopTimestamp = 0l;
    private boolean isShowProcessingTime = true;

    protected AbstractAucomTransformNode(String name) {
        super(name);
    }

    @Override
    protected TOut transform(TIn input) throws Exception {
        if (input == null) {
            return null;
        }
        this.startTimestamp = System.currentTimeMillis();
        TOut out = null;
        try {
            Logger.getLogger(this.getClass().getCanonicalName()).info("input is " + input);
            out = iTransform(input);
            Logger.getLogger(this.getClass().getCanonicalName()).info(" output is " + out);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (out == null) {
            if (input.isMarkedAsFirstElement()) {
                fireStatusChangedEvent(new TransformNodeEvent(this, NodeStatus.RECEIVED_FIRST_ELEMENT));
                System.out.println(this.toString() + " fires RECEIVED_FIRST_ELEMENT");
            } else if (input.isMarkedAsLastElement()) {
                fireStatusChangedEvent(new TransformNodeEvent(this, NodeStatus.RECEIVED_LAST_ELEMENT));
                System.out.println(this.toString() + " fires RECEIVED_LAST_ELEMENT");
            }
            return null;
        }
        copyMarkings(input, out);
        if (this.getTimeSeries() != null) {
            this.getTimeSeries().add(out);
        }
        this.stopTimestamp = System.currentTimeMillis();
        long newPoint = (this.stopTimestamp - this.startTimestamp);
        if (this.isShowProcessingTime && (Math.abs(this.lastProcessingTime - newPoint)) > 10) {
            Logger.getLogger(this.getClass().getCanonicalName()).info(this.name + " current timestamp " + newPoint + " increase: " + (this.lastProcessingTime - newPoint));
        }
        this.lastProcessingTime = newPoint;
        if (this.getClass().equals(Classify.class)) {
            if (input.getAttributes().size() != out.getAttributes().size()) {
                System.out.println("in " + input.getAttributes() + " out " + out.getAttributes());
            }
        }
        return out;
    }

    /*
     * @param input
     * @param out
     */
    private void copyMarkings(TIn input, TOut out) {
        if (input.isMarkedAsFirstElement()) {
            out.isMarkedAsFirstElement();
            Logger.getLogger(this.getClass().getCanonicalName()).info(this.getClass().getName() + " copying mark of first element");
        }
        if (input.isMarkedAsLastElement()) {
            out.markAsLastElement();
            Logger.getLogger(this.getClass().getCanonicalName()).info(this.getClass().getName() + " copying mark of last element");
        }
    }

    protected abstract TOut iTransform(TIn input) throws Exception;

    @SuppressWarnings("unchecked")
    public void setTimeSeries(TimeSeries<?> ts) {
        this.ts = (TimeSeries<TOut>) ts;
    }

    public TimeSeries<TOut> getTimeSeries() {
        return this.ts;
    }

    /* event handling ----> */

    protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

    void fireStatusChangedEvent(TransformNodeEvent evt) {
        Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == TransformNodeEventListener.class) {
                ((TransformNodeEventListener) listeners[i + 1]).handleTransformNodeEvent(evt);
            }
        }
    }

    public int getNumberListeners() {
        return listenerList.getListenerCount();
    }

    public boolean isListenerRegistered(TransformNodeEventListener listener) {
        boolean isRegistered = false;
        Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i + 1].equals(listener)) {
                isRegistered = true;
            }
        }
        return isRegistered;
    }

    public void addTransformNodeListener(TransformNodeEventListener listener) {
        if (isListenerRegistered(listener)) {
            return;
        }
        this.listenerList.add(TransformNodeEventListener.class, listener);

    }

    public void removeTransformNodeListener(TransformNodeEventListener listener) {
        listenerList.remove(TransformNodeEventListener.class, listener);
    }

    public void removeAllListeners() {
        Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == TransformNodeEventListener.class) {
                listenerList.remove(TransformNodeEventListener.class, (TransformNodeEventListener) listeners[i + 1]);
            }
        }
    }
    /* <---- event handling */
}
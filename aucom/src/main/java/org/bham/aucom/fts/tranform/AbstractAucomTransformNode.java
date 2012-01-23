package org.bham.aucom.fts.tranform;

import net.sf.xcf.fts.nodes.transform.AbstractTransformNode;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.sink.NodeStatus;

import java.util.logging.Logger;

/**
 * This class extends the FTS transform node with aucom specific facilities.
 * <p/>
 * The aim of this class is to sit in the ring processing structure, taking
 * input and transforming it into output by the means specified by concrete
 * subclasses.
 *
 * @param <TIn>
 * @param <TOut>
 */
public abstract class AbstractAucomTransformNode<TIn extends AbstractData, TOut extends AbstractData> extends AbstractTransformNode<TIn, TOut> {
    private final TimeSeries<TOut> ts = null;
    private long lastProcessingTime = 0l;

    protected AbstractAucomTransformNode(String name) {
        super(name);
    }

    @Override
    protected TOut transform(TIn input) throws Exception {
        if (input == null) return null;

        long startTimestamp = System.currentTimeMillis();
        long stopTimestamp = System.currentTimeMillis();
        long newPoint = (stopTimestamp - startTimestamp);
        TOut out = null;

        try {
            Logger.getLogger(getClass().getCanonicalName()).info("input is " + input);
            out = iTransform(input);
            Logger.getLogger(getClass().getCanonicalName()).info(" output is " + out);
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        if (out == null) {
            if (input.isMarkedAsFirstElement()) {
                fireStatusChangedEvent(new TransformNodeEvent(this, NodeStatus.RECEIVED_FIRST_ELEMENT));
                System.out.println(toString() + " fires RECEIVED_FIRST_ELEMENT");
            } else if (input.isMarkedAsLastElement()) {
                fireStatusChangedEvent(new TransformNodeEvent(this, NodeStatus.RECEIVED_LAST_ELEMENT));
                System.out.println(toString() + " fires RECEIVED_LAST_ELEMENT");
            }
            return null;
        }
        copyMarkings(input, out);
        if (getTimeSeries() != null) {
            getTimeSeries().add(out);
        }


        if ((Math.abs(lastProcessingTime - newPoint)) > 10) {
            Logger.getLogger(getClass().getCanonicalName()).info(name + " current timestamp " + newPoint + " increase: " + (lastProcessingTime - newPoint));
        }
        lastProcessingTime = newPoint;
        if (getClass().equals(Classify.class)) {
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
            Logger.getLogger(getClass().getCanonicalName()).info(getClass().getName() + " copying mark of first element");
        }
        if (input.isMarkedAsLastElement()) {
            out.markAsLastElement();
            Logger.getLogger(getClass().getCanonicalName()).info(getClass().getName() + " copying mark of last element");
        }
    }

    protected abstract TOut iTransform(TIn input) throws Exception;


    public TimeSeries<TOut> getTimeSeries() {
        return ts;
    }

    /* event handling ----> */

    private final javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

    void fireStatusChangedEvent(TransformNodeEvent evt) {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == TransformNodeEventListener.class) {
                ((TransformNodeEventListener) listeners[i + 1]).handleTransformNodeEvent(evt);
            }
        }
    }

    boolean isListenerRegistered(TransformNodeEventListener listener) {
        boolean isRegistered = false;
        Object[] listeners = listenerList.getListenerList();
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
        listenerList.add(TransformNodeEventListener.class, listener);

    }
}
package org.bham.aucom.fts.graph;

import net.sf.xcf.fts.Node;
import net.sf.xcf.fts.engine.EngineThread;
import net.sf.xcf.fts.engine.Graph;
import net.sf.xcf.fts.export.DotGraphExport;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.fts.sink.AucomSinkAdapter;
import org.bham.aucom.fts.sink.AucomSinkStatusEvent;
import org.bham.aucom.fts.sink.NodeStatus;
import org.bham.aucom.fts.sink.SinkStatusListener;
import org.bham.aucom.fts.source.*;
import org.bham.aucom.fts.tranform.AbstractAucomTranformNode;
import org.bham.aucom.fts.tranform.TransformNodeEvent;
import org.bham.aucom.fts.tranform.TransformNodeEventListener;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.main.GraphStatusListener;

import java.io.*;
import java.lang.Thread.UncaughtExceptionHandler;
import java.util.Collection;
import java.util.Iterator;
import java.util.logging.Level;
import java.util.logging.Logger;

public abstract class AbstractAucomGraph implements SourceStatusListener, SinkStatusListener, Serializable, TransformNodeEventListener {
    private GraphStatus currentStatus = GraphStatus.NOTREADY;
    private GraphStatus previousStatus = currentStatus;

    private static final long serialVersionUID = 1L;
    protected transient javax.swing.event.EventListenerList listenerList = null;
    protected transient Graph graph;
    private final String graphName;
    protected transient EngineThread engineThread;

    /**
     * Defines possible states of an AucomGraph
     *
     * @author rgolombe
     */
    public enum GraphStatus {
        NOTREADY, READY, RUNNING, STOPPED, PAUSED,
    }

    /**
     * Default constructor. Should be called by all subclasses
     *
     * @param inGraphName
     */
    public AbstractAucomGraph(String inGraphName) {
        this.graphName = inGraphName;
        this.graph = new Graph();
        currentStatus = GraphStatus.NOTREADY;
        previousStatus = GraphStatus.NOTREADY;
        this.listenerList = new javax.swing.event.EventListenerList();
        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.CONFIG, "instantiating " + this.graphName);
    }

    /**
     * Registers a listener for GraphStatusChangedEvents
     *
     * @param listener the listener to register
     */
    public void addGraphListener(GraphStatusListener listener) {
        this.listenerList.add(GraphStatusListener.class, listener);
    }

    /**
     * Unregisters the listener from listening to GraphStatusChangedEvents
     *
     * @param the listener to unregister
     */
    public void removeMyEventListener(GraphStatusListener listener) {
        this.listenerList.remove(GraphStatusListener.class, listener);
    }

    /**
     * Unregisters all GraphStatusListener from this object
     */
    public void removeAllListeners() {
        Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == GraphStatusListener.class) {
                this.listenerList.remove(GraphStatusListener.class, (GraphStatusListener) listeners[i + 1]);
            }
        }
    }

    /**
     * fts Graph construction function. It is expected that after calling this function the topology of the fts graph used
     * by this implementation of the AbstractAucomGraph is established.
     */
    protected abstract void initGraph();

    @SuppressWarnings("cast")
    protected void fireGraphStatusChangedEvent(GraphStateChangedEvent evt) {
        Logger.getLogger(this.getClass().getCanonicalName()).info(getGraphName() + " fires " + evt + "to " + listenerList.getListenerCount() + " listeners");
        Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == GraphStatusListener.class) {
                Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, getGraphName() + " nfires to " + (GraphStatusListener) listeners[i + 1]);
                ((GraphStatusListener) listeners[i + 1]).graphStatusChanged(evt);
            }
        }
    }

    /**
     * Save a dot representation of the fts graph used by this object. Does nothing in case the fts graph is not
     * constructed yet.
     */
    public void saveGraph() {
        if (graph == null) {
            return;
        }
        File fileToSave = new File(AucomIO.getInstance().getCurrentWorkingDirectory(), this.graphName + ".dot");
        DotGraphExport exp = new DotGraphExport();
        exp.addGraph(this.graphName, graph);
        try {
            FileWriter f = new FileWriter(fileToSave);
            f.write(exp.getDotString());
            f.flush();
            f.close();
            Logger.getLogger(this.getClass().getCanonicalName()).info("graph saved to " + fileToSave.getAbsolutePath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the name of graph
     *
     * @return name of this graph
     */
    public String getGraphName() {
        return this.graphName;
    }

    /**
     * Utility function. Offers a convenient mean to check whether the status of this graph object is {@link #GraphStatus
     * NOTREADY}
     *
     * @return
     */
    public boolean isNotReadyStatus() {
        return getStatus().equals(GraphStatus.NOTREADY);
    }

    /**
     * Utility function. Offers a convenient mean to check whether the status of this graph object is {@link #GraphStatus
     * RUNNING}
     *
     * @return
     */
    public boolean isRunningStatus() {
        return getStatus().equals(GraphStatus.RUNNING);
    }

    /**
     * Starts the graph. Does nothing in case the graph is already running. Otherwise checks whether preconditions are
     * satisfied and starts the fts graph
     *
     * @throws ActionFailedException When 1) the graph status is {@link GraphStatus STOPPED} 2) preconditions are not
     *                               satisfied i.e., {@link #preconditionsSatisfied()} returns false  3) an exception has
     *                               been caught while starting the fts graph.
     */
    public void start() throws ActionFailedException {
        if (currentStatus.equals(GraphStatus.STOPPED)) {
            throw new ActionFailedException(getGraphName() + "Status is STOPPED. Graph cannot be restarted after stopping it.");
        }
        if (isRunningStatus()) {
            return;
        }
        if (preconditionsSatisfied()) {
            if (isNotReadyStatus()) {
                setStatus(GraphStatus.READY);
            }
        } else {
            String reason = getReason();
            setStatus(GraphStatus.NOTREADY);
            throw new ActionFailedException("graph " + getGraphName() + " preconditions not satisfied, reason: " + reason);
        }
        if (engineThread != null) {
            return;
        }
        try {
            listenToNodes(graph.getSourceNodes().iterator());
            engineThread = new EngineThread(this.graph);
            Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, this.getClass().getName() + " starting new EngineThread for graph " + this.graphName);
            this.engineThread.setName(this.graphName + "-Thread");
            engineThread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    Logger.getLogger(this.getClass().getCanonicalName()).severe(t.getName() + " uncaughtException + " + e);
                }
            });
            this.engineThread.start();
            for (Node<?, ?> node : graph.getSourceNodes()) {
                AucomSourceAdapter<?> source = (AucomSourceAdapter<?>) node;
                source.connect();
            }
            setStatus(GraphStatus.RUNNING);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ActionFailedException(getGraphName() + " start failed, reason: " + e.getLocalizedMessage());
        }
    }

    protected abstract String getReason();

    /**
     * Indicates whether the preconditions for the immediate execution of the graph are met. Postcondition: If this
     * function returns True the status of the graph must not be {@link #GraphStatus NOTREADY}
     */
    public abstract boolean preconditionsSatisfied();

    /**
     * Connects to all fts nodes of the fts graph and listens to status events
     *
     * @param nodes
     */
    protected void listenToNodes(Iterator<Node<?, ?>> nodes) {
        while (nodes.hasNext()) {
            Node<?, ?> node = nodes.next();
            // recurrence
            if (graph.getEmergents(node).hasNext()) {
                listenToNodes(graph.getEmergents(node));
            }
            try {
                if (graph.isSource(node)) {
                    ((AucomSourceAdapter<?>) node).addSourceStatusListener(this);
                    Logger.getLogger(this.getClass().getCanonicalName()).info("listening to source " + node.toString());
                } else if (graph.isSink(node)) {
                    ((AucomSinkAdapter<?>) node).addSinkStatusListener(this);
                    Logger.getLogger(this.getClass().getCanonicalName()).info("listening to sink " + node.toString());
                } else {
                    ((AbstractAucomTranformNode<?, ?>) node).addTransformNodeListener(this);
                    Logger.getLogger(this.getClass().getCanonicalName()).info("listening to node " + node.toString());
                }
            } catch (ClassCastException exception) {
                Logger.getLogger(this.getClass().getCanonicalName()).info("ignoring node " + node.toString() + " it doesn't implement the AbstractAucomTransformNode interface");
            }
        }
    }

    /**
     * Pauses the graph execution by pausing the sources. The graph can be
     * always paused, except when its already paused. In this case we don't pause again as we don't want
     * to override the previous state.
     */
    public void pause() throws IllegalStateChange {
        if (!currentStatus.equals(GraphStatus.PAUSED)) {
            setPausedState();
            pauseAllSources();
            Logger.getLogger(this.getClass().getCanonicalName()).info(this.getClass().getName() + "paused, previous state was " + this.previousStatus);
        } else {
            Logger.getLogger(this.getClass().getCanonicalName()).info("graph is not running, no pause, state " + currentStatus);
        }
    }

    /**
     * Changes the graph state to {@link #GraphStatus PAUSED}
     */
    private void setPausedState() {
        copyCurrentToPreviousStatus();
        setStatus(GraphStatus.PAUSED);
    }

    /**
     * Copies the value of {@link #currentStatus} to {@link #previousStatus}
     */

    private void copyCurrentToPreviousStatus() {
        this.previousStatus = this.currentStatus;
    }

    /**
     * calls pause() an all sources of the fts graph. By this means the fts graph is completely paused.
     *
     * @throws IllegalStateChange
     */
    private void pauseAllSources() throws IllegalStateChange {
        if (this.graph != null) {
            Collection<Node<?, ?>> sources = this.graph.getSourceNodes();
            for (Node<?, ?> node : sources) {
                AucomSourceAdapter source = ((AucomSourceAdapter) node);
                source.pause();
            }
        }
    }

    /**
     * Resumes graph execution by resuming the execution of all sources of the fts graph if the graph was previously
     * paused. Otherwise the call to this function has no effect.
     */
    public void resume() {
        if (currentStatus.equals(GraphStatus.PAUSED)) {
            resumeAllSources();
            restoreCurrentFromPreviousStatus();
            Logger.getLogger(this.getClass().getCanonicalName()).info(this.getClass().getName() + " resumed, state restored to " + this.currentStatus);
        }
    }

    /**
     * Helper function. Copies the value of {@link #previousStatus} to  {@link #currentStatus}
     */
    private void restoreCurrentFromPreviousStatus() {
        this.currentStatus = this.previousStatus;
    }

    /**
     * Calls resume on all available sources of the fts graph.
     */
    private void resumeAllSources() {
        if (this.graph != null) {
            Collection<Node<?, ?>> sources = this.graph.getSourceNodes();
            for (Node<?, ?> node : sources) {
                try {
                    ((AucomSourceAdapter<?>) node).resume();
                } catch (IllegalStateChange exception) {
                    exception.printStackTrace();
                }
            }
        }
    }

    /**
     * Stop this Graph if {@link GraphStatus} is not STOPPED. Afterwards this graph object cannot be restarted again In
     * doing so the {@link #cleanUp()} function will be called which offers a customized way to neatly clean up any
     * resources used by this graph
     */
    public void stop() {
        Logger.getLogger(this.getClass().getCanonicalName()).info(this.getClass().getName() + " stopping graph: " + this.graphName);
        if (!currentStatus.equals(GraphStatus.STOPPED)) {
            cleanUp();
            Collection<Node<?, ?>> sources = this.graph.getSourceNodes();
            try {
                for (Node<?, ?> node : sources) {
                    ((AucomSourceAdapter<?>) node).removeSourceStatusListener(this);
                    ((AucomSourceAdapter<?>) node).disconnect();
                }
                setStatus(GraphStatus.STOPPED);
                removeAllListeners();
            } catch (ActionFailedException exception) {
                exception.printStackTrace();
            }
            this.engineThread.interrupt();
            this.engineThread = null;
            Logger.getLogger(this.getClass().getCanonicalName()).info("stopped");
        }
    }

    /**
     * Interface offering a mean to clean up resources used by this graph before stopping this graph. Called by {@link
     * #stop()}
     */
    protected abstract void cleanUp();

    /**
     * SourceEvent handling function. Changes {@link #currentStatus} to RUNNING if a source notifies about sending its
     * first element ({@link #SourceStatus FIRST_ELEMENT_SENT}) and if {@link #currentStatus} is not RUNNING
     */
    @Override
    public void sourceStatusChanged(SourceStatusEvent event) {
        if (event.getStatus().equals(SourceStatus.FIRST_ELEMENT_SENT)) {
            if (!isRunningStatus()) {
                setStatus(GraphStatus.RUNNING);
            }
            Logger.getLogger(this.getClass().getCanonicalName()).info("processing started");
        }
    }

    /**
     * TransformNodeEvent handling function. Changes {@link #currentStatus} to READY if a transformation node notifies
     * about receiving last element ({@link #NodeStatus RECEIVEDLASTELEMENT}) and if {@link #currentStatus} is RUNNING
     */
    @Override
    public void handleTransformNodeEvent(TransformNodeEvent event) {
        if (event.getStatus().equals(NodeStatus.RECEIVEDLASTELEMENT) && currentStatus.equals(GraphStatus.RUNNING)) {
            setStatus(GraphStatus.READY);
            Logger.getLogger(this.getClass().getCanonicalName()).info("processing finished in a transformation node");
        }
    }

    /**
     * SinkStatusEvent handling function. Changes {@link #currentStatus} to READY if a sink node notifies about receiving
     * last element ({@link #NodeStatus RECEIVEDLASTELEMENT}) and if {@link #currentStatus} is RUNNING
     */
    @Override
    public void sinkStatusChanged(AucomSinkStatusEvent event) {
        if (event.getStatus().equals(NodeStatus.RECEIVEDLASTELEMENT) && currentStatus.equals(GraphStatus.RUNNING)) {
            Logger.getLogger(this.getClass().getCanonicalName()).info("processing finished in a sink");
            setStatus(GraphStatus.READY);
        }
    }

    /**
     * Sets the state of the graph. It additionally save the previous state to {@link previousStatus} and Notifies all
     * {@link GraphStatusListener}
     *
     * @historyState and fires an @GraphStateChangeEvent
     */
    protected void setStatus(GraphStatus inNewStatus) {
        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, "setting new status " + inNewStatus);
        copyCurrentToPreviousStatus();
        this.currentStatus = inNewStatus;
        GraphStateChangedEvent gsce = new GraphStateChangedEvent(this, this.previousStatus, this.currentStatus);
        fireGraphStatusChangedEvent(gsce);
    }

    /**
     * returns the current graph status
     *
     * @return {@link #currentStatus} the current status of the graph
     */
    public GraphStatus getStatus() {
        return currentStatus;
    }

    /**
     * returns the number of currently registered {@link GraphStatusListener}
     *
     * @return number of currently registered listeners
     */
    public int getNumberGraphListeners() {
        return listenerList.getListenerCount();
    }
}

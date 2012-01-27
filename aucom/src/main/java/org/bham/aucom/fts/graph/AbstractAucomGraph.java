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
    private final Logger log = Logger.getLogger(getClass().getName());
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
     * Default constructor. Should be called by all subclasses.
     *
     * @param inGraphName the graph name
     */
    public AbstractAucomGraph(String inGraphName) {
        graphName = inGraphName;
        graph = new Graph();
        currentStatus = GraphStatus.NOTREADY;
        previousStatus = GraphStatus.NOTREADY;
        listenerList = new javax.swing.event.EventListenerList();
        log.config("Instantiating " + graphName);
    }

    /**
     * Registers a listener for GraphStatusChangedEvents
     *
     * @param listener the listener to register
     */
    public void addGraphListener(GraphStatusListener listener) {
        listenerList.add(GraphStatusListener.class, listener);
    }

    /**
     * Unregisters the listener from listening to GraphStatusChangedEvents
     *
     * @param listener to unregister
     */
    public void removeMyEventListener(GraphStatusListener listener) {
        listenerList.remove(GraphStatusListener.class, listener);
    }

    /**
     * Unregisters all GraphStatusListener from this object
     */
    public void removeAllListeners() {
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2)
            if (listeners[i] == GraphStatusListener.class)
                listenerList.remove(GraphStatusListener.class, (GraphStatusListener) listeners[i + 1]);


    }

    /**
     * fts Graph construction function. It is expected that after calling this
     * function the topology of the fts graph used by this implementation of
     * the
     * AbstractAucomGraph is established.
     */
    protected abstract void initGraph();

    @SuppressWarnings("cast")
    protected void fireGraphStatusChangedEvent(GraphStateChangedEvent evt) {
        log.info(getGraphName() + " fires " + evt + "to " + listenerList.getListenerCount() + " listeners");
        Object[] listeners = listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == GraphStatusListener.class) {
                log.info(getGraphName() + " nfires to " + listeners[i + 1]);
                ((GraphStatusListener) listeners[i + 1]).graphStatusChanged(evt);
            }
        }
    }

    /**
     * Save a dot representation of the fts graph used by this object. Does
     * nothing
     * in case the fts graph is not constructed yet.
     */
    public void saveGraph() {
        if (graph == null) {
            return;
        }
        File fileToSave = new File(AucomIO.getInstance().getCurrentWorkingDirectory(), graphName + ".dot");
        DotGraphExport exp = new DotGraphExport();
        exp.addGraph(graphName, graph);
        try {
            FileWriter f = new FileWriter(fileToSave);
            f.write(exp.getDotString());
            f.flush();
            f.close();
            log.info("graph saved to " + fileToSave.getAbsolutePath());
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
        return graphName;
    }

    /**
     * Utility function. Offers a convenient mean to check whether the status
     * of
     * this graph object is {@link GraphStatus#NOTREADY}
     *
     * @return true iff graph is not yet ready
     */
    public boolean isNotReadyStatus() {
        return getStatus().equals(GraphStatus.NOTREADY);
    }

    /**
     * Utility function. Offers a convenient mean to check whether the status
     * of this graph object is {@link GraphStatus#RUNNING}
     *
     * @return the current running status of the graph
     */
    public boolean isRunningStatus() {
        return getStatus().equals(GraphStatus.RUNNING);
    }

    /**
     * Starts the graph. Does nothing in case the graph is already running.
     * Otherwise checks whether preconditions are satisfied and starts the fts
     * graph.
     * <p/>
     * Exceptions occur when:
     * <ul>
     * <li>the graph status is {@link GraphStatus#STOPPED}</li>
     * <li> preconditions are not satisfied i.e., {@link
     * #preconditionsSatisfied()} returns false</li>
     * <li> an exception has been caught while starting the fts graph.</li>
     * </ul>
     *
     * @throws ActionFailedException an error occurred
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
            log.fine("Starting new EngineThread for graph: " + graphName);

            engineThread = new EngineThread(graph);
            engineThread.setName(graphName + "-Thread");
            engineThread.setUncaughtExceptionHandler(new UncaughtExceptionHandler() {

                @Override
                public void uncaughtException(Thread t, Throwable e) {
                    log.severe(t.getName() + " uncaughtException + " + e);
                }
            });
            engineThread.start();

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
     * Indicates whether the preconditions for the immediate execution of the
     * graph are met.
     * <p/>
     * <b>Postcondition</b>: If this function returns true, then the
     * status of the graph must not be {@link GraphStatus#NOTREADY}.
     *
     * @return true i.f.f. all graph preconditions are satistfied
     */
    public abstract boolean preconditionsSatisfied();

    /**
     * Connects to all fts nodes of the fts graph and listens to status events
     *
     * @param nodes the nodes to listen to
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
                    log.info("listening to source " + node.toString());
                } else if (graph.isSink(node)) {
                    ((AucomSinkAdapter<?>) node).addSinkStatusListener(this);
                    log.info("listening to sink " + node.toString());
                } else {
                    ((AbstractAucomTranformNode<?, ?>) node).addTransformNodeListener(this);
                    log.info("listening to node " + node.toString());
                }
            } catch (ClassCastException exception) {
                log.info("ignoring node " + node.toString() + " it doesn't implement the AbstractAucomTransformNode interface");
            }
        }
    }

    /**
     * Pauses the graph execution by pausing the sources. The graph can be
     * always paused, except when its already paused. In this case we don't
     * pause again as we don't want to override the previous state.
     *
     * @throws IllegalStateChange could not transition to pause
     */
    public void pause() throws IllegalStateChange {
        if (!currentStatus.equals(GraphStatus.PAUSED)) {
            setPausedState();
            pauseAllSources();
            log.fine("Paused, previous state was " + previousStatus);
        } else {
            log.fine("Graph is not running, no pause, state is" + currentStatus);
        }
    }

    /**
     * Changes the graph state to {@link GraphStatus#PAUSED}
     */
    private void setPausedState() {
        copyCurrentToPreviousStatus();
        setStatus(GraphStatus.PAUSED);
    }

    /**
     * Copies the value of {@link #currentStatus} to {@link #previousStatus}
     */

    private void copyCurrentToPreviousStatus() {
        previousStatus = currentStatus;
    }

    /**
     * calls pause() an all sources of the fts graph. By this means the fts
     * graph
     * is completely paused.
     *
     * @throws IllegalStateChange could not change the states
     */
    private void pauseAllSources() throws IllegalStateChange {
        if (graph != null) {
            Collection<Node<?, ?>> sources = graph.getSourceNodes();
            for (Node<?, ?> node : sources) {
                AucomSourceAdapter source = ((AucomSourceAdapter) node);
                source.pause();
            }
        }
    }

    /**
     * Resumes graph execution by resuming the execution of all sources of the
     * fts
     * graph if the graph was previously paused. Otherwise the call to this
     * function has no effect.
     */
    public void resume() {
        if (currentStatus.equals(GraphStatus.PAUSED)) {
            resumeAllSources();
            restoreCurrentFromPreviousStatus();
            log.fine("Resumed, state restored to " + currentStatus);
        }
    }

    /**
     * Helper function. Copies the value of {@link #previousStatus} to  {@link
     * #currentStatus}
     */
    private void restoreCurrentFromPreviousStatus() {
        currentStatus = previousStatus;
    }

    /**
     * Calls resume on all available sources of the fts graph.
     */
    private void resumeAllSources() {
        if (graph != null) {
            Collection<Node<?, ?>> sources = graph.getSourceNodes();
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
     * Stop this Graph if {@link GraphStatus} is not STOPPED. Afterwards this
     * graph
     * object cannot be restarted again In doing so the {@link #cleanUp()}
     * function
     * will be called which offers a customized way to neatly clean up any
     * resources used by this graph
     */
    public void stop() {
        log.info(getClass().getName() + " stopping graph: " + graphName);
        if (!currentStatus.equals(GraphStatus.STOPPED)) {
            cleanUp();
            Collection<Node<?, ?>> sources = graph.getSourceNodes();
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
            engineThread.interrupt();
            engineThread = null;
            log.info("stopped");
        }
    }

    /**
     * Interface offering a mean to clean up resources used by this graph
     * before
     * stopping this graph. Called by {@link #stop()}
     */
    protected abstract void cleanUp();

    /**
     * SourceEvent handling function. Changes {@link #currentStatus} to RUNNING
     * if
     * a source notifies about sending its first element ({@link
     * SourceStatus#FIRST_ELEMENT_SENT}) and if {@link #currentStatus} is not
     * RUNNING
     */
    @Override
    public void sourceStatusChanged(SourceStatusEvent event) {
        if (event.getStatus().equals(SourceStatus.FIRST_ELEMENT_SENT)) {
            if (!isRunningStatus()) {
                setStatus(GraphStatus.RUNNING);
            }
            log.info("processing started");
        }
    }

    /**
     * TransformNodeEvent handling function. Changes {@link #currentStatus} to
     * READY if a transformation node notifies about receiving last element
     * ({@link NodeStatus#RECEIVEDLASTELEMENT}) and if {@link #currentStatus}
     * is <tt>RUNNING</tt>
     */
    @Override
    public void handleTransformNodeEvent(TransformNodeEvent event) {
        if (event.getStatus().equals(NodeStatus.RECEIVEDLASTELEMENT) && currentStatus.equals(GraphStatus.RUNNING)) {
            setStatus(GraphStatus.READY);
            log.info("processing finished in a transformation node");
        }
    }

    /**
     * SinkStatusEvent handling function. Changes {@link #currentStatus} to
     * READY
     * if a sink node notifies about receiving last element ({@link
     * NodeStatus#RECEIVEDLASTELEMENT}) and if {@link #currentStatus} is
     * <tt>RUNNING</tt>.
     */
    @Override
    public void sinkStatusChanged(AucomSinkStatusEvent event) {
        if (event.getStatus().equals(NodeStatus.RECEIVEDLASTELEMENT) && currentStatus.equals(GraphStatus.RUNNING)) {
            log.info("processing finished in a sink");
            setStatus(GraphStatus.READY);
        }
    }

    /**
     * Sets the state of the graph. It additionally save the previous state to
     * {@link #previousStatus} and Notifies all {@link GraphStatusListener}
     * and fires an {@link GraphStateChangedEvent}
     *
     * @param newStatus the new graph status
     */
    protected void setStatus(GraphStatus newStatus) {
        log.log(Level.FINE, "setting new status " + newStatus);
        copyCurrentToPreviousStatus();
        currentStatus = newStatus;
        GraphStateChangedEvent gsce = new GraphStateChangedEvent(this, previousStatus, currentStatus);
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

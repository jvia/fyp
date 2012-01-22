package org.bham.aucom.diagnoser;

import org.bham.aucom.Presentable;
import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.SlidingWindow;
import org.bham.aucom.diagnoser.t2gram.detector.T2GramDetectorPanel;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.AnomalyClassifier;
import org.bham.aucom.fts.graph.AbstractAucomGraph;
import org.bham.aucom.fts.graph.DetectorGraph;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.source.IllegalStateChange;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.main.GraphStatusListener;
import org.bham.aucom.util.Constants;

import javax.swing.*;
import java.awt.*;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Detector implements Presentable{
    JPanel panel;
    private DetectorGraph detectorGraph;

    public Detector() {
        detectorGraph = new DetectorGraph();
        detectorGraph.saveGraph();
        detectorGraph.addGraphListener(new GraphStatusListener() {

            
            public void graphStatusChanged(GraphStateChangedEvent evt) {
                if (evt.getPreviousState().equals(AbstractAucomGraph.GraphStatus.RUNNING) && evt.getNewState().equals(AbstractAucomGraph.GraphStatus.READY)) {
                    setStatus(DetectorStatus.READY);
                }
                if (evt.getPreviousState().equals(AbstractAucomGraph.GraphStatus.READY) && evt.getNewState().equals(AbstractAucomGraph.GraphStatus.RUNNING)) {
                    setStatus(DetectorStatus.RUNNING);
                }
                if (evt.getNewState().equals(AbstractAucomGraph.GraphStatus.STOPPED)) {
                    setStatus(DetectorStatus.STOPPED);
                }
            }
        });
    }

    public JPanel getPanel() {
        if (panel == null) {
            panel = new T2GramDetectorPanel(this);
            panel.setName("Detector");
            panel.setPreferredSize(new Dimension(Constants.DEFAULT_PRESENTABLE_WIDTH, 160));
        }
        return panel;
    }

    
    public void pause() throws IllegalStateChange {
        detectorGraph.pause();
    }

    
    public void stop() {
        detectorGraph.stop();
    }

    public void setModel(Model m) throws ClassCastException {
        detectorGraph.setModel((Model) m);
    }

    public void setClassificator(AnomalyClassifier classifierToSet) {

        detectorGraph.setClassifier(classifierToSet);
    }

    public AnomalyClassifier getClassificator() {
        return detectorGraph.getClassificator();
    }

    public void setSlidingWindow(SlidingWindow slidingWindow) {
        detectorGraph.setSlidingWindow(slidingWindow);
    }

    public Model getModel() {
        return detectorGraph.getModel();
    }

    public SlidingWindow getSlidingWindow() {
        if (detectorGraph != null) {
            return detectorGraph.getSlidingWindow();
        }
        return null;
    }

    
    public void resume() {
        detectorGraph.resume();
    }

    
    public TimeSeries<Classification> getOutput() {
        if (detectorGraph != null) {
            return detectorGraph.getClassificationTimeSeries();
        }
        return null;
    }

    private void t2g(String msg) {
        Logger.getLogger(this.getClass().getCanonicalName()).info(msg);
    }

    
    public void start(TimeSeries<Observation> inTimeSeries) throws ActionFailedException {
        if (inTimeSeries.size() == 0) {
            Logger.getLogger(this.getClass().getCanonicalName()).log(Level.WARNING, "input is empty");
        }
        t2g("starting with new sequence of size " + inTimeSeries.size());
        detectorGraph.reset();
        detectorGraph.setInput(inTimeSeries);
        detectorGraph.start();
    }

    public enum DetectorStatus {
        NOT_READY,
        READY,
        RUNNING,
        STOPPED
    }

    DetectorStatus previousStatus = DetectorStatus.NOT_READY;
    private DetectorStatus currentStatus = DetectorStatus.NOT_READY;

    protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

    public void setStatus(DetectorStatus newStatus) {
        if (newStatus.equals(getCurrentStatus())) {
            Logger.getLogger(this.getClass().getCanonicalName()).info("trying to set previous status " + newStatus);
            return;
        }

        previousStatus = getCurrentStatus();
        setCurrentStatus(newStatus);

        DetectorStatusChangedEvent event = new DetectorStatusChangedEvent(this,
                                                                          previousStatus,
                                                                          getCurrentStatus());
        Logger.getLogger(this.getClass().getCanonicalName()).info("setting status from " + previousStatus + " to " + getCurrentStatus());
        fireDetectorStatusChangedEvent(event);
    }

    /*
      * event handling ---->
      */


    /*
      * add a status listener to the detector. listener will be notified when the status of the detector changes
      * see: @DetectorStatus for more information
      * @param the listener to add
      */
    
    public void addStatusListener(DetectorStatusChangedListener listener) {
        if (isSinkStatusListenerRegistered(listener)) {
            Logger.getLogger(this.getClass().getCanonicalName()).log(Level.WARNING, "listener " + listener + " already registered");
            return;
        }
        this.listenerList.add(DetectorStatusChangedListener.class, listener);
    }

    public boolean isSinkStatusListenerRegistered(DetectorStatusChangedListener listener) {
        boolean isRegistered = false;
        Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i + 1].equals(listener)) {
                isRegistered = true;
            }
        }
        return isRegistered;
    }

    /**
     * removes a specific listener from this object. no more notification will be sent to the removed listener
     */
    
    public void removeStatusListener(DetectorStatusChangedListener listener) {
        this.listenerList.remove(DetectorStatusChangedListener.class, listener);
    }

    
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
                Logger.getLogger(this.getClass().getCanonicalName()).info("notifying " + listeners[i + 1]);
                ((DetectorStatusChangedListener) listeners[i + 1]).handleDetectorStatusChangedEvent(evt);
            }
        }
    }

    
    public void addDetectionListener(DetectionListener listener) {
        // TODO Auto-generated method stub

    }


    
    public void removeDetectionListener(DetectionListener listener) {
        // TODO Auto-generated method stub

    }

    
    public void removeAllDetectionListeners() {
        // TODO Auto-generated method stub

    }

    /*
      * event handling ---->
      */

    /*
    *	Returns a default detector panel. It allows basic interaction with the detector.
    */

//	
//	public JPanel getPanel() {
//		JPanel p = new JPanel();
//		p.setName("DefaultDetectorPanel");
//		p.add(new JLabel("default"));
//		p .setPreferredSize(new Dimension(Constants.DEFAULT_PRESENTABLE_WIDTH, 40));
//		return p;
//	}

    protected void setCurrentStatus(DetectorStatus currentStatus) {
        this.currentStatus = currentStatus;
    }

    public DetectorStatus getCurrentStatus() {
        return currentStatus;
    }
}

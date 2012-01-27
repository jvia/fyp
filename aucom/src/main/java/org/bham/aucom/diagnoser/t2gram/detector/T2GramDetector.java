/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */

/*
 * Monitor.java
 *
 * Created on Feb 18, 2011, 9:55:12 AM
 */

package org.bham.aucom.diagnoser.t2gram.detector;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.SlidingWindow;
import org.bham.aucom.diagnoser.AbstractDetector;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
import org.bham.aucom.fts.graph.AbstractAucomGraph.GraphStatus;
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

/**
 * @author rgolombe
 */
public class T2GramDetector extends AbstractDetector {
    JPanel panel;
    private DetectorGraph detectorGraph;

    public T2GramDetector() {
        detectorGraph = new DetectorGraph();
        detectorGraph.saveGraph();
        detectorGraph.addGraphListener(new GraphStatusListener() {

            @Override
            public void graphStatusChanged(GraphStateChangedEvent evt) {
                if (evt.getPreviousState().equals(GraphStatus.RUNNING) && evt.getNewState().equals(GraphStatus.READY)) {
                    setStatus(DetectorStatus.READY);
                }
                if (evt.getPreviousState().equals(GraphStatus.READY) && evt.getNewState().equals(GraphStatus.RUNNING)) {
                    setStatus(DetectorStatus.RUNNING);
                }
                if (evt.getNewState().equals(GraphStatus.STOPPED)) {
                    setStatus(DetectorStatus.STOPPED);
                }
            }
        });
    }

    @Override
    public JPanel getPanel() {
        if (panel == null) {
            panel = new T2GramDetectorPanel(this);
            panel.setName("Detector");
            panel.setPreferredSize(new Dimension(Constants.DEFAULTPRESENTABEWIDTH, 160));
        }
        return panel;
    }

    @Override
    public void pause() throws IllegalStateChange {
        detectorGraph.pause();
    }

    @Override
    public void stop() {
        detectorGraph.stop();
    }

    public void setModel(Model m) throws ClassCastException {
        detectorGraph.setModel((T2GramModelI) m);
    }

    public void setClassificator(AnomalyClassificator classificatorToSet) {

        detectorGraph.setClassificator(classificatorToSet);
    }

    public AnomalyClassificator getClassificator() {
        return detectorGraph.getClassificator();
    }

    public void setSlidingWindow(SlidingWindow slidingWindow) {
        detectorGraph.setSlidingWindow(slidingWindow);
    }

    public T2GramModelI getModel() {
        return detectorGraph.getModel();
    }

    public SlidingWindow getSlidingWindow() {
        if (detectorGraph != null) {
            return detectorGraph.getSlidingWindow();
        }
        return null;
    }

    @Override
    public void resume() {
        detectorGraph.resume();
    }

    @Override
    public TimeSeries<Classification> getOutput() {
        if (detectorGraph != null) {
            return detectorGraph.getClassificationTimeSeries();
        }
        return null;
    }

    private void t2g(String msg) {
        Logger.getLogger(this.getClass().getCanonicalName()).info(msg);
    }

    @Override
    public void start(TimeSeries<Observation> inTimeSeries) throws ActionFailedException {
        if (inTimeSeries.size() == 0) {
            Logger.getLogger(this.getClass().getCanonicalName()).log(Level.WARNING, "input is empty");
        }
        t2g("starting with new sequence of size " + inTimeSeries.size());
        detectorGraph.reset();
        detectorGraph.setInput(inTimeSeries);
        detectorGraph.start();
    }
}

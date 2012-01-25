package org.bham.aucom.diagnoser;

import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.Presentable;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.graph.AbstractAucomGraph;
import org.bham.aucom.fts.graph.T2GramTrainerGraph;
import org.bham.aucom.gui.TrainerPanel;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.main.GraphStatusListener;
import org.bham.aucom.util.Constants;
import org.bham.aucom.util.HashMatrix;
import org.bham.aucom.util.Tuple;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;


public class ModelTrainer implements GraphStatusListener, Presentable {
    private LinkedHashMap<String, DataType> lastTrainOccurrences;

    private TrainerStatus currentStatus = TrainerStatus.READY;
    private TrainerStatus previousStatus = TrainerStatus.READY;

    private JPanel panel;
    private Model model;

    private final T2GramTrainerGraph graph;

    public ModelTrainer(Model inModel) {
        model = inModel;
        graph = new T2GramTrainerGraph();
        graph.addGraphListener(this);
        setTrainingData(new HashMatrix<Integer, Integer, ArrayList<Double>>());
        setLastTrainOccurrences(new LinkedHashMap<String, DataType>());
        currentStatus = TrainerStatus.READY;
        previousStatus = TrainerStatus.READY;
    }

    public ModelTrainer() {
        graph = new T2GramTrainerGraph();
        graph.addGraphListener(this);
        //setTrainingData(new HashMatrix<Integer, Integer, ArrayList<Double>>());
        setLastTrainOccurrences(new LinkedHashMap<String, DataType>());
        currentStatus = TrainerStatus.READY;
        previousStatus = TrainerStatus.READY;
    }


    public void start(TimeSeries<Observation> inTrainingData) throws Exception {
        if (inTrainingData == null) {
            Logger.getLogger(this.getClass().getCanonicalName()).info("cannot train with empty input");
            return;
        }
        Logger.getLogger(this.getClass().getCanonicalName()).info("start called with " + inTrainingData.toString());
        if (currentStatus.equals(TrainerStatus.READY)) {
            Logger.getLogger(this.getClass().getCanonicalName()).info("trainer ready starting training " + graph.getOutput().size());
            graph.setInput(inTrainingData);
            graph.start();
        } else {
            throw new ActionNotPermittedException("starting training not allowed in " + currentStatus + " state");
        }

    }

    private void trainModel(TimeSeries<TemporalDurationFeature> output) {
        try {
            Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "staring training");
            HashMatrix<Integer, Integer, ArrayList<Double>> values = computeTrainingset(output);
            Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "iterating through trainingset with " + values.size() + " elements");
            for (Tuple<Integer, Integer> t : values.keySet()) {
                double[] durations = getDurationsAsDoubleArray(values, t);
                updateModel(t.getFirst(), t.getSecond(), durations);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void updateModel(Integer firstElement, Integer secondElement, double[] durations) {
        createDistributionInModelIfMissing(model, firstElement, secondElement);
        model.getDistributionFor(firstElement, secondElement).update(durations);
    }

    /*
    * @param t
    */
    private void createDistributionInModelIfMissing(Model inModel, Integer firstElement, Integer secondElement) {
        if (!inModel.hasDistributionFor(firstElement, secondElement)) {
            inModel.addDistribution(firstElement, secondElement, inModel.getDistributionFactory().create());
        }
    }

    /*
    * @param values
    * @param t
    * @return
    */
    private double[] getDurationsAsDoubleArray(HashMatrix<Integer, Integer, ArrayList<Double>> values, Tuple<Integer, Integer> t) {
        double[] d = new double[values.get(t.getFirst(), t.getSecond()).size()];
        for (int j = 0; j < values.get(t.getFirst(), t.getSecond()).size(); j++) {
            d[j] = values.get(t.getFirst(), t.getSecond()).get(j);
        }
        return d;
    }

    /*
    * @param input
    * @param values
    */
    private HashMatrix<Integer, Integer, ArrayList<Double>> computeTrainingset(TimeSeries<TemporalDurationFeature> input) {
        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "computing training set. input " + input.size());
        HashMatrix<Integer, Integer, ArrayList<Double>> out = new HashMatrix<Integer, Integer, ArrayList<Double>>();
        try {
            for (int i = 0; i < input.size(); i++) {
                TemporalDurationFeature f = input.get(i);
                for (DataType precedessor : f.getPredecessors()) {
                    Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, "output size " + input.size() + " predecessors " + f.getPredecessors().size());
                    Integer predecessorEventType = precedessor.getEventType();
                    Integer currentEventType = f.getEventType();
                    if (!out.containsKey(predecessorEventType, currentEventType)) {
                        out.put(predecessorEventType, currentEventType, new ArrayList<Double>());
                    }
                    out.get(predecessorEventType, currentEventType).add((double) f.getDurationFor(precedessor));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return out;
    }

    private void setLastTrainOccurrences(LinkedHashMap<String, DataType> lastTrainOccurrences) {
        this.lastTrainOccurrences = lastTrainOccurrences;
    }

    private void setTrainingData(HashMatrix<Integer, Integer, ArrayList<Double>> trainingData) {
    }


    public void reset() {
        graph.getOutput().clear();
        this.lastTrainOccurrences.clear();
        Logger.getLogger(this.getClass().getCanonicalName()).info("SimpleModelTrainer reseted");
    }


    public JPanel getPanel() {
        if (panel == null) {
            panel = new TrainerPanel(this);
            panel.setName("Trainer");
            panel.setPreferredSize(new Dimension(Constants.DEFAULT_PRESENTABLE_WIDTH, Constants.DEFAULT_PRESENTABLE_HEIGHT));
        }
        return panel;
    }


    public Model getModel() {
        return model;
    }


    public void setModel(Model inModel) throws ClassCastException {
        model = inModel;
    }


    public void graphStatusChanged(GraphStateChangedEvent evt) {
        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "receives event " + evt);
        switch (evt.getNewState()) {
            case RUNNING: {
                Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "new state is running");
                setStatus(TrainerStatus.RUNNING);
                break;
            }
            case READY: {
                Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "new state is ready");
                if (evt.getPreviousState().equals(AbstractAucomGraph.GraphStatus.RUNNING)) {
                    // training is finished
                    Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "previous was running");
                    TimeSeries<TemporalDurationFeature> output = graph.getOutput();
                    Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "got output from graph");
                    trainModel(output);
                    Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "trained ");
                    setStatus(TrainerStatus.READY);
                }
                break;
            }
        }

    }

    /*
    * returns the size of the training input time series
    */
    public int getInputSize() {
        if (graph.getInput() != null)
            return graph.getInput().size();
        return 0;
    }


    public void stop() throws Exception {
    }

    void setStatus(TrainerStatus newStatus) {
        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "setting new state to " + newStatus + " previously " + previousStatus);
        previousStatus = currentStatus;
        currentStatus = newStatus;
        fireStatusChangedEvent(new StatusChangedEvent(this, previousStatus, currentStatus));
    }

    /*
    * event handling ---->
    */

    private final javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

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

    boolean isListenerRegistered(ModelTrainerListener listener) {
        boolean isRegistered = false;
        Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i + 1].equals(listener)) {
                isRegistered = true;
            }
        }
        return isRegistered;
    }


    public void addModelTrainerListener(ModelTrainerListener listener) {
        if (isListenerRegistered(listener)) {
            return;
        }
        this.listenerList.add(ModelTrainerListener.class, listener);

    }

    public void removeAllListeners() {
        Object[] listeners = this.listenerList.getListenerList();
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == ModelTrainerListener.class) {
                listenerList.remove(ModelTrainerListener.class, (ModelTrainerListener) listeners[i + 1]);
            }
        }
    }
}

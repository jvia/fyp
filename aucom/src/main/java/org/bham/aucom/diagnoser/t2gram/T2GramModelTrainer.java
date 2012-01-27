package org.bham.aucom.diagnoser.t2gram;

import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.TrainerStatus;
import org.bham.aucom.fts.graph.AbstractAucomGraph.GraphStatus;
import org.bham.aucom.fts.graph.T2GramTrainerGraph;
import org.bham.aucom.gui.TrainerPanel;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.main.GraphStatusListener;
import org.bham.aucom.util.Constants;
import org.bham.aucom.util.HashMatrix;
import org.bham.aucom.util.Tupel;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

public class T2GramModelTrainer extends AbstractModelTrainer implements GraphStatusListener {
    JPanel panel;
    T2GramModelI model;
    T2GramTrainerGraph graph;
    private HashMatrix<Integer, Integer, ArrayList<Double>> trainingData;
    private LinkedHashMap<String, DataType> lastTrainOccurances;

    public T2GramModelTrainer(T2GramModelI inModel) {
        model = inModel;
        graph = new T2GramTrainerGraph();
        graph.addGraphListener(this);
        setTrainingData(new HashMatrix<Integer, Integer, ArrayList<Double>>());
        setLastTrainOccurances(new LinkedHashMap<String, DataType>());
        currentStatus = TrainerStatus.READY;
        previousStatus = TrainerStatus.READY;
    }

    public T2GramModelTrainer() {
        graph = new T2GramTrainerGraph();
        graph.addGraphListener(this);
        setTrainingData(new HashMatrix<Integer, Integer, ArrayList<Double>>());
        setLastTrainOccurances(new LinkedHashMap<String, DataType>());
        currentStatus = TrainerStatus.READY;
        previousStatus = TrainerStatus.READY;
    }

    public double[] getValuesFromTrainingData(Integer firstKey, Integer secondKey) {
        double[] values = new double[this.trainingData.get(firstKey, secondKey).size()];
        for (int i = 0; i < values.length; i++) {
            values[i] = this.trainingData.get(firstKey, secondKey).get(i);
        }
        return values;
    }

    @Override
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
            for (Tupel<Integer, Integer> t : values.keySet()) {
                double[] durations = getDurationsAsDoubleArray(values, t);
                updateModel(model, t.getFirstElement(), t.getSecondElement(), durations);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void updateModel(T2GramModelI model2, Integer firstElement, Integer secondElement, double[] durations) {
        createDistributionInModelIfMissing(model, firstElement, secondElement);
        model.getDistributionFor(firstElement, secondElement).update(durations);
    }

    /**
     * @param t
     */
    private void createDistributionInModelIfMissing(T2GramModelI inModel, Integer firstElement, Integer secondElement) {
        if (!inModel.hasDistributionFor(firstElement, secondElement)) {
            inModel.addDistribution(firstElement, secondElement, inModel.getDistributionFactory().create());
        }
    }

    private double[] getDurationsAsDoubleArray(HashMatrix<Integer, Integer, ArrayList<Double>> values, Tupel<Integer, Integer> t) {
        double[] d = new double[values.get(t.getFirstElement(), t.getSecondElement()).size()];
        for (int j = 0; j < values.get(t.getFirstElement(), t.getSecondElement()).size(); j++) {
            d[j] = values.get(t.getFirstElement(), t.getSecondElement()).get(j);
        }
        return d;
    }

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

    private void setLastTrainOccurances(LinkedHashMap<String, DataType> lastTrainOccurances) {
        this.lastTrainOccurances = lastTrainOccurances;
    }

    private void setTrainingData(HashMatrix<Integer, Integer, ArrayList<Double>> trainingData) {
        this.trainingData = trainingData;
    }

    @Override
    public void reset() {
        graph.getOutput().clear();
        this.lastTrainOccurances.clear();
        this.trainingData = new HashMatrix<Integer, Integer, ArrayList<Double>>();
        Logger.getLogger(this.getClass().getCanonicalName()).info("SimpleModelTrainer reseted");
    }

    @Override
    public JPanel getPanel() {
        if (panel == null) {
            panel = new TrainerPanel(this);
            panel.setName("Trainer");
            panel.setPreferredSize(new Dimension(Constants.DEFAULTPRESENTABEWIDTH, Constants.DEFAULTPRESENTABELHEIGHT));
        }
        return panel;
    }

    @Override
    public Model getModel() {
        return model;
    }

    @Override
    public void setModel(Model inModel) throws ClassCastException {
        model = (T2GramModelI) inModel;
    }

    @Override
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
                if (evt.getPreviousState().equals(GraphStatus.RUNNING)) {
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
      * returns the size of the training input timeseries
      */
    public int getInputSize() {
        if (graph.getInput() != null) {
            return graph.getInput().size();
        }
        return 0;
    }

    @Override
    public void stop() throws Exception {
        // TODO code this here
    }

}

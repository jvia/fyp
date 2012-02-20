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
import org.bham.aucom.util.Tuple;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.logging.Level;
import java.util.logging.Logger;

import static java.lang.String.format;

public class T2GramModelTrainer extends AbstractModelTrainer implements GraphStatusListener {
    private JPanel panel;
    private T2GramModelI model;
    private final T2GramTrainerGraph graph;
    private HashMatrix<Integer, Integer, ArrayList<Double>> trainingData;
    private LinkedHashMap<String, DataType> lastTrainOccurances;
    private final transient Logger log = Logger.getLogger(getClass().getName());

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
        double[] values = new double[trainingData.get(firstKey, secondKey).size()];
        for (int i = 0; i < values.length; i++)
            values[i] = trainingData.get(firstKey, secondKey).get(i);
        return values;
    }

    @Override
    public void start(TimeSeries<Observation> inTrainingData) throws Exception {
        if (inTrainingData == null) {
            log.info("Cannot train with empty input");
            return;
        }
        log.info("Start called with " + inTrainingData.toString());
        if (currentStatus.equals(TrainerStatus.READY)) {
            log.info(format("Trainer ready. Starting training with %d elements", graph.getOutput().size()));
            graph.setInput(inTrainingData);
            graph.start();
        } else {
            throw new ActionNotPermittedException("starting training not allowed in " + currentStatus + " state");
        }

    }

    private void trainModel(TimeSeries<TemporalDurationFeature> output) {
        log.info("Starting training");
        try {
            HashMatrix<Integer, Integer, ArrayList<Double>> values = computeTrainingset(output);
            log.finer(format("Iterating through trainingset with %d elements", values.size()));

            for (Tuple<Integer, Integer> t : values.keySet()) {
                double[] durations = getDurationsAsDoubleArray(values, t);
                updateModel(t.getFirstElement(), t.getSecondElement(), durations);
            }
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    private void updateModel(int firstElement, int secondElement, double[] durations) {
        createDistributionInModelIfMissing(model, firstElement, secondElement);
        ProbabilityDistribution distribution = model.getDistributionFor(firstElement, secondElement);
        log.finer(format("Adding to distribution [%d --> %d] durations %s", firstElement, secondElement, durations));
        distribution.update(durations);
//        model.getDistributionFor(firstElement, secondElement).update(durations);
    }

    private void createDistributionInModelIfMissing(T2GramModelI inModel, int firstElement, int secondElement) {
        if (!inModel.hasDistributionFor(firstElement, secondElement)) {
            log.fine(format("No distribution for [%d --> %d], creating one.", firstElement, secondElement));
            ProbabilityFactory probabilityFactory = inModel.getDistributionFactory();
            ProbabilityDistribution distribution = probabilityFactory.create();
            inModel.addDistribution(firstElement, secondElement, distribution);
        }
    }

    private double[] getDurationsAsDoubleArray(HashMatrix<Integer, Integer, ArrayList<Double>> values, Tuple<Integer, Integer> t) {
        double[] d = new double[values.get(t.getFirstElement(), t.getSecondElement()).size()];
        for (int j = 0; j < values.get(t.getFirstElement(), t.getSecondElement()).size(); j++) {
            d[j] = values.get(t.getFirstElement(), t.getSecondElement()).get(j);
        }
        return d;
    }

    private HashMatrix<Integer, Integer, ArrayList<Double>> computeTrainingset(TimeSeries<TemporalDurationFeature> input) {
        log.info(format("Computing training set. Input = %d elements", input.size()));
        HashMatrix<Integer, Integer, ArrayList<Double>> out = new HashMatrix<Integer, Integer, ArrayList<Double>>();

        try {
            for (int i = 0; i < input.size(); i++) {
                TemporalDurationFeature feature = input.get(i);
                for (DataType precedessor : feature.getPredecessors()) {
                    // Get the event types & compute time between them
                    int predecessorEventType = precedessor.getEventType();
                    int currentEventType = feature.getEventType();
                    double duration = (double) feature.getDurationFor(precedessor);
                    log.fine(format("Timespan of [%3d --> %3d] is %6f", predecessorEventType, currentEventType, duration));

                    // Add to matrix
                    if (!out.containsKey(predecessorEventType, currentEventType))
                        out.put(predecessorEventType, currentEventType, new ArrayList<Double>());
                    out.get(predecessorEventType, currentEventType).add(duration);
                }
            }
        } catch (Exception e) {
            log.log(Level.SEVERE, "Error occured creating training set.", e);
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
        lastTrainOccurances.clear();
        trainingData = new HashMatrix<Integer, Integer, ArrayList<Double>>();
        log.info("SimpleModelTrainer reset");
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

    /**
     * Change the status of the graph base on a {@link GraphStateChangedEvent}.
     *
     * @param evt the event
     */
    @Override
    public void graphStatusChanged(GraphStateChangedEvent evt) {
        log.fine("Receives event: " + evt);
        switch (evt.getNewState()) {
            case RUNNING:
                log.fine("New state is running");
                setStatus(TrainerStatus.RUNNING);
                break;
            case READY:
                log.fine("New state is ready");
                if (evt.getPreviousState().equals(GraphStatus.RUNNING)) {
                    // training is finished
                    log.fine("Previous state was running");
                    TimeSeries<TemporalDurationFeature> output = graph.getOutput();
                    log.finer("Got output from graph");
                    trainModel(output);
                    log.finer("Trained");
                    setStatus(TrainerStatus.READY);
                }
                break;

        }

    }

    /**
     * Returns the size of the training input timeseries
     *
     * @return size of input timeseries
     */
    public int getInputSize() {
        return (graph.getInput() == null) ? 0
                                          : graph.getInput().size();
    }

    @Override
    public void stop() throws Exception {}
}

package org.bham.app.experiment;

import nu.xom.ParsingException;
import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.SlidingWindow;
import org.bham.aucom.diagnoser.ModelTrainerListener;
import org.bham.aucom.diagnoser.StatusChangedEvent;
import org.bham.aucom.diagnoser.TrainerStatus;
import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;
import org.bham.aucom.diagnoser.t2gram.T2GramModelTrainer;
import org.bham.aucom.diagnoser.t2gram.detector.T2GramDetector;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.StatisticalAnomalyClassificator;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.system.SystemConnectionFailedException;
import org.bham.aucom.util.Tupel;
import org.bham.system.cast.CastSystemConnection;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * This experiment handles the steps required to run through an experiment
 * with the CAST system.
 *
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class CastExperiment implements Experiment {

    private T2GramModelTrainer trainer;
    private T2GramDetector faultDetector;
    private CastSystemConnection cast;

    private int size;
    private File observation;
    private File classification;
    private boolean quiet;
    private int error;

    /**
     * Creates the CAST experiment.
     *
     * @param size number of samples to collect
     */
    public CastExperiment(File observation, File classification, int size, boolean quiet, int error) {
        this.observation = observation;
        this.classification = classification;
        this.size = size;
        this.quiet = quiet;
        this.error = error;

        trainer = new T2GramModelTrainer();

        // error checking
        if (!observation.exists()) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, "Must have an observation file or a model file...quitting.");
            System.exit(1);
        }

        printBlockMessage(70, "STARTING EXPERIMENT");

    }

    /**
     * Train and save the model if necessary then start the connection to CAST.
     */
    @Override
    public void preprocess() {
        // if there is no model, we have to learn one
        T2GramModelI model;
        printBlockMessage(70, "LEARN MODEL");
        model = trainModel(loadObservation(observation));
        //saveModel(observation.getPath(), model);

        // load the fault detector
        faultDetector = createDetector(model);

        // will block until connection is made
        printBlockMessage(70, "START CAST");
        cast = new CastSystemConnection();
        try {
            if (!quiet) System.out.print("Waiting to connected...");
            cast.connect();
            if (!quiet) System.out.println("connected.");
        } catch (SystemConnectionFailedException ex) {
            System.exit(1);
            Logger.getLogger(CastObservationCollection.class.getName()).log(Level.SEVERE, "Could not connect to CAST", ex);
        } catch (ActionNotPermittedException ex) {
            Logger.getLogger(CastObservationCollection.class.getName()).log(Level.SEVERE, "Illegal operation", ex);
        }
    }

    /**
     * Saves the model to a specified location on disk.
     *
     * @param wd    the working directory to save the file to
     * @param name  the name of the file
     * @param model the model to save
     */
    private void saveModel(String wd, String name, T2GramModelI model) {
        if (!quiet)
            System.out.printf("%s with %d distributions\n", model.getName(), model.getNumberDistirbutions());
        for (Tupel<Integer, Integer> indices : model.getTransitionMatrix().keySet()) {
            System.out.printf("(%d, %d) = %s\n",
                              indices.getFirstElement(),
                              indices.getSecondElement(),
                              model.getTransitionMatrix().get(indices.getFirstElement()).get(indices.getSecondElement()));
        }

        if (!quiet)
            System.out.println("Saving model: " + wd + "/" + name + ".ml");
        AucomIO.getInstance().writeFaultDetectionModel(model, new File(wd + "/" + name + ".ml"));
    }

    /**
     * Load the observation file from disk.
     *
     * @param obs observation file
     * @return the observation time series
     */
    private TimeSeries<Observation> loadObservation(File obs) {
        TimeSeries<Observation> ts = null;
        try {
            ts = (TimeSeries<Observation>) AucomIO.getInstance().readTimeSeries(obs);
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return ts;
    }

    /**
     * Start the classifier and start collecting data from the system.
     */
    @Override
    public void process() {
        try {
            // start fault detection
            faultDetector.start(cast.getObservationTimeSeries());
            if (!quiet)
                System.out.println("Sliding window: " + faultDetector.getSlidingWindow().getIntervalSize());

            // wait here until it is done
            while (faultDetector.getOutput().size() < size) {
                long size = faultDetector.getDetectorGraph().getTotalElementsSeen();
                if (!quiet)
                    System.out.printf("Detector: %d, Output: %d\n", size, faultDetector.getOutput().size());
                Thread.sleep(500);
            }
            faultDetector.stop();
            cast.disconnect();
        } catch (ActionFailedException e) {
            e.printStackTrace();
        } catch (ActionNotPermittedException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the results to disk.
     */
    @Override
    public void postprocess() {

        if (error != 0)
            System.out.printf("%dms\n", faultDetector.getOutput().get(error).getTimestamp());

        try {
            if (classification.createNewFile()) {
                if (!quiet)
                    System.out.printf("Writing classification to %s\n", classification.getPath());
            } else {
                if (!quiet)
                    System.out.printf("Overwriting classification to %s\n", classification.getPath());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        AucomIO.getInstance().writeTimeSeries(faultDetector.getOutput(), classification);
    }

    /**
     * Order of execution.
     *
     * @return nothing
     * @throws Exception error occurred
     */
    @Override
    public Result call() throws Exception {
        preprocess();
        process();
        postprocess();
        System.exit(0);
        return null;
    }

    /**
     * Train the model.
     *
     * @param observation the time eries observation
     * @return the model
     */
    private T2GramModelI trainModel(TimeSeries<Observation> observation) {
        if (!quiet) System.out.print("Training model...");
        // use this object to wait here until the training is done
        final Object obj = new Object();
        trainer.addModelTrainerListener(new ModelTrainerListener() {
            @Override
            public void modelTrainerStatusChanged(StatusChangedEvent evt) {
                if (evt.getPreviousStatus().equals(TrainerStatus.RUNNING) && evt.getCurrentStatus().equals(TrainerStatus.READY)) {
                    synchronized (obj) {
                        obj.notify();
                    }
                }
            }
        });

        // start and wait for training to finish
        try {
            trainer.setModel(new T2GramModelImp(new KDEProbabilityFactory()));
            trainer.start(observation);
            synchronized (obj) {
                obj.wait();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (!quiet) System.out.println("complete.");
        return (T2GramModelI) trainer.getModel();
    }

    /**
     * Create the anomaly detector using the learned model.
     * <p/>
     * This detector uses a statistical anomaly classifier with a mean of 0.5
     * and a variance of 0.25.
     * <p/>
     * Additionally it has a sliding window of 100ms with an initial overlap of
     * 50ms.
     *
     * @param model the learned model
     * @return a new fault detector
     */
    private T2GramDetector createDetector(T2GramModelI model) {
        T2GramDetector detector = new T2GramDetector();
        detector.setModel(model);
        detector.setClassificator(new StatisticalAnomalyClassificator(0.1, 0.001));
        detector.setSlidingWindow(new SlidingWindow(100, 50));
        return detector;
    }

    private void printBlockMessage(int blockLen, String msg) {
        if (quiet) return;

        int pad = msg.length();
        pad = (blockLen - pad) / 2;

        StringBuilder border = new StringBuilder();
        for (int i = 0; i < blockLen; i++)
            border.append("=");

        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < pad; i++)
            padding.append(" ");

        System.out.println(border);
        System.out.println(padding + msg);
        System.out.println(border);
    }
}

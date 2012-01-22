package org.bham.applications.experimenter.experiment;

import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
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
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.StatisticalAnomalyClassifier;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.system.SystemConnectionFailedException;
import org.bham.applications.experimenter.data.Result;
import org.bham.system.cast.CastSystemConnection;

import java.io.File;
import java.io.FileNotFoundException;
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

    private String name;
    private String wd;
    private String ml;
    private String obs;
    private int size;

    /**
     * Creates the CAST experiment.
     *
     * @param name experiment name - will be used to name model and results
     * @param wd   working directory - where all data will be read fro and written to
     * @param obs  observation file name
     * @param ml   model file name
     * @param size number of samples to collect
     */
    public CastExperiment(String name, String wd, String obs, String ml, int size) {
        this.name = name;
        this.wd = wd;
        this.ml = ml;
        this.obs = obs;
        this.size = size;

        trainer = new T2GramModelTrainer();

        // error checking
        if (obs.isEmpty() && ml.isEmpty()) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, "Must have an observation file or a model file...quitting.");
            System.exit(1);
        }
        if (!obs.isEmpty() && !ml.isEmpty()) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, "Given observation AND model file. Only using model file.");
        }

        Logger.getLogger(CastExperiment.class.getName()).log(Level.INFO,
                                                             String.format("Working Directory: %s\nFile: %s\nModel: %s\nObservation: %s\nSize: %d",
                                                                           wd, name, ml, obs, size));
        printBlockMessage(70, "STARTING EXPERIMENT");
    }

    /**
     * Train and save the model if necessary then start the connection to CAST.
     */
    @Override
    public void preprocess() {
        // if there is no model, we have to learn one
        T2GramModelI model;
        if (ml.isEmpty()) {
            printBlockMessage(70, "LEARN MODEL");
            model = trainModel(loadObservation(obs));
            saveModel(wd, name, model);
        } else {
            model = loadModelFile(ml);
        }

        // load the fault detector
        faultDetector = createDetector(model);

        // will block until connection is made
        printBlockMessage(70, "START CAST");
        cast = new CastSystemConnection();
        try {
            System.out.print("Waiting to connected...");
            cast.connect();
            System.out.println("connected.");
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
        System.out.println("Saving model: " + wd + "/" + name + ".ml");
        AucomIO.getInstance().writeFaultDetectionModel(model, new File(wd + "/" + name + ".ml"));
    }

    /**
     * Load the observation file from disk.
     *
     * @param obs observation filename
     * @return the observation time series
     */
    private TimeSeries<Observation> loadObservation(String obs) {
        File file = new File(wd + "/" + obs);
        TimeSeries<Observation> ts = null;
        try {
            ts = (TimeSeries<Observation>) AucomIO.getInstance().readTimeSeries(file);
        } catch (DataAlreadyExistsException e) {
            e.printStackTrace();
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
            System.out.println("Sliding window: " + faultDetector.getSlidingWindow().getIntervalSize());

            // wait here until it is done
            while (faultDetector.getOutput().size() < size || !cast.isConnected()) ;

            // stop fault detector and shutdown cast
            faultDetector.stop();
            cast.disconnect();
        } catch (ActionFailedException e) {
            e.printStackTrace();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
        } catch (ActionNotPermittedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Save the results to disk.
     */
    @Override
    public void postprocess() {
        File output = new File(wd + "/" + name + ".cl");
        AucomIO.getInstance().writeTimeSeries(faultDetector.getOutput(), output);
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
        System.out.print("Training model...");
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
        System.out.println("complete.");
        return (T2GramModelI) trainer.getModel();
    }

    /**
     * Read and load a model from a file.
     *
     * @param ml the path to the model file
     * @return a model
     */
    private T2GramModelI loadModelFile(String ml) {
        T2GramModelI _model = null;
        try {
            _model = (T2GramModelI) AucomIO.getInstance().readFaultDetectionModel(new File(ml));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (DataAlreadyExistsException ex) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ValidityException ex) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParsingException ex) {
            Logger.getLogger(CastExperiment.class.getName()).log(Level.SEVERE, null, ex);
        }
        return _model;
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
        detector.setClassificator(new StatisticalAnomalyClassifier(0.5, 0.25));
        detector.setSlidingWindow(new SlidingWindow(100, 50));
        return detector;
    }

    private void printBlockMessage(int blockLen, String msg) {
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

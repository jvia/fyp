package org.bham.app.experiment;

import nu.xom.ParsingException;
import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.SlidingWindow;
import org.bham.aucom.diagnoser.Detector;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.ModelTrainer;
import org.bham.aucom.diagnoser.ModelTrainerListener;
import org.bham.aucom.diagnoser.StatusChangedEvent;
import org.bham.aucom.diagnoser.TrainerStatus;
import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;
import org.bham.aucom.diagnoser.t2gram.T2GramModelTrainer;
import org.bham.aucom.diagnoser.t2gram.detector.T2GramDetector;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.StatisticalAnomalyClassifier;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.system.SystemConnectionFailedException;
import org.bham.system.playfile.PlayFileSystemConnection;

import java.io.File;
import java.io.IOException;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class Replay implements Experiment {

    private ModelTrainer trainer;
    private Detector detector;
    private PlayFileSystemConnection replay;

    private File observation;
    private File trainingObservation;
    private File classification;

    public Replay(File trainingObservation, File observation, File classification) {
        this.trainingObservation = trainingObservation;
        this.observation = observation;
        this.classification = classification;

        trainer = new T2GramModelTrainer();
    }

    @Override
    public void preprocess() {
        try {
            detector = createDetector(trainModel(loadObservation(observation)));
            replay = new PlayFileSystemConnection();
            replay.setFile(trainingObservation);
            replay.connect();
        } catch (SystemConnectionFailedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (ActionNotPermittedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        }
    }

    @Override
    public void process() {
        try {
            detector.start(replay.getObservationTimeSeries());
            while (replay.getProgress() < replay.getNumElements()) {
                Thread.sleep(50);
            }
            detector.stop();
        } catch (ActionFailedException e) {
            e.printStackTrace();
            throw new RuntimeException();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }


    }

    @Override
    public void postprocess() {
        try {
            replay.disconnect();
        } catch (ActionNotPermittedException e) {
            e.printStackTrace();
        }
        AucomIO.getInstance().writeTimeSeries(detector.getOutput(), classification);
    }

    @Override
    public Result call() throws Exception {
        preprocess();
        process();
        postprocess();
        return new EmptyResult();
    }

    /**
     * Load the observation file from disk.
     *
     * @param obs observation file
     * @return the observation time series
     */
    @SuppressWarnings({"unchecked"})
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
     * Train the model.
     *
     * @param observation the time eries observation
     * @return the model
     */
    private T2GramModelI trainModel(TimeSeries<Observation> observation) {
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
    private T2GramDetector createDetector(final Model model) {
        T2GramDetector detector = new T2GramDetector();
        detector.setModel(model);
        detector.setClassificator(new StatisticalAnomalyClassifier(0.7, 0.001));
        detector.setSlidingWindow(new SlidingWindow(1, 0));

        System.out.printf("Classifier:     %s%n", detector.getClassificator().getAttributes());
        System.out.printf("Sliding Window: %s%n", detector.getSlidingWindow());
        System.out.printf("Class. Model:   %s%n", detector.getDetectorGraph().getModel());

        return detector;
    }
}

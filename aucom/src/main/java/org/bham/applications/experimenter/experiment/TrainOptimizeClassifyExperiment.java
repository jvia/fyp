package org.bham.applications.experimenter.experiment;

import org.bham.applications.experimenter.data.Result;
import org.bham.aucom.data.Classification;
import org.bham.aucom.data.ClassificationTimeSeriesDescriptiveStatistics;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.AbstractDetector.DetectorStatus;
import org.bham.aucom.diagnoser.*;
import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;
import org.bham.aucom.diagnoser.t2gram.T2GramModelTrainer;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.optimizer.ClassifierOptimizer;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.optimizer.ClassifierOptimizerStatusEvent;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.optimizer.ClassifierOptimizerStatusListener;
import org.bham.aucom.util.FileOperator;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class TrainOptimizeClassifyExperiment implements Experiment {
    private static final String CSV_SEP = " ";
    private T2GramModelTrainer trainer;
    private T2GramModelI model = null;
    private File trainingFile;
    private File optimizingFile;
    private File testingFile;
    private ClassifierOptimizer optimizer;
    private File modelOutputFile;

    public TrainOptimizeClassifyExperiment(File inTrainingFile, File inOptimizingFile, File inTestingFile) {
        trainingFile = inTrainingFile;
        optimizingFile = inOptimizingFile;
        testingFile = inTestingFile;
        trainer = new T2GramModelTrainer(new T2GramModelImp(new KDEProbabilityFactory()));
    }

    @Override
    public Result call() throws Exception {
        System.out.println("starting");
        preprocess();
        process();
        postprocess();
        return null;
    }

    /**
     * This function uses the training file and trains a T2Gram model
     */

    @Override
    public void preprocess() throws Exception {
        System.out.println("training");
        final Object obj = new Object();
        trainer.addModelTrainerListener(new ModelTrainerListener() {

            @Override
            public void modelTrainerStatusChanged(StatusChangedEvent evt) {
                if (evt.getPreviousStatus().equals(TrainerStatus.RUNNING) && evt.getCurrentStatus().equals(TrainerStatus.READY)) {
                    synchronized (obj) {
                        obj.notify();
                    }
                    Logger.getLogger(TrainOptimizeClassifyExperiment.this.getClass().getCanonicalName()).info("model trained ...");
                }
            }
        });
        @SuppressWarnings("unchecked")
        TimeSeries<Observation> ts = (TimeSeries<Observation>) AucomIO.getInstance().readTimeSeries(trainingFile);
        modelOutputFile = new File(FileOperator.getPath(trainingFile) + File.separator + FileOperator.getName(trainingFile) + ".ml");
        Logger.getLogger(this.getClass().getCanonicalName()).info("training " + FileOperator.getName(modelOutputFile) + " with " + ts.size() + " elements");
        trainer.setModel(new T2GramModelImp(new KDEProbabilityFactory()));
        trainer.start(ts);
        synchronized (obj) {
            Logger.getLogger(this.getClass().getCanonicalName()).info("waiting ");
            obj.wait();
            Logger.getLogger(this.getClass().getCanonicalName()).info("waiting done ");
        }
        model = (T2GramModelI) trainer.getModel();
        trainer = null;
    }

    /**
     * This function uses the data from the optimize file and optimizes the
     * classificator parameters of the model
     */
    @Override
    public void process() throws Exception {
        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "optimizing");
        final Object waiterObj = new Object();
        @SuppressWarnings("unchecked")
        TimeSeries<Observation> optimizationTimeSeries = (TimeSeries<Observation>) AucomIO.getInstance().readTimeSeries(optimizingFile);
        optimizer.setTimeSeries(optimizationTimeSeries);
        optimizer.addStatusListener(new ClassifierOptimizerStatusListener() {
            @Override
            public void classifierOptimizerStatusChanged(ClassifierOptimizerStatusEvent st) {
                if (st.isFinished()) {
                    synchronized (waiterObj) {
                        waiterObj.notify();
                    }
                } else {
                    Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, st.getNumber() + "/" + st.getTotal());
                }
            }
        });
        optimizer.start();
        synchronized (waiterObj) {
            try {
                waiterObj.wait();
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            }
        }
    }

    @Override
    public void postprocess() throws Exception {
        final Object waiterObj = new Object();
        optimizer.getDetector().addStatusListener(new DetectorStatusChangedListener() {
            @Override
            public void handleDetectorStatusChangedEvent(DetectorStatusChangedEvent evt) {
                if (evt.getPreviousStatus().equals(DetectorStatus.RUNNING) && evt.getCurrentStatus().equals(DetectorStatus.READY)) {
                    synchronized (waiterObj) {
                        waiterObj.notify();
                    }
                }
            }
        });
        @SuppressWarnings("unchecked")
        TimeSeries<Observation> ts = (TimeSeries<Observation>) AucomIO.getInstance().readTimeSeries(testingFile);
        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, "processing " + ts);
        optimizer.getDetector().start(ts);
        synchronized (waiterObj) {
            waiterObj.wait();
        }
        TimeSeries<Classification> clTs = optimizer.getDetector().getOutput();
        optimizer.getDetector().removeAllDetectionListeners();
        AucomIO.getInstance().writeTimeSeries(clTs);
        AucomIO.getInstance().writeFaultDetectionModel(model);
        saveResults(new ClassificationTimeSeriesDescriptiveStatistics(clTs));
    }

    private void saveResults(ClassificationTimeSeriesDescriptiveStatistics statistics) {
        String fName = FileOperator.getName(testingFile) + "_statistics.txt";
        File filetoSaveTo = new File(AucomIO.getInstance().getCurrentWorkingDirectory().getAbsoluteFile() + File.separator + fName);
        String outputString = "";
        outputString += "FD" + CSV_SEP + "FTR" + CSV_SEP + "FA" + CSV_SEP + "SFAR" + CSV_SEP + "MSCORETOTAL" + CSV_SEP + "MSCOREHEAD" + CSV_SEP + "MSCORETAIL" + CSV_SEP + "VSCORETOTAL" + CSV_SEP + "VSCOREHEAD" + CSV_SEP + "VSCORETAIL\n";
        outputString += Math.ceil(statistics.getTailAnomalyValuePercent()) + CSV_SEP;
        outputString += statistics.getTailAnomalyValuePercent() + CSV_SEP;
        outputString += Math.ceil(statistics.getHeadAnomalyValuePercent()) + CSV_SEP;
        outputString += statistics.getHeadAnomalyValuePercent() + CSV_SEP;
        outputString += statistics.getTotalMeanScoreValue() + CSV_SEP;
        outputString += statistics.getHeadMeanScoreValue() + CSV_SEP;
        outputString += statistics.getTailMeanScoreValue() + CSV_SEP;
        outputString += statistics.getTotalScoreVarianceValue() + CSV_SEP;
        outputString += statistics.getHeadScoreVarianceValue() + CSV_SEP;
        outputString += statistics.getTailScoreVarianceValue() + CSV_SEP;
        outputString += "\n";
        BufferedWriter w;
        try {
            w = new BufferedWriter(new FileWriter(filetoSaveTo));
            w.write(outputString);
            w.flush();
            w.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}

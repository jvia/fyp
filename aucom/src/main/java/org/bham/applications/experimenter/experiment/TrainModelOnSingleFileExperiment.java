package org.bham.applications.experimenter.experiment;

import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.ModelTrainerListener;
import org.bham.aucom.diagnoser.StatusChangedEvent;
import org.bham.aucom.diagnoser.TrainerStatus;
import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.diagnoser.t2gram.T2GramModelImp;
import org.bham.aucom.diagnoser.t2gram.T2GramModelTrainer;
import org.bham.aucom.util.FileOperator;
import org.bham.aucom.util.Tuple;
import org.bham.applications.experimenter.data.Result;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TrainModelOnSingleFileExperiment implements Experiment {
    List<File> trainingFiles;
    List<Tuple<T2GramModelI, File>> models;
    private File workingDirectory;
    private T2GramModelTrainer trainer;

    public TrainModelOnSingleFileExperiment(File inWorkingDirectory) {
        workingDirectory = inWorkingDirectory;
        trainer = new T2GramModelTrainer(new T2GramModelImp(new KDEProbabilityFactory()));
        models = new ArrayList<Tuple<T2GramModelI, File>>();
        trainingFiles = new ArrayList<File>();
    }

    @Override
    public Result call() throws Exception {

        preprocess();

        process();

        postprocess();

        return new Result() {

            @Override
            public String getAsCsvString() {
                return "muh";
            }
        };
    }


    @Override
    public void preprocess() {
        Logger.getLogger(this.getClass().getCanonicalName()).info("preprocessing");
        FilenameFilter observationFiler = new FilenameFilter() {

            @Override
            public boolean accept(File arg0, String arg1) {
                if (arg0 == null) {
                    return false;
                }

                boolean isObservationFile = arg1.endsWith(".obs");
                File modelFile = new File(arg0.getAbsolutePath() + File.separator + FileOperator.getName(new File(arg1)) + ".ml");
                boolean isMissingCorrespondingModelFile = !modelFile.exists();
                return isObservationFile && isMissingCorrespondingModelFile;
            }
        };
        String observationFileNames[] = workingDirectory.list(observationFiler);
        Logger.getLogger(this.getClass().getCanonicalName()).info("getting " + observationFileNames.length + " observationfiles from " + workingDirectory);
        for (String observationFileName : observationFileNames) {
            File trainingFile = new File(workingDirectory.getAbsolutePath() + File.separator + observationFileName);
            Logger.getLogger(this.getClass().getCanonicalName()).info("adding " + trainingFile.getAbsolutePath() + " to training files");
            trainingFiles.add(trainingFile);
        }
    }

    @Override
    public void process() {
        final Object obj = new Object();
        trainer.addModelTrainerListener(new ModelTrainerListener() {

            @Override
            public void modelTrainerStatusChanged(StatusChangedEvent evt) {
                Logger.getLogger(TrainModelOnSingleFileExperiment.this.getClass().getCanonicalName()).info(evt.toString());
                if (evt.getPreviousStatus().equals(TrainerStatus.RUNNING) && evt.getCurrentStatus().equals(TrainerStatus.READY)) {
                    synchronized (obj) {
                        obj.notify();
                    }
                }
            }
        });

        Logger.getLogger(this.getClass().getCanonicalName()).info(trainingFiles.size() + " models to train");
        for (File f : trainingFiles) {
            try {
                @SuppressWarnings("unchecked")
                TimeSeries<Observation> ts = (TimeSeries<Observation>) AucomIO.getInstance().readTimeSeries(f);
                File outputFile = new File(FileOperator.getPath(f) + File.separator + FileOperator.getName(f) + ".ml");
                Logger.getLogger(this.getClass().getCanonicalName()).info("training " + FileOperator.getName(outputFile) + " with " + ts.size() + " elements");
                trainer.setModel(new T2GramModelImp(new KDEProbabilityFactory()));
                trainer.start(ts);
                synchronized (obj) {
                    Logger.getLogger(this.getClass().getCanonicalName()).info("waiting ");
                    obj.wait();
                    Logger.getLogger(this.getClass().getCanonicalName()).info("waiting done ");
                }
                models.add(new Tuple<T2GramModelI, File>((T2GramModelI) trainer.getModel(), outputFile));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (ValidityException e) {
                e.printStackTrace();
            } catch (DataAlreadyExistsException e) {
                e.printStackTrace();
            } catch (ParsingException e) {
                Logger.getLogger(this.getClass().getCanonicalName()).info("catched parsing exception on " + f.getName() + " ex: " + e.getLocalizedMessage());
            } catch (IOException e) {
                e.printStackTrace();
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
                e.printStackTrace();
            }
        }
    }

    @Override
    public void postprocess() {
        for (Tuple<T2GramModelI, File> t : models) {
            Logger.getLogger(this.getClass().getCanonicalName()).info("saving model to " + t.getSecondElement().getAbsolutePath());
            AucomIO.getInstance().writeFaultDetectionModel(t.getFirstElement(), t.getSecondElement());
        }
    }
}
package org.bham.applications.experimenter.experiment;

import java.util.logging.Level;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.applications.experimenter.data.Result;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.*;
import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.util.FileOperator;
import org.bham.aucom.util.Tuple;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

public class TrainModelOnSingleFileExperiment implements Experiment {
    private final List<File> trainingFiles;
    private final List<Tuple<Model, File>> models;
    private final File workingDirectory;
    private final ModelTrainer trainer;

    public TrainModelOnSingleFileExperiment(File inWorkingDirectory) {
        workingDirectory = inWorkingDirectory;
        trainer = new ModelTrainer(new Model(new KDEProbabilityFactory()));
        models = new ArrayList<Tuple<Model, File>>();
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
        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "getting {0} observationfiles from {1}", new Object[]{observationFileNames.length, workingDirectory});
        for (String observationFileName : observationFileNames) {
            File trainingFile = new File(workingDirectory.getAbsolutePath() + File.separator + observationFileName);
            Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "adding {0} to training files", trainingFile.getAbsolutePath());
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

        Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "{0} models to train", trainingFiles.size());
        for (File f : trainingFiles) {
            try {
                @SuppressWarnings("unchecked")
                TimeSeries<Observation> ts = (TimeSeries<Observation>) AucomIO.getInstance().readTimeSeries(f);
                File outputFile = new File(FileOperator.getPath(f) + File.separator + FileOperator.getName(f) + ".ml");
                Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "training {0} with {1} elements", new Object[]{FileOperator.getName(outputFile), ts.size()});
                trainer.setModel(new Model(new KDEProbabilityFactory()));
                trainer.start(ts);
                synchronized (obj) {
                    Logger.getLogger(this.getClass().getCanonicalName()).info("waiting ");
                    obj.wait();
                    Logger.getLogger(this.getClass().getCanonicalName()).info("waiting done ");
                }
                models.add(new Tuple<Model, File>(trainer.getModel(), outputFile));
            } catch (FileNotFoundException e) {
            } catch (ValidityException e) {
            } catch (DataAlreadyExistsException e) {
            } catch (ParsingException e) {
                Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "catched parsing exception on {0} ex: {1}", new Object[]{f.getName(), e.getLocalizedMessage()});
            } catch (IOException e) {
            } catch (Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
        }
    }

    @Override
    public void postprocess() {
        for (Tuple<Model, File> t : models) {
            Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "saving model to {0}", t.getSecond().getAbsolutePath());
            AucomIO.getInstance().writeFaultDetectionModel(t.getFirst(),
                                                           t.getSecond());
        }
    }
}
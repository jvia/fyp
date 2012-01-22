package org.bham.applications.experimenter.experiment;

import nu.xom.*;
import org.bham.applications.experimenter.data.Result;
import org.bham.aucom.data.Classification;
import org.bham.aucom.data.ClassificationTimeSeriesDescriptiveStatistics;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.SlidingWindow;
import org.bham.aucom.diagnoser.AbstractDetector.DetectorStatus;
import org.bham.aucom.diagnoser.*;
import org.bham.aucom.diagnoser.t2gram.KDEProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.detector.T2GramDetector;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.AnomalyClassifier;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.StatisticalAnomalyClassifier;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.optimizer.ClassifierOptimizer;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.optimizer.ClassifierOptimizerStatusEvent;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.optimizer.ClassifierOptimizerStatusListener;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.util.Constants;
import org.bham.aucom.util.FileOperator;

import java.io.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CrossValidateExperiment implements Experiment {
    HashMap<T2GramDetector, String> detectors;
    private List<TimeSeries<Observation>> timeSeries;
    private File workingDirectory;
    private ModelTrainer trainer;
    Document results;
    private String name;
    Logger logger;

    public CrossValidateExperiment(File inWorkingDirectory, String inName) {
        detectors = new LinkedHashMap<T2GramDetector, String>();
        timeSeries = new ArrayList<TimeSeries<Observation>>();
        workingDirectory = inWorkingDirectory;
        trainer = new ModelTrainer();
        logger = Logger.getLogger(this.getClass().getCanonicalName());
        Element root = new Element("aucom:results", Constants.URI);
        name = inName;
        if (name != null) {
            root.addAttribute(new Attribute("name", name));
        } else {
            root.addAttribute(new Attribute("name", "cossvalidation_default_name"));
        }
        results = new Document(root);
    }

    @Override
    public Result call() throws Exception {
        preprocess();
        process();
        postprocess();
        return null;
    }

    @Override
    public void preprocess() {
        loadTimeSeries();
        createDetectors();

    }

    private void optimizeModelParameter(T2GramDetector detector, TimeSeries<Observation> obsTs) {
        logger.log(Level.FINE, "optimizing parameters of " + detector);
        ClassifierOptimizer optimizer = new ClassifierOptimizer(detector);
        optimizer.setTimeSeries(obsTs);
        waitForOptimizerToFinish(optimizer);
    }

    private void createDetectors() {
        logger.log(Level.CONFIG, "creating detectors");
        for (TimeSeries<Observation> ts : timeSeries) {
            Model m;
            if (modelFileIsMissing(ts)) {
                m = train(ts);
            } else {
                m = loadModelFor(ts);
            }
            if (m != null) {
                logger.log(Level.CONFIG, "adding model ");
                T2GramDetector detector = new T2GramDetector();
                detector.setModel(m);
                detector.setClassificator(new StatisticalAnomalyClassifier(0.0, 0.0));
                detector.setSlidingWindow(new SlidingWindow(100, 50));
                detectors.put(detector, ts.getAttributeValue("file"));
            }
        }
        try {
            trainer.stop();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Model loadModelFor(TimeSeries<Observation> ts) {
        logger.log(Level.FINE, "loading model for " + ts);
        String filepath = FileOperator.getPath(new File(ts.getAttributeValue("file")));
        String filename = FileOperator.getName(new File(ts.getAttributeValue("file")));
        File modelFile = new File(filepath + File.separator + filename + ".ml");
        try {
            return AucomIO.getInstance().readFaultDetectionModel(modelFile);
        } catch (FileNotFoundException e) {
            logger.log(Level.WARNING, "file " + modelFile.getAbsolutePath() + " not found");
        } catch (ValidityException e) {
            logger.log(Level.WARNING, "could not validate file " + modelFile.getAbsolutePath());
        } catch (DataAlreadyExistsException e) {
            logger.log(Level.WARNING, "model already loaded from file " + modelFile.getAbsolutePath());
        } catch (IOException e) {
            logger.log(Level.WARNING, "io exception while reading " + modelFile.getAbsolutePath());
        } catch (ParsingException e) {
            logger.log(Level.WARNING, "couldn't parse " + modelFile.getAbsolutePath());
        }
        return null;
    }

    private boolean modelFileIsMissing(TimeSeries<Observation> ts) {
        String filepath = FileOperator.getPath(new File(ts.getAttributeValue("file")));
        String filename = FileOperator.getName(new File(ts.getAttributeValue("file")));
        File modelFile = new File(filepath + File.separator + filename + ".ml");
        logger.log(Level.FINE, "checking whether model for " + ts + " is missing " + modelFile.getAbsolutePath() + " " + !modelFile.exists());

        return !modelFile.exists();
    }

    private Model train(TimeSeries<Observation> ts) {
        logger.log(Level.FINE, "training model for " + ts);
        final Object obj = new Object();
        trainer.reset();
        trainer.removeAllListeners();
        trainer.addModelTrainerListener(new ModelTrainerListener() {
            @Override
            public void modelTrainerStatusChanged(StatusChangedEvent evt) {
                Logger.getLogger(CrossValidateExperiment.this.getClass().getCanonicalName()).log(Level.FINE, evt.toString());
                if (evt.getPreviousStatus().equals(TrainerStatus.RUNNING) && evt.getCurrentStatus().equals(TrainerStatus.READY)) {
                    synchronized (obj) {
                        obj.notify();
                    }
                }
            }
        });
        try {
            trainer.setModel(new Model(new KDEProbabilityFactory()));
            trainer.start(ts);
            synchronized (obj) {
                logger.log(Level.FINE, "waiting ");
                obj.wait();
                logger.log(Level.FINE, "waiting done ");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return trainer.getModel();
    }

    @SuppressWarnings("unchecked")
    private void loadTimeSeries() {
        logger.log(Level.FINE, "loading timeseries ");
        FilenameFilter observationFiler = new FilenameFilter() {

            @Override
            public boolean accept(File arg0, String arg1) {
                return arg0 != null && arg1.endsWith(".obs");

            }
        };
        String observationFileNames[] = workingDirectory.list(observationFiler);
        logger.log(Level.FINE, "getting " + observationFileNames.length + " observationfiles from " + workingDirectory);
        for (String observationFileName : observationFileNames) {
            File file = new File(workingDirectory.getAbsolutePath() + File.separator + observationFileName);
            logger.log(Level.FINE, "adding " + file.getAbsolutePath() + " to training files");
            TimeSeries<Observation> ts;
            try {
                ts = (TimeSeries<Observation>) AucomIO.getInstance().readTimeSeries(file);
                ts.addAttribute("file", file.getAbsolutePath());
                timeSeries.add(ts);
            } catch (FileNotFoundException e) {
                System.out.println("coudn't find file " + file.getAbsolutePath());
            } catch (ValidityException e) {
                System.out.println("coudn't validate file " + file.getAbsolutePath());
            } catch (DataAlreadyExistsException e) {
                System.out.println("data allready exists for file " + file.getAbsolutePath());
            } catch (ParsingException e) {
                System.out.println("coudn't parse file " + file.getAbsolutePath());
            } catch (IOException e) {
                System.out.println("coudn't read file " + file.getAbsolutePath());
            }
        }
    }

    /**
     * Executes the actual cross validation. Iterates through all previously learned detectors,
     * for each detector iterates through all observation time-series and classificates them.
     * For each classification result it computes the descriptive statistics.
     * If a timestamp of a fault is set as attribute in the observation time-series the descriptive statistics is
     * computed for the two parts of the time-series after being splitted based on the timestamp into
     * "before the fault" and "after fault".
     */
    @Override
    public void process() {
        int i = 1, j = 1, k = 1;
        for (T2GramDetector detector : detectors.keySet()) {
            j = 1;
            for (TimeSeries<Observation> obsTs : timeSeries) {
                AnomalyClassifier acBefore = detector.getClassificator().duplicate();
                optimizeModelParameter(detector, obsTs);
                logger.log(Level.FINE, "initial ac: " + acBefore + " optimized ac: " + detector.getClassificator());
                k = 1;
                for (TimeSeries<Observation> evaluateTs : timeSeries) {
                    logger.info("detector " + i + "/" + detectors.size() + " optTs: " + j + "/" + timeSeries.size() + " evalTs: " + k + "/" + timeSeries.size());
                    TimeSeries<Classification> output = evaluate(detector, evaluateTs);
                    output.getAttributes().putAll(obsTs.getAttributes());
                    ClassificationTimeSeriesDescriptiveStatistics ctds = new ClassificationTimeSeriesDescriptiveStatistics(output);
                    Element experimentResults = new Element("aucom:result", Constants.URI);
                    Element configuration = new Element("aucom:config", Constants.URI);

                    Element model = new Element("aucom:modelfile", Constants.URI);
                    model.appendChild(new File(detectors.get(detector)).getName());
                    configuration.appendChild(model);

                    Element optimizer = new Element("aucom:optimizerfile", Constants.URI);
                    optimizer.appendChild(new File(obsTs.getAttributeValue("file")).getName());
                    configuration.appendChild(optimizer);

                    Element evaluate = new Element("aucom:evaluatefile", Constants.URI);
                    evaluate.appendChild(new File(evaluateTs.getAttributeValue("file")).getName());
                    configuration.appendChild(evaluate);
                    experimentResults.appendChild(configuration);

                    //durations
                    Element durations = new Element("aucom:durations", Constants.URI);
                    Element fts = new Element("aucom:ftimestamp", Constants.URI);
                    fts.appendChild(String.valueOf(ctds.getFautlTimestamp()));
                    Element total = new Element("aucom:total", Constants.URI);
                    total.appendChild(String.valueOf(ctds.getDuration()));
                    Element head = new Element("aucom:head", Constants.URI);
                    head.appendChild(String.valueOf(ctds.getHeadDuration()));
                    Element tail = new Element("aucom:tail", Constants.URI);
                    tail.appendChild(String.valueOf(ctds.getTailDuration()));
                    durations.appendChild(fts);
                    durations.appendChild(total);
                    durations.appendChild(tail);
                    durations.appendChild(head);
                    experimentResults.appendChild(durations);

                    // scores
                    Element measures = new Element("aucom:measures", Constants.URI);
                    Element mscore_total = new Element("aucom:mscoretotal", Constants.URI);
                    mscore_total.appendChild(String.valueOf(ctds.getTotalMeanScoreValue()));
                    Element vscore_total = new Element("aucom:vscoretotal", Constants.URI);
                    vscore_total.appendChild(String.valueOf(ctds.getTotalScoreVarianceValue()));
                    measures.appendChild(mscore_total);
                    measures.appendChild(vscore_total);
                    Element mscore_before = new Element("aucom:mscorebefore", Constants.URI);
                    mscore_before.appendChild(String.valueOf(ctds.getHeadMeanScoreValue()));
                    Element vscore_before = new Element("aucom:vscorebefore", Constants.URI);
                    vscore_before.appendChild(String.valueOf(ctds.getHeadScoreVarianceValue()));
                    measures.appendChild(mscore_before);
                    measures.appendChild(vscore_before);
                    Element mscore_after = new Element("aucom:mscoreafter", Constants.URI);
                    mscore_after.appendChild(String.valueOf(ctds.getTailMeanScoreValue()));
                    Element vscore_after = new Element("aucom:vscoreafter", Constants.URI);
                    vscore_after.appendChild(String.valueOf(ctds.getTailScoreVarianceValue()));
                    measures.appendChild(mscore_after);
                    measures.appendChild(vscore_after);

                    // classification
                    Element faultDetection = new Element("aucom:fd", Constants.URI);
                    faultDetection.appendChild(String.valueOf(Math.min(1.0, ctds.getTailAnomalyValueCount())));
                    measures.appendChild(faultDetection);
                    // tracking rate
                    Element faultTrackingRate = new Element("aucom:ftr", Constants.URI);
                    faultTrackingRate.appendChild(String.valueOf(Math.min(1.0, ctds.getTailAnomalyValuePercent())));
                    measures.appendChild(faultTrackingRate);

                    // false positive
                    Element falsepositive = new Element("aucom:fp", Constants.URI);
                    falsepositive.appendChild(String.valueOf(Math.min(1.0, ctds.getHeadAnomalyValueCount())));
                    measures.appendChild(falsepositive);

                    // false positive seriousness
                    Element falsepositiveSeroiusness = new Element("aucom:falsepositiveseroiusness", Constants.URI);
                    falsepositiveSeroiusness.appendChild(String.valueOf(Math.min(1.0, ctds.getHeadAnomalyValuePercent())));
                    measures.appendChild(falsepositiveSeroiusness);

                    experimentResults.appendChild(measures);

                    results.getRootElement().appendChild(experimentResults);
                    k++;
                }
                j++;
            }
            i++;
        }
    }

    private TimeSeries<Classification> evaluate(final T2GramDetector detector, TimeSeries<Observation> evaluateTs) {
        final Object waitObj = new Object();
        try {
            logger.log(Level.FINE, "detector status before starting " + detector.getCurrentStatus() + " input size " + evaluateTs.size());
            detector.addStatusListener(new DetectorStatusChangedListener() {

                @Override
                public void handleDetectorStatusChangedEvent(DetectorStatusChangedEvent evt) {
                    Logger.getLogger(CrossValidateExperiment.this.getClass().getCanonicalName()).log(Level.FINE, "got DetectorStatusChangedEvent " + evt);
                    if (evt.getCurrentStatus().equals(DetectorStatus.RUNNING)) {
                        Logger.getLogger(CrossValidateExperiment.this.getClass().getCanonicalName()).log(Level.FINE, "detector started");
                    }
                    if (evt.getPreviousStatus().equals(DetectorStatus.RUNNING) && evt.getCurrentStatus().equals(DetectorStatus.READY)) {
                        synchronized (waitObj) {
                            detector.removeStatusListener(this);
                            waitObj.notifyAll();
                        }
                    }
                }
            });
            detector.start(evaluateTs);
            logger.log(Level.FINE, "waiting for detector " + detector + " to finish");
            synchronized (waitObj) {
                waitObj.wait();
            }
            logger.log(Level.FINE, "detector " + detector + "  finished evaluation");
            return detector.getOutput();
        } catch (ActionFailedException e) {
            e.printStackTrace();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void postprocess() {
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(new File(name + "_" + System.currentTimeMillis() + ".xml"));
            Serializer s;
            s = new Serializer(fileOutputStream, "UTF-8");
            s.setLineSeparator(System.getProperty("line.separator"));
            s.setIndent(3);
            s.write(results);
            s.flush();
            fileOutputStream.close();
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void waitForOptimizerToFinish(ClassifierOptimizer optimizer) {
        final Object waiterObj = new Object();
        try {
            optimizer.addStatusListener(new ClassifierOptimizerStatusListener() {
                @Override
                public void classifierOptimizerStatusChanged(ClassifierOptimizerStatusEvent st) {
                    if (st.isFinished()) {
                        synchronized (waiterObj) {
                            waiterObj.notify();
                        }
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
            optimizer.removeAllStatusListeners();
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        } catch (ActionFailedException exception) {
            exception.printStackTrace();
        }
    }

}

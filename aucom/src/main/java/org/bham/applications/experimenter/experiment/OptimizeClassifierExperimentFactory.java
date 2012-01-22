package org.bham.applications.experimenter.experiment;

import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.util.SlidingWindow;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.T2GramModelI;
import org.bham.aucom.diagnoser.t2gram.detector.T2GramDetector;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.StatisticalAnomalyClassifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.UUID;
import java.util.logging.Logger;

public class OptimizeClassifierExperimentFactory extends ExperimentFactory {

    @Override
    public Experiment createExperiment(Element experimentDescription) throws ValidityException, ParsingException, IOException {
        setWorkingDirectoryIfPresentInExperimentDescription(experimentDescription);
        OptimizeClassifierExperiment experiment = null;
        Logger.getLogger(this.getClass().getCanonicalName()).info("creating experiment ...");
        try {
            LinkedList<String> testFiles = getTestFilesFromElement(experimentDescription);
            LinkedList<UUID> testDataIds = loadFiles(testFiles);
            Logger.getLogger(this.getClass().getCanonicalName()).info(testDataIds.size() + " test files");

            LinkedList<String> validationFiles = getValidationFilesFromElement(experimentDescription);
            LinkedList<UUID> validationDataIds = loadFiles(validationFiles);
            Logger.getLogger(this.getClass().getCanonicalName()).info(testDataIds.size() + " testfiles validation files");

            File modelfile = getModelFileFromElement(experimentDescription);
            Model model = AucomIO.getInstance().readFaultDetectionModel(modelfile);
            Logger.getLogger(this.getClass().getCanonicalName()).info("model " + model);

            String name = getExperimentNameFromElement(experimentDescription);
            Logger.getLogger(this.getClass().getCanonicalName()).info("name " + name);

            T2GramDetector detector = new T2GramDetector();
            detector.setModel((T2GramModelI) model);
            detector.setClassificator(new StatisticalAnomalyClassifier(0.0, 0.0));
            detector.setSlidingWindow(new SlidingWindow(100, 50));
            Logger.getLogger(this.getClass().getCanonicalName()).info("detector " + detector);

            experiment = new OptimizeClassifierExperiment(detector, testDataIds, validationDataIds, name);
        } catch (FileNotFoundException exception) {
            exception.printStackTrace();
        } catch (ValidityException exception) {
            exception.printStackTrace();
        } catch (DataAlreadyExistsException exception) {
            exception.printStackTrace();
        } catch (ParsingException exception) {
            exception.printStackTrace();
        } catch (IOException exception) {
            exception.printStackTrace();
        } catch (Exception exception) {
            exception.printStackTrace();
        }
        Logger.getLogger(this.getClass().getCanonicalName()).info("... done");
        return experiment;
    }

}

package org.bham.applications.experimenter.experiment;

import nu.xom.Element;

import java.io.File;

public class TrainOptimizeClassificateExperimentFactory extends ExperimentFactory {

    @Override
    public Experiment createExperiment(Element experimentDescription) throws Exception {
        File trainingFile = new File(getTrainingFilesFromElement(experimentDescription).get(0));
        File optimizingFile = new File(getOptimizeFilesFromElement(experimentDescription).get(0));
        File testingFile = new File(getTestFilesFromElement(experimentDescription).get(0));
        if (validateFile(trainingFile) && validateFile(optimizingFile) && validateFile(testingFile)) {
            return new TrainOptimizeClassificateExperiment(trainingFile, optimizingFile, testingFile);
        }
        return null;
    }

}

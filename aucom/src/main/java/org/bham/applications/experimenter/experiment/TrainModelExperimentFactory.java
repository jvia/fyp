package org.bham.applications.experimenter.experiment;

import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import java.io.File;
import java.io.IOException;

public class TrainModelExperimentFactory extends ExperimentFactory {
    @Override
    public Experiment createExperiment(Element experimentDescription) throws ParsingException, IOException {
        File workingDirectory = new File(getWorkingDirectoryFromElement(experimentDescription));
        if (workingDirectory.isDirectory() && workingDirectory.exists()) {
            return new TrainModelOnSingleFileExperiment(new File(getWorkingDirectoryFromElement(experimentDescription)));
        }
        return null;
    }

}

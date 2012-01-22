package org.bham.applications.experimenter.experiment;

import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

public class CrossValidateExperimentFactory extends ExperimentFactory {

    @Override
    public Experiment createExperiment(Element experimentDescription) throws ValidityException, ParsingException, IOException {
        File dir = new File(getWorkingDirectoryFromElement(experimentDescription));
        if (!dir.exists()) {
            Logger.getLogger(this.getClass().getCanonicalName()).info(" coudn't find folder " + dir.getAbsolutePath());
            return null;
        }
        String name = getExperimentNameFromElement(experimentDescription);
        return new CrossValidateExperiment(dir, name);
    }

}

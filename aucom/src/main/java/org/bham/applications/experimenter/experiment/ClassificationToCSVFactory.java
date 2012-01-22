package org.bham.applications.experimenter.experiment;

import nu.xom.Element;
import nu.xom.ParsingException;

import java.io.IOException;

/**
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 * @version 1.6
 * @since 2011-09-06
 */
public class ClassificationToCSVFactory extends ExperimentFactory {

    @Override
    public Experiment createExperiment(Element experimentDescription) throws ParsingException, IOException {
        String wd = getWorkingDirectoryFromElement(experimentDescription);
        String name = getExperimentNameFromElement(experimentDescription);
        return new ClassificationToCSV(wd, name);
    }
}

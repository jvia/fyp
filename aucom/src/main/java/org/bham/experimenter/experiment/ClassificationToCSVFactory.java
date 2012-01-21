package org.bham.experimenter.experiment;

import java.io.IOException;

import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

/**
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 * @version 1.6
 * @since 2011-09-06
 */
public class ClassificationToCSVFactory extends ExperimentFactory {
    @Override
    public Experiment createExperiment(Element experimentDescription) throws ValidityException, ParsingException, IOException {
        String wd = getWorkingDirectoryFromElement(experimentDescription);
        String name = getExperimentNameFromElement(experimentDescription);
        return new ClassificationToCSV(wd, name);
    }
}

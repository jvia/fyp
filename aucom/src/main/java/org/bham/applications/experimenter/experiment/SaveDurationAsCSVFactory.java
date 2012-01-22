/**
 *
 */
package org.bham.applications.experimenter.experiment;

import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import java.io.File;
import java.io.IOException;


/**
 * @author rgolombe
 */
public class SaveDurationAsCSVFactory extends ExperimentFactory {

    /* (non-Javadoc)
     * @see aucom.experiment.ExperimentFactory#createExperiment(nu.xom.Element)
     */
    @Override
    public Experiment createExperiment(Element experimentDescription) throws ValidityException, ParsingException, IOException {
        File workingDirectory = new File(getWorkingDirectoryFromElement(experimentDescription));
        if (workingDirectory.isDirectory() && workingDirectory.exists()) {
            System.out.println("working directory exists");
            return new SaveDurationsAsCSV(new File(getWorkingDirectoryFromElement(experimentDescription)));
        }
        System.out.println("workind directory exists");
        return null;
    }

}

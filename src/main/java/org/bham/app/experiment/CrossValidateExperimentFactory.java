package org.bham.app.experiment;

import java.io.File;
import java.io.IOException;
import java.util.logging.Logger;

import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public class CrossValidateExperimentFactory extends ExperimentFactory {

	@Override
	public Experiment createExperiment(Element experimentDescription) throws ValidityException, ParsingException, IOException {
		File dir = new File(getWorkingDirectoryFromElement(experimentDescription));
		if(!dir.exists()){
		    Logger.getLogger(this.getClass().getCanonicalName()).info(" coudn't find folder " + dir.getAbsolutePath());
			return null;
		}
		String name = getExperimentNameFromElement(experimentDescription);
		return new CrossValidateExperiment(dir, name);
	}

}

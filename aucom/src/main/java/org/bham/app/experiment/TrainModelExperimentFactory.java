package org.bham.app.experiment;

import java.io.File;
import java.io.IOException;

import nu.xom.Element;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

public class TrainModelExperimentFactory extends ExperimentFactory {
	@Override
	public Experiment createExperiment(Element experimentDescription) throws ValidityException, ParsingException, IOException {
		File workingDirectory = new File(getWorkingDirectoryFromElement(experimentDescription));
		if (workingDirectory.isDirectory() && workingDirectory.exists()) {
		return new TrainModelOnSingleFileExperiment(new File(getWorkingDirectoryFromElement(experimentDescription)));
		}
		return null;
	}

}

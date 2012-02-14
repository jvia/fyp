package org.bham.app.experiment;

import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.ParsingException;

import java.io.IOException;

/**
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class CastExperimentFactory extends ExperimentFactory {

    @Override
    public Experiment createExperiment(Element element) throws ParsingException, IOException {
        setWorkingDirectoryIfPresentInExperimentDescription(element);
        String name = getExperimentNameFromElement(element);
        String wd = getWorkingDirectoryFromElement(element);
        String obs = getObservationFileName(element);
        String ml = getModelFileName(element);
        int size = getExperimentSize(element);

        return new CastExperiment(name, wd, obs, ml, size);
    }

    private int getExperimentSize(Element element) {
        Nodes nodes = element.query("./aucom:input/aucom:size", this.context);
        if (nodes.size() > 0)
            return Integer.parseInt(nodes.get(0).getValue());
        else
            return 0;
    }

    private String getModelFileName(Element element) {
        Nodes nodes = element.query("./aucom:input/aucom:model", this.context);
        if (nodes.size() > 0)
            return nodes.get(0).getValue() + ".ml";
        else
            return "";
    }

    private String getObservationFileName(Element element) {
           Nodes nodes = element.query("./aucom:input/aucom:observation", this.context);
        if (nodes.size() > 0)
            return nodes.get(0).getValue() + ".obs";
        else
            return "";
    }
}

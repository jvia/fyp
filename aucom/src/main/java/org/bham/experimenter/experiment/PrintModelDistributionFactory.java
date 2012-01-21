package org.bham.experimenter.experiment;

import nu.xom.Element;
import nu.xom.Nodes;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class PrintModelDistributionFactory extends ExperimentFactory {
    @Override
    public Experiment createExperiment(Element element) throws Exception {
        setWorkingDirectoryIfPresentInExperimentDescription(element);
        String wd = getWorkingDirectoryFromElement(element);
        String ml = getModelFileName(element);

        return new PrintModelDistribution(wd, ml);
    }

    private String getModelFileName(Element element) {
        Nodes nodes = element.query("./aucom:input/aucom:model", this.context);
        if (nodes.size() > 0)
            return nodes.get(0).getValue() + ".ml";
        else
            return "";
    }
}

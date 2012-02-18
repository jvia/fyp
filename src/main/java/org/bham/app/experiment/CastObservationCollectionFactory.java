package org.bham.app.experiment;

import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.ValidityException;

import java.io.IOException;

/**
 * A class to manage the creation of {@link CastObservationCollection} objects. 
 * It reads an experiment description from an XML file and sets up the experiment.
 * 
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class CastObservationCollectionFactory extends ExperimentFactory {

    public CastObservationCollectionFactory()
    {
        // default constructor   
    }

    /**
     * Creates the CastExpierment by reading the XML data from disk and 
     * configuring the experiment as necessary.
     * 
     * @param element the XML element
     * @return  the experiment
     * @throws ValidityException
     * @throws ParsingException
     * @throws IOException 
     */
    @Override
    public Experiment createExperiment(Element element) throws ValidityException, ParsingException, IOException
    {
        setWorkingDirectoryIfPresentInExperimentDescription(element);
        CastObservationCollection experiment = null;

        String name = getExperimentNameFromElement(element);
        String wd = getWorkingDirectoryFromElement(element);
        int count = getObservationSize(element);

        //experiment = new CastObservationCollection(wd, name, count);
        return null;//experiment;
    }

    public int getObservationSize(Element element)
    {
        Nodes nodes = element.query("./aucom:input/aucom:observations", this.context);
        if (nodes.size() > 0)
            return Integer.parseInt(nodes.get(0).getValue());
        else
            return 0;
    }
}

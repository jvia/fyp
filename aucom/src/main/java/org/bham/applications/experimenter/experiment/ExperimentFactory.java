package org.bham.applications.experimenter.experiment;

import nu.xom.*;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.diagnoser.t2gram.ProbabilityFactory;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;

import javax.naming.ConfigurationException;
import java.io.File;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.LinkedList;
import java.util.UUID;

public abstract class ExperimentFactory {
    Builder builder = new Builder();
    protected XPathContext context = new XPathContext("aucom", "http://www.cor-lab.de");

    public abstract Experiment createExperiment(Element experimentDescription) throws Exception;


    /**
     * @param trainingFiles
     * @param trainingDataIds
     * @throws Exception
     */
    protected LinkedList<UUID> loadFiles(LinkedList<String> trainingFiles) throws Exception {
        LinkedList<UUID> f = new LinkedList<UUID>();
        for (String trainingFile : trainingFiles) {
            f.add(AucomIO.getInstance().readTimeSeriesRelativeToCurrentWorking(trainingFile).getId());
        }
        return f;
    }

    /**
     * @param xmlElement
     */
    protected void setWorkingDirectoryIfPresentInExperimentDescription(Element xmlElement) {
        String workingDirectory = getWorkingDirectoryFromElement(xmlElement);
        if (workingDirectory != null) {
            AucomIO.getInstance().changeCurrentWorkingDirectory(new File(workingDirectory));
        }
    }

    protected int getIntervalSizeFromElement(Element xmlElement) {
        Element slidingWindowElement = (Element) xmlElement.query("./aucom:model/aucom:SlidingWindow", this.context).get(0);
        int size = Integer.valueOf(slidingWindowElement.getAttributeValue("size")).intValue();
        return size;
    }

    protected int getIntervalOverlapSize(Element xmlElement) {
        Element slidingWindowElement = (Element) xmlElement.query("./aucom:model/aucom:SlidingWindow", this.context).get(0);
        int overlap = Double.valueOf(slidingWindowElement.getAttributeValue("overlap")).intValue();
        return overlap;
    }


    protected String getAttributeValueFromExperimentElement(Element xmlElement, String attributeKey) throws ValidityException {
        if (xmlElement.getQualifiedName().equals("aucom:experiment")) {
            if (xmlElement.getAttribute(attributeKey) != null) {
                return xmlElement.getAttributeValue(attributeKey);
            }
            throw new ValidityException("attribute " + attributeKey + "  is missing for " + xmlElement.getQualifiedName());
        }
        throw new ValidityException("cannot extract attribute name from " + xmlElement.getQualifiedName() + ". Qualified name of  element should be aucom:experiment");
    }

    protected String getExperimentNameFromElement(Element xmlElement) throws ValidityException {
        return getAttributeValueFromExperimentElement(xmlElement, "name");
    }

    protected String getExperimentType(Element xmlElement) throws ValidityException {
        return getAttributeValueFromExperimentElement(xmlElement, "type");
    }

    protected AnomalyClassificator getThresholdFromElement(Element xmlElement) {
        Class<?> thresholdClass = getThresholdClassFromElement(xmlElement);
        Element thresholdElement = (Element) xmlElement.query("./aucom:model/aucom:Threshold", this.context).get(0);
        Class<?>[] parameterClasses = getParameterClassesFromElement(thresholdElement);
        Constructor<?> thresholdConstructor;
        AnomalyClassificator threshold = null;
        try {
            thresholdConstructor = thresholdClass.getConstructor(parameterClasses);
            Object[] parameterValues = getParameterValuesFromElement(thresholdElement);
            threshold = (AnomalyClassificator) thresholdConstructor.newInstance(parameterValues);
        } catch (SecurityException exception) {
            exception.printStackTrace();
        } catch (NoSuchMethodException exception) {
            exception.printStackTrace();
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        } catch (InstantiationException exception) {
            exception.printStackTrace();
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        } catch (InvocationTargetException exception) {
            exception.printStackTrace();
        }
        return threshold;
    }

    protected LinkedList<String> getValidationFilesFromElement(Element xmlElement) {
        LinkedList<String> files = new LinkedList<String>();
        Nodes nodes = xmlElement.query("./aucom:input/aucom:validation", this.context);
        for (int i = 0; i < nodes.size(); i++) {
            files.add(((Element) nodes.get(i)).getChild(0).getValue());
        }
        return files;
    }

    protected LinkedList<String> getTrainingFilesFromElement(Element xmlElement) {
        LinkedList<String> files = new LinkedList<String>();
        Nodes nodes = xmlElement.query("./aucom:input/aucom:train", this.context);
        for (int i = 0; i < nodes.size(); i++) {
            files.add(((Element) nodes.get(i)).getChild(0).getValue());
        }
        return files;
    }

    protected LinkedList<String> getTestFilesFromElement(Element xmlElement) {
        LinkedList<String> files = new LinkedList<String>();
        Nodes nodes = xmlElement.query("./aucom:input/aucom:test", this.context);
        for (int i = 0; i < nodes.size(); i++) {
            files.add(((Element) nodes.get(i)).getChild(0).getValue());
        }
        return files;
    }

    protected String getWorkingDirectoryFromElement(Element xmlElement) {
        Nodes nodes = xmlElement.query("./aucom:input/aucom:wd", this.context);
        if (nodes.size() > 0) {
            return nodes.get(0).getValue();
        }
        return "";
    }

    protected ProbabilityFactory getProbabilityFactoryFromElement(Element xmlElement) {
        ProbabilityFactory probabilityFactory = null;
        Element distributionElement = (Element) xmlElement.query("./aucom:model/aucom:distribution", this.context).get(0);
        Class<?> probabilityFactoryClass = getProbabilityFactoryClassFromElement(distributionElement);
        Class<?>[] parameters = getParameterClassesFromElement(distributionElement);
        try {
            Constructor<?> probabilityFactoryClassConstructor = probabilityFactoryClass.getConstructor(parameters);
            Object[] probabilityFactoryClassParameterValues = getParameterValuesFromElement(distributionElement);
            probabilityFactory = (ProbabilityFactory) probabilityFactoryClassConstructor.newInstance(probabilityFactoryClassParameterValues);
        } catch (SecurityException exception) {
            exception.printStackTrace();
        } catch (NoSuchMethodException exception) {
            exception.printStackTrace();
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        } catch (InstantiationException exception) {
            exception.printStackTrace();
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        } catch (InvocationTargetException exception) {
            exception.printStackTrace();
        }
        return probabilityFactory;
    }

    protected Object[] getParameterValuesFromElement(Element xmlElement) {
        Nodes parameters = xmlElement.query("./aucom:parameter", this.context);
        Object[] parameterValues = new Object[parameters.size()];
        for (int i = 0; i < parameters.size(); i++) {
            parameterValues[i] = getParameterValueFromElement((Element) parameters.get(i));
        }
        return parameterValues;
    }

    protected Class<?>[] getParameterClassesFromElement(Element xmlElement) {
        Nodes parameters = xmlElement.query("./aucom:parameter", this.context);
        Class<?>[] oneParameterSet = new Class<?>[parameters.size()];
        for (int i = 0; i < parameters.size(); i++) {
            oneParameterSet[i] = getParameterClassFromElement((Element) parameters.get(i));
        }
        return oneParameterSet;
    }

    protected Class<?> getParameterClassFromElement(Element result) {
        try {
            return Class.forName(result.getAttributeValue("type"));
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    /**
     * @param result
     */
    protected Object getParameterValueFromElement(Element result) {
        try {
            Class<?> parameterClass = Class.forName(result.getAttributeValue("type"));
            String value = result.getAttributeValue("value");
            Class<?>[] paramclasses = {String.class};
            Constructor<?> paramConstructor = parameterClass.getConstructor(paramclasses);
            return paramConstructor.newInstance(value);
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        } catch (SecurityException exception) {
            exception.printStackTrace();
        } catch (NoSuchMethodException exception) {
            exception.printStackTrace();
        } catch (IllegalArgumentException exception) {
            exception.printStackTrace();
        } catch (InstantiationException exception) {
            exception.printStackTrace();
        } catch (IllegalAccessException exception) {
            exception.printStackTrace();
        } catch (InvocationTargetException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Class<?> getProbabilityFactoryClassFromElement(Element xmlElement) {
        String type = xmlElement.getAttributeValue("type");
        try {
            return Class.forName(type);
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    public Class<?> getThresholdClassFromElement(Element xmlElement) {
        Element result = (Element) xmlElement.query("//aucom:Threshold", this.context).get(0);
        String type = result.getAttributeValue("type");
        try {
            return Class.forName(type);
        } catch (ClassNotFoundException exception) {
            exception.printStackTrace();
        }
        return null;
    }

    protected File getModelFileFromElement(Element xmlElement) {
        Element result = (Element) xmlElement.query(".//aucom:model", this.context).get(0);
        return new File(AucomIO.getInstance().getCurrentWorkingDirectory().getAbsolutePath() + File.separator + result.getValue());
    }


    /**
     * @param trainingFile
     * @throws ConfigurationException
     */
    protected boolean validateFile(File trainingFile) {
        if (!trainingFile.exists() || !trainingFile.isFile()) {
            return false;
        }
        return true;
    }


    protected LinkedList<String> getOptimizeFilesFromElement(Element xmlElement) {
        LinkedList<String> files = new LinkedList<String>();
        Nodes nodes = xmlElement.query("./aucom:input/aucom:optimize", this.context);
        for (int i = 0; i < nodes.size(); i++) {
            files.add(((Element) nodes.get(i)).getChild(0).getValue());
        }
        return files;
    }


}

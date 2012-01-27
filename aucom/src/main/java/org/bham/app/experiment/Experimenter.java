package org.bham.app.experiment;

import java.io.File;
import java.io.IOException;
import java.util.LinkedList;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import java.util.logging.Handler;
import java.util.logging.Logger;

import joptsimple.OptionParser;
import joptsimple.OptionSet;
import joptsimple.OptionSpec;
import nu.xom.Builder;
import nu.xom.Document;
import nu.xom.Element;
import nu.xom.Nodes;
import nu.xom.ParsingException;
import nu.xom.ValidityException;
import nu.xom.XPathContext;

import org.bham.aucom.util.AucomFormatter;


public class Experimenter {

    protected XPathContext context = new XPathContext("aucom", "http://www.cor-lab.de");

    private final class ExperimenterThreadFactory implements ThreadFactory {

        int num = 0;

        public ExperimenterThreadFactory() {
            // default constructor
        }

        @Override
        public Thread newThread(Runnable r) {
            Thread th = new Thread(r);
            th.setName("experimenter-" + num);
            num++;
            return th;
        }
    }

    LinkedList<Experiment> experiments;
    LinkedList<Result> results;
    public Experiment currentTask;
    private ScheduledExecutorService experimenterExecutionService;

    public Experimenter() {
        Logger.getLogger(this.getClass().getCanonicalName()).info("initializing experimenter");
        this.experimenterExecutionService = Executors.newScheduledThreadPool(1, new ExperimenterThreadFactory());
        this.experiments = new LinkedList<Experiment>();
        this.results = new LinkedList<Result>();
    }

    public void load(File xmlExperimentDescription) throws ValidityException, ParsingException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        System.out.println("loading experiment descriptions ...");
        experiments.addAll(createExperiments(xmlExperimentDescription));
    }

    private LinkedList<Experiment> createExperiments(File xmlExperimentDescription) throws ValidityException, ParsingException, IOException, InstantiationException, IllegalAccessException, ClassNotFoundException {
        Document doc = getExperimentDocumentFromfile(xmlExperimentDescription);
        Element[] experimentDefinitions = getExperimentDefinitionsFromDocument(doc);
        LinkedList<Experiment> exp = new LinkedList<Experiment>();
        if (experimentDefinitions.length == 1) {
            System.out.println("found " + experimentDefinitions.length + " experiment definition");
        } else if (experimentDefinitions.length > 1) {
            System.out.println("found " + experimentDefinitions.length + " experiment definitions");
        } else {
            System.out.println("no  experiment definitions found in " + xmlExperimentDescription.getAbsolutePath());
        }
        for (int i = 0; i < experimentDefinitions.length; i++) {
            Experiment ex = null;
            try {
                ex = getFactory(experimentDefinitions[i]).createExperiment(experimentDefinitions[i]);
            } catch (Exception e) {
                e.printStackTrace();  //To change body of catch statement use File | Settings | File Templates.
            }
            if (ex != null) {
                exp.add(ex);
            }
        }
        return exp;
    }

    private Element[] getExperimentDefinitionsFromDocument(Document doc) {
        Nodes nodes = doc.query("/aucom:experiments/aucom:experiment", context);
        Element[] experimentDefinitions = new Element[nodes.size()];
        for (int i = 0; i < nodes.size(); i++) {
            experimentDefinitions[i] = (Element) nodes.get(i);
        }
        return experimentDefinitions;
    }

    private Document getExperimentDocumentFromfile(File xmlExperimentDescription) throws ValidityException, ParsingException, IOException {
        Builder b = new Builder();
        return b.build(xmlExperimentDescription);
    }

    public ExperimentFactory getFactory(Element experimentDefinition) throws InstantiationException, IllegalAccessException, ClassNotFoundException {
        System.out.println(experimentDefinition);
        String fullyClassifiedFactoryClassName = experimentDefinition.getAttributeValue("factory");
        ExperimentFactory factory = (ExperimentFactory) Class.forName(fullyClassifiedFactoryClassName).newInstance();
        Logger.getLogger(getClass().getCanonicalName()).info("instantiated " + fullyClassifiedFactoryClassName);
        return factory;
    }

    private void start() {
        while ((this.currentTask = this.experiments.poll()) != null) {
            Future<Result> f = this.experimenterExecutionService.schedule(this.currentTask, 1, TimeUnit.SECONDS);
            try {
                f.get(); // in order to wait for computation we try to get the result
            } catch (InterruptedException exception) {
                exception.printStackTrace();
            } catch (ExecutionException exception) {
                exception.printStackTrace();
            }
            Logger.getLogger(this.getClass().getCanonicalName()).info("EXPERIMENTER FINISHED A LOOP, " + this.experiments.size() + " EXPERIMENTS LEFT");
        }
    }

    public void stop() {
        Logger.getLogger(this.getClass().getCanonicalName()).info("experimenter done");
        this.experimenterExecutionService.shutdown();
        Logger.getLogger(this.getClass().getCanonicalName()).info("experimenter done, shutting down executor service.");
    }

    public static void main(String[] args) {
        setAucomLogFormatter();
        OptionParser parser = new OptionParser();
        OptionSpec<File> descriptionFile = parser.accepts("f").withRequiredArg().ofType(File.class);
        OptionSet option = parser.parse(args);

        if (option.has(descriptionFile)) {
            File experimentDescription = descriptionFile.value(option);
            System.out.println("got description file : " + experimentDescription.getAbsolutePath());
            if (experimentDescription.exists()) {
                if (experimentDescription.isFile()) {
                    System.out.println("file exists and is actualy a file ... ");
                    Experimenter experimenter;
                    try {
                        experimenter = new Experimenter();
                        experimenter.load(experimentDescription);
                        experimenter.start();
                        experimenter.stop();
                    } catch (ValidityException exception) {
                        exception.printStackTrace();
                    } catch (ParsingException exception) {
                        exception.printStackTrace();
                    } catch (IOException exception) {
                        exception.printStackTrace();
                    } catch (InstantiationException exception) {
                        exception.printStackTrace();
                    } catch (IllegalAccessException exception) {
                        exception.printStackTrace();
                    } catch (ClassNotFoundException exception) {
                        exception.printStackTrace();
                    }
                } else {
                    System.out.println("no such file " + experimentDescription.getAbsolutePath());
                    return;
                }
            } else {
                System.out.println("no such file " + experimentDescription.getAbsolutePath());
            }
        } else {
            System.out.println("couldn't find option " + descriptionFile.toString());
        }
    }

    /**
     *
     */
    private static void setAucomLogFormatter() {
        Handler[] h = Logger.getLogger("").getHandlers();
        AucomFormatter formatter = new AucomFormatter();
        for (int i = 0; i < h.length; i++) {
            h[i].setFormatter(formatter);
        }
    }
}

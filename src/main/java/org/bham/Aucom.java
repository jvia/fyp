package org.bham;

import org.bham.app.experiment.CastExperiment;
import org.bham.app.experiment.CastObservationCollection;
import org.bham.app.experiment.ClassificationToCSV;
import org.bham.app.experiment.Replay;
import org.kohsuke.args4j.CmdLineException;
import org.kohsuke.args4j.CmdLineParser;
import org.kohsuke.args4j.ExampleMode;
import org.kohsuke.args4j.Option;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class Aucom {

    // The major mode options

    @Option(name = "-C",
            aliases = "--collection",
            usage = "Run the data collector. " +
                    "This must be used with the -o options to " +
                    "specify the name of the output file")
    private boolean collection;

    @Option(name = "-E",
            aliases = "--experiment",
            usage = "Run the data collector. This must be used with " +
                    "the -i and -o options to specify the name of " +
                    "the input and output files")
    private boolean experiment;

    @Option(name = "-D",
            aliases = "--classification-to-dat",
            usage = "Transform a classification file into a dat " +
                    "file suitable for graphing in gnuplot.")
    private boolean toDat;


    @Option(name = "-R",
            aliases = "--replay",
            usage = "Run aucom with a previously collected observation" +
                    "files as the input.")
    private boolean replay;

    // I/O options
    @Option(name = "-i",
            aliases = "--input",
            usage = "*Required* The input file. Given the major mode of the program, " +
                    "the type of file will be inferred.")
    private File in;

    @Option(name = "-o",
            aliases = "--output",
            usage = "*Required* The output file. Given the major mode of the program, " +
                    "the type of file will be inferred.",
            required = true)
    private File out;

    @Option(name = "-t",
            aliases = "--training-file",
            usage = "The file to use when training the model for replay mode.")
    private File training;

    // Misc options
    @Option(name = "-s",
            aliases = "--size",
            usage = "The number of observations to collect before terminating the program.")
    private int size = 4000;

    @Option(name = "-q",
            aliases = "--quiet",
            usage = "Inhibit output, with the exception of the fault timestamp when running an experiment.")
    private boolean quiet;

    @Option(name = "-e",
            aliases = "--error",
            usage = "The observation when the error is to occur.")
    private int error;

    private void runConversion() {
        ClassificationToCSV conversion = new ClassificationToCSV(in, out);
        try {
            conversion.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runCollection() {
        CastObservationCollection collect = new CastObservationCollection(out, size, quiet);
        try {
            collect.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runReplay() {
        Replay replay = new Replay(training, in, out);
        try {
            replay.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void runExperiment() {
        CastExperiment experiment = new CastExperiment(in, out, size, quiet, error);
        try {
            experiment.call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        Logger.getLogger(Logger.GLOBAL_LOGGER_NAME).setLevel(Level.ALL);
        Aucom aucom = new Aucom();
        CmdLineParser parser = new CmdLineParser(aucom);

        try {
            parser.parseArgument(args);
        } catch (CmdLineException e) {
            System.err.println("java AucomMain [options...]");
            parser.printUsage(System.err);
            parser.printExample(ExampleMode.ALL);
            System.exit(1);
        }

        if (aucom.experiment) {
            aucom.runExperiment();
        } else if (aucom.collection) {
            aucom.runCollection();
        } else if (aucom.toDat) {
            aucom.runConversion();
        } else if (aucom.replay) {
            aucom.runReplay();
        } else {
            System.err.println("Need to specify a major mode: Experiment, " +
                               "Collection, or Conversion.");
        }

        System.exit(0);
    }
}

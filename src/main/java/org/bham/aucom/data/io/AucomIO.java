package org.bham.aucom.data.io;

import nu.xom.ParsingException;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.io.csv.in.CSVTimeSeriesInput;
import org.bham.aucom.data.io.csv.out.CSVTimeSeriesOutput;
import org.bham.aucom.data.io.xml.in.XmlTimeSeriesInput;
import org.bham.aucom.data.io.xml.out.XmlTimeSeriesOutput;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.SlidingWindow;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AbstractAnomalyClassifier;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

/**
 * This class defines an interface for managing persistence in the
 * aucom project. For each structure we want to read/write we need an IOClass.
 * Currently known structures to save:
 * <ul>
 * <li>TimeSeries</li>
 * <li>FaultDetectionModel</li>
 * <li>Encoder</li>
 * <li>SlidingWindow</li>
 * <li>Classifier</li>
 * </ul>
 * <p/>
 *
 * @author Raphael Golombek <rgolombe@cor-lab.uni-bielefeld.de>
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 */
public class AucomIO {
    private File workingDirectory = new File(System.getProperty("user.dir"));

    private final HashMap<String, TimeSeriesIO> tsIo = new HashMap<String, TimeSeriesIO>();
    private final String defaultTimeSeriesIOType = "xml";
    private final ModelIO faultDetectionModelIO = new ModelIO();
    private final SlindingWindowIO slidingWindowIO = new SlindingWindowIO();
    private final ClassificatorIO classificatorIO = new ClassificatorIO();
    //private EncoderIO encoderIO = new EncoderIO();

    // private XmlFileWriter xmlFileWriter = new XmlFileWriter();

    /**
     * Singleton object.
     */
    private AucomIO() {
        tsIo.put("xml", new TimeSeriesIO(new XmlTimeSeriesInput(), new XmlTimeSeriesOutput()));
        tsIo.put("csv", new TimeSeriesIO(new CSVTimeSeriesInput(), new CSVTimeSeriesOutput()));
    }

    private static AucomIO instance;

    public static AucomIO getInstance() {
        if (instance == null) {
            instance = new AucomIO();
        }
        return instance;
    }

    /*
      * Fault detection model io
      */
    public boolean writeFaultDetectionModel(Model model) {
        File f = generateFileFor(model.getId(), "ml");
        boolean successfull = this.faultDetectionModelIO.write(model, f);
        if (successfull) {
            System.out.println("model: " + model.toString() + " saved to " + f);
        } else {
            System.out.println("error while saving model: " + model.toString() + " to " + f);
        }
        return successfull;
    }

    /*
      * Fault detection model io
      */
    public boolean writeFaultDetectionModel(Model model, File f) {
        boolean successfull = this.faultDetectionModelIO.write(model, f);
        if (successfull) {
            System.out.println("model: " + model.toString() + " saved to " + f);
        } else {
            System.out.println("error while saving model: " + model.toString() + " to " + f);
        }
        return successfull;
    }

    public Model readFaultDetectionModel(File file) throws IOException, ParsingException {
        return this.faultDetectionModelIO.read(file);
    }

    /*
      * TimeSeries io
      */
    public <T extends AbstractData> boolean writeTimeSeries(TimeSeries<T> timeSeries) {
        return this.tsIo.get(defaultTimeSeriesIOType).write(timeSeries, generateFileFor(timeSeries.getId(), timeSeries.getType().toString()));
    }

    public <T extends AbstractData> boolean writeTimeSeries(TimeSeries<T> timeSeries, File fileToWriteTo) {
        return this.tsIo.get(defaultTimeSeriesIOType).write(timeSeries, fileToWriteTo);
    }

    public <T extends AbstractData> boolean writeTimeSeries(TimeSeries<T> timeSeries, String type) throws IllegalOutputType {
        if (tsIo.containsKey(type)) {
            return this.tsIo.get(defaultTimeSeriesIOType).write(timeSeries, generateFileFor(timeSeries.getId(), timeSeries.getType().toString()));
        }
        throw new IllegalOutputType("output handler for type " + type + " not found");
    }

    public <T extends AbstractData> boolean writeTimeSeries(TimeSeries<T> timeSeries, File fileToWriteTo, String type) throws IllegalOutputType {
        if (tsIo.containsKey(type)) {
            return this.tsIo.get(type).write(timeSeries, fileToWriteTo);
        }
        throw new IllegalOutputType("output handler for type " + type + " not found");
    }

    public TimeSeries<?> readTimeSeriesRelativeToCurrentWorking(String file) throws ParsingException, IOException {
        return this.tsIo.get(defaultTimeSeriesIOType).read(new File(getCurrentWorkingDirectory(), file));
    }

    public TimeSeries<?> readTimeSeries(File file) throws ParsingException, IOException {
        return this.tsIo.get(defaultTimeSeriesIOType).read(file);
    }

    public TimeSeries<?> readTimeSeriesRelativeToCurrentWorking(String file, String type) throws ParsingException, IOException,
                                                                                                 IllegalOutputType {
        if (tsIo.containsKey(type)) {
            return this.tsIo.get(defaultTimeSeriesIOType).read(new File(getCurrentWorkingDirectory(), file));
        }
        throw new IllegalOutputType("output handler for type " + type + " not found");
    }

    public TimeSeries<?> readTimeSeries(File file, String type) throws ParsingException, IOException, IllegalOutputType {
        if (tsIo.containsKey(type)) {
            return this.tsIo.get(defaultTimeSeriesIOType).read(file);
        }
        throw new IllegalOutputType("output handler for type " + type + " not found");
    }

    /*
      * TimeSeries io
      */

    public boolean writeClassificator(AbstractAnomalyClassifier classificator) {
        return this.classificatorIO.write(classificator, generateFileFor(classificator.getId(), "cl"));
    }

    public AbstractAnomalyClassifier readClassificator(String file) throws ParsingException, IOException {
        return this.classificatorIO.read(new File(getCurrentWorkingDirectory(), file));
    }

    public boolean writeSlidingWindow(SlidingWindow slidingWindow) {
        return this.slidingWindowIO.write(slidingWindow, generateFileFor(slidingWindow.getId(), "sw"));
    }

    public SlidingWindow readSlidingWindow(String file) throws ParsingException, IOException {
        return this.slidingWindowIO.read(new File(getCurrentWorkingDirectory(), file));
    }

    public void changeCurrentWorkingDirectory(File dir) throws IllegalArgumentException {
        if (!dir.isDirectory()) {
            throw new IllegalArgumentException(dir.getAbsolutePath() + " is not a folder");
        }
        this.workingDirectory = dir;
        Logger.getLogger(this.getClass().getCanonicalName()).info("working directory changed to " + this.workingDirectory);
    }

    public File getCurrentWorkingDirectory() {
        return this.workingDirectory;
    }

    public File generateFileFor(UUID id, String fileExtension) {
        return new File(this.getCurrentWorkingDirectory().getAbsolutePath() + File.separator + id.toString() + "." + fileExtension);
    }
}

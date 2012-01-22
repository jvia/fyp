package org.bham.aucom.data.io;

import nu.xom.ParsingException;
import nu.xom.ValidityException;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.io.csv.in.CSVTimeSeriesInput;
import org.bham.aucom.data.io.csv.out.CSVTimeSeriesOutput;
import org.bham.aucom.data.io.xml.in.XmlTimeSeriesInput;
import org.bham.aucom.data.io.xml.out.XmlTimeSeriesOutput;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.SlidingWindow;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.AbstractAnomalyClassifier;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.HashMap;
import java.util.UUID;
import java.util.logging.Logger;

public class AucomIO {
    /*
      * This class defines an interface for managing persistency in the aucom
      * project. For each structure we want to read/write we need an IOClass.
      * Currently known structures to save: Timeseries, FaultDetectionModel,
      * SlidingWindow, Classificator, Encoder
      */
    private File workingDirectory = new File(System.getProperty("user.dir"));

    private HashMap<String, TimeSeriesIO> tsIo = new HashMap<String, TimeSeriesIO>();
    String defaultTimeSeriesIOType = "xml";
    private ModelIO faultDetectionModelIO = new ModelIO();
    private SlindingWindowIO slidingWindowIO = new SlindingWindowIO();
    private ClassifierIO classifierIO = new ClassifierIO();

    // private XmlFileWriter xmlFileWriter = new XmlFileWriter();

    /*
      * singleton
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
        boolean successful = this.faultDetectionModelIO.write(model, f);
        if (successful) {
            System.out.println("model: " + model.toString() + " saved to " + f);
        } else {
            System.out.println("error while saving model: " + model.toString() + " to " + f);
        }
        return successful;
    }

    /*
      * Fault detection model io
      */
    public boolean writeFaultDetectionModel(Model model, File f) {
        boolean successful = faultDetectionModelIO.write(model, f);
        if (successful) {
            System.out.println("model: " + model.toString() + " saved to " + f);
        } else {
            System.out.println("error while saving model: " + model.toString() + " to " + f);
        }
        return successful;
    }

    public Model readFaultDetectionModel(File file) throws FileNotFoundException, DataAlreadyExistsException, IOException, ValidityException, ParsingException {
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

    public TimeSeries<?> readTimeSeriesRelativeToCurrentWorking(String file) throws FileNotFoundException, ValidityException, DataAlreadyExistsException, ParsingException, IOException {
        return this.tsIo.get(defaultTimeSeriesIOType).read(new File(getCurrentWorkingDirectory(), file));
    }

    public TimeSeries<?> readTimeSeries(File file) throws FileNotFoundException, ValidityException, DataAlreadyExistsException, ParsingException, IOException {
        return this.tsIo.get(defaultTimeSeriesIOType).read(file);
    }

    public TimeSeries<?> readTimeSeriesRelativeToCurrentWorking(String file, String type) throws FileNotFoundException, ValidityException, DataAlreadyExistsException, ParsingException, IOException,
                                                                                                 IllegalOutputType {
        if (tsIo.containsKey(type)) {
            return this.tsIo.get(defaultTimeSeriesIOType).read(new File(getCurrentWorkingDirectory(), file));
        }
        throw new IllegalOutputType("output handler for type " + type + " not found");
    }

    public TimeSeries<?> readTimeSeries(File file, String type) throws FileNotFoundException, ValidityException, DataAlreadyExistsException, ParsingException, IOException, IllegalOutputType {
        if (tsIo.containsKey(type)) {
            return this.tsIo.get(defaultTimeSeriesIOType).read(file);
        }
        throw new IllegalOutputType("output handler for type " + type + " not found");
    }

    /*
      * TimeSeries io
      */

    public boolean writeClassificator(AbstractAnomalyClassifier classifier) {
        return this.classifierIO.write(classifier, generateFileFor(classifier.getId(), "cl"));
    }

    public AbstractAnomalyClassifier readClassificator(String file) throws FileNotFoundException, ValidityException, DataAlreadyExistsException, ParsingException, IOException {
        return this.classifierIO.read(new File(getCurrentWorkingDirectory(), file));
    }

    public boolean writeSlidingWindow(SlidingWindow slidingWindow) {
        return this.slidingWindowIO.write(slidingWindow, generateFileFor(slidingWindow.getId(), "sw"));
    }

    public SlidingWindow readSlidingWindow(String file) throws FileNotFoundException, ValidityException, DataAlreadyExistsException, ParsingException, IOException {
        return this.slidingWindowIO.read(new File(getCurrentWorkingDirectory(), file));
    }

    public void changeCurrentWorkingDirectory(File dir) throws IllegalArgumentException {
        if (!dir.isDirectory())
            throw new IllegalArgumentException(dir.getAbsolutePath() + " is not a folder");
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

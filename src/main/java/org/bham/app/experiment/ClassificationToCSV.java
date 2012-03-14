package org.bham.app.experiment;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.StatisticalAnomalyClassifier;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 * @version 1.6
 * @since 2011-09-06
 */
public class ClassificationToCSV implements Experiment {

    private final File in;
    private final File out;

    private TimeSeries<Classification> classificationTimeSeries;
    private FileWriter csv;
    private ArrayList<Classification> list;

    private transient final Logger log = Logger.getLogger(getClass().getName());

    /**
     * Constructor ClassificationToCSV creates a new ClassificationToCSV
     * instance.
     *
     * @param in  classification file
     * @param out csv file
     */
    public ClassificationToCSV(final File in, final File out) {
        this.in = in;
        this.out = out;

        log.setLevel(Level.ALL);
        log.config(String.format("Classification file: %s%nCSV file: %s",
                                 in, out));
    }

    /**
     * Method preprocess reads in the data.
     */
    @SuppressWarnings({"unchecked"})
    @Override
    public void preprocess() {
        try {
            classificationTimeSeries = (TimeSeries<Classification>) AucomIO.getInstance().readTimeSeries(in);
        } catch (Exception e) {
            e.printStackTrace();
        }
        log.fine(String.format("Classification time series size: %d",
                               classificationTimeSeries.size()));
    }

    /**
     * Method process converts the data
     */
    @Override
    public void process() {
        list = new ArrayList<Classification>();
        for (Classification classification : classificationTimeSeries.getall()) {
            //if (!list.contains(classification)) {
            list.add(classification);
            // }
        }
    }

    /**
     * Method postprocess writes out the data to CSV.
     */
    @Override
    public void postprocess() {
        try {
            csv = new FileWriter(out);
            csv.append("#    Timestamp     Score     Threshold     Status   Raw Score   Component   Count\n");
            for (Classification classification : list) {
                csv.append(String.format("     %9d     %5.3f     %6.3f    %6.0f   %9.5f   %9d   %5s%n",
                                         classification.getTimestamp(),
                                         classification.getValue(),
                                         Double.parseDouble(classification.getAttributeValue(StatisticalAnomalyClassifier.THRESHOLD_USED)),
                                         classification.getStatusAsDouble(),
                                         Double.parseDouble(classification.getAttributeValue("raw_score")),
                                         classification.getEventType(),
                                         classification.getAttributeValue("count")));
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                csv.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Method call runs the entire process.
     *
     * @return Result null
     * @throws Exception something went wrong
     */
    @Override
    public Result call() throws Exception {
        preprocess();
        process();
        postprocess();
        return null;
    }
}

package org.bham.app.experiment;

import nu.xom.ParsingException;
import org.bham.aucom.data.Classification;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.StatisticalAnomalyClassificator;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 * @version 1.6
 * @since 2011-09-06
 */
public class ClassificationToCSV implements Experiment {
    private TimeSeries<Classification> classificationTimeSeries;
    private FileWriter csv;
    private ArrayList<Classification> list;
    private File in;
    private File out;
    private final boolean quiet;

    public ClassificationToCSV(final File in, final File out, final boolean quiet) {
        this.in = in;
        this.out = out;
        this.quiet = quiet;

        printBlockMessage(70, "CONVERT TO CSV");
        if (!quiet) System.out.printf("Input: %s\nOutput: %s\n", in.getPath(), out.getPath());
    }

    @Override
    public void preprocess() {
        printBlockMessage(70, "GATHERING TIMER SERIES DATA");
        if (!quiet) System.out.print("Gathering...");
        try {
            classificationTimeSeries = (TimeSeries<Classification>) AucomIO.getInstance().readTimeSeries(in);
        } catch (ParsingException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("done");
    }

    @Override
    public void process() {
        printBlockMessage(70, "PRUNING DATA");
        if (!quiet) System.out.print("Pruning...");
        list = new ArrayList<Classification>();
        for (Classification classification : classificationTimeSeries.getall()) {
            if (!list.contains(classification)) {
                list.add(classification);
            }
        }
        System.out.println("done");
    }

    @Override
    public void postprocess() {
        printBlockMessage(70, "SAVING DATA");
        if (!quiet) System.out.print("Writing...");
        try {
            csv = new FileWriter(out);
            csv.append("#    Timestamp     Score     Threshold     Status\n");
            for (Classification classification : list) {
                csv.append(String.format("     %9d     %5.3f     %6.3f    %6.0f\n",
                                         classification.getTimestamp(),
                                         classification.getValue(),
                                         Double.parseDouble(classification.getAttributeValue(StatisticalAnomalyClassificator.THRESHOLD_USED)),
                                         classification.getStatusAsDouble()));
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
        if (!quiet) System.out.println("done");
    }

    @Override
    public Result call() throws Exception {
        preprocess();
        process();
        postprocess();
        return null;
    }

    private void printBlockMessage(int blockLen, String msg) {
        if (quiet) return;

        int pad = msg.length();
        pad = (blockLen - pad) / 2;

        StringBuilder border = new StringBuilder();
        for (int i = 0; i < blockLen; i++)
            border.append("=");

        StringBuilder padding = new StringBuilder();
        for (int i = 0; i < pad; i++)
            padding.append(" ");

        System.out.println(border);
        System.out.println(padding + msg);
        System.out.println(border);
    }
}

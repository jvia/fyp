package org.bham.experimenter.experiment;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import nu.xom.ParsingException;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.StatisticalAnomalyClassificator;
import org.bham.experimenter.data.Result;

/**
 * @author Jeremiah M. Via <jxv911@cs.bham.ac.uk>
 * @version 1.6
 * @since 2011-09-06
 */
public class ClassificationToCSV implements Experiment {
    private String wd;
    private String name;
    private TimeSeries<Classification> classificationTimeSeries;
    private FileWriter csv;
    private ArrayList<Classification> list;

    public ClassificationToCSV(String wd, String name) {
        printBlockMessage(70, "CONVERT TO CSV");
        System.out.printf("Working Directory: %s\nFile Name: %s\n", wd, name);
        this.wd = wd;
        this.name = name;

    }

    @Override
    public void preprocess() throws IOException, ParsingException, DataAlreadyExistsException {
        printBlockMessage(70, "GATHERING TIMER SERIES DATA");
        System.out.print("Gathering...");
        classificationTimeSeries = (TimeSeries<Classification>) AucomIO.getInstance().readTimeSeries(new File(wd + "/" + name + ".cl"));
        System.out.println("done");
    }

    @Override
    public void process() {
        printBlockMessage(70, "PRUNING DATA");
        System.out.print("Pruning...");
        list = new ArrayList<Classification>();
        for (Classification classification : classificationTimeSeries.getall()) {
            if (!list.contains(classification)) {
                list.add(classification);
            }
        }
        System.out.println("done");
    }

    @Override
    public void postprocess() throws IOException {
        printBlockMessage(70, "SAVING DATA");
        System.out.print("Writing...");
        try {
            csv = new FileWriter(wd + "/" + name + ".csv");
            csv.append("#    Timestamp     Score     Threshold     Status\n");
            for (Classification classification : list) {
                csv.append(String.format("     %9d     %5.3f     %6.3f    %6.0f\n",
                        classification.getTimestamp(),
                        classification.getValue(),
                        Double.parseDouble(classification.getAttributeValue(StatisticalAnomalyClassificator.THRESHOLD_USED)),
                        classification.getStatusAsDouble()));
            }
        } finally {
            csv.close();
        }
        System.out.println("done");
    }

    @Override
    public Result call() throws Exception {
        preprocess();
        process();
        postprocess();
        return null;
    }

    private void printBlockMessage(int blockLen, String msg) {
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

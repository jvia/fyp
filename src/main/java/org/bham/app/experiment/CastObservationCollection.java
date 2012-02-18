package org.bham.app.experiment;

import org.bham.aucom.ActionNotPermittedException;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.system.SystemConnectionFailedException;
import org.bham.aucom.xcfrecorder.Recorder;
import org.bham.system.cast.CastSystemConnection;

import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Runs an experiment with the CAST middleware and saves the results.
 *
 * @author Jeremiah Via <jxv911@cs.bham.ac.uk>
 */
public class CastObservationCollection implements Experiment {

    private int amount;
    private Recorder recorder;
    private CastSystemConnection cast;
    private File out;

    public CastObservationCollection(File out, final int amount) {
        this.out = out;
        this.amount = amount;

        // quick error checking
        if (amount == 0) {
            Logger.getLogger(CastObservationCollection.class.getName()).log(Level.SEVERE, "0 observations to be collected...quitting");
            System.exit(1);
        }

        System.out.printf("Collection %d observations and saving to %s\n", amount, out.getPath());
    }

    /**
     * Prepares the system for a test run.
     * <p/>
     * In this case, it starts up the cast-server and cast-client processes with
     * the parameters supplied in the experiment XML configuration.
     */
    @Override
    public void preprocess() {
        printBlockMessage(70, "CONNECT TO CAST");
        // will block until connection is made
        cast = new CastSystemConnection();
        try {
            System.out.println("Waiting...");
            cast.connect();
            System.out.println("done");
        } catch (SystemConnectionFailedException ex) {
            System.exit(1);
            Logger.getLogger(CastObservationCollection.class.getName()).log(Level.SEVERE, "Could not connect to CAST", ex);
        } catch (ActionNotPermittedException ex) {
            Logger.getLogger(CastObservationCollection.class.getName()).log(Level.SEVERE, "Illegal operation", ex);
        }
        recorder = new Recorder(cast);
    }

    /**
     * Runs the experiment.
     */
    @Override
    public void process() {
        try {
            printBlockMessage(70, "RECORDING DATA");
            recorder.record();
        } catch (ActionFailedException ex) {
            Logger.getLogger(CastObservationCollection.class.getName()).log(Level.SEVERE, null, ex);
        }

        // wait until we have collected enough data
        while (recorder.getTimeSeries().size() < amount) {
            if (recorder.getTimeSeries().size() % 50 == 0)
                printBlockMessage(70, recorder.getTimeSeries().size() + " OBSERVATIONS");
            try {
                Thread.sleep(50);
            } catch (InterruptedException ex) {
                Logger.getLogger(CastObservationCollection.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    /**
     * Handles post-experiment necessities, e.g., shutting down the CAST
     * processes and saving the results.
     */
    @Override
    public void postprocess() {
        // stop the graph recorder & cast connection
        try {
            recorder.stop();
            cast.disconnect();
        } catch (ActionNotPermittedException ex) {
            Logger.getLogger(CastObservationCollection.class.getName()).log(Level.SEVERE, "Error stopping recorder", ex);
        }

        // get the time series and write results to disk
        printBlockMessage(70, "SAVING DATA");
        TimeSeries<Observation> timeSeries = recorder.getTimeSeries();
        System.out.println("Writing to " + out.getPath());
        System.out.println("TIMER SERIES SIZE " + timeSeries.size());

        try {
            if (!out.createNewFile())     {
                out.delete();
                out.createNewFile();
            }

            AucomIO.getInstance().writeTimeSeries(timeSeries, out);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * The order of processing.
     *
     * @return returns nothing
     * @throws Exception
     */
    @Override
    public Result call() throws Exception {
        preprocess();
        process();
        postprocess();
        System.exit(0);
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

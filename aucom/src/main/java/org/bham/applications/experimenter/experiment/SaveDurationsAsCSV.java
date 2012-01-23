/**
 *
 */
package org.bham.applications.experimenter.experiment;

import nu.xom.ParsingException;
import org.bham.applications.experimenter.data.Result;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.encoder.Encoder;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.io.IllegalOutputType;
import org.bham.aucom.data.management.DataAlreadyExistsException;
import org.bham.aucom.data.timeseries.TemporalDurationFeatureTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.graph.AbstractAucomGraph.GraphStatus;
import org.bham.aucom.fts.graph.TemporalDurationFeatureGenerationGraph;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.fts.tranform.TemporalDurationFeatureGenerator;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.main.GraphStatusListener;
import org.bham.aucom.util.Constants;
import org.bham.aucom.util.FileOperator;

import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author rgolombe
 * @version 0.1
 */

public class SaveDurationsAsCSV implements Experiment {
    /**
     *
     */
    private static final String SAVE_FILE = "savefile";
    private final List<File> observationTimeseriesFiles;
    private final List<TimeSeries<Observation>> observationTimeseries;
    private final List<TimeSeries<TemporalDurationFeature>> temporalDurationFeatureTimeseries;
    private final File folder;

    /*
     * @param inFolder
     */
    public SaveDurationsAsCSV(File inFolder) {
        folder = inFolder;
        observationTimeseriesFiles = new ArrayList<File>();
        observationTimeseries = new ArrayList<TimeSeries<Observation>>();
        temporalDurationFeatureTimeseries = new ArrayList<TimeSeries<TemporalDurationFeature>>();
    }

    /*
     *
     * 
     */
    @SuppressWarnings("unchecked")
    @Override
    public void preprocess() throws IOException, ParsingException, DataAlreadyExistsException {
        Logger.getLogger(this.getClass().getCanonicalName()).info("preprocessing");
        FilenameFilter observationFiler = new FilenameFilter() {

            @Override
            public boolean accept(File arg0, String arg1) {
                return arg0 != null && arg1.endsWith(".obs");
            }
        };
        String observationFileNames[] = folder.list(observationFiler);
        System.out.println("getting " + observationFileNames.length + " observationfiles from " + folder);
        for (String observationFileName : observationFileNames) {
            File observationFile = new File(folder.getAbsolutePath() + File.separator + observationFileName);
            System.out.println("adding " + observationFile.getAbsolutePath() + " to observation files");
            TimeSeries<Observation> obsTs = (TimeSeries<Observation>) AucomIO.getInstance().readTimeSeries(observationFile);
            obsTs.addAttribute(SAVE_FILE, FileOperator.getPath(observationFile) + File.separator + FileOperator.getName(observationFile) + ".csv");
            observationTimeseries.add(obsTs);
        }
    }

    /**
     * write down what happens here
     */
    /*
     * (non-Javadoc)
     * @see aucom.experiment.Experiment#process()
     */
    @Override
    public void process() throws IOException {
        final Object wObject = new Object();
        for (int i = 0; i < observationTimeseries.size(); i++) {
            TimeSeries<Observation> obsTs = observationTimeseries.get(i);
            Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, i + ") processing " + obsTs.getAttributeValue(SAVE_FILE));
            TemporalDurationFeatureGenerationGraph graph = new TemporalDurationFeatureGenerationGraph();
            List<Integer> initialIds = getInitialIds(obsTs);
            Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "extracted " + initialIds.size() + " initial ids");
            graph.setGenerator(new TemporalDurationFeatureGenerator(initialIds));
            try {
                Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "starting temporal duration generation graph ");
                graph.start();
                graph.addGraphListener(new GraphStatusListener() {
                    @Override
                    public void graphStatusChanged(GraphStateChangedEvent evt) {
                        if (evt.getPreviousState().equals(GraphStatus.RUNNING) && evt.getNewState().equals(GraphStatus.READY)) {
                            synchronized (wObject) {
                                Logger.getLogger(SaveDurationsAsCSV.this.getClass().getCanonicalName()).log(Level.INFO, "graph finished");
                                wObject.notify();
                                System.out.println("##################### " + evt);
                            }
                        }
                    }
                });
                graph.setInputAndResetGraph(obsTs);
                synchronized (wObject) {
                    Logger.getLogger(this.getClass().getCanonicalName()).log(Level.INFO, "waiting until finished ... ");
                    wObject.wait();
                }
            } catch (ActionFailedException e) {
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            graph.removeAllListeners();
            TimeSeries<TemporalDurationFeature> tdfTs = graph.getOutTimeSeries();
            tdfTs.addAttribute(SAVE_FILE, obsTs.getAttributeValue(SAVE_FILE));
            temporalDurationFeatureTimeseries.add(tdfTs);
            graph.stop();
        }

    }

    /*
     * @param tsObs
     * @return
     */
    private List<Integer> getInitialIds(TimeSeries<Observation> tsObs) {
        Set<Integer> set = new HashSet<Integer>();
        for (Observation obs : tsObs.getall()) {
            set.add(Encoder.getInstance().encode(obs));
        }
        return new ArrayList<Integer>(set);
    }

    /*
     * (non-Javadoc)
     * @see java.util.concurrent.Callable#call()
     */
    @Override
    public Result call() throws Exception {
        if (folder != null && folder.exists() && folder.isDirectory()) {
            preprocess();
            process();
            postprocess();

        } else {
            System.err.println("either training or testing file does not exist. skipping experiment");
        }

        return new Result() {

            @Override
            public String getAsCsvString() {
                return "muh";
            }
        };
    }

    /*
     * (non-Javadoc)
     * @see aucom.experiment.Experiment#postprocess()
     */
    @Override
    public void postprocess() throws IOException {
        for (TimeSeries<TemporalDurationFeature> tdfTs : temporalDurationFeatureTimeseries) {
            if (!tdfTs.containsAttribute(SAVE_FILE)) {
                System.out.println("coudn't save " + tdfTs + " " + SAVE_FILE + " attribute is missing");
            }
            try {
                if (tdfTs.containsAttribute(Constants.FAULT_INDUCED)) {
                    Long induced_timestamp = Long.valueOf(tdfTs.getAttributeValue(Constants.FAULT_INDUCED));
                    File file_before = new File(tdfTs.getAttributeValue("savefile") + "_before");
                    File file_after = new File(tdfTs.getAttributeValue("savefile") + "_after");

                    TimeSeries<TemporalDurationFeature> tdfTs_head = new TemporalDurationFeatureTimeSeries();
                    tdfTs_head.addAll(tdfTs.getHead(induced_timestamp));
                    AucomIO.getInstance().writeTimeSeries(tdfTs_head, file_before, "csv");

                    TimeSeries<TemporalDurationFeature> tdfTs_tail = new TemporalDurationFeatureTimeSeries();
                    tdfTs_tail.addAll(tdfTs.getTail(induced_timestamp));
                    AucomIO.getInstance().writeTimeSeries(tdfTs_tail, file_after, "csv");
                    Logger.getLogger(this.getClass().getCanonicalName()).info("saving durations as csv into two files " + tdfTs.getAttributeValue("savefile"));

                } else {
                    File file = new File(tdfTs.getAttributeValue("savefile"));
                    Logger.getLogger(this.getClass().getCanonicalName()).info("saving durations as csv to " + tdfTs.getAttributeValue("savefile"));
                    AucomIO.getInstance().writeTimeSeries(tdfTs, file, "csv");
                }
            } catch (IllegalOutputType e) {
                e.printStackTrace();
            }
        }

    }

    /*
     * (non-Javadoc)
     * @see java.lang.Object#toString()
     */
    @Override
    public String toString() {
        return "SaveDurationsAsCSV";
    }

}

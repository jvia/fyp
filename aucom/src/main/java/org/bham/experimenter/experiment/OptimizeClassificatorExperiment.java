package org.bham.experimenter.experiment;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.ClassificationTimeSeriesDescriptiveStatistics;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.io.AucomIO;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.util.DataManager;
import org.bham.aucom.diagnoser.AbstractDetector.DetectorStatus;
import org.bham.aucom.diagnoser.DetectorStatusChangedEvent;
import org.bham.aucom.diagnoser.DetectorStatusChangedListener;
import org.bham.aucom.diagnoser.t2gram.detector.T2GramDetector;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer.ClassificatorOptimizer;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer.ClassificatorOptimizerStatusEvent;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer.ClassificatorOptimizerStatusListener;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.util.TimeSeriesNotFoundException;
import org.bham.experimenter.data.Result;

public class OptimizeClassificatorExperiment implements Experiment {
	private static final String CSV_SEP = " ";
	private Result dummyResult = null;
	LinkedList<UUID> testTimeSeriesIds;
	LinkedList<UUID> validationTimeSeriesIds;
	List<TimeSeries<Classification>> outcomes;
	ClassificatorOptimizer optimizer;
	private String name;

	public OptimizeClassificatorExperiment(T2GramDetector inDetector, LinkedList<UUID> inTestTimeSeriesIds, LinkedList<UUID> inValidationTimeSeriesIds, String inName) {
		optimizer = new ClassificatorOptimizer(inDetector);
		testTimeSeriesIds = inTestTimeSeriesIds;
		validationTimeSeriesIds = inValidationTimeSeriesIds;
		outcomes = new ArrayList<TimeSeries<Classification>>();
		name = inName;
	}

	@Override
	public Result call() throws Exception {
		co_experiment("experiment started");
		preprocess();
		co_experiment("preprocessing done");
		process();
		co_experiment("processing done");
		postprocess();
		co_experiment("postprocessing done");
		return this.dummyResult;
	}

	/**
	 * @return Returns true if there are files left for training
	 */

	public boolean isPreprocessFinished() {
		return true;
	}


	private boolean isPostProcessFinished() {
		return true;
	}

	boolean isProcessFinished() {
		return this.validationTimeSeriesIds.isEmpty();
	}

	/**
	 * perform optimization of the classificator for the detector in the
	 * preprocess step
	 */

	@Override
	public void preprocess() {
		Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, "preprocess called");
		final Object waiterObj = new Object();
		optimizer.setTimeseries(getObservationTestTimeSeries());
		try {
			optimizer.addStatusListener(new ClassificatorOptimizerStatusListener() {
				@Override
				public void classificatorOptimizatorStatusChanged(ClassificatorOptimizerStatusEvent st) {
					if (st.isFinished()) {
						synchronized (waiterObj) {
							waiterObj.notify();
						}
					} else {
						co_experiment(st.getNumber() + "/" + st.getTotal());
					}
				}
			});
			optimizer.start();
			synchronized (waiterObj) {
				try {
					waiterObj.wait();
				} catch (InterruptedException exception) {
					exception.printStackTrace();
				}
			}
		} catch (IllegalArgumentException exception) {
			exception.printStackTrace();
		} catch (ActionFailedException exception) {
			exception.printStackTrace();
		}
	}

	protected void co_experiment(String string) {
		Logger.getLogger(this.getClass().getCanonicalName()).info(string);
	}

	@SuppressWarnings("unchecked")
	@Override
	public void process() {
		co_experiment("starting processing");
		final Object waiterObj = new Object();
		optimizer.getDetector().addStatusListener(new DetectorStatusChangedListener() {
			@Override
			public void handleDetectorStatusChangedEvent(DetectorStatusChangedEvent evt) {
				if (evt.getPreviousStatus().equals(DetectorStatus.RUNNING) && evt.getCurrentStatus().equals(DetectorStatus.READY)) {
					synchronized (waiterObj) {
						waiterObj.notify();
					}
				}
			}
		});
		try {
			while (!isProcessFinished()) {
				TimeSeries<Observation> ts = getValidationSequence();
				if (ts == null) {
					continue;
				}
				co_experiment("processing " + ts);
				optimizer.getDetector().start(ts);
				synchronized (waiterObj) {
					waiterObj.wait();
				}
				outcomes.add(optimizer.getDetector().getOutput());
			}
			optimizer.getDetector().removeAllDetectionListeners();
		} catch (ActionFailedException exception) {
			exception.printStackTrace();
		} catch (InterruptedException exception) {
			exception.printStackTrace();
		}
	}

	@SuppressWarnings("unchecked")
	private TimeSeries<Observation> getObservationTestTimeSeries() {
		TimeSeries<Observation> ts;
		try {
			ts = (TimeSeries<Observation>) DataManager.getInstance().getTimeSeriesById(this.testTimeSeriesIds.get(0));
			return ts;
		} catch (TimeSeriesNotFoundException exception) {
			return null;
		}
	}

	private TimeSeries<Observation> getValidationSequence() {
		if (this.validationTimeSeriesIds.isEmpty())
			return null;
		TimeSeries<Observation> ts;
		try {
			ts = (TimeSeries<Observation>) DataManager.getInstance().getTimeSeriesById(this.validationTimeSeriesIds.pop());
			return ts;
		} catch (TimeSeriesNotFoundException exception) {
			return null;
		}
	}

	@Override
	public void postprocess() {
		ClassificationTimeSeriesDescriptiveStatistics statistics = new ClassificationTimeSeriesDescriptiveStatistics();
		for (TimeSeries<Classification> clTimeSeries : outcomes) {
			statistics.setTimeSeries(clTimeSeries);
			saveResults(statistics);
		}

		Logger.getLogger(this.getClass().getCanonicalName()).info("" + optimizer.getDetector().getOutput());
		dummyResult = new Result() {

			@Override
			public String getAsCsvString() {
				return "nonsense";
			}
		};
		String dirPath = "";
		Logger.getLogger(this.getClass().getCanonicalName()).info("calling postprocess");
	}

	private void saveResults(ClassificationTimeSeriesDescriptiveStatistics statistics) {
		String fName = name + "_statistics.txt";
		File filetoSaveTo = new File(AucomIO.getInstance().getCurrentWorkingDirectory().getAbsoluteFile() + File.separator + fName);
		String outputString = "";
		outputString += "FD" + CSV_SEP + "FTR" + CSV_SEP + "FA" + CSV_SEP + "SFAR" + CSV_SEP + "MSCORETOTAL" + CSV_SEP + "MSCOREHEAD" + CSV_SEP + "MSCORETAIL" + CSV_SEP + "VSCORETOTAL" + CSV_SEP + "VSCOREHEAD" + CSV_SEP + "VSCORETAIL\n";
		outputString += Math.ceil(statistics.getTailAnomalyValuePercent()) + CSV_SEP;
		outputString += statistics.getTailAnomalyValuePercent() + CSV_SEP;
		outputString += Math.ceil(statistics.getHeadAnomalyValuePercent()) + CSV_SEP;
		outputString += statistics.getHeadAnomalyValuePercent() + CSV_SEP;
		outputString += statistics.getTotalMeanScoreValue() + CSV_SEP;
		outputString += statistics.getHeadMeanScoreValue() + CSV_SEP;
		outputString += statistics.getTailMeanScoreValue() + CSV_SEP;
		outputString += statistics.getTotalScoreVarianceValue() + CSV_SEP;
		outputString += statistics.getHeadScoreVarianceValue() + CSV_SEP;
		outputString += statistics.getTailScoreVarianceValue() + CSV_SEP;
		outputString += "\n";
		BufferedWriter w;
		try {
			w = new BufferedWriter(new FileWriter(filetoSaveTo));
			w.write(outputString);
			w.flush();
			w.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

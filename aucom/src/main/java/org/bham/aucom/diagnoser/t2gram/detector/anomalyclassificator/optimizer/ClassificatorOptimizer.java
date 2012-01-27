package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer;

import java.awt.Dimension;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JPanel;

import org.bham.aucom.Presentable;
import org.bham.aucom.data.ClassificationTimeSeriesDescriptiveStatistics;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.detector.T2GramDetector;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.StatisticalAnomalyClassificator;
import org.bham.aucom.fts.graph.ObservationToScoreGraph;
import org.bham.aucom.fts.graph.OptimizerGraph;
import org.bham.aucom.fts.graph.AbstractAucomGraph.GraphStatus;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.main.GraphStatusListener;
import org.bham.aucom.util.Constants;

public class ClassificatorOptimizer implements Presentable, GraphStatusListener {
	OptimizerGraph optimizationGraph = null;
	ObservationToScoreGraph obsToScoreGraph = null;
	TimeSeries<Score> scoreTs = null;
	private T2GramDetector detector;
	ClassificatorOptimizationMethod optimizationMethod;

	public ClassificatorOptimizer(T2GramDetector inDetector) {
		setDetector(inDetector);
		obsToScoreGraph = new ObservationToScoreGraph();
		obsToScoreGraph.addGraphListener(this);
		optimizationGraph = new OptimizerGraph();
		optimizationGraph.addGraphListener(this);
		optimizationMethod = new ClassificatorOptimizationMethod();
		optimizationMethod.initializeClassificators();
		Logger.getLogger(this.getClass().getCanonicalName()).log(Level.CONFIG, "classificatorOptimizator initialized");
	}

	public void setModel(Model inModel) {
		obsToScoreGraph.setModel(inModel);
	}

	public void setTimeseries(TimeSeries<Observation> inTs) {
		if (inTs != null) {
			Logger.getLogger(this.getClass().getCanonicalName()).info("optimization set " + inTs.size());
			obsToScoreGraph.setInput(inTs);
		}
	}

	/**
	 * @throws ActionFailedException
	 * 
	 */
	public void start() throws IllegalArgumentException, ActionFailedException {
		Logger.getLogger(this.getClass().getCanonicalName()).info("starting optimization");
		if (scoreTs == null) {
			setModel(getDetector().getModel());
			obsToScoreGraph.start();
			return;
		}
	}

	public void stop() {
		this.optimizationGraph.stop();
		this.optimizationGraph = null;
		if (this.optimizationMethod.getBestClassificator() != null) {
			Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, "stopping optimiziation old: " + detector.getClassificator() + " new " + this.optimizationMethod.getBestClassificator());
			detector.getClassificator().copy(this.optimizationMethod.getBestClassificator());
		}
	}

	/**
	 * @return
	 */
	private boolean isAcceptableFalsePositiveRate() {
	    ClassificationTimeSeriesDescriptiveStatistics statistics = new ClassificationTimeSeriesDescriptiveStatistics(this.optimizationGraph.getOutput());
		boolean isAcceptAble = statistics.getAnomalyValuePercent() <= this.optimizationMethod.getAllowedFalsePositiveRate(); 
		String acceptableStr = "false positive rate is acceptable["+statistics.getAnomalyValuePercent()+" <= "+this.optimizationMethod.getAllowedFalsePositiveRate()+"]: ";
		if (isAcceptAble) {
			co(acceptableStr + "YES");
			Logger.getLogger(this.getClass().getCanonicalName()).info("false positive rate is acceptable");
		} else {
			co(acceptableStr + "NO");
		}
		return isAcceptAble;
	}

	public AnomalyClassificator getBestAnomalyClassificator() {
		return this.optimizationMethod.getBestClassificator();
	}

	public double getBestFalsPositveRate() {
		return this.optimizationMethod.getBestFalsePositiveRate();
	}

	@Override
	public void graphStatusChanged(GraphStateChangedEvent evt) {
		if (evt.getSource().equals(optimizationGraph)) {
			handleOptimizationGraphStatusChangedEvent(evt);
		} else {
			handleObsToScoreGraphStatusChangedEvent(evt);
		}

	}

	private void handleObsToScoreGraphStatusChangedEvent(GraphStateChangedEvent evt) {
		if (isGraphFinishedEvent(evt)) {
			try {
				obsToScoreGraph.stop();
				scoreTs = obsToScoreGraph.getOutput();
				optimizationGraph.setInput(scoreTs);
				optimizationMethod.setCurrentClassificator((StatisticalAnomalyClassificator) optimizationMethod.getClassificatorsToTest().pop().duplicate());
				optimizationGraph.setClassificator(optimizationMethod.getCurrentClassificator());
				optimizationGraph.start();
				Logger.getLogger(this.getClass().getCanonicalName()).info("optimization graph started");
			} catch (ActionFailedException exception) {
				Logger.getLogger(this.getClass().getCanonicalName()).severe(exception.getReason());
			} catch (Exception exception) {
				exception.printStackTrace();

			}

		}
	}

	/**
	 * handles events sent by the optimization graph.
	 * 
	 * @param evt
	 *            the event to handle
	 */
	private void handleOptimizationGraphStatusChangedEvent(GraphStateChangedEvent evt) {
		try {
			if (isGraphFinishedEvent(evt)) {
				if (optimizationGraph.getOutput().size() == 0) {
					Logger.getLogger(this.getClass().getCanonicalName()).log(Level.WARNING, evt + " size of optimization output is 0");
				}
				co((optimizationMethod.getTestedClassificators().size()+1) + "/" + (optimizationMethod.getClassificatorsToTest().size() + optimizationMethod.getTestedClassificators().size()));
				optimizationMethod.getStatistic().setTimeSeries(this.optimizationGraph.getOutput());
				double currentFalsePositive = optimizationMethod.getStatistic().getAnomalyValuePercent();
				double currentPositiveQuadraticDistance = optimizationMethod.getStatistic().getPositiveQuadraticDistance();
				if (isAcceptableFalsePositiveRate()) {
					if (isFirstAnomalyClassificator()) {
						handleFirstAnomalyClassificatorResults(currentFalsePositive, currentPositiveQuadraticDistance);
					} else if (isBetterClassificator(currentPositiveQuadraticDistance)) {
						handleAnomalyClassificatorResults(currentPositiveQuadraticDistance);
					}
				}
				ClassificatorOptimizerStatusEvent event =new ClassificatorOptimizerStatusEvent(this, this.optimizationMethod.getCurrentClassificator(), this.optimizationMethod.getBestClassificator(),
						currentFalsePositive, currentPositiveQuadraticDistance, this.optimizationMethod.getTestedClassificators().size()+1, this.optimizationMethod.getClassificatorsToTest().size()
						+ this.optimizationMethod.getTestedClassificators().size());
				if (this.optimizationMethod.getClassificatorsToTest().isEmpty()) {
					event.markAsFinished();
					co("\nFinished. best " + optimizationMethod.getBestClassificator() + "bfpr " + optimizationMethod.getBestFalsePositiveRate() + "/" + optimizationMethod.getBestPositiveQuadraticDistance() + "\n");
					stop();
					fireClassificatorOptimizerEvent(event);
					return;
				}
				fireClassificatorOptimizerEvent(event);
				Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, "starting next experiment");
				prepareNextAnomalyClassificator();
				this.optimizationGraph.resetOutput();
				this.optimizationGraph.setInput(scoreTs);
				co("\n\n");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	/**
	 * 
	 */
	private void co(String inMsg) {
		Logger.getLogger(this.getClass().getCanonicalName()).log(
				Level.INFO,inMsg);
	}

	/**
	 * 
	 */
	private void prepareNextAnomalyClassificator() {
		optimizationMethod.getTestedClassificators().put(this.optimizationMethod.getCurrentClassificator().duplicate(), optimizationMethod.getStatistic().getAnomalyValuePercent());
		optimizationMethod.getCurrentClassificator().copy(optimizationMethod.getClassificatorsToTest().pop());
		optimizationMethod.getCurrentClassificator().reset();
		Logger.getLogger(this.getClass().getCanonicalName()).info("next classificator to test " + optimizationMethod.getCurrentClassificator());
	}

	/**
	 * @param currentPositiveQuadraticDistance
	 */
	private void handleAnomalyClassificatorResults(double currentPositiveQuadraticDistance) {
		this.optimizationMethod.getBestClassificator().copy(this.optimizationMethod.getCurrentClassificator());
		this.optimizationMethod.setBestFalsePositiveRate(optimizationMethod.getStatistic().getAnomalyValuePercent());
		optimizationMethod.setBestPositiveQuadraticDistance(currentPositiveQuadraticDistance);
	}

	/**
	 * @param currentPositiveQuadraticDistance
	 * @return
	 */
	private boolean isBetterClassificator(double currentPositiveQuadraticDistance) {
		boolean isBetter = currentPositiveQuadraticDistance < optimizationMethod.getBestPositiveQuadraticDistance();
		if (isBetter) {
			co("current classifiactor is better " + currentPositiveQuadraticDistance + " < " + optimizationMethod.getBestPositiveQuadraticDistance());
		} else {
			co("current classifiactor is poorer " + currentPositiveQuadraticDistance + " < " + optimizationMethod.getBestPositiveQuadraticDistance());
		}
		return isBetter;
	}

	/**
	 * @param currentFalsePositive
	 * @param currentPositiveQuadraticDistance
	 */
	private void handleFirstAnomalyClassificatorResults(double currentFalsePositive, double currentPositiveQuadraticDistance) {
		this.optimizationMethod.setBestFalsePositiveRate(currentFalsePositive);
		this.optimizationMethod.setBestClassificator((StatisticalAnomalyClassificator) optimizationMethod.getCurrentClassificator().duplicate());
		this.optimizationMethod.setBestPositiveQuadraticDistance(currentPositiveQuadraticDistance);
	}

	/**
	 * @param evt
	 * @return
	 */
	private boolean isGraphFinishedEvent(GraphStateChangedEvent evt) {
		return evt.getPreviousState().equals(GraphStatus.RUNNING) && evt.getNewState().equals(GraphStatus.READY);
	}

	/*
	 * event handling ---->
	 */
	protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

	public void addStatusListener(ClassificatorOptimizerStatusListener listener) {
		this.listenerList.add(ClassificatorOptimizerStatusListener.class, listener);
	}

	public void removeTimeseriesStatusListener(ClassificatorOptimizerStatusListener listener) {
		this.listenerList.remove(ClassificatorOptimizerStatusListener.class, listener);
	}
	public void removeAllStatusListeners() {
		Object[] listeners = this.listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == ClassificatorOptimizerStatusListener.class) {
				this.listenerList.remove(ClassificatorOptimizerStatusListener.class, (ClassificatorOptimizerStatusListener) listeners[i + 1]);
			}
		}
	}

	// This method is used to fire TrainingStatusChangedEvents
	void fireClassificatorOptimizerEvent(ClassificatorOptimizerStatusEvent evt) {
		Object[] listeners = this.listenerList.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == ClassificatorOptimizerStatusListener.class) {
				((ClassificatorOptimizerStatusListener) listeners[i + 1]).classificatorOptimizatorStatusChanged(evt);
			}
		}
	}

	@Override
	public JPanel getPanel() {
		JPanel p = new OptimizerPanel(this);
		p.setName("ClassificatorOptimizer");
		p.setPreferredSize(new Dimension(Constants.DEFAULTPRESENTABEWIDTH, 100));
		return p;
	}

	public T2GramDetector getDetector() {
		return detector;
	}

	public void setDetector(T2GramDetector detector) {
		this.detector = detector;
	}

	public void copyBestClassificatorToDetector() {
		if (detector != null && optimizationMethod.getBestClassificator() != null) {
			detector.setClassificator(optimizationMethod.getBestClassificator());
		}
	}

	public boolean preconditionsSatisfied() {
		return obsToScoreGraph.preconditionsSatisfied();
	}

	private boolean isFirstAnomalyClassificator() {
		boolean isFirst = optimizationMethod.getBestFalsePositiveRate() == Double.MAX_VALUE;
		if (isFirst) {
			Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, "current anomaly classificator is the first one");
		}
		return isFirst;
	}

}
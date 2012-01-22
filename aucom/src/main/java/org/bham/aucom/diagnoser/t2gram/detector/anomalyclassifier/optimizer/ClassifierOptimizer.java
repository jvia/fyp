package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.optimizer;

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
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.AnomalyClassifier;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.StatisticalAnomalyClassifier;
import org.bham.aucom.fts.graph.AbstractAucomGraph.GraphStatus;
import org.bham.aucom.fts.graph.ObservationToScoreGraph;
import org.bham.aucom.fts.graph.OptimizerGraph;
import org.bham.aucom.fts.source.ActionFailedException;
import org.bham.aucom.main.GraphStateChangedEvent;
import org.bham.aucom.main.GraphStatusListener;
import org.bham.aucom.util.Constants;


public class ClassifierOptimizer implements Presentable, GraphStatusListener {
	OptimizerGraph optimizationGraph = null;
	ObservationToScoreGraph obsToScoreGraph = null;
	TimeSeries<Score> scoreTs = null;
	private T2GramDetector detector;
	ClassifierOptimizationMethod optimizationMethod;

	public ClassifierOptimizer(T2GramDetector inDetector) {
		setDetector(inDetector);
		obsToScoreGraph = new ObservationToScoreGraph();
		obsToScoreGraph.addGraphListener(this);
		optimizationGraph = new OptimizerGraph();
		optimizationGraph.addGraphListener(this);
		optimizationMethod = new ClassifierOptimizationMethod();
		optimizationMethod.initializeClassifiers();
		Logger.getLogger(this.getClass().getCanonicalName()).log(Level.CONFIG, "classifierOptimizer initialized");
	}

	public void setModel(Model inModel) {
		obsToScoreGraph.setModel(inModel);
	}

	public void setTimeSeries(TimeSeries<Observation> inTs) {
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
		if (this.optimizationMethod.getBestClassifier() != null) {
			Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, "stopping optimization old: " + detector.getClassificator() + " new " + this.optimizationMethod.getBestClassifier());
			detector.getClassificator().copy(this.optimizationMethod.getBestClassifier());
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

	public AnomalyClassifier getBestAnomalyClassifier() {
		return this.optimizationMethod.getBestClassifier();
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
				optimizationMethod.setCurrentClassifier((StatisticalAnomalyClassifier) optimizationMethod.getClassifiersToTest().pop().duplicate());
				optimizationGraph.setClassificator(optimizationMethod.getCurrentClassifier());
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
				co((optimizationMethod.getTestedClassifiers().size()+1) + "/" + (optimizationMethod.getClassifiersToTest().size() + optimizationMethod.getTestedClassifiers().size()));
				optimizationMethod.getStatistic().setTimeSeries(this.optimizationGraph.getOutput());
				double currentFalsePositive = optimizationMethod.getStatistic().getAnomalyValuePercent();
				double currentPositiveQuadraticDistance = optimizationMethod.getStatistic().getPositiveQuadraticDistance();
				if (isAcceptableFalsePositiveRate()) {
					if (isFirstAnomalyClassifier()) {
						handleFirstAnomalyClassifierResults(currentFalsePositive, currentPositiveQuadraticDistance);
					} else if (isBetterClassifier(currentPositiveQuadraticDistance)) {
						handleAnomalyClassifierResults(currentPositiveQuadraticDistance);
					}
				}
				ClassifierOptimizerStatusEvent event =new ClassifierOptimizerStatusEvent(this, this.optimizationMethod.getCurrentClassifier(), this.optimizationMethod.getBestClassifier(),
						currentFalsePositive, currentPositiveQuadraticDistance, this.optimizationMethod.getTestedClassifiers().size()+1, this.optimizationMethod.getClassifiersToTest().size()
						+ this.optimizationMethod.getTestedClassifiers().size());
				if (this.optimizationMethod.getClassifiersToTest().isEmpty()) {
					event.markAsFinished();
					co("\nFinished. best " + optimizationMethod.getBestClassifier() + "bfpr " + optimizationMethod.getBestFalsePositiveRate() + "/" + optimizationMethod.getBestPositiveQuadraticDistance() + "\n");
					stop();
					fireClassificatorOptimizerEvent(event);
					return;
				}
				fireClassificatorOptimizerEvent(event);
				Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, "starting next experiment");
				prepareNextAnomalyClassifier();
				this.optimizationGraph.resetOutput();
				this.optimizationGraph.setInput(scoreTs);
				co("\n\n");
			}
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	private void co(String inMsg) {
		Logger.getLogger(this.getClass().getCanonicalName()).log(
				Level.INFO,inMsg);
	}

	private void prepareNextAnomalyClassifier() {
		optimizationMethod.getTestedClassifiers().put(this.optimizationMethod.getCurrentClassifier().duplicate(), Double.valueOf(optimizationMethod.getStatistic().getAnomalyValuePercent()));
		optimizationMethod.getCurrentClassifier().copy(optimizationMethod.getClassifiersToTest().pop());
		optimizationMethod.getCurrentClassifier().reset();
		Logger.getLogger(this.getClass().getCanonicalName()).info("next classifier to test " + optimizationMethod.getCurrentClassifier());
	}

	private void handleAnomalyClassifierResults(double currentPositiveQuadraticDistance) {
		this.optimizationMethod.getBestClassifier().copy(this.optimizationMethod.getCurrentClassifier());
		this.optimizationMethod.setBestFalsePositiveRate(optimizationMethod.getStatistic().getAnomalyValuePercent());
		optimizationMethod.setBestPositiveQuadraticDistance(currentPositiveQuadraticDistance);
	}

	private boolean isBetterClassifier(double currentPositiveQuadraticDistance) {
		boolean isBetter = currentPositiveQuadraticDistance < optimizationMethod.getBestPositiveQuadraticDistance();
		if (isBetter) {
			co("current classifier is better " + currentPositiveQuadraticDistance + " < " + optimizationMethod.getBestPositiveQuadraticDistance());
		} else {
			co("current classifier is poorer " + currentPositiveQuadraticDistance + " < " + optimizationMethod.getBestPositiveQuadraticDistance());
		}
		return isBetter;
	}

	private void handleFirstAnomalyClassifierResults(double currentFalsePositive, double currentPositiveQuadraticDistance) {
		this.optimizationMethod.setBestFalsePositiveRate(currentFalsePositive);
		this.optimizationMethod.setBestClassifier((StatisticalAnomalyClassifier) optimizationMethod.getCurrentClassifier().duplicate());
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

	public void addStatusListener(ClassifierOptimizerStatusListener listener) {
		this.listenerList.add(ClassifierOptimizerStatusListener.class, listener);
	}

	public void removeTimeseriesStatusListener(ClassifierOptimizerStatusListener listener) {
		this.listenerList.remove(ClassifierOptimizerStatusListener.class, listener);
	}
	public void removeAllStatusListeners() {
		Object[] listeners = this.listenerList.getListenerList();
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == ClassifierOptimizerStatusListener.class) {
				this.listenerList.remove(ClassifierOptimizerStatusListener.class, (ClassifierOptimizerStatusListener) listeners[i + 1]);
			}
		}
	}

	// This method is used to fire TrainingStatusChangedEvents
	void fireClassificatorOptimizerEvent(ClassifierOptimizerStatusEvent evt) {
		Object[] listeners = this.listenerList.getListenerList();
		// Each listener occupies two elements - the first is the listener class
		// and the second is the listener instance
		for (int i = 0; i < listeners.length; i += 2) {
			if (listeners[i] == ClassifierOptimizerStatusListener.class) {
				((ClassifierOptimizerStatusListener) listeners[i + 1]).classifierOptimizerStatusChanged(evt);
			}
		}
	}

	@Override
	public JPanel getPanel() {
		JPanel p = new OptimizerPanel(this);
		p.setName("ClassifierOptimizer");
		p.setPreferredSize(new Dimension(Constants.DEFAULTPRESENTABEWIDTH, 100));
		return p;
	}

	public T2GramDetector getDetector() {
		return detector;
	}

	public void setDetector(T2GramDetector detector) {
		this.detector = detector;
	}

	public void copyBestClassifierToDetector() {
		if (detector != null && optimizationMethod.getBestClassifier() != null) {
			detector.setClassificator(optimizationMethod.getBestClassifier());
		}
	}

	public boolean preconditionsSatisfied() {
		return obsToScoreGraph.preconditionsSatisfied();
	}

	private boolean isFirstAnomalyClassifier() {
		boolean isFirst = optimizationMethod.getBestFalsePositiveRate() == Double.MAX_VALUE;
		if (isFirst) {
			Logger.getLogger(this.getClass().getCanonicalName()).log(Level.FINE, "current anomaly classifier is the first one");
		}
		return isFirst;
	}

}
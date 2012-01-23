package org.bham.aucom.fts.graph;

import java.util.logging.Logger;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.AnomalyClassifier;
import org.bham.aucom.fts.sink.TimeSeriesSink;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.Classify;


public class OptimizerGraph extends AbstractAucomGraph {
	private static final long serialVersionUID = 0L;
	private TimeSeriesSource<Score> source;
	private Classify classify;
	private TimeSeriesSink<Classification> sink;

	public OptimizerGraph() {
		super("OptimizerGraph");
		initGraph();
	}

	@Override
	protected void initGraph() {

		// source
		this.source = new TimeSeriesSource<Score>("scoreSource");
		this.source.addSourceStatusListener(this);

		// node
		this.classify = new Classify();

		// sink
		this.sink = new TimeSeriesSink<Classification>(new ClassificationTimeSeries());
		this.sink.addSinkStatusListener(this);

		// connection
		this.graph.connect(source, classify);
		this.graph.connect(classify, sink);
	}

	@Override
	protected void cleanUp() {
		this.source.setInput(null);
		this.classify.setClassifier(null);
		this.sink.setOutput(null);
	}

	@Override
	public boolean preconditionsSatisfied() {
		return classify.getClassifier() != null;
	}

	public void setInput(TimeSeries<Score> input) {
		try {
			if (input != null && input.size() == 0) {
				Logger.getLogger(this.getClass().getCanonicalName()).severe(this.getClass().getName() + " warning: input queue is empty");
			}
			this.source.setInput(input);
			Logger.getLogger(this.getClass().getCanonicalName()).info("set input to " + input);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
	}

	public AnomalyClassifier getClassificator() {
		return classify.getClassifier();
	}

	public void setClassificator(AnomalyClassifier inAc) {
		classify.setClassifier(inAc);
	}

	public TimeSeries<Classification> getOutput() {
		return this.sink.getOutput();
	}

	public void resetOutput() {
		if (this.sink.getOutput() != null) {
			this.sink.getOutput().clear();
		}
	}

	@Override
	protected String getReason() {
		if (classify.getClassifier() == null) {
			return "anomaly classificator not set";
		}
		return "not ready for unknown reason";
	}
}

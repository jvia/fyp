package org.bham.aucom.fts.graph;

import java.util.logging.Logger;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;
import org.bham.aucom.fts.sink.TimeSeriesSink;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.Classificate;

public class OptimizerGraph extends AbstractAucomGraph {
	private static final long serialVersionUID = 0L;
	TimeSeriesSource<Score> source;
	Classificate classificate;
	TimeSeriesSink<Classification> sink;

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
		this.classificate = new Classificate();

		// sink
		this.sink = new TimeSeriesSink<Classification>(new ClassificationTimeSeries());
		this.sink.addSinkStatusListener(this);

		// connection
		this.graph.connect(source, classificate);
		this.graph.connect(classificate, sink);
	}

	@Override
	protected void cleanUp() {
		this.source.setInput(null);
		this.classificate.setClassificator(null);
		this.sink.setOutput(null);
	}

	@Override
	public boolean preconditionsSatisfied() {
		return classificate.getClassificator() != null;
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

	public AnomalyClassificator getClassificator() {
		return classificate.getClassificator();
	}

	public void setClassificator(AnomalyClassificator inAc) {
		classificate.setClassificator(inAc);
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
		if (classificate.getClassificator() == null) {
			return "anomaly classificator not set";
		}
		return "not ready for unknown reason";
	}
}

package org.bham.aucom.fts.tranform;

import java.util.logging.Logger;

import org.bham.aucom.data.Classification;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.SystemFaultStatus;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;


public class Classificate extends AbstractAucomTranformNode<Score, Classification> {
	private AnomalyClassificator classificator = null;

	public Classificate(AnomalyClassificator inThreshold) {
		super("ClassificateScore");
		setClassificator(inThreshold);
	}

	public Classificate() {
		super("ClassificateScore");
	}

	private Classification decide(Score in) {

		Classification cl = null;
		if (getClassificator().satisfies(in)) {
			cl = new Classification(in, SystemFaultStatus.NORMAL);
		} else {
			cl = new Classification(in, SystemFaultStatus.ABNORMAL);
		}
		Logger.getLogger(this.getClass().getCanonicalName()).info(in.toString() + " classificated as " + cl.getStatus());
		return cl;
	}

	@Override
	protected Classification iTransform(Score arg0) throws Exception {
		try {
			Logger.getLogger(this.getClass().getCanonicalName()).info("classificating score " + arg0);
			return decide(arg0);
		} catch (Exception exception) {
			exception.printStackTrace();
		}
		return null;
	}

	public void setClassificator(AnomalyClassificator threshold) {
		this.classificator = threshold;
	}

	public AnomalyClassificator getClassificator() {
		return this.classificator;
	}

	public void reset() {
		if (classificator != null) {
			classificator.reset();
		}
	}
}

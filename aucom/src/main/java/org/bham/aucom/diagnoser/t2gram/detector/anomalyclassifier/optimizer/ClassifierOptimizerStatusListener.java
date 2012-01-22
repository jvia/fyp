package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.optimizer;

import java.util.EventListener;

public interface ClassifierOptimizerStatusListener extends EventListener {
	public void classifierOptimizerStatusChanged(ClassifierOptimizerStatusEvent status);
}

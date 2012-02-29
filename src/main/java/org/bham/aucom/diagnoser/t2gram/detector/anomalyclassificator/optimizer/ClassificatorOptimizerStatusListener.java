package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.optimizer;

import java.util.EventListener;

public interface ClassificatorOptimizerStatusListener extends EventListener {
    public void classificatorOptimizatorStatusChanged(ClassificatorOptimizerStatusEvent status);
}

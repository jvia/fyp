package org.bham.aucom.gui.threshold;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassifier;

import javax.swing.*;

public abstract class AbstractCreateAnomalyClassificatorPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public abstract AnomalyClassifier getAnomalyClassificator() throws Exception;
}

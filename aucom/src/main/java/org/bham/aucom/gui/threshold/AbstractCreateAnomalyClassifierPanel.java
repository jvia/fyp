package org.bham.aucom.gui.threshold;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.AnomalyClassifier;

import javax.swing.*;


public abstract class AbstractCreateAnomalyClassifierPanel extends JPanel {
    private static final long serialVersionUID = 1L;

    public abstract AnomalyClassifier getAnomalyClassifier() throws Exception;
}

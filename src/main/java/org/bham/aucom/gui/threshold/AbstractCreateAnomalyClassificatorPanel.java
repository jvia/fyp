package org.bham.aucom.gui.threshold;

import javax.swing.JPanel;

import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassificator;

public abstract class AbstractCreateAnomalyClassificatorPanel extends JPanel {
	private static final long serialVersionUID = 1L;
	public abstract AnomalyClassificator getAnomalyClassificator() throws Exception;
}

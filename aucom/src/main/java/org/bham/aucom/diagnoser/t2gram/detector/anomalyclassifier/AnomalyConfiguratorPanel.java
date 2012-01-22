package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier;

import javax.swing.JPanel;

public class AnomalyConfiguratorPanel extends JPanel {
	private static final long serialVersionUID = 0L;
	private AnomalyConfigurator aCfg;

	public AnomalyConfigurator getaCfg() {
		return aCfg;
	}

	public void setAnomalyConfigurator(AnomalyConfigurator aCfg) {
		this.aCfg = aCfg;
	}
}

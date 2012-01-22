package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier;

import org.bham.aucom.data.ConfigurationFailedException;

public interface AnomalyConfigurator{
	public void configure(AnomalyClassifier in) throws ConfigurationFailedException;
}

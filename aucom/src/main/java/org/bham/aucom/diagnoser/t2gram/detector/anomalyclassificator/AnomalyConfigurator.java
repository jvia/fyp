package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import org.bham.aucom.data.ConfigurationFailedException;

public interface AnomalyConfigurator{
	public void configure(AnomalyClassificator in) throws ConfigurationFailedException;
}

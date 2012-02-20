package org.bham.aucom.data.timeseries;

import org.jfree.data.xy.XYSeries;

public class ScoreSeries extends XYSeries {
	private static final long serialVersionUID = 0L;

	private String generatorName;
	private String dataSourceName;
	public ScoreSeries(Comparable key, String generatorName, String dataSourceName) {
		super(key);
		this.generatorName = generatorName;
		this.dataSourceName = dataSourceName;
	}

}

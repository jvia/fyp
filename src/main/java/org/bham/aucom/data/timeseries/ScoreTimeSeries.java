package org.bham.aucom.data.timeseries;

import org.bham.aucom.data.Score;

public class ScoreTimeSeries extends TimeSeries<Score> {
   
       private static final long serialVersionUID = 0L;
    
	public ScoreTimeSeries() {
		setType(TimeSeriesType.score);
	}

}

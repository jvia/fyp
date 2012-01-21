package org.bham.aucom.fts.graph;

import java.util.HashMap;

import net.sf.xcf.fts.engine.Graph;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.timeseries.DataTypeTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.fts.source.TimeSeriesSource;
import org.bham.aucom.fts.tranform.CalculateDuration;
import org.bham.aucom.fts.tranform.CountDifferentClasses;
import org.bham.aucom.fts.tranform.CountEvents;
import org.bham.aucom.fts.tranform.CountFrequencyForClasses;
import org.bham.aucom.fts.tranform.EncodeData;



public class DataSequenceStatisticsGraph extends Graph{
	HashMap<Integer, Integer> classCounter;
	public DataSequenceStatisticsGraph(TimeSeries<Observation> sequence) {
		TimeSeriesSource<Observation> source = new TimeSeriesSource<Observation>(sequence,"statisticsSource" );
		TimeSeries<DataType> out = new DataTypeTimeSeries();
		out.setGeneratedFrom(sequence.getId());
		EncodeData classify = new EncodeData();
		CountEvents countEvents = new CountEvents(out);
		CountDifferentClasses countClasses = new CountDifferentClasses(out);
		CalculateDuration calculateDuration = new CalculateDuration(out);
		CountFrequencyForClasses countFrequency = new CountFrequencyForClasses(out);
		this.connect(source, classify);
		this.connect(classify, countEvents);
		this.connect(countEvents, countClasses);
		this.connect(countClasses, calculateDuration);
		this.connect(calculateDuration, countFrequency);
	}
}

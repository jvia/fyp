package org.bham.aucom.util;

import java.util.ArrayList;

import javax.swing.AbstractListModel;

import org.bham.aucom.data.timeseries.DataTypeTimeSeries;
import org.bham.aucom.gui.charts.ProbabilityChartFrame;


public class ScoreFrameListModel extends AbstractListModel {
	ArrayList<ProbabilityChartFrame> data;
	public ScoreFrameListModel() {
		data = new ArrayList<ProbabilityChartFrame>();
	}
	private static final long serialVersionUID = 8720591898683345358L;
	
	public void add(ProbabilityChartFrame element) {
		data.add(element);
		this.fireIntervalAdded(element, data.indexOf(element), data.indexOf(element));
	}
	public void delete(ProbabilityChartFrame element){
		if(data.contains(element))
			data.remove(element);
		this.fireIntervalRemoved(element, data.indexOf(element), data.indexOf(element));
	}
	@Override
	public Object getElementAt(int arg0) {
		return data.get(arg0);
	}
	public ProbabilityChartFrame get(String title){
		for(ProbabilityChartFrame frame: data){
			if(frame.getName().equals(title))
				return frame;
		}
		return null;
	}
	public ArrayList<ProbabilityChartFrame> getAll(){
		return data;
	}
	@Override
	public int getSize() {
		return data.size();
	}

	public boolean containsKey(ProbabilityChartFrame element) {
		return data.contains(element);
	}
	public ArrayList<ProbabilityChartFrame> getFramesForScoreSequence(DataTypeTimeSeries s){
		ArrayList<ProbabilityChartFrame> out = new ArrayList<ProbabilityChartFrame>();
		for(ProbabilityChartFrame frame : data){
		}
                return null;
	}
}

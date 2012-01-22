package org.bham.aucom.fts.graph;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.timeseries.TimeSeries;


public class DataSequenceStatistics {
	//TODO fix DataSequenceStatistics 
	TimeSeries<DataType> sequence;
	ArrayList<Integer> classIds = new ArrayList<Integer>();
	HashMap<String, Integer> sources = new HashMap<String, Integer>();
	HashMap<String, Integer> sourcesFrequencies = new HashMap<String, Integer>();
	ArrayList<String> types = new ArrayList<String>();
	ArrayList<String> scopes = new ArrayList<String>();
	long duration = 0l;

	public DataSequenceStatistics(TimeSeries<DataType> sequence) {
		this.sequence = sequence;
		this.duration = this.sequence.get(this.sequence.size() - 1).getTimestamp() - this.sequence.get(0).getTimestamp();
	}



	public long getDurationInSeconds() {
        return this.duration / 1000;
	}

	public String getDurationAsString() {
		DecimalFormat formatter = new DecimalFormat("00");
		long tmp;
		long hours = this.duration / (1000 * 60 * 60);
		tmp = this.duration - hours * (1000 * 60 * 60);
		long minutes = tmp / (1000 * 60);
		tmp = tmp - minutes * (1000 * 60);
		long seconds = tmp / (1000);
		tmp = tmp - seconds * 1000;
		long ms = this.duration / (1000);
		return formatter.format(hours) + ":" + formatter.format(minutes) + ":" + formatter.format(seconds) + ":" + formatter.format(ms);
	}

	public int getNumberClasses() {
		return this.classIds.size();
	}

	public ArrayList<Integer> getClassIds() {
		return this.classIds;
	}

	public Set<String> getSources() {
		return this.sources.keySet();
	}

	public int getNumberSources() {
		return this.sources.size();
	}

	public int getCountForSources(String source) {
		if (this.sources.containsKey(source))
			return this.sources.get(source);
		return 0;
	}

	public double getCountFrequencyForSource(String source) {
		long durationInSeconds = getDurationInSeconds();
		if (this.sources.containsKey(source))
			return ((double) this.sources.get(source).intValue()) / ((double) durationInSeconds);
		return 0.0d;
	}

	public ArrayList<String> getScopes() {
		return this.scopes;
	}

	public int getNumberScopes() {
		return this.scopes.size();
	}

	public ArrayList<String> getTypes() {
		return this.types;
	}

	public int getNumberTypes() {
		return this.types.size();
	}

	public int getNumberElements() {
		return this.sequence.size();
	}

}
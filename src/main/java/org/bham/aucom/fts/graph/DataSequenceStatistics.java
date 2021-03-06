package org.bham.aucom.fts.graph;

import org.bham.aucom.data.DataType;
import org.bham.aucom.data.timeseries.TimeSeries;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Set;

public class DataSequenceStatistics {
    //TODO fix DataSequenceStatistics
    TimeSeries<DataType> sequence;
    ArrayList<Integer> classIds = new ArrayList<Integer>();
    HashMap<String, Integer> sources = new HashMap<String, Integer>();
//    HashMap<String, Integer> sourcesFrequencies = new HashMap<String, Integer>();
    ArrayList<String> types = new ArrayList<String>();
    ArrayList<String> scopes = new ArrayList<String>();
    long duration = 0l;

    public DataSequenceStatistics(TimeSeries<DataType> sequence) {
        this.sequence = sequence;
        this.duration = this.sequence.get(this.sequence.size() - 1).getTimestamp() - this.sequence.get(0).getTimestamp();
        calcIds();
        calcSources();
        calcTypes();
        calcScopes();
        calcFrequenciesinSlidingWinows(100);

    }

    private void calcFrequenciesinSlidingWinows(int slidingWinowSize) {

    }

    public long getDurationInSeconds() {
        long durationInSeconds = this.duration / 1000;
        return durationInSeconds;
    }

    public String getDurationAsString() {
        DecimalFormat formatter = new DecimalFormat("00");
        long tmp = 0l;
        long hours = this.duration / (1000 * 60 * 60);
        tmp = this.duration - hours * (1000 * 60 * 60);
        long minutes = tmp / (1000 * 60);
        tmp = tmp - minutes * (1000 * 60);
        long seconds = tmp / (1000);
        tmp = tmp - seconds * 1000;
        long ms = this.duration / (1000);
        return formatter.format(hours) + ":" + formatter.format(minutes) + ":" + formatter.format(seconds) + ":" + formatter.format(ms);
    }

    /**
     *
     */
    private void calcScopes() {
//for (DataType d : this.sequence.getall()) {
//if (!this.scopes.contains(d.getFeatures().getValueForFeatureWithName("scope")))
//this.scopes.add(d.getValueForFeatureWithName("scope"));
//}
    }

    /**
     *
     */
    private void calcTypes() {
//for (AbstractData d : this.sequence.getall()) {
//if (d.containsFeatureWithName("scope")) {
//if (!this.types.contains(d.getValueForFeatureWithName("type")))
//this.types.add(d.getValueForFeatureWithName("type"));
//}
//}
    }

    /**
     *
     */
    private void calcSources() {
//for (AbstractData d : this.sequence.getall()) {
//if (d.containsFeatureWithName("scope")) {
//if (!this.sources.containsKey(d.getValueForFeatureWithName("type"))) {
//this.sources.put(d.getValueForFeatureWithName("type"), Integer.valueOf(0));
//} else {
//int count = this.sources.get(d.getValueForFeatureWithName("type")).intValue();
//this.sources.put(d.getValueForFeatureWithName("type"), Integer.valueOf(count + 1));
//}
//}}
    }

    private void calcIds() {
//HashSet<Integer> hashSet = new HashSet<Integer>();
//for (AbstractData d : this.sequence.getall()) {
//if(d instanceof PointData){
//d.setEventTypeId(SourceScopeTypeEncoder.getInstance().convertEncoding((PointData)d));
//}
//hashSet.add(Integer.valueOf(d.getEventTypeId()));
//}
//for (Integer id : hashSet) {
//this.classIds.add(id);
//}
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
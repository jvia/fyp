package org.bham.aucom.data.util;

import java.awt.Dimension;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.logging.Logger;

import javax.swing.JPanel;

import junit.framework.Assert;
import nu.xom.Element;
import org.bham.aucom.Presentable;
import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.Classification;
import org.bham.aucom.data.DataType;
import org.bham.aucom.data.DomainFeature;
import org.bham.aucom.data.Observation;
import org.bham.aucom.data.Score;
import org.bham.aucom.data.TemporalDurationFeature;
import org.bham.aucom.data.TemporalProbabilityFeature;
import org.bham.aucom.data.timeseries.ClassificationTimeSeries;
import org.bham.aucom.data.timeseries.DataTypeTimeSeries;
import org.bham.aucom.data.timeseries.ScoreTimeSeries;
import org.bham.aucom.data.timeseries.TemporalDurationFeatureTimeSeries;
import org.bham.aucom.data.timeseries.TemporalProbabilityFeatureTimeSeries;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesType;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.util.Constants;
import org.bham.aucom.util.TimeSeriesNotFoundException;

public class DataManager implements Presentable{
	
	DataModel dataModel;
	private static DataManager manager;
	private DataManagerPanel panel;
	

	private DataManager() {
		this.dataModel = DataModel.getInstance();
	}
	
	public TimeSeries<?> createResultTimeSeriesFor(TimeSeries<?> timeSeries) {
		Assert.assertNotNull(timeSeries);
		TimeSeries<?> generatedTimeSeries = null;
		switch (timeSeries.getType()) {
		case obs:
			generatedTimeSeries = new DataTypeTimeSeries();
			generatedTimeSeries.setGeneratedFrom(timeSeries.getId());
			break;
		case dtp:
			generatedTimeSeries = new TemporalDurationFeatureTimeSeries();
			generatedTimeSeries.setGeneratedFrom(timeSeries.getId());
			break;
		case tdf:
			generatedTimeSeries = new TemporalProbabilityFeatureTimeSeries();
			generatedTimeSeries.setGeneratedFrom(timeSeries.getId());
			break;
		case tpf:
			generatedTimeSeries = new ScoreTimeSeries();
			generatedTimeSeries.setGeneratedFrom(timeSeries.getId());
			break;
		case score: 
			generatedTimeSeries = new ClassificationTimeSeries();
			generatedTimeSeries.setGeneratedFrom(timeSeries.getId());
			break;
		case cl:
			break;
		}
		this.dataModel.addTimeSeries(generatedTimeSeries);
		return generatedTimeSeries;
	}

	public void reset() {
		this.dataModel.clear();
	}

	public TimeSeries<?> getTimeSeriesById(UUID id) throws TimeSeriesNotFoundException {
		return this.dataModel.getTimeSeriesById(id);
	}

	public void addTimeSeries(TimeSeries<?> in) {
		Logger.getLogger(this.getClass().getCanonicalName()).info(" adding timeseries "+ in.getId() + " " +  in.getType());
		this.dataModel.addTimeSeries(in);
	}
	public Model getFaultDetectionModelById(UUID id) {
		return this.dataModel.getModels().get(id);
	}

	public TimeSeriesType getTimeSeriesTypeFor(AbstractData data){
		TimeSeriesType type = null;
		if (data.getClass().equals(Observation.class)) {
			type = TimeSeriesType.obs; 
		} else if (data.getClass().equals(DataType.class)) {
			type = TimeSeriesType.dtp; 
		} else if (data.getClass().equals(Score.class)) {
			type = TimeSeriesType.score; 
		} else if (data.getClass().equals(Classification.class)) {
			type = TimeSeriesType.cl; 
		} else if (data.getClass().equals(TemporalDurationFeature.class)) {
			type = TimeSeriesType.tdf; 
		} else if (data.getClass().equals(TemporalProbabilityFeature.class)) {
			type = TimeSeriesType.tpf; 
		}
		return type;
	}
	public static DataManager getInstance() {
		if (manager == null)
			manager = new DataManager();
		return manager;
	}

	public boolean containsTimeSeriesWithID(UUID id) {
		return this.dataModel.containsTimeSeriesWithId(id);
	}

	@SuppressWarnings("unchecked")
	public <T extends AbstractData> List<TimeSeries<T>> getTimeSeriesOfType(Class<T> classToSelectAfter) {
		List<TimeSeries<T>> out = new ArrayList<TimeSeries<T>>();
		for(TimeSeries<? extends AbstractData> timeSeries: this.dataModel.getTimeseries().values()){
			if(timeSeries.isOfType(classToSelectAfter))
				out.add((TimeSeries<T>)timeSeries);
		}
		return out;
	}
	public TimeSeries<Score> deriveScoreSequenceFrom(TimeSeries<Score> sequenceToDeriveFrom, String nameOfDerivedSequence) {
		TimeSeries<Score> derivedSequence = null;
		return derivedSequence;
	}


	// TODO need functions for other test elements, too
	public DataType getTestItemDataType(){
		ArrayList<DomainFeature> features = new ArrayList<DomainFeature>();
		features.add(new DomainFeature("scope", "a"));
		features.add(new DomainFeature("type", "b"));
		features.add(new DomainFeature("source", "c"));
		return new DataType(features,1, getTestObservation());
	}

	public Observation getTestObservation() {
		Element e = new Element("<ROBOTPOSITION source=\"RDS\" eventType=\"INSERT\" memoryName=\"publisher\" dataSetId=\"XcfEventToDocument\" timestamp=\"1276070723418\" dataSetIndex=\"0\">" +
  "<xcf:metadata xmlns:xcf=\"http://xcf.sf.net\">" +
    "<timing>"+
       "<ts key=\"xcf:cre\" src=\"OdometryData\" ms=\"1276070723418\" ns=\"195068\" dateTime=\"xs:dateTime\" />"+
       "<ts key=\"xcf:pub\" src=\"OdometryData\" ms=\"1276070723418\" ns=\"326904\" dateTime=\"xs:dateTime\" />"+
    "</timing>"+
  "</xcf:metadata>"+
"<GENERATOR>RDS</GENERATOR><TIMESTAMP><INSERTED value=\"1276070723413\" /><UPDATED value=\"1276070723413\" /></TIMESTAMP><READTIME><SECONDS value=\"1276070723\" /><MICROSECONDS value=\"370528\" /></READTIME><POSITION kind=\"absolute\" ref=\"world\" theta=\"-3.124139308929443\" x=\"-3.809863328933716\" y=\"-5.656352996826172\" /></ROBOTPOSITION>\")");
		return new Observation(e, System.currentTimeMillis());
	}

	@Override
	public JPanel getPanel() {
		if(panel == null){
			panel = new DataManagerPanel(this);
			panel.setName("DataManager");
			panel.setPreferredSize(new Dimension(Constants.DEFAULTPRESENTABEWIDTH, 100));
		}
		return panel;
	}
	public void addDataModelStatusListener(DataModelStatusListener listener){
		dataModel.addModelDataStatusListener(listener);
	}
	public void removeDataModelStatusListener(DataModelStatusListener listener){
		dataModel.removeModelDataStatusListener(listener);
	}

	@SuppressWarnings("unchecked")
	public List<TimeSeries<Observation>> getAllObservationTimeSeries() {
		List<TimeSeries<Observation>> l = new ArrayList<TimeSeries<Observation>>();
		for(TimeSeries<?> ts:  dataModel.getTimeseries().values()){
			if(ts.getType().equals(TimeSeriesType.obs)){
				l.add((TimeSeries<Observation>)ts);
			}
		}
		return l;
	}
}
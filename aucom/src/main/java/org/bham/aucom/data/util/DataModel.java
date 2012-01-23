package org.bham.aucom.data.util;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesStatus;
import org.bham.aucom.data.timeseries.TimeSeriesStatusEvent;
import org.bham.aucom.data.timeseries.TimeSeriesStatusListener;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier.AnomalyClassifier;
import org.bham.aucom.util.TimeSeriesNotFoundException;

import java.util.*;


public class DataModel implements TimeSeriesStatusListener {
    private LinkedHashMap<UUID, TimeSeries<?>> timeSeries;
    private final LinkedHashMap<UUID, Model> models;
    private LinkedHashMap<UUID, AnomalyClassifier> classifiers;
    private final HashMap<UUID, Object> itemsById = new HashMap<UUID, Object>();
    private static DataModel instance = null;
    private final javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

    /*
      * creates an empty data Model, to fill it use DataLoader
      */

    public static DataModel getInstance() {
        if (instance == null)
            instance = new DataModel();
        return instance;
    }

    private DataModel() {
        this.setTimeSeries(new LinkedHashMap<UUID, TimeSeries<?>>());
        this.models = new LinkedHashMap<UUID, Model>();
        this.classifiers = new LinkedHashMap<UUID, AnomalyClassifier>();
    }

    public <T extends AbstractData> UUID addTimeSeries(TimeSeries<T> timeSeriesToAdd) {
        if (timeSeriesToAdd == null)
            return null;
        this.getTimeSeries().put(timeSeriesToAdd.getId(), timeSeriesToAdd);
        timeSeriesToAdd.addTimeSeriesStatusListener(this);
        synchronizeWithDataItemHashMap(timeSeriesToAdd);
        List<UUID> addedElement = new ArrayList<UUID>();
        addedElement.add(timeSeriesToAdd.getId());
        fireDataModelStatusChangedEvent(new DataModelStatusEvent(this, DataModelStatus.ADDED, addedElement, timeSeriesToAdd.getClass()));
        return timeSeriesToAdd.getId();
    }

    /*
      * @param <T>
      * @param timeSeriesToAdd
      */
    private <T extends AbstractData> void synchronizeWithDataItemHashMap(TimeSeries<T> timeSeriesToAdd) {
        if (timeSeriesToAdd.size() > 0)
            timeSeriesStatusChanged(new TimeSeriesStatusEvent(timeSeriesToAdd, TimeSeriesStatus.ELEMENTS_ADDED, 0, timeSeriesToAdd.size() - 1));
    }

    public UUID addModel(Model modelToAdd) {
        this.models.put(modelToAdd.getId(), modelToAdd);
        return modelToAdd.getId();
    }

    public UUID addAnomalyClassificator(AnomalyClassifier classificatiorToAdd) {
        this.classifiers.put(classificatiorToAdd.getId(), classificatiorToAdd);
        return classificatiorToAdd.getId();
    }

    public void clear() {
        this.getTimeSeries().clear();
        this.getModels().clear();
    }

    public LinkedHashMap<UUID, Model> getModels() {
        return this.models;
    }

    public LinkedHashMap<UUID, AnomalyClassifier> getClassifiers() {
        return this.classifiers;
    }

    protected void setClassifiers(LinkedHashMap<UUID, AnomalyClassifier> classifiers) {
        this.classifiers = classifiers;
    }

    /*
      * event handling
      */
    public void addModelDataStatusListener(DataModelStatusListener listener) {
        this.listenerList.add(DataModelStatusListener.class, listener);
    }

    public void removeModelDataStatusListener(DataModelStatusListener listener) {
        this.listenerList.remove(DataModelStatusListener.class, listener);
    }

    // This method is used to fire StatusChangedEvents
    void fireDataModelStatusChangedEvent(DataModelStatusEvent evt) {
        Object[] listeners = this.listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == DataModelStatusListener.class) {
                DataModelStatusListener l = ((DataModelStatusListener) listeners[i + 1]);
                if (l.accepts(evt))
                    l.dataModelStatusChanged(evt);
            }
        }
    }

    private void setTimeSeries(LinkedHashMap<UUID, TimeSeries<?>> timeSeries) {
        this.timeSeries = timeSeries;
    }

    LinkedHashMap<UUID, TimeSeries<?>> getTimeSeries() {
        return this.timeSeries;
    }

    private HashMap<UUID, Object> getItemsByIdHashMap() {
        return this.itemsById;
    }

    public boolean containsDataItemWith(UUID id) {
        return getItemsByIdHashMap().containsKey(id);
    }

    @SuppressWarnings("unchecked")
    public <T extends AbstractData> T getDataItemById(UUID id) {
        return (T) getItemsByIdHashMap().get(id);
    }

    public TimeSeries<?> getTimeSeriesById(UUID id) throws TimeSeriesNotFoundException {
        if (!containsTimeSeriesWithId(id))
            throw new TimeSeriesNotFoundException("ts with id " + id + " not found");
        return this.getTimeSeries().get(id);
    }

    /*
      * @param id
      * @return
      */
    public boolean containsTimeSeriesWithId(UUID id) {
        return getTimeSeries().containsKey(id);
    }

    public int getNumerTimeseries() {
        return this.timeSeries.size();
    }

    @Override
    public void timeSeriesStatusChanged(TimeSeriesStatusEvent status) {
//		TimeSeries<? extends AbstractData> s = (TimeSeries<? extends AbstractData>) (status.getSource());
//		Logger.getLogger(this.getClass().getCanonicalName()).info("timeSeries changed : " + s.getType() + " [" + status.getStartIndex() +  ";" + status.getEndIndex()+ "]");
    }
//		int from = status.getStartIndex();
//		int to = status.getStartIndex();
//		for (int i = from; i <= to; i++) {
//			switch (status.getStatus()) {
//			case ELEMENTS_ADDED: {
//				break;
//			}
//			case ELEMENTS_REMOVED: {
//				break;
//			}
//			}
//			Logger.getLogger(this.getClass().getCanonicalName()).info("timeSeries" + s.getType() + " changed");
//		}
//	}

    public boolean isReady() {
        return false;
    }
}
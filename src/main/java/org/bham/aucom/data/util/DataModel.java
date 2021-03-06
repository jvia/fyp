package org.bham.aucom.data.util;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;
import org.bham.aucom.data.timeseries.TimeSeriesStatusListener;
import org.bham.aucom.data.timeseries.TimeSeriesStatus;
import org.bham.aucom.data.timeseries.TimeSeriesStatusEvent;
import org.bham.aucom.diagnoser.Model;
import org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator.AnomalyClassifier;
import org.bham.aucom.util.TimeSeriesNotFoundException;

import java.util.*;

public class DataModel implements TimeSeriesStatusListener {
    private LinkedHashMap<UUID, TimeSeries<? extends AbstractData>> timeseries;
    private LinkedHashMap<UUID, Model> models;
    private LinkedHashMap<UUID, AnomalyClassifier> classificators;
    private HashMap<UUID, Object> itemsById = new HashMap<UUID, Object>();
    private static DataModel instance = null;
    protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

    /*
      * creates an empty data Model, to fill it use DataLoader
      */

    public static DataModel getInstance() {
        if (instance == null)
            instance = new DataModel();
        return instance;
    }

    private DataModel() {
        this.setTimeseries(new LinkedHashMap<UUID, TimeSeries<?>>());
        this.models = new LinkedHashMap<UUID, Model>();
        this.classificators = new LinkedHashMap<UUID, AnomalyClassifier>();
    }

    public <T extends AbstractData> UUID addTimeSeries(TimeSeries<T> timeSeriesToAdd) {
        if (timeSeriesToAdd == null)
            return null;
        this.getTimeseries().put(timeSeriesToAdd.getId(), timeSeriesToAdd);
        timeSeriesToAdd.addTimeSeriesStatusListener(this);
        synchronizeWithDataItemHashMap(timeSeriesToAdd);
        List<UUID> addedElement = new ArrayList<UUID>();
        addedElement.add(timeSeriesToAdd.getId());
        fireDataModelStatusChangedEvent(new DataModelStatusEvent(this, DataModelStatus.ADDED, addedElement, timeSeriesToAdd.getClass()));
        return timeSeriesToAdd.getId();
    }

    /**
     * @param <T>
     * @param timeSeriesToAdd
     */
    private <T extends AbstractData> void synchronizeWithDataItemHashMap(TimeSeries<T> timeSeriesToAdd) {
        if (timeSeriesToAdd.size() > 0)
            timeseriesStatusChanged(new TimeSeriesStatusEvent(timeSeriesToAdd, TimeSeriesStatus.ELEMENTS_ADDED, 0, timeSeriesToAdd.size() - 1));
    }

    public UUID addModel(Model modelToAdd) {
        this.models.put(modelToAdd.getId(), modelToAdd);
        return modelToAdd.getId();
    }

    public UUID addAnomalyClassificator(AnomalyClassifier classificatiorToAdd) {
        this.classificators.put(classificatiorToAdd.getId(), classificatiorToAdd);
        return classificatiorToAdd.getId();
    }

    public void clear() {
        this.getTimeseries().clear();
        this.getModels().clear();
    }

    public LinkedHashMap<UUID, Model> getModels() {
        return this.models;
    }

    public LinkedHashMap<UUID, AnomalyClassifier> getClassificators() {
        return this.classificators;
    }

    protected void setClassificators(LinkedHashMap<UUID, AnomalyClassifier> classificators) {
        this.classificators = classificators;
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

    private void setTimeseries(LinkedHashMap<UUID, TimeSeries<?>> timeseries) {
        this.timeseries = timeseries;
    }

    LinkedHashMap<UUID, TimeSeries<?>> getTimeseries() {
        return this.timeseries;
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

    @SuppressWarnings("unchecked")
    public TimeSeries<?> getTimeSeriesById(UUID id) throws TimeSeriesNotFoundException {
        if (!containsTimeSeriesWithId(id))
            throw new TimeSeriesNotFoundException("ts with id " + id + " not found");
        return this.getTimeseries().get(id);
    }

    /**
     * @param id
     * @return
     */
    public boolean containsTimeSeriesWithId(UUID id) {
        return getTimeseries().containsKey(id);
    }

    public int getNumerTimeseries() {
        return this.timeseries.size();
    }

    @SuppressWarnings("unchecked")
    @Override
    public void timeseriesStatusChanged(TimeSeriesStatusEvent status) {
    }

    public boolean isReady() {
        return false;
    }
}
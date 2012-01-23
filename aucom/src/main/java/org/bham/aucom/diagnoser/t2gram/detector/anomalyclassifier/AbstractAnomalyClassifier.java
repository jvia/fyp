package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassifier;

import org.bham.aucom.data.timeseries.AnomalyClassifierListener;
import org.bham.aucom.data.timeseries.AnomalyClassifierStatusEvent;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

public abstract class AbstractAnomalyClassifier implements AnomalyClassifier {
    private static final long serialVersionUID = 1L;
    final LinkedHashMap<String, Number> parameters = new LinkedHashMap<String, Number>();
    final LinkedHashMap<String, String> attributes = new LinkedHashMap<String, String>();
    javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();
    UUID id;

    AbstractAnomalyClassifier(String name) {
        this.attributes.put("name", name);
        this.id = UUID.randomUUID();
    }

    @Override
    public UUID getId() {
        return this.id;
    }

    @Override
    public HashMap<String, String> getAttributes() {
        return this.attributes;
    }

    public String getName() {
        return this.attributes.get("name");
    }

    @Override
    public void addAttribute(String propertyName, String propertyValue) {
        this.attributes.put(propertyName, propertyValue);
    }

    @Override
    public void deleteAttribute(String propertyName) {
        this.attributes.remove(propertyName);
    }

    @Override
    public String getAttributeValue(String propertyName) {
        return this.attributes.get(propertyName);
    }

    @Override
    public boolean containsAttribute(String propertyName) {
        return this.attributes.containsKey(propertyName);
    }


    public void addSequenceStatusListener(AnomalyClassifierListener listener) {
        this.listenerList.add(AnomalyClassifierListener.class, listener);
    }

    public void removeStatusListener(AnomalyClassifierListener listener) {
        this.listenerList.remove(AnomalyClassifierListener.class, listener);
    }

    // This method is used to fire TrainingStatusChangedEvents
    void fireStatusChangedEvent(AnomalyClassifierStatusEvent evt) {
        Object[] listeners = this.listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == AnomalyClassifierListener.class) {
                ((AnomalyClassifierListener) listeners[i + 1]).anomalyClassifierStatusChanged(evt);
            }
        }
    }
}
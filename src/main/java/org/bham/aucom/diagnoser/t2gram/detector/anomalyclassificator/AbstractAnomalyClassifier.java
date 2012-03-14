package org.bham.aucom.diagnoser.t2gram.detector.anomalyclassificator;

import org.bham.aucom.data.timeseries.AnomalyClassificatorListener;
import org.bham.aucom.data.timeseries.AnomalyClassificatorStatusEvent;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.UUID;

public abstract class AbstractAnomalyClassifier implements AnomalyClassifier {
    LinkedHashMap<String, Number> parameters = new LinkedHashMap<String, Number>();
    LinkedHashMap<String, String> attributes = new LinkedHashMap<String, String>();
    private static final long serialVersionUID = 1L;
    UUID id;

    public AbstractAnomalyClassifier(String name) {
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
        // TODO Auto-generated method stub
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

    /*
      * event handling ---->
      */
    protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

    public void addSequenceStatusListener(AnomalyClassificatorListener listener) {
        this.listenerList.add(AnomalyClassificatorListener.class, listener);
    }

    public void removeStatusListener(AnomalyClassificatorListener listener) {
        this.listenerList.remove(AnomalyClassificatorListener.class, listener);
    }

    // This method is used to fire TrainingStatusChangedEvents
    void fireStatusChangedEvent(AnomalyClassificatorStatusEvent evt) {
        Object[] listeners = this.listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == AnomalyClassificatorListener.class) {
                ((AnomalyClassificatorListener) listeners[i + 1]).anomalyClassificatorStatusChanged(evt);
            }
        }
    }
    /*
      *  <---- event handling
      */
}
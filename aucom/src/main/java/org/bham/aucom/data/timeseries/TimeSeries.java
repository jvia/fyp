package org.bham.aucom.data.timeseries;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.LinkEnum;
import org.bham.aucom.fts.AbstractLinkableNode;
import org.bham.aucom.util.RingBuffer;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.UUID;


public abstract class TimeSeries<T extends AbstractData> extends AbstractLinkableNode {
    private static final long serialVersionUID = 1L;
    /*
     * Constants indicating attributes of elements in the timeseries
     */
    public static final String item = "item";
    public static final String last = "LAST_TIMESERIES_ELEMENT";
    public static final String first = "FIRST_TIMESERIES_ELEMENT";
    private TimeSeriesType type;
    protected RingBuffer<T> list;

    public TimeSeries() {
        this(UUID.randomUUID());
        list = new RingBuffer<T>();
    }

    public TimeSeries(int num) {
        this(UUID.randomUUID());
        list = new RingBuffer<T>(num);
    }

    /*
     * Concrete Timeseries, use this construtor to load a timeseries from file
     */
    public TimeSeries(UUID id) {
        super(id);
        list = new RingBuffer<T>();
    }

    public TimeSeries(UUID id, int num) {
        super(id);
        list = new RingBuffer<T>(num);
    }

    public TimeSeries(UUID generatorId, UUID dataSourceId) {
        this(generatorId);
        setGenerator(generatorId);
        setGeneratedFrom(dataSourceId);
    }

    public TimeSeries(UUID generatorId, UUID dataSourceId, int num) {
        this(generatorId, num);
        setGenerator(generatorId);
        setGeneratedFrom(dataSourceId);
    }

    public TimeSeries(UUID generatorId, UUID dataSourceId, UUID id, List<T> content) {
        this(generatorId, dataSourceId);
        addAll(content);
        setId(id);
    }

    public TimeSeries(UUID generatorId, UUID dataSourceId, UUID id, List<T> content, int num) {
        this(generatorId, dataSourceId, num);
        addAll(content);
        setId(id);
    }

    /*
     * @param dataTypeTimeSeriesId
     */
    public void setGeneratedFrom(UUID dataTypeTimeSeriesId) {
        addLink(LinkEnum.generatedFromId, dataTypeTimeSeriesId);
    }

    /*
     * @param generatoId
     */
    public void setGenerator(UUID generatoId) {
        addLink(LinkEnum.generatorId, generatoId);
    }

    public UUID getGeneratorID() {
        return getLinks(LinkEnum.generatorId).get(0);
    }

    public UUID getGeneratedFromID() {
        return getLinks(LinkEnum.generatedFromId).get(0);
    }

    public boolean hasGenerator() {
        return containsLink(LinkEnum.generatorId);
    }

    public boolean hasgeneratedFrom() {
        return containsLink(LinkEnum.generatedFromId);
    }

    public synchronized T get(int index) {
        return this.list.get(index);
    }

    @SuppressWarnings("boxing")
    public synchronized void add(T s) {
        this.list.add(s);
        // this.timestampToElement.put(s.getTimestamp(), s);
        fireTimeseriesStatusChangedEvent(new TimeseriesStatusEvent(this, TimeseriesStatus.ELEMENTSADDED, this.list.size() - 1, this.list.size() - 1));
        // trim();
    }

    @SuppressWarnings({"boxing"})
    public synchronized void addAll(Collection<T> c) {
        try {

            if (c.size() == 0) {
                return;
            }

            int startIndex = Math.min(this.list.size(), list.getCapacity() - 1);
            this.list.addAll(c.toArray());
            fireTimeseriesStatusChangedEvent(new TimeseriesStatusEvent(this, TimeseriesStatus.ELEMENTSADDED, startIndex, this.list.size() - 1));
        } catch (Exception exception) {
            exception.printStackTrace();
        }
    }

    public synchronized void clear() {
        int lastIndex = this.list.size() - 1;
        this.list.clear();
        // this.timestampToElement.clear();
        fireTimeseriesStatusChangedEvent(new TimeseriesStatusEvent(this, TimeseriesStatus.ELEMENTSREMOVED, 0, lastIndex));
    }

    public synchronized int size() {
        return this.list.size();
    }

    @SuppressWarnings("unchecked")
    public synchronized Collection<T> getall() {
        Object[] elements_tmp = this.list.toArray();
        Collection<T> out = new ArrayList<T>();
        for (Object anElements_tmp : elements_tmp) {
            out.add((T) anElements_tmp);
        }
        return out;
    }

    /*
     * event handling ---->
     */
    protected javax.swing.event.EventListenerList listenerList = new javax.swing.event.EventListenerList();

    public void addTimeSeriesStatusListener(TimeSeriesStatusListener listener) {
        this.listenerList.add(TimeSeriesStatusListener.class, listener);
    }

    public void removeTimeseriesStatusListener(TimeSeriesStatusListener listener) {
        this.listenerList.remove(TimeSeriesStatusListener.class, listener);
    }

    // This method is used to fire TrainingStatusChangedEvents
    void fireTimeseriesStatusChangedEvent(TimeseriesStatusEvent evt) {
        Object[] listeners = this.listenerList.getListenerList();
        // Each listener occupies two elements - the first is the listener class
        // and the second is the listener instance
        for (int i = 0; i < listeners.length; i += 2) {
            if (listeners[i] == TimeSeriesStatusListener.class) {
                ((TimeSeriesStatusListener) listeners[i + 1]).timeseriesStatusChanged(evt);
            }
        }
    }

    /*
     * <---- event handling
     */

    public synchronized boolean isEmpty() {
        return this.list.isEmpty();
    }

    @Override
    public boolean equals(Object arg0) {
        if (arg0.getClass().equals(this.getClass())) {
            TimeSeries<?> s = (TimeSeries<?>) arg0;
            return s.getId().equals(this.getId());
        }
        return false;
    }

    @Override
    public String toString() {
        return this.getId().toString() + " #" + size() + " |" + type + "";
    }

    public boolean isOfType(Class<? extends AbstractData> classToCheck) {
        return this.list.size() != 0 && this.list.get(0).getClass().equals(classToCheck);
    }

    protected void setType(TimeSeriesType type) {
        this.type = type;
    }

    public TimeSeriesType getType() {
        return this.type;
    }

    public List<T> getHead(long inTimestamp) {
        Collection<T> all = getall();
        List<T> head = new ArrayList<T>();

        for (T element : all) {
            if (element.getTimestamp() < inTimestamp) {
                head.add(element);
            }
        }
        return head;
    }

    public List<T> getTail(long inTimestamp) {
        Collection<T> all = getall();
        List<T> tail = new ArrayList<T>();

        for (T element : all) {
            if (element.getTimestamp() >= inTimestamp) {
                tail.add(element);
            }
        }
        return tail;
    }

    public synchronized void remove(int i) {
        // TODO code this
    }
}

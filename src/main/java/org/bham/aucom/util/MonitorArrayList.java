package org.bham.aucom.util;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

public class MonitorArrayList<E> extends ArrayList<E> implements Serializable {
    private static final long serialVersionUID = 1L;
    transient ArrayList<ListDataListener> listeners = new ArrayList<ListDataListener>();
    private ArrayList<E> backedList;

    public MonitorArrayList(final ArrayList<E> inList) {
        backedList = inList;
    }

    @Override
    public E get(final int index) {
        return backedList.get(index);
    }

    public void removeListDataListener(final ListDataListener l) {
        listeners.remove(l);
    }

    public void addListDataListener(final ListDataListener l) {
        listeners.add(l);
    }

    @Override
    public int size() {
        return backedList.size();
    }

    void notifyListeners(final int from, final int to) {
        ListDataEvent le = new ListDataEvent(backedList,
                                             ListDataEvent.CONTENTS_CHANGED, from, to);
        for (ListDataListener listener : listeners) {
            listener.contentsChanged(le);
        }
    }

    void notifyListenersRemove(final int from, final int to) {
        ListDataEvent le = new ListDataEvent(backedList,
                                             ListDataEvent.INTERVAL_REMOVED, from, to);
        for (ListDataListener listener : listeners) {
            listener.intervalRemoved(le);
        }
    }

    void notifyListenersAdd(final int from, final int to) {
        ListDataEvent le = new ListDataEvent(backedList,
                                             ListDataEvent.INTERVAL_ADDED, from, to);
        for (ListDataListener listener : listeners) {
            listener.intervalAdded(le);
        }
    }

    @Override
    public boolean add(final E o) {
        boolean b = backedList.add(o);
        int index = backedList.size() - 1;
        if (b) {
            notifyListenersAdd(index, index);
        }
        return b;
    }

    @Override
    public void add(int index, E element) {
        backedList.add(index, element);
        notifyListenersAdd(index, index);
    }

    @Override
    public boolean addAll(Collection<? extends E> o) {
        int from = backedList.size();
        boolean b = backedList.addAll(o);
        int to = backedList.size() - 1;
        if (b) {
            notifyListenersAdd(from, to);
        }
        return b;
    }

    @Override
    public void clear() {
        int to = backedList.size() - 1;
        backedList.clear();
        notifyListenersRemove(0, to);
    }

    @Override
    public E remove(int i) {
        E o = backedList.remove(i);
        // notifyListeners();
        notifyListenersRemove(i, i);
        return o;
    }

    @Override
    public boolean remove(Object o) {
        int index = backedList.indexOf(o);
        boolean b = backedList.remove(o);
        if (b) {
            notifyListenersRemove(index, index);
        }
        return b;
    }

    @Override
    public E set(int index, E element) {
        E o = backedList.set(index, element);
        notifyListeners(index, index);
        return o;
    }
}

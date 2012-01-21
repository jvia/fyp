package org.bham.aucom.util;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;

import javax.swing.event.ListDataEvent;
import javax.swing.event.ListDataListener;

public class MonitorableArrayList<E> extends ArrayList<E> implements Serializable{
	private static final long serialVersionUID = 1L;
	transient ArrayList<ListDataListener> listeners = new ArrayList();
	private ArrayList<E> backedList;

	public MonitorableArrayList(ArrayList<E> inList) {
		backedList = inList;
	}

	@Override
	public E get(int index) {
		return backedList.get(index);
	}

	public void removeListDataListener(ListDataListener l) {
		listeners.remove(l);
	}

	public void addListDataListener(ListDataListener l) {
		listeners.add(l);
	}

	@Override
	public int size() {
		return backedList.size();
	}

	void notifyListeners(int from, int to) {
		ListDataEvent le = new ListDataEvent(backedList,
				ListDataEvent.CONTENTS_CHANGED, from, to);
		for (int i = 0; i < listeners.size(); i++) {
			(listeners.get(i)).contentsChanged(le);
		}
	}

	void notifyListenersRemove(int from, int to) {
		ListDataEvent le = new ListDataEvent(backedList,
				ListDataEvent.INTERVAL_REMOVED, from, to);
		for (int i = 0; i < listeners.size(); i++) {
			(listeners.get(i)).intervalRemoved(le);
		}
	}

	void notifyListenersAdd(int from, int to) {
		ListDataEvent le = new ListDataEvent(backedList,
				ListDataEvent.INTERVAL_ADDED, from, to);
		for (int i = 0; i < listeners.size(); i++) {
			(listeners.get(i)).intervalAdded(le);
		}
	}

	// REMAINDER ARE OVERRIDES JUST TO CALL NOTIFYLISTENERS

	@Override
	public boolean add(E o) {
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

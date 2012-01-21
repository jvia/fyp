package org.bham.aucom.util;

import java.io.Serializable;
import java.util.ArrayList;

public class RangedArrayList<E> extends ArrayList<E> implements Serializable {
	private static final long serialVersionUID = 1L;
	private int index = 0;
	private int range = 0;
	private final ArrayList<E> list;
	public RangedArrayList(ArrayList<E> list) {
		this.list = list;
	}

	public void setIndex(int index) {
		this.index = index;
	}

	public int getIndex() {
		return index;
	}

	public void setRange(int range) {
		this.range = range;
	}

	public int getRange() {
		return range;
	}

	public ArrayList<E> getList() {
		return list;
	}

}

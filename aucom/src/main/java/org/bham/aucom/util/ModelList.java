package org.bham.aucom.util;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractListModel;

import org.bham.aucom.diagnoser.Model;


public class ModelList extends AbstractListModel implements Observer{
	/**
	 * 
	 */
	private static final long serialVersionUID = 3671172755117348322L;
	private final List<Model> models;

	public ModelList(List<Model> modelList) {
		this.models = modelList;
	}

	public void put(Model model) {
		this.models.add(model);
		//model.addObserver(this); TODO why did I this?
		this.fireIntervalAdded(this, indexOf(model), indexOf(model));
	}
	public void delete(Model model){
		if(this.models.contains(model))
			this.models.remove(model);
		this.fireIntervalRemoved(this, indexOf(model), indexOf(model));
	}

	int indexOf(Model model) {
		return this.models.indexOf(model);
	}
	@Override
	public Object getElementAt(int index) {
		return this.models.get(index);
	}

	@Override
	public int getSize() {
		return this.models.size();
	}

	@Override
	public void update(Observable arg0, Object arg1) {
			this.fireIntervalAdded(this, 0, this.models.size()-1);
	}

}

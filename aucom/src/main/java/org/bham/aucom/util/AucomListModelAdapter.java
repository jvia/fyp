package org.bham.aucom.util;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.swing.AbstractListModel;

import org.bham.aucom.data.util.DataModelStatus;
import org.bham.aucom.data.util.DataModelStatusEvent;
import org.bham.aucom.data.util.DataModelStatusListener;

public class AucomListModelAdapter extends AbstractListModel implements DataModelStatusListener{
	private static final long serialVersionUID = 3671172755117348322L;
	List<UUID> values = new ArrayList<UUID>();
	public AucomListModelAdapter(Class<?> filterDataClass) {
		this.filterDataClass = filterDataClass;
	}
	public AucomListModelAdapter() {
		this(null);
	}

	@Override
	public Object getElementAt(int index) {
		return this.values.get(index);
	}
	@Override
	public int getSize() {
		return this.values.size();
	}

	protected Class<?> filterDataClass;
	@Override
	public boolean accepts(DataModelStatusEvent status){
		if(filterDataClass == null){
			return true;
		}
		return status.getFilterDataClass() == this.filterDataClass;
	}


	@Override
	public void dataModelStatusChanged(DataModelStatusEvent status) {
		if(status.getStatus().equals(DataModelStatus.ADDED)){
			values.addAll(status.getElements());
			System.out.println("updating AucomListModel, adding 0 -> " + Math.max(0, this.values.size()-1));
			this.fireIntervalAdded(this, Math.max(0, this.values.size()-1), Math.max(0, this.values.size()-1));
		}
		if(status.getStatus().equals(DataModelStatus.REMOVED)){
			values.removeAll(status.getElements());
			System.out.println("updating AucomListModel, deleting");
			this.fireIntervalRemoved(this, Math.max(0, this.values.size()-1), Math.max(0, this.values.size()-1));
		}
	}
}
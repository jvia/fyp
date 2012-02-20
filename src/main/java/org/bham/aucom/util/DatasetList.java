package org.bham.aucom.util;

import org.bham.aucom.data.timeseries.DataTypeTimeSeries;

import javax.swing.*;
import java.util.LinkedHashMap;

public class DatasetList extends AbstractListModel {
    private LinkedHashMap<String, DataTypeTimeSeries> datasets;

    public DatasetList() {
        setDatasets(new LinkedHashMap<String, DataTypeTimeSeries>());
    }

    private static final long serialVersionUID = 8720591898683345358L;

    public void put(String name, DataTypeTimeSeries model) {
        getDatasets().put(name, model);
        this.fireIntervalAdded(this, indexOf(name), indexOf(name));
    }

    public void delete(String name) {
        if (getDatasets().containsKey(name))
            getDatasets().remove(name);
        this.fireIntervalRemoved(name, indexOf(name), indexOf(name));
    }

    public DataTypeTimeSeries get(String name) {
        return getDatasets().get(name);
    }

    public int indexOf(String name) {
        int i = 0;
        for (String n : getDatasets().keySet()) {
            if (n.equals(name))
                return i;
            i++;
        }
        return -1;
    }

    @Override
    public Object getElementAt(int arg0) {
        Object returnElement = null;
        int i = 0;
        for (DataTypeTimeSeries m : getDatasets().values()) {
            if (i == arg0) {
                returnElement = m;
                break;
            }
            i++;
        }
        return returnElement;
    }

    @Override
    public int getSize() {
        return getDatasets().size();
    }

    public boolean containsKey(String name) {
        return getDatasets().containsKey(name);
    }

    public void setDatasets(LinkedHashMap<String, DataTypeTimeSeries> datasets) {
        this.datasets = datasets;
    }

    public LinkedHashMap<String, DataTypeTimeSeries> getDatasets() {
        return datasets;
    }
}

package org.bham.aucom.data.util;

import org.bham.aucom.data.AbstractData;
import org.bham.aucom.data.timeseries.TimeSeries;

import javax.swing.*;

public class SequenceListModel<T extends AbstractData> extends AbstractListModel {
    TimeSeries<T> s;
    private static final long serialVersionUID = 1L;

    public SequenceListModel(TimeSeries<T> s) {
        this.s = s;
    }

    @Override
    public int getSize() {
        return this.s.size();
    }

    @Override
    public Object getElementAt(int index) {
        return this.s.get(index);
    }

}

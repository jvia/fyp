package org.bham.aucom.data.util;

import java.util.EventListener;


public interface DataModelStatusListener extends EventListener {
    public boolean accepts(DataModelStatusEvent status);

    public abstract void dataModelStatusChanged(DataModelStatusEvent status);
}

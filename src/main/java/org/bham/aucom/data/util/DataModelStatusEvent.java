package org.bham.aucom.data.util;

import java.util.EventObject;
import java.util.List;
import java.util.UUID;

public class DataModelStatusEvent extends EventObject {
    private static final long serialVersionUID = 0L;
    private final DataModelStatus status;
    private final Class<?> filterDataClass;
    private final List<UUID> elements;

    public DataModelStatusEvent(Object source, DataModelStatus status, List<UUID> elements, Class<?> filterDataClass) {
        super(source);
        this.elements = elements;
        this.status = status;
        this.filterDataClass = filterDataClass;
    }

    public DataModelStatus getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return this.status + " " + this.elements.size() + " elements";
    }

    public List<UUID> getElements() {
        return this.elements;
    }

    public Class<?> getFilterDataClass() {
        return this.filterDataClass;
    }

}

package org.bham.aucom.main;

import java.util.EventObject;

public class SystemModelStatusEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    private SystemModelStatus status;

    public SystemModelStatusEvent(Object source, SystemModelStatus status) {
        super(source);
        this.setStatus(status);
    }

    private void setStatus(SystemModelStatus status) {
        this.status = status;
    }

    public SystemModelStatus getStatus() {
        return this.status;
    }

    @Override
    public String toString() {
        return super.toString() + this.status;
    }
}

package org.bham.aucom.system;

import java.util.EventObject;

public class SystemConnectionStatusChangedEvent extends EventObject {

    private static final long serialVersionUID = 1L;
    private SystemConnectionStatus newStatus;
    private SystemConnectionStatus oldStatus;

    public SystemConnectionStatusChangedEvent(Object source, SystemConnectionStatus inOldStatus, SystemConnectionStatus inNewStatus) {
        super(source);
        setNewStatus(inNewStatus);
        setOldStatus(inOldStatus);
    }

    private void setNewStatus(SystemConnectionStatus newStatus) {
        this.newStatus = newStatus;
    }

    public SystemConnectionStatus getNewStatus() {
        return newStatus;
    }

    private void setOldStatus(SystemConnectionStatus oldStatus) {
        this.oldStatus = oldStatus;
    }

    public SystemConnectionStatus getOldStatus() {
        return oldStatus;
    }

    @Override
    public String toString() {
        return "[" + oldStatus + "-->" + newStatus + "]";
    }
}

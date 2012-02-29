package org.bham.aucom.main;

import java.util.EventListener;

public interface SystemModelStatusListener extends EventListener {
    public void systemModelStatusChanged(SystemModelStatusEvent evt);
}

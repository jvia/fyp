package org.bham.aucom.fts.source;

import java.util.EventListener;

public interface SourceEventListener extends EventListener {
    public void sourceEventFired(SourceEvent event);
}

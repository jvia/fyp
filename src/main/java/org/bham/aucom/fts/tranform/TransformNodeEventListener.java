package org.bham.aucom.fts.tranform;

import java.util.EventListener;

public interface TransformNodeEventListener extends EventListener {
    public void handleTransformNodeEvent(TransformNodeEvent evt);
}

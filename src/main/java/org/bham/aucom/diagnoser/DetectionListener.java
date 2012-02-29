package org.bham.aucom.diagnoser;

import java.util.EventListener;

public interface DetectionListener extends EventListener {
    public void handleDetectorEvent(DetectorEvent event);
}

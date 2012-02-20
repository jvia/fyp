package org.bham.aucom.diagnoser;

import java.util.EventListener;

public interface DetectorStatusChangedListener extends EventListener {
	public void handleDetectorStatusChangedEvent(DetectorStatusChangedEvent evt);
}

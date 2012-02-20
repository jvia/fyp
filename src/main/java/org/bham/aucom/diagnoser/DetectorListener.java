package org.bham.aucom.diagnoser;

import java.util.EventListener;

public interface DetectorListener extends EventListener {
	public void handleDetectorEvent(DetectorEvent event);
}

package org.bham.aucom.diagnoser;

import java.util.EventListener;

interface DetectionListener extends EventListener {
	public void handleDetectorEvent(DetectorEvent event);
}

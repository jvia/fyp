package org.bham.aucom.xcfrecorder;

import java.util.EventListener;

public interface RecorderStatusListener extends EventListener {
	public void handleRecorderStatusEvent(RecorderStatusChangedEvent evt);
}

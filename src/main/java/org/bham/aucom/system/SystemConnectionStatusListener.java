package org.bham.aucom.system;

import java.util.EventListener;


public interface SystemConnectionStatusListener extends EventListener {
	public void handleSystemConnectionEvent(SystemConnectionStatusChangedEvent event);
}

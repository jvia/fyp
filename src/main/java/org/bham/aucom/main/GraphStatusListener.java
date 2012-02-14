package org.bham.aucom.main;

import java.util.EventListener;

public interface GraphStatusListener extends EventListener { 
	public void graphStatusChanged(GraphStateChangedEvent evt); 
} 

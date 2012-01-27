package org.bham.aucom.fts.source;

import java.util.EventListener;

public interface SourceStatusListener extends EventListener {
	public void  sourceStatusChanged(SourceStatusEvent event);
}

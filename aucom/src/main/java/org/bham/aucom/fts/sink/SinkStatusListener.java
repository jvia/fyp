package org.bham.aucom.fts.sink;

import java.util.EventListener;

public interface SinkStatusListener extends EventListener  {
	public void  sinkStatusChanged(AucomSinkStatusEvent event);

}

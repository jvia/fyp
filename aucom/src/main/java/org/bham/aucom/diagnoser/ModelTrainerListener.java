package org.bham.aucom.diagnoser;

import java.util.EventListener;

public interface ModelTrainerListener extends EventListener {
	public void modelTrainerStatusChanged(StatusChangedEvent evt);
}

package org.bham.aucom.diagnoser;

import java.io.Serializable;
import java.util.UUID;


public interface Model extends Serializable {
	public boolean isTrained();
	public String getName();
	public UUID getId();
	public void addModelListener();
	public void removeModelListener();
}
package org.bham.experimenter.data;

import java.util.LinkedList;

import org.bham.aucom.data.AttributableObject;

public abstract class Result {
	private LinkedList<AttributableObject> configurationElements = new LinkedList<AttributableObject>(); 
	public abstract String getAsCsvString();
	void setConfigurationElements(LinkedList<AttributableObject> configurationElements) {
		this.configurationElements = configurationElements;
	}
	LinkedList<AttributableObject> getConfigurationElements() {
		return this.configurationElements;
	}
}

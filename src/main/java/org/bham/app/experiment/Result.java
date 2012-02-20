package org.bham.app.experiment;

import org.bham.aucom.data.AttributableObject;

import java.util.LinkedList;
import java.util.concurrent.Callable;

public abstract class Result implements Callable{
    private LinkedList<AttributableObject> configurationElements = new LinkedList<AttributableObject>();

    public abstract String getAsCsvString();

    void setConfigurationElements(LinkedList<AttributableObject> configurationElements) {
        this.configurationElements = configurationElements;
    }

    LinkedList<AttributableObject> getConfigurationElements() {
        return this.configurationElements;
    }
}

package org.bham.aucom.util;

import java.util.Observable;

public class AucomObservable extends Observable {
    @Override
    public void notifyObservers() {
        setChanged();
        super.notifyObservers();
    }

    @Override
    public void notifyObservers(Object o) {
        setChanged();
        super.notifyObservers(o);
    }
}

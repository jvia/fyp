package org.bham.aucom.system;

public class SystemConnectionNotFoundException extends Exception {
    private static final long serialVersionUID = 0L;

    public SystemConnectionNotFoundException(String inFactoryName) {
        super("coulnd't find factory " + inFactoryName);
    }
}

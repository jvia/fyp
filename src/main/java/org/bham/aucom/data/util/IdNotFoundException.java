package org.bham.aucom.data.util;

import java.util.UUID;

public class IdNotFoundException extends Exception {
    private static final long serialVersionUID = 0L;
    private final UUID id;

    public IdNotFoundException(UUID id) {
        this.id = id;
    }

    public UUID getId() {
        return this.id;
    }
}
